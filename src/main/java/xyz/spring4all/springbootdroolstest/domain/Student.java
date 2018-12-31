package xyz.spring4all.springbootdroolstest.domain;

/**
 * @program: spring-boot-drools-test
 * @description:
 * @author: qiankeqin
 * @create: 2018-12-31 10:37
 **/
public class Student {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
