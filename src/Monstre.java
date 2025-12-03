public abstract class Monstre extends Entite{
    //protected String nom;
    //protected Class<? extends Arme> faiblesse;

    public Monstre(int x, int y, String name) {
        super(x, y, name);
    }

    public boolean estFaibleContre(Arme arme){
        return faiblesse.isInstance(arme);
    }

    //public String getNom() {
        //return nom;
    //}

    //public boolean estFaibleA(Arme arme) {
        //return arme != null && faiblesse.isInstance(arme);
    //}
}
