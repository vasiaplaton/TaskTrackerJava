package platon.ru.vsu.cs.project.console.commands;

import platon.ru.vsu.cs.project.database.models.Group;
import platon.ru.vsu.cs.project.database.models.Mark;
import platon.ru.vsu.cs.project.database.models.Student;
import platon.ru.vsu.cs.project.database.models.Task;

import java.util.Scanner;

public class PrintAll extends BaseCommand {
    public PrintAll(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void execute() {
        System.out.println("students");
        for (Group g: repositoryLib.getGroupRepository().getAll()) {
            System.out.print("Group id: ");
            System.out.print(g.getId());
            System.out.print(" num: ");
            System.out.println(g.getNum());

         //   Logic logic = Logic.getInstance();


            for (Student s : repositoryLib.getStudentRepository().getStudentsByGroup(g.getId())) {
                System.out.print("Student id: ");
                System.out.print(s.getId());
                System.out.print(" ");
                System.out.print(s.getFirstName());
                System.out.print(" ");
                System.out.print(s.getLastName());
                System.out.print(", marks:{");
                for (Mark m : repositoryLib.getMarkRepository().getMarkByStudent(s.getId())) {
                    System.out.print(repositoryLib.getTaskRepository().getById(m.getTaskId()).getName());
                    System.out.print(" ");
                    System.out.print(m.getIsDone());
                    System.out.print("; ");
                }
                System.out.println("} ");
            }
            System.out.println("---");
        }
        System.out.println();
        System.out.println("tasks");
        for (Task t: repositoryLib.getTaskRepository().getAll()) {
            System.out.print("Task id: ");
            System.out.print(t.getId());
            System.out.print(" name:");
            System.out.print(t.getName());
            System.out.print(" descripition:");
            System.out.print(t.getDescription());
            System.out.println(";;; ");
        }
        System.out.println("--------------------------");
        System.out.println();
    }

    @Override
    public String getName() {
        return "Print all";
    }
}
