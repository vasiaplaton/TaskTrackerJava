package platon.ru.vsu.cs.console.commands.task;

import platon.ru.vsu.cs.console.commands.BaseCommand;

import java.util.Scanner;

public class ReplaceTask extends BaseCommand {
    public ReplaceTask(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void execute() {
        System.out.println("input old id");
        int oldId = scanner.nextInt();
        db.updateTask(oldId, AddTask.readTask(scanner));
        System.out.println("replaced");
    }

    @Override
    public String getName() {
        return "Replace task";
    }
}
