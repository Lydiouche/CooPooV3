public class Sorcier extends Personne {

    public Sorcier(String nom) {
        super(nom);
    }
    @Override
    public void attaquer() {
        System.out.println("Le Sorcier lance un sort avec sa baguette (Dégâts moyens).");
    }
}