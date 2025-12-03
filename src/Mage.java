public class Mage extends Personne {
    public Mage(String nom) {
        super(nom);
    }
    @Override
    public void attaquer() {
        System.out.println("Le Mage lance un sort (Dégâts variés).");
    }
}