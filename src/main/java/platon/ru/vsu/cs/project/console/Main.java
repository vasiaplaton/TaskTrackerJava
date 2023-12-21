package platon.ru.vsu.cs.project.console;

import platon.ru.vsu.cs.project.console.commands.BaseCommand;
import platon.ru.vsu.cs.project.console.commands.PrintAll;
import platon.ru.vsu.cs.project.console.commands.PrintStudents;
import platon.ru.vsu.cs.project.console.commands.group.AddGroup;
import platon.ru.vsu.cs.project.console.commands.group.RemoveGroup;
import platon.ru.vsu.cs.project.console.commands.group.ReplaceGroup;
import platon.ru.vsu.cs.project.console.commands.mark.AddMark;
import platon.ru.vsu.cs.project.console.commands.mark.RemoveMark;
import platon.ru.vsu.cs.project.console.commands.student.AddStudent;
import platon.ru.vsu.cs.project.console.commands.student.RemoveStudent;
import platon.ru.vsu.cs.project.console.commands.student.ReplaceStudent;
import platon.ru.vsu.cs.project.console.commands.task.AddTask;
import platon.ru.vsu.cs.project.console.commands.task.RemoveTask;
import platon.ru.vsu.cs.project.console.commands.task.ReplaceTask;
import platon.ru.vsu.cs.project.database.RepositoryLib;
import platon.ru.vsu.cs.project.database.sql.SQLRepositoryLib;
import platon.ru.vsu.cs.project.database.sql.repostiories.GroupSQLRepo;
import platon.ru.vsu.cs.project.database.sql.repostiories.MarkSQLRepo;
import platon.ru.vsu.cs.project.database.sql.repostiories.StudentSQLRepo;
import platon.ru.vsu.cs.project.database.sql.repostiories.TaskSQLRepo;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        RepositoryLib repositoryLib = new SQLRepositoryLib();
       // databaseSQL.dropAll();
      //  databaseSQL.createTables();
        //Generator.spawnBaseData(repositoryLib);
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

        System.out.println(StudentSQLRepo.getINSTANCE().generateCreateCode());
        System.out.println(GroupSQLRepo.getINSTANCE().generateCreateCode());
        System.out.println(MarkSQLRepo.getINSTANCE().generateCreateCode());
        System.out.println(TaskSQLRepo.getINSTANCE().generateCreateCode());

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