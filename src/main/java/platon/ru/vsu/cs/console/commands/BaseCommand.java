package platon.ru.vsu.cs.console.commands;

import platon.ru.vsu.cs.database.Database;
import platon.ru.vsu.cs.database.sql.DatabaseSQL;
import platon.ru.vsu.cs.logic.Logic;

import java.util.Scanner;

public abstract class BaseCommand {
    protected final Scanner scanner;
    protected final Database db;
    protected final Logic logic = Logic.getInstance();

    public BaseCommand(Scanner scanner){
        db = DatabaseSQL.getInstance();
        this.scanner = scanner;
    }

    public abstract void execute();

    public abstract String getName();
}
