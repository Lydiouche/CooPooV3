public class Archer extends Personne {
    public Archer(String nom) {
        super(nom);
    }
    @Override
    public void attaquer() {
        System.out.println("L'Archer décoche une flèche (Dégâts modérés).");
    }
}