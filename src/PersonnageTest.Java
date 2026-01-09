import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class PersonnageTest {

    // --- TEST 1 : HÉRITAGE ET CRÉATION (Mage) ---
    @Test
    public void testCreationMage() {
        Mage gandalf = new Mage("Gandalf");

        // Vérifie que l'héritage fonctionne (Mage -> Personne -> Entite)
        assertEquals("Gandalf", gandalf.getName());
        assertEquals(0, gandalf.getX(), "Par défaut, X doit être 0");
        assertEquals(0, gandalf.getY(), "Par défaut, Y doit être 0");
    }

    // --- TEST 2 : CONSTRUCTEUR AVEC PARAMÈTRES (Sorcier) ---
    @Test
    public void testSorcierCoordonnees() {
        Sorcier harry = new Sorcier("Harry", 5, 10);

        assertEquals("Harry", harry.getName());
        assertEquals(5, harry.getX(), "Le X du sorcier n'est pas bon (Vérifie ton super())");
        assertEquals(10, harry.getY(), "Le Y du sorcier n'est pas bon");
    }

    // --- TEST 3 : POLYMORPHISME (Tableau de Personnes) ---
    @Test
    public void testPolymorphisme() {
        // On vérifie qu'on peut mettre tout le monde dans un tableau de Personne
        Personne[] groupe = new Personne[3];
        groupe[0] = new Chevalier("Arthur");
        groupe[1] = new Archer("Robin");
        groupe[2] = new Paysan("Jacque");

        assertEquals("Arthur", groupe[0].getName());
        // Vérifie que la méthode attaquer ne plante pas
        assertDoesNotThrow(() -> groupe[0].attaquer());
        assertDoesNotThrow(() -> groupe[1].attaquer());
        assertDoesNotThrow(() -> groupe[2].attaquer()); // Le paysan ne fait rien, mais ne doit pas planter
    }

    // --- TEST 4 : VIE (Getter/Setter) ---
    @Test
    public void testVie() {
        Paysan p = new Paysan("Testeur");
        // Par défaut c'est null (Boolean objet), attention aux NullPointerException
        assertNull(p.getVie());

        p.setVie(true);
        assertTrue(p.getVie(), "La vie devrait être true");

        p.setVie(false);
        assertFalse(p.getVie(), "La vie devrait être false");
    }


    }
