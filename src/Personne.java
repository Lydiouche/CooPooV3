public abstract class Personne {
    protected String nom;
    protected Coordonnees position;
    protected Boolean vie;   // True = Vivant, False = Mort

    // Constructor 1 : Création (Début du jeu)
    public Personne(String nom) {
        this.nom = nom;
        this.position = new Coordonnees (0,0);
        this.vie = true;
    }
    // Getter
    public String getNom() {
        return nom;
    }
    public Coordonnees getPosition() {
        return position;
    }
    public Boolean getVie(){
        return(vie);
    }
    // Setter
    public void setNom(String nom) { this.nom = nom; }

    public void setPosition(Coordonnees position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }
    public void setVie(Boolean vie) {
        this.vie = vie;
    }

    // MÉTHODE DE DÉPLACEMENT
    // 1: Gauche | 2: Droite | 3: Bas | 4: Haut
    public void seDeplacer(int choix) {

        int xActuel = this.position.getX();
        int yActuel = this.position.getY();

        if (choix == 1) {      // GAUCHE (X-1)
            xActuel = xActuel - 1;
        } else if (choix == 2) { // DROITE (X+1)
            xActuel = xActuel + 1;
        } else if (choix == 3) { // BAS (Y+1)
            yActuel = yActuel + 1;
        } else if (choix == 4) { // HAUT (Y-1)
            yActuel = yActuel - 1;
        }
        this.position.setX(xActuel);
        this.position.setY(yActuel);
        System.out.println(this.nom + " se déplace vers " +
                " -> Case [" + xActuel + "," + yActuel + "]");
    }

    public abstract void attaquer();
}
