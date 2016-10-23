package esprit.org.espritappliaction.Entity;

/**
 * Created by ayach on 13/02/16.
 */
public class AbsenceEns {

    public AbsenceEns(){}


    public AbsenceEns(String id_edutiant, String code_claasse, String code_Module, String semestre, String anne_deb, String num_seance, String date_seance, String id_enseign) {
        this.id_edutiant = id_edutiant;
        this.code_claasse = code_claasse;
        this.code_Module = code_Module;
        this.semestre = semestre;
        this.anne_deb = anne_deb;
        this.num_seance = num_seance;
        this.date_seance = date_seance;
        this.id_enseign = id_enseign;
    }

    public String getId_edutiant() {
        return id_edutiant;
    }

    public void setId_edutiant(String id_edutiant) {
        this.id_edutiant = id_edutiant;
    }

    public String getCode_Module() {
        return code_Module;
    }

    public void setCode_Module(String code_Module) {
        this.code_Module = code_Module;
    }

    public String getCode_claasse() {
        return code_claasse;
    }

    public void setCode_claasse(String code_claasse) {
        this.code_claasse = code_claasse;
    }

    public String getAnne_deb() {
        return anne_deb;
    }

    public void setAnne_deb(String anne_deb) {
        this.anne_deb = anne_deb;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getNum_seance() {
        return num_seance;
    }

    public void setNum_seance(String num_seance) {
        this.num_seance = num_seance;
    }

    public String getId_enseign() {
        return id_enseign;
    }

    public void setId_enseign(String id_enseign) {
        this.id_enseign = id_enseign;
    }

    public String getDate_seance() {
        return date_seance;
    }

    public void setDate_seance(String date_seance) {
        this.date_seance = date_seance;
    }

    String id_edutiant ;
    String code_Module;
    String code_claasse;
    String anne_deb;
    String semestre;
    String num_seance;
    String date_seance;
    String id_enseign;


}
