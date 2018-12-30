package xyz.spring4all.springbootdroolstest.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: spring-boot-drools-test
 * @description:
 * @author: qiankeqin
 * @create: 2018-12-30 19:56
 **/
public class Person {
    private String name;
    private Integer age;
    private List<String> interests;
    private Map<String,String> otherDetails = new HashMap<>();

    public Map<String, String> getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(Map<String, String> otherDetails) {
        this.otherDetails = otherDetails;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", interests=" + interests +
                ", otherDetails=" + otherDetails +
                '}';
    }
}
