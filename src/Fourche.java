import java.util.Scanner;

public class Fourche extends Arme {

    public Fourche() {
        super("Fourche", 0, 0);
    }

    @Override
    public void enigmeArme() {
        System.out.println("Pour récupérer l'arme, résolvez cette énigme :");
        System.out.println("Quel outil à dents sert à retourner la paille ou le foin ?");
        System.out.println("1. Fourche");
        System.out.println("2. Pelle");
        System.out.println("3. Hache");

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
            System.out.println("Correct ! Vous avez récupéré la fourche.");
            this.estRecuperee = true;
        } else {
            System.out.println("Incorrect. Vous ne récupérez pas l'arme.");
            this.estRecuperee = false;
        }
    }
}