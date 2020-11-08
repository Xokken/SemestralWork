package ru.itis.xokken.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO extends dto{
    private String name;
    private String email;
    private String password;
    private int age;
    private String img;
    private boolean law;

}
