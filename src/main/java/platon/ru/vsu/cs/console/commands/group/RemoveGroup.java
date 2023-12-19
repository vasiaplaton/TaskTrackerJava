package platon.ru.vsu.cs.console.commands.group;

import platon.ru.vsu.cs.console.commands.BaseCommand;

import java.util.Scanner;

public class RemoveGroup extends BaseCommand {
    public RemoveGroup(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void execute() {
        System.out.println("Input group id");
        int groupId = scanner.nextInt();
        db.removeGroup(groupId);
    }

    @Override
    public String getName() {
        return "Remove group";
    }
}
