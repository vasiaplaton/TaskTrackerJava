package platon.ru.vsu.cs.project.database.sql;

import platon.ru.vsu.cs.project.database.RepositoryLib;
import platon.ru.vsu.cs.project.database.repostiories.GroupRepository;
import platon.ru.vsu.cs.project.database.repostiories.MarkRepository;
import platon.ru.vsu.cs.project.database.repostiories.StudentRepository;
import platon.ru.vsu.cs.project.database.repostiories.TaskRepository;
import platon.ru.vsu.cs.project.database.sql.repostiories.GroupSQLRepo;
import platon.ru.vsu.cs.project.database.sql.repostiories.MarkSQLRepo;
import platon.ru.vsu.cs.project.database.sql.repostiories.StudentSQLRepo;
import platon.ru.vsu.cs.project.database.sql.repostiories.TaskSQLRepo;

public class SQLRepositoryLib implements RepositoryLib {
    @Override
    public GroupRepository getGroupRepository() {
        return GroupSQLRepo.getINSTANCE();
    }

    @Override
    public MarkRepository getMarkRepository() {
        return MarkSQLRepo.getINSTANCE();
    }

    @Override
    public StudentRepository getStudentRepository() {
        return StudentSQLRepo.getINSTANCE();
    }

    @Override
    public TaskRepository getTaskRepository() {
        return TaskSQLRepo.getINSTANCE();
    }
}
