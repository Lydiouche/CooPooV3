/** Classe fille de Personne.
 * Elle gère le nom de l'Archer et ses attaques.
 *
 * @author Alexis BOULBES, Lydia LEFEBVRE
 * @version 1.0 */

public class Archer extends Personne {

    /** Constructeur principal de l'Archer.
     * Initialise l'Archer avec son nom
     */
    public Archer(String nom) {
        super(nom);
    }

    @Override
    public void attaquer() {
        System.out.println("L'Archer décoche une flèche (Dégâts modérés).");
    }
}