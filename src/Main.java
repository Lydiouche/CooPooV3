import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choix = "";
        boolean saisieValide = false;
        do{
            System.out.print("Veuillez votre nom :");
        try {
            choix = scanner.nextLine();
            if (choix != null && !choix.isEmpty()) {
                saisieValide = true;
            } else {
                System.out.println("Vous avez fait une erreur de saisie : Le nom ne peut pas être vide.");
            }
        }catch(Exception e) {
            System.out.println("Erreur");
            scanner.next();
            }
        } while (!saisieValide);

        String nomJoueur = choix;

        Paysan joueur = new Paysan(nomJoueur);

        VueLabyrinthe vue = new VueLabyrinthe();

        vue.jouer(joueur);
    }
}
