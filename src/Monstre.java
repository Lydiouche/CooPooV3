public abstract class Monstre extends Entite{

    protected Class<? extends Personnage> faiblesse; //classe du personnage

    public Monstre(int x, int y, String name, Class<? extends Personnage> faiblesse) {
        super(x, y, name);
        this.faiblesse = faiblesse;
    }

    public boolean estFaibleContre(Personnage perso){
        return faiblesse.isInstance(perso);
    }
}
