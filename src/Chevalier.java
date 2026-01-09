/** Classe fille de Personne.
 * Elle gère le nom du Chevalier et ses attaques.
 *
 * @author Alexis BOULBES
 * @version 1.0 */

public class Chevalier extends Personne {

    /** Constructeur principal du Chevalier.
     * Initialise le Chevalier avec son nom
     */
    public Chevalier(String nom) {
        super(nom);
    }

    @Override
    public void attaquer() {
        System.out.println("Le Chevalier donne un coup d'épée (Dégâts élevés).");
    }
}
