public class Paysan extends Personne {
    public Paysan(String nom, int x, int y) {
        super(nom);
    }

    @Override
    public void attaquer() {
        System.out.println(getNom() + "n'a pas d'arme.");
    }
}
