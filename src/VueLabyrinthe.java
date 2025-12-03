public class VueLabyrinthe {
public void jouer() {
    System.out.println("====================================================");
        System.out.println("           Bienvenue dans le Labyrinthe !           ");
        System.out.println("Objectif : Atteindre le TRESOR (3,0) sans tomber dans un Piège et attention au monstre .");
        System.out.println("Commandes : A (Avancer), F (fuire), C (combat), Q (Quitter).");
        System.out.println("====================================================");


}
while(vie){
      System.out.println("\n----------------------------------------------------");
            System.out.println("Position actuelle: (X=" + ?.getX() + ", Y=" + ?.getY() + ")");
            System.out.print("Entrez votre mouvement (A/Q) : ");

if (input.equals("Q")) {
                vie = false;
                System.out.println("Partie terminée.");
                break;
            }
if (){
    System.out.println("Le monstre est : Monstre.getNom()  ");
    System.out.print("Entrez une action (F/C) : ");

    if (input.equals("A")) {
                ?.avancer();
            } else if (input.equals("F")) {
                ?.fuir();
            } else if (input.equals("C")) {
                ?.combattre();
            } else {
                System.out.println("Commande invalide. Veuillez réessayer.");
            }

        }
    }
}