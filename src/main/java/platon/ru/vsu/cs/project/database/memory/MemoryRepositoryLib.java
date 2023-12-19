package platon.ru.vsu.cs.project.database.memory;

import platon.ru.vsu.cs.project.database.RepositoryLib;
import platon.ru.vsu.cs.project.database.memory.repositories.GroupMemoryRepository;
import platon.ru.vsu.cs.project.database.memory.repositories.MarkMemoryRepository;
import platon.ru.vsu.cs.project.database.memory.repositories.StudentMemoryRepository;
import platon.ru.vsu.cs.project.database.memory.repositories.TaskMemoryRepository;
import platon.ru.vsu.cs.project.database.repostiories.GroupRepository;
import platon.ru.vsu.cs.project.database.repostiories.MarkRepository;
import platon.ru.vsu.cs.project.database.repostiories.StudentRepository;
import platon.ru.vsu.cs.project.database.repostiories.TaskRepository;

public class MemoryRepositoryLib implements RepositoryLib {


    @Override
    public GroupRepository getGroupRepository() {
        return GroupMemoryRepository.getINSTANCE();
    }

    @Override
    public MarkRepository getMarkRepository() {
        return MarkMemoryRepository.getINSTANCE();
    }

    @Override
    public StudentRepository getStudentRepository() {
        return StudentMemoryRepository.getINSTANCE();
    }

    @Override
    public TaskRepository getTaskRepository() {
        return TaskMemoryRepository.getINSTANCE();
    }
}
