package ru.itis.xokken.Repositories.Utility;

import ru.itis.xokken.Entity.Person;
import ru.itis.xokken.Entity.User;
import ru.itis.xokken.Entity.World;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapper {
    public static User returnUser(ResultSet resultSet){
        User user = null;
        try {
            user = User.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .email(resultSet.getString(3))
                    .password(resultSet.getString(4))
                    .age(resultSet.getInt(5))
                    .img(resultSet.getString(6))
                    .law(resultSet.getBoolean(7))
                    .build();
        } catch (SQLException throwables) {
        }
        return user;
    }

    public static Person returnPerson(ResultSet resultSet){
        Person person = null;
        resultSet.toString();
        try{
            person = Person.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .world(resultSet.getString(3))
                    .img(resultSet.getString(4))
                    .build();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return person;
    }

    public static World returnWorld(ResultSet resultSet){
        World world = null;
        try {
            world = World.builder()
                    .name(resultSet.getString(2))
                    .img(resultSet.getString(3))
                    .build();
        } catch (SQLException throwables) {
        }
        return world;

    }
}
