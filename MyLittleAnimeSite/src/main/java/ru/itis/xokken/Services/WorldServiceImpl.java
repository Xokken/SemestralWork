package ru.itis.xokken.Services;

import ru.itis.xokken.Entity.World;
import ru.itis.xokken.Repositories.WorldRepImp;
import ru.itis.xokken.dto.UserDTO;
import ru.itis.xokken.dto.WorldDTO;

import java.util.ArrayList;
import java.util.List;

public class WorldServiceImpl implements WorldService{
    private final WorldRepImp worldRepImp;

    public WorldServiceImpl(WorldRepImp worldRepImp) {
        this.worldRepImp = worldRepImp;
    }


    @Override
    public boolean add(WorldDTO entity) {
        return false;
    }

    @Override
    public boolean update(UserDTO entity) {
        return false;
    }

    @Override
    public List<WorldDTO> showAllWorlds() {
        List<World> listOne = worldRepImp.showWorlds();
        List<WorldDTO> listTwo = new ArrayList();
        for (World num: listOne) {
            WorldDTO worldDTO = WorldDTO.builder()
                    .name(num.getName())
                    .img(num.getImg())
                    .build();
            listTwo.add(worldDTO);
        }
        return listTwo;
    }
}
