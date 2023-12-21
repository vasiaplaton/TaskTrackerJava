package platon.ru.vsu.cs.project.console.commands.student;

import platon.ru.vsu.cs.project.console.commands.BaseCommand;

import java.util.Scanner;

public class ReplaceStudent extends BaseCommand {
    public ReplaceStudent(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void execute() {
        System.out.println("input old id");
        int oldId = scanner.nextInt();
        repositoryLib.getStudentRepository().update(oldId, AddStudent.readStudent(scanner));
        System.out.println("replaced");
    }

    @Override
    public String getName() {
        return "Replace student";
    }
}
