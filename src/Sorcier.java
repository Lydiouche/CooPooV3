public class Sorcier extends Personne {
    public Sorcier(String nom, int x, int y) {
        super(nom, x, y);
    }
    @Override
    public void attaquer() {
        System.out.println("Le Sorcier lance un sort avec sa baguette (Dégâts moyens).");
    }
}