package platon.ru.vsu.cs.bweb_lib.server.method;

import platon.ru.vsu.cs.bweb_lib.server.path.ParseMethod;

public class WebMethodArgument {
    public final ParseMethod parseMethod;

    public final Class<?> clazz;

    public final boolean pathParam;

    public final String name;

    public WebMethodArgument(ParseMethod parseMethod, Class<?> clazz, boolean pathParam, String name) {
        this.parseMethod = parseMethod;
        this.clazz = clazz;
        this.pathParam = pathParam;
        this.name = name;
    }
}
