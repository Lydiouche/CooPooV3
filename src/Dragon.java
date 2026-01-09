/** Classe fille de Monstre.
 * Elle gère la position et le nom du Dragon.
 *
 * @author Cléo THURY, Lydia LEFEBVRE
 * @version 1.0 */

public class Dragon extends Monstre {

    /** faiblesse du Dragon.
     * De type Archer
     */
    private Archer faiblesse ;

    /** Constructeur principal du Dragon.
     * Initialise le Dragon avec son nom et sa position 2;0
     */
    public Dragon() {
        super("Dragon", 2, 0);
    }

    /** Permet de retourner le cri de guerre du Dragon
     * @return le nom sous forme de caractères
     */
    public String criDeGuerre() {
        return "GROAAAAR ! Je vais te réduire en cendres !";
    }

}
