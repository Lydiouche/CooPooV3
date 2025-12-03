public class Squelette extends Monstre {

    private Archer faiblesse ;

    public Squelette() {
        super("Squelette", 1, 2);
    }

    public String criDeGuerre() {
        return "Clac clac clac... (Bruit d'os)";
    }
}