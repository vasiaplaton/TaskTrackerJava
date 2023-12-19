package platon.ru.vsu.cs.database;

import platon.ru.vsu.cs.database.repostiories.GroupRepository;
import platon.ru.vsu.cs.database.repostiories.MarkRepository;
import platon.ru.vsu.cs.database.repostiories.StudentRepository;
import platon.ru.vsu.cs.database.repostiories.TaskRepository;

public interface RepositoryLib {
    GroupRepository getGroupRepository();
    MarkRepository getMarkRepository();
    StudentRepository getStudentRepository();
    TaskRepository getTaskRepository();
}
