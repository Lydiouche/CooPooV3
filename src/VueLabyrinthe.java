import java.util.Scanner;

public class VueLabyrinthe {
    
    private Plateau plateau;
    
    // Position de départ selon l'image (Start(X=0; Y=0))
    private static final int START_X = 0; 
    private static final int START_Y = 0; 
    
    // Déclaration du Scanner pour la boucle de jeu
    private Scanner scanner = new Scanner(System.in);

    // Correction de la signature pour prendre le joueur en paramètre
    public void jouer(Personne joueur) {
        this.plateau = new Plateau(); 
        String input = "";
        
        // Initialisation du joueur à la position de départ
        joueur.setX(START_X); 
        joueur.setY(START_Y);
        joueur.setVie(true); 

        System.out.println("====================================================");
        System.out.println("           Bienvenue dans le Labyrinthe !           ");
        System.out.println("Objectif : Atteindre le TRESOR (3, 0). Attention aux PIÈGES et aux monstres.");
        System.out.println("Commandes : A (Avancer), F (fuir), C (combat), Q (Quitter).");
        System.out.println("====================================================");

        // La boucle utilise le getter de vie
        while(joueur.getVie()){
            System.out.println("\n----------------------------------------------------");
            System.out.println("Position actuelle: (X=" + joueur.getX() + ", Y=" + joueur.getY() + ")");
            System.out.println("Classe: " + joueur.getName());
            
            // Logique de Monstre en cours (à définir)
            Monstre monstreSurCase = getMonstreSurCase(joueur.getX(), joueur.getY());

            if (monstreSurCase != null) {
                // Si un monstre est sur la case, l'interaction est forcée
                System.out.println("🚨 Le monstre est : " + monstreSurCase.getName() + " ! " + monstreSurCase.criDeGuerre());
                System.out.print("Entrez une action (F/C/Q) : ");
                input = scanner.nextLine().trim().toUpperCase();
                
                if (input.equals("Q")) {
                    joueur.setVie(false);
                    System.out.println("Partie terminée.");
                    break;
                } else if (input.equals("F")) {
                    gererFuir(joueur);
                } else if (input.equals("C")) {
                    gererCombat(joueur, monstreSurCase);
                } else {
                    System.out.println("Commande invalide. Veuillez réessayer.");
                }
            } else {
                // Pas de monstre, phase de déplacement
                System.out.print("Entrez votre action (A/Q) : ");
                input = scanner.nextLine().trim().toUpperCase();
                
                if (input.equals("Q")) {
                    joueur.setVie(false);
                    System.out.println("Partie terminée.");
                    break;
                } else if (input.equals("A")) {
                    gererDeplacement(joueur);
                } else {
                    System.out.println("Commande invalide. Veuillez réessayer.");
                }
            }
        }
        scanner.close();
    }
    
    private void gererDeplacement(Personne joueur) {
        int oldX = joueur.getX();
        int oldY = joueur.getY();
        
        System.out.print(">> Entrer votre direction (1 Gauche, 2 Droite, 3 Bas, 4 Haut) : ");
        
        if (!scanner.hasNextInt()) {
            System.out.println("Saisie invalide. Mouvement annulé.");
            scanner.nextLine();
            return;
        }

        int direction = scanner.nextInt();
        scanner.nextLine(); // Consommer le newline

        int nextX = oldX;
        int nextY = oldY;

        // Calcul de la prochaine position
        if (direction == 1) { nextX--; }
        else if (direction == 2) { nextX++; }
        else if (direction == 3) { nextY++; }
        else if (direction == 4) { nextY--; }
        else {
            System.out.println("Direction invalide. Mouvement annulé.");
            return;
        }

        // Déplacement effectif (même si piège/monstre, l'interaction se fait sur cette case)
        joueur.setX(nextX);
        joueur.setY(nextY);
        System.out.println(joueur.getName() + " se déplace vers la case [" + nextX + "," + nextY + "]");

        // --- INTERACTION ---
        Entite entiteSurCase = plateau.getEntite(nextX, nextY);

        if (entiteSurCase != null) {
            gererInteraction(joueur, entiteSurCase);
        } else if (plateau.estPiege(nextX, nextY)) {
            // C'est un piège
            System.out.println("💀 Vous êtes tombé sur un Piège ou sorti du labyrinthe ! GAME OVER !");
            joueur.setX(START_X); 
            joueur.setY(START_Y);
            System.out.println("Vous recommencez au point de départ (" + START_X + ", " + START_Y + ").");
        } else {
            System.out.println("Case sûre.");
        }
    }
    
    private Monstre getMonstreSurCase(int x, int y) {
        Entite entite = plateau.getEntite(x, y);
        if (entite instanceof Monstre) {
            return (Monstre) entite;
        }
        return null;
    }

    private void gererFuir(Personne joueur) {
        // En cas de fuite, le joueur est renvoyé au départ (simplification)
        System.out.println("🏃 Vous fuyez le combat ! Vous êtes renvoyé à la case de départ.");
        joueur.setX(START_X);
        joueur.setY(START_Y);
    }

    private void gererCombat(Personne joueur, Monstre monstre) {
        // Logique de combat simplifiée
        joueur.attaquer();
        System.out.println("Vous attaquez le " + monstre.getName() + " !");
        
        // Simuler le résultat: on tue le monstre et on gagne la case
        System.out.println(monstre.getName() + " a été vaincu !");
        plateau.removeEntite(monstre.getX(), monstre.getY());
    }

    private void gererInteraction(Personne joueur, Entite entite) {
        
        if (entite instanceof Monstre) {
            // Ne devrait pas arriver ici si la logique de combat est au-dessus,
            // mais sert de filet de sécurité.
            System.out.println("🚨 Monstre détecté. Commencez le combat ou fuyez.");
            
        } else if (entite instanceof Arme) {
            Arme arme = (Arme) entite;
            System.out.println("🎁 Vous trouvez l'arme : " + arme.getNomA() + ".");
            
            // Logique de changement de classe si c'est l'Épée
            if (arme instanceof Epee) {
                // Dans un code plus complexe, on changerait la classe du joueur ici.
                System.out.println("Vous ramassez l'Épée ! Vous devenez un Chevalier !");
            }
            plateau.removeEntite(entite.getX(), entite.getY()); 
        
        } else if (entite.getName().equals("Tresor")) {
            System.out.println("🏆 Vous avez trouvé le Trésor à (3, 0) ! Vous avez gagné !");
            joueur.setVie(false); // Termine la partie
            
        } else {
            System.out.println("Interaction avec une entité spéciale non gérée : " + entite.getName());
        }
    }
}