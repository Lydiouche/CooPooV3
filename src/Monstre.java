public abstract class Monstre extends Entiter{

    protected Class<? extends Personne> faiblesse; //classe du personnage

    public Monstre(String name, int x, int y, Class<? extends Personne> faiblesse) {
        super(name, x, y);
        this.faiblesse = faiblesse;
    }

    public boolean estFaibleContre(Personne perso){
        return faiblesse.isInstance(perso);
    }
}
