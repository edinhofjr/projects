import java.io.Serializable;

public class Account implements Serializable {
    String name;
    Account(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
    void setName(String name) {
        this.name = name;
    }
}
