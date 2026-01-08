import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MonstreTest {

    // --- TEST DU SQUELETTE ---
    @Test
    public void testSquelette() {

        Squelette skel = new Squelette();

        assertEquals(1, skel.getX(), "Le Squelette devrait être en X=1");
        assertEquals(1, skel.getY(), "Le Squelette devrait être en Y=1");


        assertEquals("Squelette", skel.getName(), "Le nom doit être 'Squelette'");
        assertEquals("Clac clac clac... (Bruit d'os)", skel.criDeGuerre());
    }



    // --- TEST DE LA SIRENE ---
    @Test
    public void testSirene() {
        Sirene sirene = new Sirene();

        //Vérification de la position
        assertEquals(0, sirene.getX(), "La Sirène devrait être en X=0");
        assertEquals(-1, sirene.getY(), "La Sirène devrait être en Y=-1");

        assertEquals("Sirene", sirene.getName());
        assertNotNull(sirene.criDeGuerre());
        assertTrue(sirene.criDeGuerre().contains("chant"), "Le cri doit parler de chant");
    }

    // --- TEST DU DRAGON ---
    @Test
    public void testDragon() {
        Dragon dragon = new Dragon();

        // Code fourni: 2, 0
        assertEquals(2, dragon.getX());
        assertEquals(0, dragon.getY());
        assertEquals("Dragon", dragon.getName());
        assertEquals("GROAAAAR ! Je vais te réduire en cendres !", dragon.criDeGuerre());
    }

    // --- TEST DU CHIEN D'ENFER (CERBÈRE) ---
    @Test
    public void testChienEnfer() {
        Chien_enfer cerbere = new Chien_enfer();


        assertEquals(1, cerbere.getX());
        assertEquals(-3, cerbere.getY());

        assertEquals("Cerbère", cerbere.getName(), "Le nom interne doit être Cerbère");

        // Vérification du cri
        assertTrue(cerbere.criDeGuerre().contains("WOOOOUFFF"), "Le cri doit commencer par WOOOOUFFF");
    }

    // --- TEST DE POLYMORPHISME ---
    // On vérifie que tous ces monstres sont bien considérés comme des "Monstre"
    @Test
    public void testEstUnMonstre() {
        Monstre m1 = new Dragon();
        Monstre m2 = new Squelette();


        assertTrue(m1 instanceof Monstre, "Le Dragon doit être considéré comme un Monstre");
        assertTrue(m2 instanceof Monstre, "Le Squelette doit être considéré comme un Monstre");
    }
}