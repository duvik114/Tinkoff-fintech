package tinkoff.fintech;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student(2364527, "Nikolay", 23));
        list.add(new Student(3545443, "Anna", 22));
        list.add(new Student(9864352, "Maria", 18));
        list.add(new Student(1234567, "Donkey", 19));
        list.add(new Student(3534523, "Nikolay", 23));
        list.add(new Student(1234234, "Anna", 22));
        list.add(new Student(3453722, "Alex", 22));
        list.add(new Student(8822993, "Rob", 20));
        list.add(new Student(4444999, "Bob", 20));
        Scanner scanner = new Scanner(System.in);

        // first
        String name = scanner.nextLine();
        System.out.println(list.stream().filter(s -> s.getName().equals(name)).mapToLong(s -> (long) s.getAge()).sum());

        // second
        HashSet<String> set = list.stream().map(Student::getName).collect(Collectors.toCollection(HashSet::new));

        //third
        int age = scanner.nextInt();
        System.out.println(list.stream().anyMatch(s -> s.getAge() > age));

        //fourth
        HashMap<Integer, String> map = new HashMap<>(list.stream().collect(Collectors.toMap(Student::getID, Student::getName)));

        //fifth
        HashMap<Integer, List<Student>> advansedMap = new HashMap<>(list.stream().collect(Collectors.groupingBy(Student::getAge)));
    }
}
