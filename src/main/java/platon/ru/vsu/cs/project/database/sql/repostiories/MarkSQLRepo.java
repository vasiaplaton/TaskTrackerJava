package platon.ru.vsu.cs.project.database.sql.repostiories;

import platon.ru.vsu.cs.adb_lib.sql.SQLRepository;
import platon.ru.vsu.cs.adb_lib.sql.conns.PostgreSQLConn;
import platon.ru.vsu.cs.project.database.models.Mark;
import platon.ru.vsu.cs.project.database.repostiories.MarkRepository;

public class MarkSQLRepo extends SQLRepository<Mark> implements MarkRepository {
    private MarkSQLRepo() {
        super(Mark.class, PostgreSQLConn.getInstance());
    }

    private static MarkSQLRepo INSTANCE;

    public static MarkSQLRepo getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new MarkSQLRepo();
        }
        return INSTANCE;
    }
}
