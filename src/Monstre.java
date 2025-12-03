public abstract class Monstre extends Entiter{

    public Monstre(String name, int x, int y) {
        super(name, x, y);
    }

    public boolean estFaibleContre(Personne perso){
        return faiblesse.isInstance(perso);
    }
}
