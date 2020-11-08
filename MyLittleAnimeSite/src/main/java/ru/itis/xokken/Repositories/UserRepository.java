package ru.itis.xokken.Repositories;

import ru.itis.xokken.Entity.User;
import ru.itis.xokken.dto.UserDTO;

public interface UserRepository extends CrudRepository<User>{
    public User findUserByEmail(String email);
    public boolean addUser(User user);
    public void updateImg(User user, String path);
}
