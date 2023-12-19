package platon.ru.vsu.cs.console.commands;

import platon.ru.vsu.cs.database.models.*;
import platon.ru.vsu.cs.logic.Logic;

import java.util.Scanner;

public class PrintAll extends BaseCommand {
    public PrintAll(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void execute() {
        System.out.println("students");
        for (Group g: db.getGroups()) {
            System.out.print("Group id: ");
            System.out.print(g.getId());
            System.out.print(" num: ");
            System.out.println(g.getNum());

            Logic logic = Logic.getInstance();


            for (Student s : logic.getStudentsByGroup(g.getId())) {
                System.out.print("Student id: ");
                System.out.print(s.getId());
                System.out.print(" ");
                System.out.print(s.getFirstName());
                System.out.print(" ");
                System.out.print(s.getLastName());
                System.out.print(", marks:{");
                for (Mark m : logic.getMarksByStudent(s.getId())) {
                    System.out.print(db.getTasks(m.getTaskId()).getName());
                    System.out.print(" ");
                    System.out.print(m.isDone());
                    System.out.print("; ");
                }
                System.out.println("} ");
            }
            System.out.println("---");
        }
        System.out.println();
        System.out.println("tasks");
        for (Task t: db.getTasks()) {
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
