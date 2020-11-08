package ru.itis.xokken.Repositories;

import org.springframework.security.core.parameters.P;
import ru.itis.xokken.Entity.Person;
import ru.itis.xokken.Entity.User;
import ru.itis.xokken.Entity.World;
import ru.itis.xokken.Repositories.Utility.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonsRepImp implements PersonsRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "15022001";
    Connection conn = null;
    private static final String FIND_PERSON = "select * from anime_persons where name=?";
    //language=sql
    private static final String ADD_PERSON_USER = "insert into persons_users (user_id, person_id) values (?,?)";
    private static final String DELETE_PERSON_USER = "delete from persons_users where user_id=? and person_id=?";
    private static final String FIND_BY_WORLD = "select * from anime_persons where world=?";
    private static final String FIND_ID_PERSONS_USERS = "select anime_persons.id from anime_persons " +
            "inner join persons_users on anime_persons.id = persons_users.person_id\n" +
            "where persons_users.user_id=?";
    private static final String FIND_PERSONS_USERS = "select * from anime_persons where id=?";
    private static final String SAVE = "insert into anime_persons (name, world, img) values (?,?,?)";
    private static final String UPDATE = "update anime_persons set name=?, world=?, img=? where id=?";
    private static final String DELETE = "delete from anime_persons where id=?";
    private static final String FIND_ALL = "select * from anime_persons";
    private static final String DELETE_FROM_PER_USER = "delete from persons_users where person_id=?";

    public static void main(String[] args) {
        PersonsRepImp personsRepImp = new PersonsRepImp();
        User user = User.builder()
                .id(105)
                .email("xokkeuct12832@gmail.com")
                .build();
        List<Person> list = personsRepImp.showPersonsUsers(user);
        for (Person person: list) {
            System.out.println(person);
        }

        Person person = Person.builder().build();
        System.out.println(person);

        personsRepImp.addPersonUsers(user, list.get(0));
    }

    @Override
    public List<Person> showPersonsUsers(User user) {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            UserRepImp userRepImp = new UserRepImp();
            User userBuf = userRepImp.findUserByEmail(user.getEmail());
            PreparedStatement prepStatOne = conn.prepareStatement(FIND_ID_PERSONS_USERS);
            prepStatOne.setInt(1, userBuf.getId());
            ResultSet resultSet = prepStatOne.executeQuery();
            ResultSet resultSet1;
            List <Person> list = new ArrayList<>();
            PreparedStatement prepStatTwo = conn.prepareStatement(FIND_PERSONS_USERS);
            while (resultSet.next()){
                prepStatTwo.setInt(1, resultSet.getInt(1));
                resultSet1 = prepStatTwo.executeQuery();
                resultSet1.next();
                Person person = RowMapper.returnPerson(resultSet1);
                list.add(person);
            }
            resultSet.close();
            prepStatOne.close();
            prepStatTwo.close();
            conn.close();
            return list;
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Person> showPersonsWorld(World world) {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prepStat = conn.prepareStatement(FIND_BY_WORLD);
            prepStat.setString(1, world.getName());
            ResultSet resultSet = prepStat.executeQuery();
            List <Person> list = new ArrayList<>();
            while (resultSet.next()){
                Person person = RowMapper.returnPerson(resultSet);
                list.add(person);
            }
            resultSet.close();
            prepStat.close();
            conn.close();
            return list;
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void addPersonUsers(User user, Person person) {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prepStat = conn.prepareStatement(ADD_PERSON_USER);
            prepStat.setInt(1, user.getId());
            prepStat.setInt(2, person.getId());
            prepStat.executeUpdate();
            prepStat.close();
            conn.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void deletePersonUsers(User user, Person person) {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prepStat = conn.prepareStatement(DELETE_PERSON_USER);
            prepStat.setInt(1, user.getId());
            prepStat.setInt(2, person.getId());
            prepStat.executeUpdate();
            prepStat.close();
            conn.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public Person findPersonByName(String name){
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prepStat = conn.prepareStatement(FIND_PERSON);
            prepStat.setString(1, name);
            ResultSet resultSet = prepStat.executeQuery();
            Person person = null;
            if (resultSet.next()) {
                person = RowMapper.returnPerson(resultSet);
            }
            resultSet.close();
            prepStat.close();
            conn.close();
            return person;
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Person entity) {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prepStat = conn.prepareStatement(SAVE);
            prepStat.setString(1, entity.getName());
            prepStat.setString(2, entity.getWorld());
            prepStat.setString(3, entity.getImg());
            prepStat.executeUpdate();
            prepStat.close();
            conn.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Person entity) {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prepStat = conn.prepareStatement(UPDATE);
            prepStat.setString(1, entity.getName());
            prepStat.setString(2, entity.getWorld());
            prepStat.setString(3, entity.getImg());
            prepStat.setInt(4, entity.getId());
            prepStat.executeUpdate();
            prepStat.close();
            conn.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Person entity) {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prepStat = conn.prepareStatement(DELETE);
            prepStat.setInt(1, entity.getId());
            deleteFromPerUser(entity);
            prepStat.executeUpdate();
            prepStat.close();
            conn.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void deleteFromPerUser(Person person){
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prepStat = conn.prepareStatement(DELETE_FROM_PER_USER);
            prepStat.setInt(1, person.getId());
            prepStat.executeUpdate();
            prepStat.close();
            conn.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Person> findAll() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prepStat = conn.prepareStatement(FIND_ALL);
            ResultSet resultSet = prepStat.executeQuery();
            List<Person> personList = new ArrayList<>();
            Person person = null;
            while (resultSet.next()) {
                person = RowMapper.returnPerson(resultSet);
                personList.add(person);
            }
            resultSet.close();
            prepStat.close();
            conn.close();
            return personList;
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
