package ru.itis.xokken.Repositories;

import ru.itis.xokken.Entity.User;
import ru.itis.xokken.dto.UserDTO;

import java.sql.*;
import java.util.List;
import ru.itis.xokken.Repositories.Utility.RowMapper;

public class UserRepImp implements UserRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "15022001";
    Connection conn = null;
    //language=sql
    private static final String SELECT = "insert into users (name, email, password, age) values (?,?,?,?)";
    //language=sql
    private static final String FIND = "select * from users where email=?";
    //language=sql
    private static final String UPDATE_IMG="update users set img = ? where id=?";

    public static void main(String[] args) {
        UserRepImp userRepImp = new UserRepImp();
        User user = User.builder()
                .age(15)
                .email("xokkeuct12832@gmail.com")
                .password("123321")
                .name("Алексей Дьяконов")
                .id(107)
                .img("hostImg/1.jpg")
                .build();
        userRepImp.updateImg(user, "hostImg/1.jpg");
        System.out.println(userRepImp.findUserByEmail(user.getEmail()));
    }

    public User findUserByEmail(String email) {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prepStat = conn.prepareStatement(FIND);
            prepStat.setString(1, email);
            ResultSet resultSet = prepStat.executeQuery();
            User user1 = null;
            if (resultSet.next()) {
                user1 = RowMapper.returnUser(resultSet);
            }
            resultSet.close();
            prepStat.close();
            conn.close();
            return user1;
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
        }
        return null;
    }

    @Override
    public boolean addUser(User user) {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prepStat = conn.prepareStatement(SELECT);
            prepStat.setObject(1, user.getName());
            prepStat.setObject(2, user.getEmail());
            prepStat.setObject(3, user.getPassword());
            prepStat.setObject(4, user.getAge());
            prepStat.executeUpdate();
            prepStat.close();
            conn.close();
            return true;
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
            return false;
        }
    }

    @Override
    public void updateImg(User user, String path) {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prepStat = conn.prepareStatement(UPDATE_IMG);
            prepStat.setInt(2, user.getId());
            prepStat.setString(1, path);
            prepStat.executeUpdate();
            prepStat.close();
            conn.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
