package ru.dmitriy.rest.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dmitriy.jdbc.controller.RabbitMqListener;
import ru.dmitriy.jdbc.model.Person;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Функциональный интерфейс Конвертер
 */
public interface Converter {
    XmlMapper XML_MAPPER = new XmlMapper();
    Logger log = LoggerFactory.getLogger(RabbitMqListener.class);

    /**
     *  Метод конвертирующий XML в List Person
     */
    static List<Person> convertFromXml(String xml){
        List<Person> person = new ArrayList<>();
        try {
            person = Arrays.asList(XML_MAPPER.readValue(xml,Person[].class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("Ошибка преобразования из XML");
        }
        return person;
    }

    /**
     *  Метод конвертирующий List Person в JSON строку
     */
    static String convertToJson(List<Person> person){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(person);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("Ошибка преобразования в Json");
        }
        return "";
    }

    /**
     *  Метод преобразующий XML файл в String
     */
    static String convertFile(String filename) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            String line;
            StringBuilder xml = new StringBuilder();
            while ((line = br.readLine()) != null) {
                xml.append(line.trim());
            }
            result = xml.toString();
        } catch (IOException t) {
            t.printStackTrace();
        }
        return result;
    }
}
