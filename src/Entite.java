/** Classe mère Entite.
 * Elle gère les positions et les noms de toutes les entités.
 *
 * @author Madjeneba DRAME, Alexis BOULBES, Lydia LEFEBVRE, Cléo THURY
 * @version 1.0 */

public abstract class Entite{

    /** integer pour la position x */
    protected int x ;

    /** integer pour la position y */
    protected int y ;

    /** chaîne de caractère pour le nom */
    protected String name ;

    /** Constructeur par défaut de l'Entité.
     */
    public Entite(){
        x = 0;
        y = 0;
    }

    /** Constructeur principal de l'Entité.
     *
     * @param name Le nom de l'entité (ex: "Arme")
     */
    public Entite(String name){
        this.name = name;
        x = 0;
        y = 0;
    }

    /** Constructeur principal de l'Entité.
     *
     * @param name Le nom de l'entité (ex: "Arme")
     * @param x La position x de l'entité
     * @param y La position y de l'entité
     */
    public Entite(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /** Permet de retourner la position x
     * @return un integer */
    public int getX() {
        return x;
    }

    /** Permet de retourner la position y
     * @return un integer */
    public int getY() {
        return y;
    }

    /** Permet de retourner le nom
     * @return une chaîne de caractères*/
    public String getName() {
        return name;
    }

    /** Modifie les coordonnées X de l'Entité. */
    public void setX(int x) {
        this.x = x;
    }

    /** Modifie les coordonnées Y de l'Entité. */
    public void setY(int y) {
        this.y = y;
    }
}
