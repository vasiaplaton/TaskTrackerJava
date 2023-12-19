package platon.ru.vsu.cs.database.memory.repositories;

import platon.ru.vsu.cs.database.memory.repository.MemoryRepository;
import platon.ru.vsu.cs.database.models.Group;
import platon.ru.vsu.cs.database.repostiories.GroupRepository;

public class GroupMemoryRepository extends MemoryRepository<Group> implements GroupRepository {
    private GroupMemoryRepository() {
        super(Group.class);
    }

    private static GroupMemoryRepository INSTANCE;

    public static GroupMemoryRepository getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new GroupMemoryRepository();
        }
        return INSTANCE;
    }
}
