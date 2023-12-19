package platon.ru.vsu.cs.console.commands.student;

import platon.ru.vsu.cs.console.commands.BaseCommand;
import platon.ru.vsu.cs.database.models.Student;

import java.util.Scanner;

public class AddStudent extends BaseCommand {
    public AddStudent(Scanner scanner) {
        super(scanner);
    }

    public static Student readStudent(Scanner scanner){
        System.out.println("input group id");
        int groupId = scanner.nextInt();
        System.out.println("input first name");
        String firstName = scanner.nextLine();
        System.out.println("input last name");
        String lastName = scanner.nextLine();
        return new Student(groupId, firstName, lastName);
    }

    @Override
    public void execute() {
        Student s = db.addStudent(readStudent(scanner));
        System.out.print("student added, id:");
        System.out.println(s.getId());
    }

    @Override
    public String getName() {
        return "Add student";
    }
}
