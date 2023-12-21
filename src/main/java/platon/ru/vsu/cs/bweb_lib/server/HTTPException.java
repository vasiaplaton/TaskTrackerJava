package platon.ru.vsu.cs.bweb_lib.server;

public class HTTPException extends Exception{
    public final int sc;
    public final String msg;

    public HTTPException(int sc, String msg) {
        this.sc = sc;
        this.msg = msg;
    }
}
