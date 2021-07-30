package ru.dmitriy.jdbc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dmitriy.jdbc.dao.CRUD;
import ru.dmitriy.jdbc.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Слушатель Rabbit
 */
@EnableRabbit
@Component
public class RabbitMqListener {

    private final Logger log = LoggerFactory.getLogger(RabbitMqListener.class);

    @Autowired
    CRUD personDAO;

    /**
     *  Ожидание сообщения queue
     */
    @RabbitListener(queues = "queue")
    public void processQueue(String message) {
        System.out.println("Received from queue: " + message);
        List<Person> people = convertToPerson(message);
        savePersons(people);
        System.out.println("Persons add db");        
    }

    /**
     *  Преобразование String в List Persons
     */
    private List<Person> convertToPerson(String message) {
        ObjectMapper mapper = new ObjectMapper();
        List<Person> person = new ArrayList<>();
        try {
            person = Arrays.asList(mapper.readValue(message,Person[].class));
            System.out.println("Persons convert");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("Ошибка получения Persons");
        }
        return person;
    }

    private void savePersons(List<Person> person) {
        person.forEach(pers -> personDAO.save(pers));
        System.out.println("Persons saved");
    }
}
