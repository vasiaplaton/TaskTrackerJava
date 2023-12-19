package platon.ru.vsu.cs.database.memory.repositories;

import platon.ru.vsu.cs.database.memory.repository.MemoryRepository;
import platon.ru.vsu.cs.database.models.Group;
import platon.ru.vsu.cs.database.models.Mark;
import platon.ru.vsu.cs.database.repostiories.MarkRepository;

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
