import java.util.Scanner;

/// Classe abstraite représentant tous les personnages du jeu (Joueur).
/// Elle gère la vie et les déplacements.
///
/// @author Madjeneba DRAME, Cléo THURY, Lydia LEFEBVRE
/// @version 1.0
public  abstract class Personne extends Entite {
    /// Booléen à True pour montrer que le personnage est vivant
    private Boolean vie;

    /// Constructeur principal du Personnage.
    ///
    /// @param name Le nom du personnage (ex: "Gandalf")
    public Personne(String name){
        super(name);}

    public Boolean getVie(){
        return(vie);
    }
    public void setVie(Boolean vie) {
        this.vie = vie;
    }


    /// Permet au personnage de se déplacer via une saisie clavier.
    /// Modifie les coordonnées X et Y de l'Entité.
    public void seDeplacer() {
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        boolean saisieValide = false;
        do{
            System.out.print(">> Entrer votre choix (1 Gauche, 2 Droite, 3 Bas, 4 Haut : ");
        try {
            choix = scanner.nextInt();
            if (choix > 0 && choix < 5) {
                saisieValide = true;
            } else {
                System.out.println("Vous avez fait une erreur de saisie : Le choix doit être entre 1 et 4.");
            }
        }catch(Exception e) {
            System.out.println("Erreur");
            scanner.next();
            }
        } while (!saisieValide);
        int xActuel = this.getX();
        int yActuel = this.getY();

        if (choix == 1) {
            xActuel = xActuel - 1;
        } else if (choix == 2) {
            xActuel = xActuel + 1;
        } else if (choix == 3) {
            yActuel = yActuel + 1;
        } else {
            yActuel = yActuel - 1;
        }

        this.setX(xActuel);
        this.setY(yActuel);

        System.out.println(getName() + " se déplace vers " +
                " la case [" + xActuel + "," + yActuel + "]");
    }


    /// Méthode abstraite d'attaque.
    /// Chaque classe fille (Sorcier, Chevalier) doit définir ses propres dégâts.
    public abstract void attaquer();
}
