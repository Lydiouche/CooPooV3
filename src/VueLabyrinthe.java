import java.util.Scanner;

public class VueLabyrinthe {

    private Plateau plateau;

    private static final int START_X = 0;
    private static final int START_Y = 0;

    private Scanner scanner = new Scanner(System.in);

    public void jouer(Personne joueur) {
        this.plateau = new Plateau();
        String input = "";

        joueur.setX(START_X);
        joueur.setY(START_Y);
        joueur.setVie(true);

        System.out.println("====================================================");
        System.out.println("           Bienvenue dans le Labyrinthe !           ");
        System.out.println("Objectif : Atteindre le TRESOR (3, 0). Attention aux PIÈGES et aux monstres.");
        System.out.println("Commandes de déplacement: 1 (Gauche/X-1), 2 (Droite/X+1), 3 (Bas/Y-1), 4 (Haut/Y+1).");
        System.out.println("Actions: A (Avancer), F (fuir), C (combat), Q (Quitter).");
        System.out.println("====================================================");

        while(joueur.getVie()){
            System.out.println("\n----------------------------------------------------");
            System.out.println("Position: (X=" + joueur.getX() + ", Y=" + joueur.getY() + ")");
            System.out.println("Classe: " + joueur.getClass().getSimpleName() + " (Nom: " + joueur.getName() + ")");

            Monstre monstreSurCase = getMonstreSurCase(joueur.getX(), joueur.getY());

            if (monstreSurCase != null) {
                System.out.println("🚨 Le monstre est : " + monstreSurCase.getName() + " ! " + monstreSurCase.criDeGuerre());
                System.out.println("Que voulez vous faire F : Fuire , C : Combattre , Q : Quitter");
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
                // Phase de déplacement
                System.out.print("Entrez votre action (A/Q) : ");
                input = scanner.nextLine().trim().toUpperCase();

                if (input.equals("Q")) {
                    joueur.setVie(false);
                    System.out.println("Partie terminée.");
                    break;
                } else if (input.equals("A")) {
                    // L'objet joueur est réassigné au résultat du déplacement (gère le changement de classe)
                    joueur = gererDeplacement(joueur);
                } else {
                    System.out.println("Commande principale invalide.");
                }
            }
        }
        scanner.close();
    }

    private Personne gererDeplacement(Personne joueur) {
        int oldX = joueur.getX();
        int oldY = joueur.getY();

        System.out.print(">> Entrer votre direction (1 Gauche, 2 Droite, 3 Bas, 4 Haut) : ");

        if (!scanner.hasNextInt()) {
            System.out.println("Saisie invalide. Mouvement annulé.");
            scanner.nextLine();
            return joueur;
        }

        int direction = scanner.nextInt();
        scanner.nextLine();

        int nextX = oldX;
        int nextY = oldY;

        if (direction == 1) { nextX--; }
        else if (direction == 2) { nextX++; }
        else if (direction == 3) { nextY--; }
        else if (direction == 4) { nextY++; }
        else {
            System.out.println("Direction invalide. Mouvement annulé.");
            return joueur;
        }

        joueur.setX(nextX);
        joueur.setY(nextY);
        System.out.println(joueur.getName() + " se déplace vers la case [" + nextX + "," + nextY + "]");

        Entite entiteSurCase = plateau.getEntite(nextX, nextY);

        if (entiteSurCase != null) {
            joueur = gererInteraction(joueur, entiteSurCase);
        } else if (plateau.estPiege(nextX, nextY)) {
            System.out.println("💀 Vous êtes tombé sur un Piège ou sorti du labyrinthe ! GAME OVER !");
            joueur.setX(START_X);
            joueur.setY(START_Y);
            System.out.println("Vous recommencez au point de départ (" + START_X + ", " + START_Y + ").");
        } else {
            System.out.println("Case sûre.");
        }

        return joueur;
    }

    private void gererCombat(Personne joueur, Monstre monstre) {
        Class<?> faiblesseClasse = null;

        // La logique des faiblesses est correcte selon les classes fournies
        if (monstre instanceof Dragon) {
            faiblesseClasse = Archer.class;
        } else if (monstre instanceof Squelette) {
            faiblesseClasse = Chevalier.class;
        } else if (monstre instanceof Sirene) {
            faiblesseClasse = Mage.class;
        } else if (monstre instanceof Chien_enfer) {
            faiblesseClasse = Sorcier.class;
        }

        if (faiblesseClasse != null && faiblesseClasse.isInstance(joueur)) {
            joueur.attaquer();
            System.out.println("⚔️ Attaque Super Efficace ! Votre classe (" + joueur.getClass().getSimpleName() + ") est la faiblesse de ce monstre.");
            System.out.println(monstre.getName() + " a été vaincu et vous gagnez la case !");

            // CORRECTION: Suppression de l'entité en utilisant la position du joueur (la clé correcte)
            plateau.removeEntite(joueur.getX(), joueur.getY());

        } else {
            System.out.println("❌ Votre classe (" + joueur.getClass().getSimpleName() + ") n'est pas la faiblesse du " + monstre.getName() + " !");
            System.out.println(monstre.getName() + " vous écrase !");

            joueur.setVie(false);
            System.out.println("💀 La partie est terminée. Vous n'avez pas survécu à l'affrontement !");
        }
    }

    private void gererFuir(Personne joueur) {
        System.out.println("🏃 Vous fuyez le combat ! Vous êtes renvoyé à la case de départ.");
        joueur.setX(START_X);
        joueur.setY(START_Y);
    }

    private Monstre getMonstreSurCase(int x, int y) {
        Entite entite = plateau.getEntite(x, y);
        if (entite instanceof Monstre) {
            return (Monstre) entite;
        }
        return null;
    }

    // MÉTHODE MISE À JOUR : Gère le remplacement d'objet pour toutes les armes
    private Personne gererInteraction(Personne joueur, Entite entite) {

        if (entite instanceof Arme) {
            Arme arme = (Arme) entite;
            System.out.println("🎁 Vous trouvez l'arme : " + arme.getNomA() + ".");

            Class<?> nouvelleClasse = null;

            // Déterminer la nouvelle classe
            if (arme instanceof Epee) {
                nouvelleClasse = Chevalier.class;
            } else if (arme instanceof Baguette) {
                nouvelleClasse = Sorcier.class;
            } else if (arme instanceof Arc) {
                nouvelleClasse = Archer.class;
            } else if (arme instanceof Baton) {
                nouvelleClasse = Mage.class;
            }

            if (nouvelleClasse != null) {
                System.out.println("Vous ramassez " + arme.getNomA() + " ! Vous devenez un " + nouvelleClasse.getSimpleName() + " !");
                plateau.removeEntite(entite.getX(), entite.getY());

                // CRÉATION ET TRANSFERT DE L'ÉTAT DU NOUVEAU JOUEUR
                Personne nouveauJoueur;

                // Note : On utilise le constructeur (String nom) et on met à jour la position
                if (nouvelleClasse == Chevalier.class) {
                    nouveauJoueur = new Chevalier(joueur.getName());
                } else if (nouvelleClasse == Sorcier.class) {
                    nouveauJoueur = new Sorcier(joueur.getName(), 0, 0); // Utilise un constructeur simple (0,0) puis setX/Y
                } else if (nouvelleClasse == Archer.class) {
                    nouveauJoueur = new Archer(joueur.getName());
                } else { // Mage (si Baton)
                    nouveauJoueur = new Mage(joueur.getName());
                }

                nouveauJoueur.setX(joueur.getX());
                nouveauJoueur.setY(joueur.getY());
                nouveauJoueur.setVie(joueur.getVie());

                return nouveauJoueur;
            }
            plateau.removeEntite(entite.getX(), entite.getY());

        } else if (entite.getName().equals("Tresor")) {
            System.out.println("🏆 Vous avez trouvé le Trésor à (3, 0) ! Vous avez gagné !");
            joueur.setVie(false);
        }

        return joueur;
    }
}