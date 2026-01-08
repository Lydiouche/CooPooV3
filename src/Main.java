public class Main {

    public static void main(String[] args) {
        String nomJoueur = "Héros Inconnu";

        Paysan joueur = new Paysan(nomJoueur);

        VueLabyrinthe vue = new VueLabyrinthe();

        vue.jouer(joueur);
    }
}