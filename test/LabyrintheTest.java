import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LabyrintheTest {

    @Test
    public void testInitialisationCarte() {
        Labyrinthe laby = new Labyrinthe();
        Case caseVide = laby.getCase(0, 1);
        assertNotNull(caseVide, "La case (0,1) doit exister");
        assertEquals("Vide", caseVide.getContenu(), "La case (0,1) devrait être vide");


        Case caseEpee = laby.getCase(0, -2);
        assertNotNull(caseEpee.getObjet(), "Il doit y avoir un objet sur la case (0,-2)");
        assertEquals("Epee", caseEpee.getObjet().getName(), "L'objet en (0,-2) doit être l'épée");
    }

    @Test
    public void testLimitesCarte() {
        Labyrinthe laby = new Labyrinthe();
        
        Case caseInconnue = laby.getCase(10, 10);
        assertNull(caseInconnue, "Une case hors carte doit être null");
    }
}