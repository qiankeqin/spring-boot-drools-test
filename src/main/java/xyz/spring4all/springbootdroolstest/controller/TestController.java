package xyz.spring4all.springbootdroolstest.controller;

import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.*;
import xyz.spring4all.springbootdroolstest.domain.Person;
import xyz.spring4all.springbootdroolstest.domain.Student;
import xyz.spring4all.springbootdroolstest.utils.KieSessionUtils;

import java.util.List;

/**
 * @program: spring-boot-drools-test
 * @description:
 * @author: qiankeqin
 * @create: 2018-12-29 20:04
 **/
@RestController
public class TestController {
    /**
     * 测试规则引擎
     * @param num
     * @return
     * @throws Exception
     */
    @RequestMapping("/index/test")
    public Object show(@RequestParam Integer num) throws Exception {
        KieSession kieSession = KieSessionUtils.getAllRules();
        kieSession.insert(new Double(num));
        kieSession.fireAllRules();
        return "ok";
    }


    /**
     * 测试规则引擎
     * @param num
     * @return
     * @throws Exception
     */
    @RequestMapping("/index/testDouble")
    public Object show(@RequestParam Double num) throws Exception {
        KieSession kieSession = KieSessionUtils.getAllRules();
        kieSession.insert(num);
        kieSession.fireAllRules();
        return "ok";
    }

    /**
     * 测试类参数
     * @param person
     * @return
     * @throws Exception
     */
    @PostMapping("/index/testObj")
    public Object showObject(@RequestBody Person person) throws Exception {
        KieSession kieSession = KieSessionUtils.getAllRules();
        kieSession.insert(person);
        kieSession.fireAllRules();
        return person;
    }

    /**
     * 测试List参数
     * @param person
     * @return
     * @throws Exception
     */
    @PostMapping("/index/testObjList")
    public Object showObjectList(@RequestBody Person person) throws Exception{
        KieSession kieSession = KieSessionUtils.getAllRules();
        kieSession.insert(person);
        kieSession.fireAllRules();
        return person;
    }

    @PostMapping("/index/testObjMap")
    public Object showObjectMap(@RequestBody Person person) throws Exception{
        KieSession kieSession = KieSessionUtils.getAllRules();
        person.getOtherDetails().put("address","zhejiang");
        person.getOtherDetails().put("nickname","规则引擎");
        kieSession.insert(person);
        kieSession.fireAllRules();
        return person;
    }


    /**
     * 测试agenda
     * @param person
     * @return
     * @throws Exception
     */
    @PostMapping("/index/testAgenda")
    public Object showAgenda(@RequestBody Person person) throws Exception {
        KieSession kieSession = KieSessionUtils.getAllRules();
        kieSession.insert(person);
        //激活规则
        kieSession.getAgenda().getAgendaGroup("abc").setFocus();
        kieSession.fireAllRules();
        return person;
    }


    /**
     * no-loop
     * @param student
     * @return
     * @throws Exception
     */
    @PostMapping("/index/testNoLoop")
    public Object showNoLoop(@RequestBody Student student) throws Exception {
        KieSession kieSession = KieSessionUtils.getAllRules();
        kieSession.insert(student);
        kieSession.fireAllRules();
        return student;
    }




}
