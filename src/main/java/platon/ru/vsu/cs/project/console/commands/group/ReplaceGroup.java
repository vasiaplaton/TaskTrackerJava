package platon.ru.vsu.cs.project.console.commands.group;

import platon.ru.vsu.cs.project.console.commands.BaseCommand;

import java.util.Scanner;

public class ReplaceGroup extends BaseCommand {
    public ReplaceGroup(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void execute() {
        System.out.println("Input old id");
        int id = scanner.nextInt();
        repositoryLib.getGroupRepository().update(id, AddGroup.readGroup(scanner));
        System.out.print("group replaced");
    }

    @Override
    public String getName() {
        return "Replace group";
    }
}
