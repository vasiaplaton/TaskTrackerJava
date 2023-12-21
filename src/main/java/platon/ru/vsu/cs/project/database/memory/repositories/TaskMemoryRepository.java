package platon.ru.vsu.cs.project.database.memory.repositories;

import platon.ru.vsu.cs.adb_lib.memory.MemoryRepo;
import platon.ru.vsu.cs.adb_lib.memory.MemoryRepository;
import platon.ru.vsu.cs.project.database.models.Task;
import platon.ru.vsu.cs.project.database.repostiories.TaskRepository;

@MemoryRepo(clazz = Task.class)
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
