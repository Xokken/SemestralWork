package ru.itis.xokken.Services;

import ru.itis.xokken.dto.UserDTO;


public interface UserService {

    boolean add(UserDTO entity);
    boolean update(UserDTO entity, String password);
}
