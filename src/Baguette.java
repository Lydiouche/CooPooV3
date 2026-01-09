import java.util.Scanner;

public class Baguette extends Arme {

    public Baguette() {
        super("Baguette", 2, 1);
    }

    @Override
    public void enigmeArme() {
        System.out.println("Pour récupérer l'arme, résolvez cette énigme :");
        System.out.println("Dans quel univers retrouve-t-on la célèbre baguette de Sureau ?");
        System.out.println("1. Harry Potter");
        System.out.println("2. Le Seigneur des Anneaux");
        System.out.println("3. Narnia");

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
            System.out.println("Correct ! Vous avez récupéré la baguette.");
            this.estRecuperee = true;
        } else {
            System.out.println("Incorrect. Vous ne récupérez pas l'arme.");
            this.estRecuperee = false;
        }
    }
}