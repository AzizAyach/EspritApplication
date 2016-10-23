package esprit.org.espritappliaction.Entity;

/**
 * Created by ayach on 14/02/16.
 */
public class Absence {
    public Absence(){}
    public Absence(String code_classe, String name, String code_Module, String date_abs, String num_seance) {
        this.code_classe = code_classe;
        this.name = name;
        this.code_Module = code_Module;
        this.date_abs = date_abs;
        this.num_seance = num_seance;
    }

    public String getCode_classe() {
        return code_classe;
    }

    public void setCode_classe(String code_classe) {
        this.code_classe = code_classe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode_Module() {
        return code_Module;
    }

    public void setCode_Module(String code_Module) {
        this.code_Module = code_Module;
    }

    public String getDate_abs() {
        return date_abs;
    }

    public void setDate_abs(String date_abs) {
        this.date_abs = date_abs;
    }

    public String getNum_seance() {
        return num_seance;
    }

    public void setNum_seance(String num_seance) {
        this.num_seance = num_seance;
    }

    private String code_classe;
    private String name;
    private String code_Module;
    private String date_abs;
    private String num_seance;

}
