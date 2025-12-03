public class Chevalier extends Personne {
    public Chevalier(String nom) {
        super(nom);
    }
    @Override
    public void attaquer() {
        System.out.println("Le Chevalier donne un coup d'épée (Dégâts élevés).");
    }
}
