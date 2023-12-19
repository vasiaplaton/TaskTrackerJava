package platon.ru.vsu.cs.project.database.memory.repositories;

import platon.ru.vsu.cs.adb_lib.memory.MemoryRepo;
import platon.ru.vsu.cs.adb_lib.memory.MemoryRepository;
import platon.ru.vsu.cs.project.database.models.Mark;
import platon.ru.vsu.cs.project.database.repostiories.MarkRepository;

@MemoryRepo(clazz = Mark.class)
public class MarkMemoryRepository extends MemoryRepository<Mark> implements MarkRepository {
    private MarkMemoryRepository() {
        super(Mark.class);
    }

    private static MarkMemoryRepository INSTANCE;

    public static MarkMemoryRepository getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new MarkMemoryRepository();
        }
        return INSTANCE;
    }
}
