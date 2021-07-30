package ru.dmitriy.rest.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dmitriy.jdbc.model.Person;
import ru.dmitriy.rest.handler.Converter;

import java.io.*;
import java.util.List;

/**
 *  Простой рест контролер
 */

@RestController
public class RestControll {

    @Autowired
    static AmqpTemplate template;

    /**
     *  Метод get
     */
    @GetMapping("/add-to")
    public static String sendMessage () {
        return "Добрый день \n Пришлите XML";
    }


    /**
     *  Метод Post принимающий параметр String XML, отправляющий в MQ для слушателя
     */
    @PostMapping("/add-to")
    public static void push (@RequestParam("Person") String xml) {
        List<Person> person;
        person = Converter.convertFromXml(xml);
        String json = Converter.convertToJson(person);
        if (!json.isEmpty()) {
            template.convertAndSend("queue", json);
            System.out.println("Json отправлен");
        }
        else  System.out.println("Json не отправлен отправлен");
    }
}
