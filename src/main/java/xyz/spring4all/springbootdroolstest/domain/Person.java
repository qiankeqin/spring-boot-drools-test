package xyz.spring4all.springbootdroolstest.domain;

import java.util.List;

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
                '}';
    }
}
