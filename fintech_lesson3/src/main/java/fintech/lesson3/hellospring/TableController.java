package fintech.lesson3.hellospring;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;
import lombok.Getter;

@Getter
@Component
public class TableController {

    private List<Student> studentList;

    @Scheduled(fixedRate = 3600000) // every hour
    public void makeCheck() throws IOException {
        studentList = getBusyStudents();
    }

    public List<Student> getBusyStudents() throws IOException {
        List<Student> studentList = ParseTime.parseTimeTable("src/main/resources/students.csv");
        studentList.removeIf(s -> !s.checkCurrentTime());
        return studentList;
    }
}
