package platon.ru.vsu.cs.console;

import platon.ru.vsu.cs.console.commands.BaseCommand;
import platon.ru.vsu.cs.console.commands.PrintAll;
import platon.ru.vsu.cs.console.commands.PrintStudents;
import platon.ru.vsu.cs.console.commands.group.*;
import platon.ru.vsu.cs.console.commands.mark.AddMark;
import platon.ru.vsu.cs.console.commands.mark.RemoveMark;
import platon.ru.vsu.cs.console.commands.student.AddStudent;
import platon.ru.vsu.cs.console.commands.student.RemoveStudent;
import platon.ru.vsu.cs.console.commands.student.ReplaceStudent;
import platon.ru.vsu.cs.console.commands.task.AddTask;
import platon.ru.vsu.cs.console.commands.task.RemoveTask;
import platon.ru.vsu.cs.console.commands.task.ReplaceTask;
import platon.ru.vsu.cs.database.*;
import platon.ru.vsu.cs.database.models.*;
import platon.ru.vsu.cs.database.sql.DatabaseSQL;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void spawnBaseData() throws SQLException {
        Database db = DatabaseSQL.getInstance();

        for (int i = 7; i <= 12; i++) {
            db.addGroup(new Group(i));
        }
        // students
        String[] firstNames = {
                "Alice", "Bob", "Charlie", "David", "Eve",
        };

        String[] lastNames = {
                "Smith", "Johnson", "Brown", "Davis", "Wilson",
        };
        Random rand = new Random();
        for(String first: firstNames){
            for(String last: lastNames) {
                int randomIndex = rand.nextInt(db.getGroups().size());
                int groupId = db.getGroups().get(randomIndex).getId();
                db.addStudent(new Student(groupId, first, last));
            }
        }
        // tasks
        String[] array = new String[]{"ONE", "TWO", "THREE"};
        for(String taskName: array){
            db.addTask(new Task(taskName, "Great description"));
        }
        // marks
        for (Student st: db.getStudents()) {
            for (Task task: db.getTasks()) {
                boolean b = rand.nextBoolean();
                if (!b ) {
                    continue;
                }
                db.addMark(new Mark(true, st.getId(), task.getId()));
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        DatabaseSQL databaseSQL = DatabaseSQL.getInstance();
       // databaseSQL.dropAll();
      //  databaseSQL.createTables();
     //   spawnBaseData();
        Scanner scanner = new Scanner(System.in);
        BaseCommand[] commands = new BaseCommand[]{
                new AddGroup(scanner),
                new RemoveGroup(scanner),
                new ReplaceGroup(scanner),

                new AddStudent(scanner),
                new RemoveStudent(scanner),
                new ReplaceStudent(scanner),

                new AddTask(scanner),
                new RemoveTask(scanner),
                new ReplaceTask(scanner),

                new AddMark(scanner),
                new RemoveMark(scanner),

                new PrintStudents(scanner),
                new PrintAll(scanner),
        };

        commands[commands.length-1].execute();
        while (true) {
            for (int i = 0; i < commands.length; i++) {
                BaseCommand c = commands[i];
                System.out.print(i);
                System.out.print(") ");
                System.out.println(c.getName());
            }
            System.out.println("Choose number:");
            int num = scanner.nextInt();
            if (num < 0) break;
            if (num >= commands.length) {
                System.out.println("illegal num");
                continue;
            }

            try {
                commands[num].execute();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            System.out.println();

        }
    }
}