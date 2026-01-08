import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 



public class EpeeTest {

    @Test
    public void testCreationEpee() {

        Epee monEpee = new Epee();

        assertEquals("Epee", monEpee.getNomA(), "Le nom de l'arme doit etre Epee");

        assertEquals(0, monEpee.getX(), "L'épée doit etre en X=0");
        assertEquals(-2, monEpee.getY(), "L'épée doit etre en Y=-2");
    }
}