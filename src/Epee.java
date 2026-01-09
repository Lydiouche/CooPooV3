import java.util.Scanner;

public class Epee extends Arme {

    public Epee() {
        super("Epée", 0, 1);
    }

    @Override
    public void enigmeArme() {
        System.out.println("Pour récupérer l'arme, résolvez cette énigme :");
        System.out.println("Quelle épée légendaire a été reforgée pour Aragorn (Andúril) ?");
        System.out.println("1. Aragorn");
        System.out.println("2. Frodo");
        System.out.println("3. Gandalf");

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
            System.out.println("Correct ! Vous avez récupéré l'épée.");
            this.estRecuperee = true;
        } else {
            System.out.println("Incorrect. Vous ne récupérez pas l'arme.");
            this.estRecuperee = false;
        }
    }
}