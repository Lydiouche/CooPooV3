import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArmeTest {

    // --- TEST 1 : L'ARC ---
    @Test
    public void testArc() {
        Arc arc = new Arc();

        assertEquals("Arc", arc.getNomA(), "Le nom doit être 'Arc'");

        assertEquals(-1, arc.getX(), "L'Arc doit être en X = -1");
        assertEquals(-1, arc.getY(), "L'Arc doit être en Y = -1");
    }

    // --- TEST 2 : LA BAGUETTE ---
    @Test
    public void testBaguette() {
        Baguette baguette = new Baguette();

        assertEquals("Baguette", baguette.getNomA());
        assertEquals(2, baguette.getX());
        assertEquals(1, baguette.getY());
    }

    // --- TEST 3 : LE BÂTON ---
    @Test
    public void testBaton() {
        Baton baton = new Baton();


        assertEquals("Bâton", baton.getNomA(), "Attention à l'accent sur Bâton");
        assertEquals(0, baton.getX());
        assertEquals(-3, baton.getY());
    }

    // --- TEST 4 : L'ÉPÉE ---
    @Test
    public void testEpee() {
        Epee epee = new Epee();

        assertEquals("Epée", epee.getNomA());
        assertEquals(0, epee.getX());
        assertEquals(1, epee.getY());
    }

    // --- TEST 5 : LA FOURCHE ---
    @Test
    public void testFourche() {
        Fourche fourche = new Fourche();

        assertEquals("Fourche", fourche.getNomA());
        assertEquals(0, fourche.getX());
        assertEquals(0, fourche.getY());
    }

    // --- TEST 6 : HÉRITAGE ET POLYMORPHISME ---
    // On vérifie que toutes ces classes sont bien des "Armes" et des "Entités"
    @Test
    public void testHeritage() {
        Arme a1 = new Epee();
        Arme a2 = new Arc();


        assertNotNull(a1);
        assertTrue(a2.getNomA().length() > 0, "L'arme doit avoir un nom");
    }
}