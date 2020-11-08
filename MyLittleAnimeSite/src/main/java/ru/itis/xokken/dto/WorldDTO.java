package ru.itis.xokken.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WorldDTO extends dto{
    private String name;
    private String img;
}
