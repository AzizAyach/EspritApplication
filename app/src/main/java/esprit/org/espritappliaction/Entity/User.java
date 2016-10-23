package esprit.org.espritappliaction.Entity;

/**
 * Created by aziz on 12/01/2016.
 */
public class User {

    public User(){}
    public User(String id, String cin, String pasword) {
        this.id = id;
        this.cin = cin;
        this.pasword = pasword;
    }

    private  String id;

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    private  String cin ;
    private  String  pasword ;
}
