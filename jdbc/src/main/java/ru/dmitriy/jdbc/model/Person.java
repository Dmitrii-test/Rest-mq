package ru.dmitriy.jdbc.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *  Java класс Person
 */
public class Person {

    private String name;
    private Date birthday;

    //Не сериализовать если значение null
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArrayList<Hobby> hobbies;

    // Форматер для парсинга даты
    private static final SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

    // Конструктор по умолчанию для сериализации
    public Person() {
    }

    public Person(String name, String birthday) {
        this.name = name;
        try {
            this.birthday = formater.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Неверный формат даты");
        }
    }

    public Person(String name, String birthday, ArrayList<Hobby> hobbies) {
        this(name, birthday);
        this.hobbies = hobbies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return formater.format(birthday);
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public ArrayList<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Person: " + this.getName() +
                "\nBirthday:" + this.getBirthday() + "\n" +
                this.hobbies;
    }
}
