public class Main {

    public static void main(String[] args) {
        String nomJoueur = "Clément";

        Paysan joueur = new Paysan(nomJoueur);

        VueLabyrinthe vue = new VueLabyrinthe();

        vue.jouer(joueur);
    }
}
