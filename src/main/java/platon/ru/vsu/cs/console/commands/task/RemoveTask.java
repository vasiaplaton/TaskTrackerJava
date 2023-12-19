package platon.ru.vsu.cs.console.commands.task;

import platon.ru.vsu.cs.console.commands.BaseCommand;

import java.util.Scanner;

public class RemoveTask extends BaseCommand {
    public RemoveTask(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void execute() {
        System.out.println("Input task id");
        int taskId = scanner.nextInt();
        db.removeTask(taskId);
    }

    @Override
    public String getName() {
        return "Remove task";
    }
}
