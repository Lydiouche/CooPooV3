import java.util.HashMap;
import java.util.Map;

/**
 * La classe Plateau gère la carte du labyrinthe, les limites et
 * la position de toutes les entités Arme, Monstre, Piège et Trésor,
 * selon la grille de l'image (X de -2 à 3, Y de -2 à 4).
 */
public class Plateau {
    
    private final Map<String, Entite> entites;
    
    // Limites du plateau basées sur la seconde image : X de -2 à 3, Y de -2 à 4
    private static final int MIN_X = -2;
    private static final int MAX_X = 3;
    private static final int MIN_Y = -2;
    private static final int MAX_Y = 4;
    private static final int START_X = 0; 
    private static final int START_Y = 0; 

    public Plateau() {
        this.entites = new HashMap<>();
        initialiserEntites();
    }

    private void initialiserEntites() {
        // NOTE: Les coordonnées utilisées ici sont celles corrigées pour correspondre à l'image.

        // --- Monstres ---
        entites.put(getKey(1, -1), new Squelette());       // SQUELETTE (1, -1)
        entites.put(getKey(0, 1), new Sirene());           // SIRENE (0, 1)
        entites.put(getKey(2, 0), new Dragon());           // DRAGON (2, 0)
        entites.put(getKey(1, 3), new Chien_enfer());      // SERBER (1, 3)

        // --- Armes ---
        entites.put(getKey(0, -1), new Epee());            // EPEE (0, -1)
        entites.put(getKey(-1, 0), new Arc());             // ARC (-1, 0)
        entites.put(getKey(2, -1), new Baguette());        // BAGUETTE (2, -1)
        entites.put(getKey(0, 3), new Baton());            // BATON (0, 3)
        
        // La fourche (0,0) est omise car c'est la case de départ.

        // --- Cases Spéciales ---
        entites.put(getKey(3, 0), new Entite("Tresor", 3, 0) {}); // TRESOR (3, 0)
    }
    
    /**
     * Vérifie si une case est un Piège.
     * Inclut les pièges de bordure et les cases "vides" du labyrinthe.
     */
    public boolean estPiege(int x, int y) {
        // 1. Si le mouvement est en dehors de la grille visible, c'est un piège.
        if (x < MIN_X || x > MAX_X || y < MIN_Y || y > MAX_Y) {
            return true;
        }

        // 2. La case de départ (0, 0) n'est pas un piège.
        if (x == START_X && y == START_Y) return false; 
        
        // 3. Si la case contient déjà une entité spéciale (Arme, Monstre, Trésor), ce n'est pas un piège.
        if (getEntite(x, y) != null) return false;
        
        // 4. Si la case n'est pas la case de départ et ne contient pas d'entité, c'est un piège 
        //    (selon la logique que toutes les cases "vides" sont des pièges).
        
        // Pour être plus précis et suivre la grille de l'image (si on considère les cases blanches comme sûres) :
        // La seule case blanche et vide est (1, 0).
        if (x == 1 && y == 0) return false; // Case Sûre explicite
        
        // Toutes les autres sont des pièges.
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