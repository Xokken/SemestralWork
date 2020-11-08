package ru.itis.xokken.Repositories;

import ru.itis.xokken.Entity.Person;
import ru.itis.xokken.Entity.User;
import ru.itis.xokken.Entity.World;

import java.util.List;

public interface PersonsRepository extends CrudRepository<Person>{
    public List<Person> showPersonsUsers(User user);
    public List<Person> showPersonsWorld(World world);
    public void addPersonUsers(User user, Person person);
    public void deletePersonUsers(User user, Person person);
    public Person findPersonByName(String name);

    void save(Person entity);
    void update(Person entity);
    void delete(Person entity);

    List<Person> findAll();
}
