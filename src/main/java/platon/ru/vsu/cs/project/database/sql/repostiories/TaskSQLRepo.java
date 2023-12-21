package platon.ru.vsu.cs.project.database.sql.repostiories;

import platon.ru.vsu.cs.adb_lib.sql.SQLRepository;
import platon.ru.vsu.cs.adb_lib.sql.conns.PostgreSQLConn;
import platon.ru.vsu.cs.project.database.models.Task;
import platon.ru.vsu.cs.project.database.repostiories.TaskRepository;

public class TaskSQLRepo extends SQLRepository<Task> implements TaskRepository {
    private TaskSQLRepo() {
        super(Task.class, PostgreSQLConn.getInstance());
    }

    private static TaskSQLRepo INSTANCE;

    public static TaskSQLRepo getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new TaskSQLRepo();
        }
        return INSTANCE;
    }
}
