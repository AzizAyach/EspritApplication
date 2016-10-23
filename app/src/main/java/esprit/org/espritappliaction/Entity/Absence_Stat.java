package esprit.org.espritappliaction.Entity;

/**
 * Created by Yahya on 07/03/2016.
 */
public class Absence_Stat {

private String code_module;
  private  String nom_prenom;

    public Absence_Stat(String code_module, String nom_prenom) {
        this.code_module = code_module;
        this.nom_prenom = nom_prenom;
    }

    public String getCode_module() {
        return code_module;
    }

    public void setCode_module(String code_module) {
        this.code_module = code_module;
    }

    public String getNom_prenom() {
        return nom_prenom;
    }

    public void setNom_prenom(String nom_prenom) {
        this.nom_prenom = nom_prenom;
    }

    @Override
    public String toString() {
        return "Absence{" +
                "code_module='" + code_module + '\'' +
                ", nom_prenom='" + nom_prenom + '\'' +
                '}';
    }
}
