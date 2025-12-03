import java.util.HashMap;
import java.util.Map;

public class Plateau {

    private final Map<String, Entite> entites;

    // NOUVELLES LIMITES: Y s'étend à -3
    private static final int MIN_X = -2;
    private static final int MAX_X = 3;
    private static final int MIN_Y = -3; // CORRIGÉ
    private static final int MAX_Y = 4;
    private static final int START_X = 0;
    private static final int START_Y = 0;

    public Plateau() {
        this.entites = new HashMap<>();
        initialiserEntites();
    }

    private void initialiserEntites() {
        // --- Monstres ---
        entites.put(getKey(1, 1), new Squelette());       // SQUELETTE (1, 1)
        entites.put(getKey(0, -1), new Sirene());           // SIRENE (0, -1)
        entites.put(getKey(2, 0), new Dragon());           // DRAGON (2, 0)
        entites.put(getKey(1, -3), new Chien_enfer());      // SERBER (1, -3)

        // --- Armes ---
        entites.put(getKey(0, 1), new Epee());            // EPEE (0, -1)
        entites.put(getKey(-1, 1), new Arc());             // ARC (-1, 1)
        entites.put(getKey(2, -1), new Baguette());        // BAGUETTE (2, -1)
        entites.put(getKey(0, -3), new Baton());           // BATON (0, -3) CORRIGÉ

        // --- Cases Spéciales ---
        entites.put(getKey(3, 0), new Entite("Tresor", 3, 0) {}); // TRESOR (3, 0)
    }

    /**
     * Vérifie si une case est un Piège (inclut les bords et les zones grises).
     */
    public boolean estPiege(int x, int y) {
        // 1. Piège de bordure
        if (x < MIN_X || x > MAX_X || y < MIN_Y || y > MAX_Y) {
            return true;
        }

        // 2. Case de départ et Entités ne sont pas des pièges
        if (x == START_X && y == START_Y) return false;
        if (getEntite(x, y) != null) return false;

        // 3. Identification des CASES SURES VIDES (Chemins blancs ou anciennes entités)

        // Toutes les coordonnées qui contenaient une entité sont sûres une fois l'entité retirée.
        // Coordonnées Sûres (Chemin blanc ou ancienne position d'entité) :
        if ((x == 1 && y == 0) ||   // Case Sûre explicite
                (x == 0 && y == -1) ||  // Epee / Maintenant sûre
                (x == 2 && y == -1) ||  // Baguette / Maintenant sûre
                (x == 1 && y == -1) ||  // Squelette / Maintenant sûre
                (x == 0 && y == 1) ||   // Sirene / Maintenant sûre
                (x == -1 && y == 1) ||  // Arc / Maintenant sûre
                (x == 0 && y == 3) ||   // Baton (ancienne pos)
                (x == 1 && y == 3) ||   // Serber / Maintenant sûre
                (x == 0 && y == -3)     // Baton (nouvelle pos) / Maintenant sûre
        ) {
            return false;
        }

        // 4. Toutes les autres cases sont des Pièges (y compris les cases grises Y=-3 non répertoriées).
        return true;
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