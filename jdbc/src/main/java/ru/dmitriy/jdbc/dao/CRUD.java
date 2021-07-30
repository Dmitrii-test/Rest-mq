package ru.dmitriy.jdbc.dao;

import ru.dmitriy.jdbc.model.Person;

/**
 *  Интерфейс CRUD для DAO
 */
public interface CRUD {

    void save(Person person);

    Person show(int Id);

    void update(int id, Person person);

    void delete(int id);

}
