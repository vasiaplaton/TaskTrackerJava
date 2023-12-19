package platon.ru.vsu.cs.database.memory.repositories;

import platon.ru.vsu.cs.database.memory.repository.MemoryRepository;
import platon.ru.vsu.cs.database.models.Student;
import platon.ru.vsu.cs.database.models.Task;
import platon.ru.vsu.cs.database.repostiories.TaskRepository;

public class TaskMemoryRepository extends MemoryRepository<Task> implements TaskRepository {
    private TaskMemoryRepository() {
        super(Task.class);
    }

    private static TaskMemoryRepository INSTANCE;

    public static TaskMemoryRepository getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new TaskMemoryRepository();
        }
        return INSTANCE;
    }
}
