package ru.itis.xokken.Entity;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Person {
    private int id;
    private String name;
    private String world;
    private String img;
}
