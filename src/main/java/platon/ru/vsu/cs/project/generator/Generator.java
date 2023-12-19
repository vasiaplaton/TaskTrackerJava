package platon.ru.vsu.cs.project.generator;

import platon.ru.vsu.cs.project.database.RepositoryLib;
import platon.ru.vsu.cs.project.database.models.Group;
import platon.ru.vsu.cs.project.database.models.Mark;
import platon.ru.vsu.cs.project.database.models.Student;
import platon.ru.vsu.cs.project.database.models.Task;

import java.util.List;
import java.util.Random;

public class Generator {
    public static void spawnBaseData(RepositoryLib repositoryLib) {

        for (int i = 7; i <= 12; i++) {
            repositoryLib.getGroupRepository().add(new Group(i));
        }
        // students
        String[] firstNames = {
                "Alice", "Bob", "Charlie", "David", "Eve",
        };

        String[] lastNames = {
                "Smith", "Johnson", "Brown", "Davis", "Wilson",
        };
        Random rand = new Random();
        for(String first: firstNames){
            for(String last: lastNames) {
                List<Group> groups = repositoryLib.getGroupRepository().getAll();
                int randomIndex = rand.nextInt(groups.size());
                int groupId = groups.get(randomIndex).getId();
                repositoryLib.getStudentRepository().add(new Student(first, last, groupId));
            }
        }
        // tasks
        String[] array = new String[]{"ONE", "TWO", "THREE"};
        for(String taskName: array){
            repositoryLib.getTaskRepository().add(new Task(taskName, "Great description"));
        }
        // marks
        for (Student st: repositoryLib.getStudentRepository().getAll()) {
            for (Task task: repositoryLib.getTaskRepository().getAll()) {
                boolean b = rand.nextBoolean();
                if (!b ) {
                    continue;
                }
                repositoryLib.getMarkRepository().add(new Mark(true, st.getId(), task.getId()));
            }
        }
    }
}
