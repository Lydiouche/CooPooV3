import java.util.Scanner;

public class Arc extends Arme {

    public Arc() {
        super("Arc", -1, -1);
    }

    @Override
    public void enigmeArme() {
        System.out.println("Pour récupérer l'arme, résolvez cette énigme :");
        System.out.println("Quelle jeu se deroule dans un monde préhistorique avec des dinosaures ?");
        System.out.println("1. Ark : Survival Evolved");
        System.out.println("2. Minecraft");
        System.out.println("3. Skyrim");

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
            System.out.println("Correct ! Vous avez récupéré l'arc (Ark).");
            this.estRecuperee = true;
        } else {
            System.out.println("Incorrect. Essayez à nouveau plus tard.");
            this.estRecuperee = false;
        }
    }
}