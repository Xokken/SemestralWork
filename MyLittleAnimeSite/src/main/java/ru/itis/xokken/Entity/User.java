package ru.itis.xokken.Entity;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private int age;
    private String img;
    private boolean law;

}
