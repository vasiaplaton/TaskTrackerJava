package platon.ru.vsu.cs.database.memory;

import platon.ru.vsu.cs.database.RepositoryLib;
import platon.ru.vsu.cs.database.memory.repositories.GroupMemoryRepository;
import platon.ru.vsu.cs.database.memory.repositories.MarkMemoryRepository;
import platon.ru.vsu.cs.database.memory.repositories.StudentMemoryRepository;
import platon.ru.vsu.cs.database.memory.repositories.TaskMemoryRepository;
import platon.ru.vsu.cs.database.memory.repository.MemoryRepository;
import platon.ru.vsu.cs.database.models.Group;
import platon.ru.vsu.cs.database.models.Mark;
import platon.ru.vsu.cs.database.repostiories.GroupRepository;
import platon.ru.vsu.cs.database.repostiories.MarkRepository;
import platon.ru.vsu.cs.database.repostiories.StudentRepository;
import platon.ru.vsu.cs.database.repostiories.TaskRepository;

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
