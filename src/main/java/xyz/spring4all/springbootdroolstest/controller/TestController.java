package xyz.spring4all.springbootdroolstest.controller;

import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.spring4all.springbootdroolstest.utils.KieSessionUtils;

/**
 * @program: spring-boot-drools-test
 * @description:
 * @author: qiankeqin
 * @create: 2018-12-29 20:04
 **/
@RestController
public class TestController {

    @RequestMapping("/index/test")
    public Object show(@RequestParam Integer num) throws Exception {
        KieSession kieSession = KieSessionUtils.getAllRules();
        kieSession.insert(new Double(num));
        kieSession.fireAllRules();
        return "ok";
    }
}
