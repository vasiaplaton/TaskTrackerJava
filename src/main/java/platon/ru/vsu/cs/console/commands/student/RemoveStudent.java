package platon.ru.vsu.cs.console.commands.student;

import platon.ru.vsu.cs.console.commands.BaseCommand;

import java.util.Scanner;

public class RemoveStudent extends BaseCommand {
    public RemoveStudent(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void execute() {
        System.out.println("Input student id");
        int groupId = scanner.nextInt();
        db.removeStudent(groupId);
    }

    @Override
    public String getName() {
        return "Remove student";
    }
}
