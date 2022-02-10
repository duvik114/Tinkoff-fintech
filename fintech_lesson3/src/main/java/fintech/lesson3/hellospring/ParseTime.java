package fintech.lesson3.hellospring;

import org.springframework.stereotype.Component;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

@Component
public final class ParseTime {

    public static List<Student> parseTimeTable(String timeTablePath) throws IOException {
        ArrayList<Student> students = new ArrayList<>();
        Reader in = new FileReader(timeTablePath, StandardCharsets.UTF_8);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        for (CSVRecord record : records) {
            try {
                students.add(new Student(record.get(0), Integer.parseInt(record.get(1)),
                        Integer.parseInt(record.get(2)), Integer.parseInt(record.get(3))));
            } catch (NumberFormatException e) {
                System.out.println("ERROR : " + e.getMessage() + " for student name = " + record.get(0));
            }
        }
        return students;
    }
}
