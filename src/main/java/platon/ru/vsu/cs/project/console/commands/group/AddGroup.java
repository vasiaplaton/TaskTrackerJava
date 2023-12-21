package platon.ru.vsu.cs.project.console.commands.group;

import platon.ru.vsu.cs.project.console.commands.BaseCommand;
import platon.ru.vsu.cs.project.database.models.Group;

import java.util.Scanner;

public class AddGroup extends BaseCommand {
    public AddGroup(Scanner scanner) {
        super(scanner);
    }

    public static Group readGroup(Scanner scanner){
        System.out.println("Input number of group");
        int num = scanner.nextInt();
        return new Group(num);
    }

    @Override
    public void execute() {
        Group g = repositoryLib.getGroupRepository().add(readGroup(scanner));
        System.out.print("group added, id:");
        System.out.println(g.getId());
    }

    @Override
    public String getName() {
        return "Add group";
    }
}
