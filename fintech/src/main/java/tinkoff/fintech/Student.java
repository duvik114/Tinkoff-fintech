package tinkoff.fintech;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class Student {

    private int ID;
    private String name;
    private int age;

    public Student(int ID, String name, int age) {
        this.ID = ID;
        this.age = age;
        this.name = name;
    }
}
