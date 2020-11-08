package ru.itis.xokken.Services;

import ru.itis.xokken.Entity.Person;
import ru.itis.xokken.Entity.User;
import ru.itis.xokken.Entity.World;
import ru.itis.xokken.Repositories.PersonsRepImp;
import ru.itis.xokken.Repositories.UserRepImp;
import ru.itis.xokken.dto.PersonDTO;
import ru.itis.xokken.dto.UserDTO;
import ru.itis.xokken.dto.WorldDTO;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceImpl implements PersonService{
    private final PersonsRepImp personsRepImp;
    private final UserRepImp userRepImp;

    public PersonServiceImpl(PersonsRepImp personsRepImp, UserRepImp userRepImp) {
        this.personsRepImp = personsRepImp;
        this.userRepImp = userRepImp;
    }

    public static void main(String[] args) {
        UserDTO user = UserDTO.builder()
                .email("xokkeuct12832@gmail.com")
                .age(15)
                .password("123321")
                .name("Алексей Дьяконов")
                .build();
        PersonServiceImpl personService = new PersonServiceImpl(new PersonsRepImp(), new UserRepImp());
        List<PersonDTO> personDTOS = personService.findAll();
        for (PersonDTO person: personDTOS) {
            System.out.println(person.toString());
        }
    }

    @Override
    public List<PersonDTO> showAllPersonByWorld(WorldDTO worldDTO) {
        World world = World.builder()
                .name(worldDTO.getName())
                .img(worldDTO.getImg())
                .build();
        List<Person> listOne = personsRepImp.showPersonsWorld(world);
        List<PersonDTO> listTwo = new ArrayList();
        for (Person person: listOne) {
            PersonDTO personDTO = returnPersonDTO(person);
            listTwo.add(personDTO);
        }
        return listTwo;
    }

    @Override
    public List<PersonDTO> showAllPersonByUser(UserDTO userDTO) {
        User user = userRepImp.findUserByEmail(userDTO.getEmail());
        List<Person> list1 = personsRepImp.showPersonsUsers(user);
        List<PersonDTO> list2 = new ArrayList<>();
        for (Person person: list1) {
            PersonDTO personDTO = returnPersonDTO(person);
            list2.add(personDTO);
        }
        return list2;
    }

    @Override
    public void addPersonUsers(UserDTO userDTO, PersonDTO personDTO) {
        User user = userRepImp.findUserByEmail(userDTO.getEmail());
        Person person = personsRepImp.findPersonByName(personDTO.getName());
        personsRepImp.addPersonUsers(user, person);
    }

    @Override
    public void deletePersonUsers(UserDTO userDTO, PersonDTO personDTO) {
        User user = userRepImp.findUserByEmail(userDTO.getEmail());
        Person person = personsRepImp.findPersonByName(personDTO.getName());
        personsRepImp.deletePersonUsers(user, person);
    }

    @Override
    public void save(PersonDTO entity) {
        Person person = Person.builder()
                .img(entity.getImg())
                .world(entity.getWorld())
                .name(entity.getName())
                .build();
        personsRepImp.save(person);
    }

    @Override
    public void update(PersonDTO entity) {
        Person person = personsRepImp.findPersonByName(entity.getName());
        personsRepImp.update(person);
    }

    @Override
    public void delete(PersonDTO entity) {
        Person person = personsRepImp.findPersonByName(entity.getName());
        personsRepImp.delete(person);
    }

    @Override
    public List<PersonDTO> findAll() {
        List<PersonDTO> personDTOS = new ArrayList<>();
        PersonDTO personDTO;
        List<Person> personList = personsRepImp.findAll();
        for (Person person: personList){
            personDTO = returnPersonDTO(person);
            personDTOS.add(personDTO);
        }
        return personDTOS;
    }

    private PersonDTO returnPersonDTO(Person person){
        PersonDTO personDTO = PersonDTO.builder()
                .img(person.getImg())
                .world(person.getWorld())
                .name(person.getName())
                .build();
        return personDTO;
    }
}
