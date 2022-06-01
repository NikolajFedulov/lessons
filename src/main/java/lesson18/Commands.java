package lesson18;

public enum Commands {
    ADD("add"),
    DELETE("del"),
    FIND("find"),
    VIEW("view"),
    EXIT("exit"),
    YES("y"),
    NO("n"),
    NO_CMD("no command");

    public final String value;

    Commands(String command){
        this.value = command;
    }
}
