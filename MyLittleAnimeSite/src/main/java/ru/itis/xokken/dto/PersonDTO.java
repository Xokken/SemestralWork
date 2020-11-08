package ru.itis.xokken.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PersonDTO extends dto{
    private String name;
    private String world;
    private String img;
    private boolean admin;
}
