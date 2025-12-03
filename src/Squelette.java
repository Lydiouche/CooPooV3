public class Squelette extends Monstre {

    private Chevalier faiblesse ;

    public Squelette() {
        super("Squelette", 1, 2);
    }

    public String criDeGuerre() {
        return "Clac clac clac... (Bruit d'os)";
    }
}