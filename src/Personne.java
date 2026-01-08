import java.util.Scanner;

public  abstract class Personne extends Entite {
    private Boolean vie;

    public Personne(String name){
        super(name);}

    public Boolean getVie(){
        return(vie);
    }
    public void setVie(Boolean vie) {
        this.vie = vie;
    }

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

    public abstract void attaquer();
}
