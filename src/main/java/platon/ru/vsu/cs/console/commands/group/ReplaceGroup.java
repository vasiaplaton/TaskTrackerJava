package platon.ru.vsu.cs.console.commands.group;

import platon.ru.vsu.cs.console.commands.BaseCommand;

import java.util.Scanner;

public class ReplaceGroup extends BaseCommand {
    public ReplaceGroup(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void execute() {
        System.out.println("Input old id");
        int id = scanner.nextInt();
        db.updateGroup(id, AddGroup.readGroup(scanner));
        System.out.print("group replaced");
    }

    @Override
    public String getName() {
        return "Replace group";
    }
}
