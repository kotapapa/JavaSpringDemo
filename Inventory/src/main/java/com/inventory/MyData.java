package com.inventory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MyData {

    @Id
    @GeneratedValue
    protected Integer id;
    protected String name;
    protected String mail;
    protected String tel;
    protected int age;

    public MyData(){
        super();
    }

    public MyData(String name, String mail,
            String tel, int age){
        super();
        this.name = name;
        this.mail = mail;
        this.tel = tel;
        this.age = age;
    }

    public String toString(){
        return "[name:" + name + ", mail:" + mail+
            ", tel:" + tel + ", age:" + age + "]";
    }
}