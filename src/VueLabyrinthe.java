import java.util.Scanner;

public class VueLabyrinthe {

    private Plateau plateau;
    private LabyrintheGUI gui;
    private static final int START_X = 0;
    private static final int START_Y = 0;

    private Scanner scanner = new Scanner(System.in);

    public void jouer(Personne joueur) {
        this.plateau = new Plateau();
        this.gui = new LabyrintheGUI(plateau, joueur);
        String input = "";

        joueur.setX(START_X);
        joueur.setY(START_Y);
        joueur.setVie(true);

        System.out.println("====================================================");
        System.out.println("La Légende de " + joueur.getName() +" et la Tache de Café\n" +
                "Le Héros : \n" +
                joueur.getName() + ", modeste paysan du Royaume de KévinLand et éleveur de panais de père en fils.\n " +
                "Un jour, il choisit de prendre sa destinée en main !!!!!\n" +
                "L'Histoire :\n" +
                joueur.getName() + " n'a jamais voulu être un héros. Son ambition se limitait à avoir les plus beaux légumes du royaume. \n" +
                "Mais hier soir, en courant dans les couloirs de la taverne du B2, un croche pied de Samuel le fait tomber dans le bureau d’Annie La Grande. \n" +
                "Nez à nez avec les copies d’examen, il découvre un vieux parchemin qui servait de cale table. \n" +
                "C'était une carte au trésor légendaire ! \n" +
                "Enfin... c'est ce qu'il pense. Le problème, c'est que quelqu'un a renversé une chope de café et mangé un pain d’épices dessus il y a 30 ans.\n" +
                "La carte du labyrinthe est totalement illisible, c'est une bouillie d'encre. \n" +
                "MAIS, par miracle, au dos du parchemin, les coordonnées exactes du trésor sont écrites proprement : X: 3 | Y: 0.\n" +
                "Armé de sa seule fourche (aussi efficace pour le foin que pour chatouiller un dragon) et de son sens de l'orientation approximatif, " + joueur.getName() + " entre dans le donjon.\n" +
                "Il sait où est la sortie, mais il ignore totalement quels monstres, pièges et portes magiques se dressent entre lui et la richesse.\n");
        System.out.println("====================================================");

        System.out.println("====================================================");
        System.out.println("           Bienvenue dans le Labyrinthe !           ");
        System.out.println("Objectif : Atteindre le TRESOR (3, 0). Attention aux pièges et aux monstres.");
        System.out.println("Faites attention à ne pas rester trop longtemps dans le labyrinthe !");
        System.out.println("Vous commencez en (0, 0). Bonne chance !");
        System.out.println("Commandes de déplacement: 1 (Gauche), 2 (Droite), 3 (Bas), 4 (Haut).");
        System.out.println("Actions globales : A (Avancer), F (fuir), C (combat), Q (Quitter).");
        System.out.println("====================================================");
        joueur.setFaim(100);
        while(joueur.getVie() && joueur.getFaim() > 0) {
            gui.actualiser();
            System.out.println("\n----------------------------------------------------");
            System.out.println("Position: (X=" + joueur.getX() + ", Y=" + joueur.getY() + ")");
            System.out.println("Classe: " + joueur.getClass().getSimpleName() + " (Nom: " + joueur.getName() + ")");
            System.out.println("Faim: " + joueur.getFaim());
            Monstre monstreSurCase = getMonstreSurCase(joueur.getX(), joueur.getY());

            if (monstreSurCase != null) {
                System.out.println("Le monstre est : " + monstreSurCase.getName() + " ! " + monstreSurCase.criDeGuerre());
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
                    joueur = gererDeplacement(joueur);
                    gui.actualiser();
            }
        }
        gui.dispose();
        scanner.close();
    }

    private Personne gererDeplacement(Personne joueur) {
        int oldX = joueur.getX();
        int oldY = joueur.getY();
        joueur.setFaim(joueur.getFaim() - 10);
        if (joueur.getFaim() <= 0) {
            System.out.println("Vous avez trop faim et vous vous effondrez...");
            joueur.setVie(false);
            return joueur;
        }
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
            System.out.println("Vous êtes tombé sur un Piège !");
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
            System.out.println("Attaque Super Efficace ! Votre classe (" + joueur.getClass().getSimpleName() + ") est la faiblesse de ce monstre.");
            System.out.println(monstre.getName() + " a été vaincu et vous gagnez la case !");

            plateau.removeEntite(joueur.getX(), joueur.getY());

        } else {
            System.out.println("Votre classe (" + joueur.getClass().getSimpleName() + ") n'est pas la faiblesse du " + monstre.getName() + " !");
            System.out.println(monstre.getName() + " vous écrase !");

            joueur.setVie(false);
            System.out.println("La partie est terminée. Vous n'avez pas survécu à l'affrontement !");
        }
    }

    private void gererFuir(Personne joueur) {
        System.out.println("Vous fuyez le combat ! Vous êtes renvoyé à la case de départ.");
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

    private Personne gererInteraction(Personne joueur, Entite entite) {

        if (entite instanceof Arme) {
            Arme arme = (Arme) entite;
            System.out.println("Vous trouvez l'arme : " + arme.getNomA() + ".");

            Class<?> nouvelleClasse = null;

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

                Personne nouveauJoueur;

                if (nouvelleClasse == Chevalier.class) {
                    nouveauJoueur = new Chevalier(joueur.getName());
                } else if (nouvelleClasse == Sorcier.class) {
                    nouveauJoueur = new Sorcier(joueur.getName());
                } else if (nouvelleClasse == Archer.class) {
                    nouveauJoueur = new Archer(joueur.getName());
                } else {
                    nouveauJoueur = new Mage(joueur.getName());
                }

                nouveauJoueur.setX(joueur.getX());
                nouveauJoueur.setY(joueur.getY());
                nouveauJoueur.setVie(joueur.getVie());
                nouveauJoueur.setFaim(joueur.getFaim());
                if (this.gui != null) {
                    this.gui.setJoueur(nouveauJoueur);
                }
                return nouveauJoueur;
            }
            plateau.removeEntite(entite.getX(), entite.getY());

        } else if (entite.getName().equals("Tresor")) {
            System.out.println("Vous avez trouvé le Trésor à (3, 0) ! Vous avez gagné !");
            joueur.setVie(false);
        }

        return joueur;
    }
}