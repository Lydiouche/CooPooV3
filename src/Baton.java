import java.util.Scanner;

/** Classe fille de Arme.
 * Elle gère la position et le nom du Baton.
 *
 * @author Madjeneba DRAME
 * @version 1.0 */

public class Baton extends Arme {

    /** Constructeur principal du Baton.
     * Initialise le Baton avec son nom et les coordonnées par défaut 0;-3
     */
    public Baton() {
        super("Bâton", 0, -3);
    }

    @Override
    public void enigmeArme() {
        System.out.println("Pour récupérer l'arme, résolvez cette énigme :");
        System.out.println("Quel objet utilise un chef d'orchestre pour diriger ?");
        System.out.println("1. Une baguette");
        System.out.println("2. Une trompette");
        System.out.println("3. Une guitare");

        Scanner scanner = new Scanner(System.in);
        int reponse = -1;
        while (true) {
            System.out.print("Votre choix (1-3) : ");
            if (!scanner.hasNextInt()) {
                System.out.println("Entrée invalide. Entrez un nombre.");
                scanner.next();
                continue;
            }
            reponse = scanner.nextInt();
            if (reponse >= 1 && reponse <= 3) break;
            System.out.println("Choix hors plage. Réessayez.");
        }

        if (reponse == 1) {
            System.out.println("Correct ! Vous avez récupéré le bâton.");
            this.estRecuperee = true;
        } else {
            System.out.println("Incorrect. Vous ne récupérez pas l'arme.");
            this.estRecuperee = false;
        }
    }
}