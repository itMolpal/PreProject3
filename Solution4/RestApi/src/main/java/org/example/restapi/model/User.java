package org.example.restapi.model;

import lombok.Data;


@Data
public class User {
    private Long id;
    private String name;
    private String lastName;
    private byte age;

//    public User(String name, String lastName, byte age) {
//        this.name = name;
//        this.lastName = lastName;
//        this.age = age;
//    }


}
