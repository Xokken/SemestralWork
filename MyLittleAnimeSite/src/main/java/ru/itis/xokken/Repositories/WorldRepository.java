package ru.itis.xokken.Repositories;

import ru.itis.xokken.Entity.User;
import ru.itis.xokken.Entity.World;

import java.util.List;

public interface WorldRepository extends CrudRepository {
    public boolean addWorld(World world);
    public List<World> showWorlds();
}
