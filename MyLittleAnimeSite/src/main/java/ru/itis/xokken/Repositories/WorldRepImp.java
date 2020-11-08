package ru.itis.xokken.Repositories;

import ru.itis.xokken.Entity.World;
import ru.itis.xokken.Repositories.Utility.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorldRepImp implements WorldRepository{
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "15022001";
    Connection conn = null;
    //language=sql
    //language=sql
    private static final String SHOW_ALL = "select * from worlds";

    @Override
    public boolean addWorld(World world) {
        return false;
    }

    @Override
    public List<World> showWorlds() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prepStat = conn.prepareStatement(SHOW_ALL);
            ResultSet resultSet = prepStat.executeQuery();
            List <World> list = new ArrayList<>();
            while (resultSet.next()){
                World world = RowMapper.returnWorld(resultSet);
                list.add(world);
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
    public void save(Object entity) {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public List findAll() {
        return null;
    }
}
