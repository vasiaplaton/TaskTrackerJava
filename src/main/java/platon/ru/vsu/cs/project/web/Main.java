package platon.ru.vsu.cs.project.web;

import platon.ru.vsu.cs.bweb_lib.annotations.WebClass;
import platon.ru.vsu.cs.bweb_lib.server.Server;
import platon.ru.vsu.cs.project.database.RepositoryLib;
import platon.ru.vsu.cs.project.database.sql.SQLRepositoryLib;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RepositoryLib repositoryLib = new SQLRepositoryLib();

        List<WebClass> webClasses = new ArrayList<>();
        webClasses.add(new StudentServlet(repositoryLib.getStudentRepository(), repositoryLib));
        webClasses.add(new GroupServlet(repositoryLib.getGroupRepository()));
        webClasses.add(new MarkServlet(repositoryLib.getMarkRepository()));
        webClasses.add(new TaskServlet(repositoryLib.getTaskRepository()));

        Server server = new Server(webClasses);
        server.runServer();
    }
}
