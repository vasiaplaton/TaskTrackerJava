package platon.ru.vsu.cs.project.console.commands.mark;

import platon.ru.vsu.cs.project.console.commands.BaseCommand;

import java.util.Scanner;

public class RemoveMark extends BaseCommand {
    public RemoveMark(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void execute() {
        System.out.println("input student id");
        int studentId = scanner.nextInt();
        System.out.println("input task id");
        int taskId = scanner.nextInt();
        repositoryLib.getMarkRepository().setMark(studentId, taskId, true);
       // logic.setMark(studentId, taskId, false);
    }

    @Override
    public String getName() {
        return "Remove mark";
    }
}
