package ru.dmitriy.jdbc.model;

/**
 *  Java класс Hobby
 */
public class Hobby {
    private int complexity;
    private String hobby_name;

    // Конструктор по умолчанию для сериализации
    public Hobby() {
    }

    public Hobby(int complexity, String hobby_namey) {
        this.complexity = complexity;
        this.hobby_name = hobby_namey;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public String getHobby_name() {
        return hobby_name;
    }

    public void setHobby_name(String hobby_name) {
        this.hobby_name = hobby_name;
    }

    @Override
    public String toString() {
        return "Hobby: " +
                "complexity:" + complexity +
                ", name:'" + hobby_name + '\'';
    }
}
