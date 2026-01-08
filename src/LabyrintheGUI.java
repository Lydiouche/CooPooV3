import javax.swing.*;
import java.awt.*;

    public class LabyrintheGUI extends JFrame {
        private Plateau plateau;
        private Personne joueur;
        private JPanel[][] casesPhysiques;

        // Définition des limites basées sur le code de Plateau
        private final int MIN_X = -2;
        private final int MAX_X = 3;
        private final int MIN_Y = -3;
        private final int MAX_Y = 4;

        public LabyrintheGUI(Plateau plateau, Personne joueur) {
            this.plateau = plateau;
            this.joueur = joueur;
            int nbColonnes = MAX_X - MIN_X + 1; // 3 - (-2) + 1 = 6
            int nbLignes = MAX_Y - MIN_Y + 1;   // 4 - (-3) + 1 = 8
            this.casesPhysiques = new JPanel[nbColonnes][nbLignes];
            setTitle("Aperçu du Labyrinthe");
            setSize(500, 600);
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            JPanel grillePanel = new JPanel(new GridLayout(nbLignes, nbColonnes));

            for (int y = MAX_Y; y >= MIN_Y; y--) {
                for (int x = MIN_X; x <= MAX_X; x++) {
                    JPanel p = new JPanel(new BorderLayout());
                    p.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

                    casesPhysiques[x - MIN_X][y - MIN_Y] = p;
                    grillePanel.add(p);
                }
            }
            add(grillePanel);
            actualiser();
            setVisible(true);
        }

        public void actualiser() {
            for (int y = MAX_Y - 1; y >= MIN_Y; y--) {
                for (int x = MIN_X; x <= MAX_X; x++) {
                    JPanel p = casesPhysiques[x - MIN_X][y - MIN_Y];
                    p.removeAll();
                    Entite e = plateau.getEntite(x, y);

                    if (x == joueur.getX() && y == joueur.getY()) {
                        p.setBackground(Color.CYAN);
                        p.add(new JLabel("👤", SwingConstants.CENTER));
                    } else if (e instanceof Monstre) {
                        p.setBackground(new Color(255, 200, 200));
                        p.add(new JLabel("👾", SwingConstants.CENTER));
                    } else if (e instanceof Arme) {
                        p.setBackground(new Color(255, 255, 200));
                        p.add(new JLabel("⚔️", SwingConstants.CENTER));
                    } else if (e != null && e.getName().equals("Tresor")) {
                        p.setBackground(Color.ORANGE);
                        p.add(new JLabel("💎", SwingConstants.CENTER));
                    } else if (plateau.estPiege(x, y)) {
                        p.setBackground(Color.DARK_GRAY);
                    } else {
                        p.setBackground(Color.WHITE);
                    }
                }
            }
            // Force le rafraîchissement visuel proprement
            getContentPane().validate();
            getContentPane().repaint();
        }
        public void setJoueur(Personne nouveauJoueur) {
            this.joueur = nouveauJoueur;
        }
    }