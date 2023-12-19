package platon.ru.vsu.cs.database.sql;

import platon.ru.vsu.cs.database.Database;
import platon.ru.vsu.cs.database.models.Group;
import platon.ru.vsu.cs.database.models.Mark;
import platon.ru.vsu.cs.database.models.Student;
import platon.ru.vsu.cs.database.models.Task;
import platon.ru.vsu.cs.database.sql.conns.PostgreSQLConn;
import platon.ru.vsu.cs.database.sql.conns.SQLConnector;
import platon.ru.vsu.cs.database.sql.table.TableSQL;
import platon.ru.vsu.cs.logger.Log;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseSQL extends Database {
    protected final SQLConnector sqlConnector;

    private DatabaseSQL(){
        try {
            sqlConnector = PostgreSQLConn.getInstance();
        } catch (SQLException e) {
            Log.getI().log(e.getMessage(), 0);
            throw new RuntimeException(e);
        }

        groupTable = new TableSQL<>(sqlConnector, "Groups", Group.class);
        markTable = new TableSQL<>(sqlConnector, "Marks", Mark.class);
        studentTable = new TableSQL<>(sqlConnector, "Students", Student.class);
        taskTable = new TableSQL<>(sqlConnector, "Tasks", Task.class);

    }

    private static DatabaseSQL INSTANCE;

    public static DatabaseSQL getInstance(){
        if(INSTANCE == null){
            INSTANCE = new DatabaseSQL();
        }
        return INSTANCE;
    }

    public void dropAll(){
        String[] tables = new String[]{"Marks", "Students", "Tasks", "Groups"};
        for (String t : tables) {
            String query = "DROP TABLE " + t + ";";
            try {
                PreparedStatement ps = sqlConnector.makeUpdate(query);
                ps.close();
            } catch (SQLException e) {
                Log.getI().log(e.getMessage(), 0);
            }
        }
    }

    public void createTables(){
        String[] queries = new String[]{
"""
CREATE TABLE Groups(
    id SERIAL PRIMARY KEY,
    num INTEGER NOT NULL
);
""",
"""
CREATE TABLE Tasks(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT
);
""",
"""
CREATE TABLE Students(
    id SERIAL PRIMARY KEY,
    groupId INTEGER,
    firstName TEXT NOT NULL,
    lastName TEXT NOT NULL,
    
    CONSTRAINT students_groups
        FOREIGN KEY (groupId)
        REFERENCES Groups(id)
        ON DELETE CASCADE
);
""",
"""
CREATE TABLE Marks(
    id SERIAL PRIMARY KEY,
    isDone INTEGER NOT NULL,
    studentId INTEGER,
    taskId INTEGER,
    
    CONSTRAINT mark_student
        FOREIGN KEY (studentId)
        REFERENCES Students(id)
        ON DELETE CASCADE,
        
    CONSTRAINT mark_task
        FOREIGN KEY (taskId)
        REFERENCES Tasks(id)
        ON DELETE CASCADE
);
""",
        };
        for (String q : queries) {
            try {
                PreparedStatement ps = sqlConnector.makeUpdate(q);
                ps.close();
            } catch (SQLException e) {
                Log.getI().log(e.getMessage(), 0);
                throw new RuntimeException(e);
            }
        }
    }
}
