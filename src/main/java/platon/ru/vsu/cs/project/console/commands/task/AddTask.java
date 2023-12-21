package platon.ru.vsu.cs.project.console.commands.task;

import platon.ru.vsu.cs.project.console.commands.BaseCommand;
import platon.ru.vsu.cs.project.database.models.Task;

import java.util.Scanner;

public class AddTask extends BaseCommand {
    public AddTask(Scanner scanner) {
        super(scanner);
    }

    public static Task readTask(Scanner scanner){
        System.out.println("input name");
        String name = scanner.nextLine();
        System.out.println("input description");
        String description = scanner.nextLine();
        return new Task(name, description);
    }

    @Override
    public void execute() {
        Task t = repositoryLib.getTaskRepository().add(readTask(scanner));
        System.out.print("task added, id:");
        System.out.println(t.getId());
    }

    @Override
    public String getName() {
        return "Add task";
    }
}
