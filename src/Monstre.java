public abstract class Monstre {
    protected String nom;
    protected Class<? extends Arme> faiblesse;

    public Monstre(String nom, Class<? extends Arme> faiblesse) {
        this.nom = nom;
        this.faiblesse = faiblesse;
    }

    public String getNom() {
        return nom;
    }

    public boolean estFaibleA(Arme arme) {
        return arme != null && faiblesse.isInstance(arme);
    }
}
