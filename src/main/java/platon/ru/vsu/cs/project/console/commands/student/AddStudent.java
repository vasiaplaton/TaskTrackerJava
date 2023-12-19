package platon.ru.vsu.cs.project.console.commands.student;

import platon.ru.vsu.cs.project.console.commands.BaseCommand;
import platon.ru.vsu.cs.project.database.models.Student;

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
        return new Student(lastName, firstName, groupId);
    }

    @Override
    public void execute() {
        Student s = repositoryLib.getStudentRepository().add(readStudent(scanner));
        System.out.print("student added, id:");
        System.out.println(s.getId());
    }

    @Override
    public String getName() {
        return "Add student";
    }
}
