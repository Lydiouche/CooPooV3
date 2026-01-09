/** Classe fille de Monstre.
 * Elle gère la position et le nom du Chien des enfers.
 *
 * @author Cléo THURY
 * @version 1.0 */

public class Chien_enfer extends Monstre {

    /** faiblesse du chien des enfers.
     * De type Sorcier
     */
    private Sorcier faiblesse ;

    /** Constructeur principal du Chien des enfers.
     * Initialise le Chien des enfers avec son nom "Cerbère" et sa position 1;-3
     */
    public Chien_enfer() {
        super("Cerbère", 1, -3);
    }

    /** Permet de retourner le cri de guerre du Cerbère
     * @return le nom sous forme de caractères
     */
    public String criDeGuerre() {
        return "WOOOOUFFF ! Je déchire les âmes comme la chair !";
    }
}
