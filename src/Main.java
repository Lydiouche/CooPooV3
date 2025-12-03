public class Main {

    public static void main(String[] args) {
        // 1. Définition du nom du joueur
        String nomJoueur = "Héros Inconnu";

        // 2. Création du personnage de départ (Paysan)
        Paysan joueur = new Paysan(nomJoueur);

        // 3. Création et lancement de la Vue/Logique de jeu
        VueLabyrinthe vue = new VueLabyrinthe();

        // Lancement de la boucle de jeu
        vue.jouer(joueur);
    }
}