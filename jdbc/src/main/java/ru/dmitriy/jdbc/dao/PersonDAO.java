package ru.dmitriy.jdbc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.dmitriy.jdbc.model.Person;
import ru.dmitriy.jdbc.model.Hobby;

import java.sql.PreparedStatement;

/**
 *  DAO класс для работы с БД
 */
@Component
public class PersonDAO implements CRUD {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     *  Метод сохраняющий Person и Hobby в таблицы
     */
    @Override
    public void save(Person person) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO person (name, birthday) VALUES(?, ?)");
            ps.setString(1,person.getName());
            ps.setString (2,person.getBirthday());
            return ps;
        }, keyHolder);

        if (!person.getHobbies().isEmpty()) {
            for (Hobby hobbys: person.getHobbies()) {
                jdbcTemplate.update("INSERT INTO hobby (complexity, hobby_name, person_id) VALUES(?, ?)", hobbys.getComplexity(),
                        hobbys.getHobby_name(), keyHolder.getKey());
            }
        }
    }

    @Override
    public Person show(int Id) {
        return null;
    }

    @Override
    public void update(int id, Person person) {

    }

    @Override
    public void delete(int id) {

    }

}
