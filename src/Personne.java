import java.util.Scanner;

public  abstract class Personne extends Entite {
    private Boolean vie;
    // Constructor 1 : Création (Début du jeu)
    public Personne(String name){
        super(name);}
    // Getter
    public Boolean getVie(){
        return(vie);
    }
    public void setVie(Boolean vie) {
        this.vie = vie;
    }
    // MÉTHODE DE DÉPLACEMENT
    // 1: Gauche | 2: Droite | 3: Bas | 4: Haut
    public void seDeplacer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(">> Entrer votre choix (1 Gauche, 2 Droite, 3 Bas, 4 Haut : ");
        int choix = scanner.nextInt();

        int xActuel = this.getX();
        int yActuel = this.getY();

        if (choix == 1) {      // GAUCHE (X-1)
            xActuel = xActuel - 1;
        } else if (choix == 2) { // DROITE (X+1)
            xActuel = xActuel + 1;
        } else if (choix == 3) { // BAS (Y+1)
            yActuel = yActuel + 1;
        } else if (choix == 4) { // HAUT (Y-1)
            yActuel = yActuel - 1;
        }
        System.out.println(getName() + " se déplace vers " +
                " la case [" + xActuel + "," + yActuel + "]");
    }

    public abstract void attaquer();
}
