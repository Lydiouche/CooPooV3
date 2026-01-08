import java.util.HashMap;
import java.util.Map;

public class Plateau {

    private final Map<String, Entite> entites;

    private static final int MIN_X = -2;
    private static final int MAX_X = 3;
    private static final int MIN_Y = -3; 
    private static final int MAX_Y = 4;
    private static final int START_X = 0;
    private static final int START_Y = 0;

    public Plateau() {
        this.entites = new HashMap<>();
        initialiserEntites();
    }

    private void initialiserEntites() {
        entites.put(getKey(1, 1), new Squelette());
        entites.put(getKey(0, -1), new Sirene());
        entites.put(getKey(2, 0), new Dragon());
        entites.put(getKey(1, -3), new Chien_enfer());

        entites.put(getKey(0, 1), new Epee());
        entites.put(getKey(-1, -1), new Arc());
        entites.put(getKey(2, 1), new Baguette());
        entites.put(getKey(0, -3), new Baton());

        entites.put(getKey(3, 0), new Entite("Tresor", 3, 0) {});
    }

    /**
     * Vérifie si une case est un Piège (inclut les bords et les zones grises).
     */
    public boolean estPiege(int x, int y) {
        if (x < MIN_X || x > MAX_X || y < MIN_Y || y > MAX_Y) {
            return true;
        }

        if (x == START_X && y == START_Y) return false;
        if (getEntite(x, y) != null) return false;

        if ((x == -2 && y == -1) ||
                (x == -1 && y == 1) ||
                (x == -1 && y == 0) ||
                (x == -1 && y == -2) ||
                (x == -1 && y == -3) ||
                (x == 0 && y == 2) ||
                (x == 0 && y == -2) ||
                (x == 0 && y == -4) ||
                (x == 1 && y == 2)   ||
                (x == 1 && y == -4)||
                (x == 2 && y == 2)||
                (x == 2 && y == -1)||
                (x == 2 && y == -2)||
                (x == 2 && y == -3)||
                (x == 3 && y == 1)
        ) {
            return true;
        }

        return false;
    }

    public Entite getEntite(int x, int y) {
        return entites.get(getKey(x, y));
    }

    public void removeEntite(int x, int y) {
        entites.remove(getKey(x, y));
    }

    private String getKey(int x, int y) {
        return x + "," + y;
    }
}