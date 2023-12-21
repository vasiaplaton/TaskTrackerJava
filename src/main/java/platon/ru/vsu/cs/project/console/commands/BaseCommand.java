package platon.ru.vsu.cs.project.console.commands;

import platon.ru.vsu.cs.project.database.RepositoryLib;
import platon.ru.vsu.cs.project.database.sql.SQLRepositoryLib;

import java.util.Scanner;

public abstract class BaseCommand {
    protected final Scanner scanner;
    protected final RepositoryLib repositoryLib;

    public BaseCommand(Scanner scanner){
        repositoryLib = new SQLRepositoryLib();
        this.scanner = scanner;
    }

    public abstract void execute();

    public abstract String getName();
}
