package ru.itis.xokken.Services;

import ru.itis.xokken.Entity.Person;
import ru.itis.xokken.dto.PersonDTO;
import ru.itis.xokken.dto.UserDTO;
import ru.itis.xokken.dto.WorldDTO;

import java.util.List;

public interface PersonService {
    void save(PersonDTO entity);
    void update(PersonDTO entity);
    void delete(PersonDTO entity);
    List<PersonDTO> findAll();

    List<PersonDTO> showAllPersonByWorld(WorldDTO worldDTO);
    List<PersonDTO> showAllPersonByUser(UserDTO userDTO);
    void addPersonUsers(UserDTO userDTO, PersonDTO personDTO);
    void deletePersonUsers(UserDTO userDTO, PersonDTO personDTO);
}
