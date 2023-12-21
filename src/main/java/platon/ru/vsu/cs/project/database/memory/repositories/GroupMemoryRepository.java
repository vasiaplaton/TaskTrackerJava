package platon.ru.vsu.cs.project.database.memory.repositories;

import platon.ru.vsu.cs.adb_lib.memory.MemoryRepo;
import platon.ru.vsu.cs.adb_lib.memory.MemoryRepository;
import platon.ru.vsu.cs.project.database.models.Group;
import platon.ru.vsu.cs.project.database.repostiories.GroupRepository;

@MemoryRepo(clazz = Group.class)
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
