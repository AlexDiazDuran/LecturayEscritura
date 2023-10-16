import java.io.Serializable;

public class PR132persona implements Serializable {
    String nom, cognom;
    Integer edat;
    
    PR132persona(String nom, String cognom, Integer edat){
        this.nom = nom;
        this.cognom = cognom;
        this.edat = edat;
    }

    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return cognom;
    }

    public int getEdat() {
        return edat;
    }

    @Override
    public String toString () {
        return "Nom: " + this.nom + ", Cognom: " + this.cognom + ", Edat: " + this.edat;
    }

}
