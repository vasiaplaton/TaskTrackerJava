package platon.ru.vsu.cs.bweb_lib.server.method;

import platon.ru.vsu.cs.bweb_lib.server.path.ParseMethod;

public class ParamDescr {
    public final ParseMethod parseMethod;

    public final Class<?> clazz;

    public final boolean pathParam;

    public final String name;

    public final int number;

    public ParamDescr(ParseMethod parseMethod, Class<?> clazz, boolean pathParam, String name, int number) {
        this.parseMethod = parseMethod;
        this.clazz = clazz;
        this.pathParam = pathParam;
        this.name = name;
        this.number = number;
    }
}
