package ru.itis.xokken.Services;

import ru.itis.xokken.dto.UserDTO;
import ru.itis.xokken.dto.WorldDTO;

import java.util.List;

public interface WorldService {
    boolean add(WorldDTO entity);
    boolean update(UserDTO entity);
    List<WorldDTO> showAllWorlds();
}
