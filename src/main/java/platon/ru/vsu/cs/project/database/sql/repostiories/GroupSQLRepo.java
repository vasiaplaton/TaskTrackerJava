package platon.ru.vsu.cs.project.database.sql.repostiories;

import platon.ru.vsu.cs.adb_lib.sql.SQLRepository;
import platon.ru.vsu.cs.adb_lib.sql.conns.PostgreSQLConn;
import platon.ru.vsu.cs.project.database.models.Group;
import platon.ru.vsu.cs.project.database.repostiories.GroupRepository;

public class GroupSQLRepo extends SQLRepository<Group> implements GroupRepository {
    private GroupSQLRepo() {
        super(Group.class, PostgreSQLConn.getInstance());
    }

    private static GroupSQLRepo INSTANCE;

    public static GroupSQLRepo getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new GroupSQLRepo();
        }
        return INSTANCE;
    }
}
