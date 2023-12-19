package platon.ru.vsu.cs.console.commands;

import platon.ru.vsu.cs.database.models.Student;

import java.util.Scanner;

public class PrintStudents extends BaseCommand{
    public PrintStudents(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void execute() {
        for (Student s : db.getStudents()) {
            System.out.print("Student id: ");
            System.out.print(s.getId());
            System.out.print(" ");
            System.out.print(s.getLastName());
            System.out.print(" ");
            System.out.print(s.getLastName());
            System.out.println();
        }
        System.out.println("------");
    }

    @Override
    public String getName() {
        return "Print students";
    }
}
