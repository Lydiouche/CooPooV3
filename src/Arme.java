/** Classe fille de Entite.
 * Elle gère les positions et les noms des armes qui seront ses classes filles.
 *
 * @author Madjeneba DRAME
 * @version 1.0 */
public abstract class Arme extends Entite {

    /** Constructeur principal de l'Arme.
     * Initialise l'Arme avec son nom et les coordonnées
     */
    public Arme(String name, int x, int y) {
        super(name, x, y);
    }

    /** Permet de retourner le nom de l'arme
     * @return le nom sous forme de caractères
     */
    public String getNomA() {
        return super.getName();
    }
}
