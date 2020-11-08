package ru.itis.xokken.Entity;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class World {
    private String name;
    private String img;
}
