package platon.ru.vsu.cs.project.database;

import platon.ru.vsu.cs.project.database.repostiories.GroupRepository;
import platon.ru.vsu.cs.project.database.repostiories.MarkRepository;
import platon.ru.vsu.cs.project.database.repostiories.StudentRepository;
import platon.ru.vsu.cs.project.database.repostiories.TaskRepository;

public interface RepositoryLib {
    GroupRepository getGroupRepository();
    MarkRepository getMarkRepository();
    StudentRepository getStudentRepository();
    TaskRepository getTaskRepository();
}
