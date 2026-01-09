import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class LabyrintheApp extends Application {


    private static final int TAILLE_CASE = 60;
    private static final int MIN_X = -2;
    private static final int MAX_X = 3;
    private static final int MIN_Y = -3;
    private static final int MAX_Y = 4;


    private Plateau plateau;
    private Personne joueur;


    private StackPane[][] grilleGraphique;
    private TextArea zoneTexte;
    private Label statusLabel;
    private GridPane grille;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
 
        String nomJoueur = demanderNomUtilisateur();
        if (nomJoueur == null) {
            Platform.exit();
            return;
        }

    
        plateau = new Plateau();
        joueur = new Paysan(nomJoueur);
        joueur.setX(0);
        joueur.setY(0);
        joueur.setVie(true);
        

        try {
            joueur.setFaim(120);
            joueur.setInventaire(Personne.Objets.Vide);
        } catch (Exception e) {
            System.err.println("Erreur: Vérifiez que Personne.java contient bien faim et inventaire.");
        }


        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: #2c3e50;");

        // --- GRILLE ---
        grille = new GridPane();
        grille.setAlignment(Pos.CENTER);
        grille.setHgap(2);
        grille.setVgap(2);

        int colonnes = MAX_X - MIN_X + 1;
        int lignes = MAX_Y - MIN_Y + 1;
        grilleGraphique = new StackPane[colonnes][lignes];

        for (int y = MAX_Y; y >= MIN_Y; y--) {
            for (int x = MIN_X; x <= MAX_X; x++) {
                StackPane casePane = new StackPane();
                casePane.setPrefSize(TAILLE_CASE, TAILLE_CASE);
                casePane.setStyle("-fx-background-color: #ecf0f1; -fx-background-radius: 5;");
                
                int colIndex = x - MIN_X;
                int rowIndex = MAX_Y - y;
                grilleGraphique[colIndex][rowIndex] = casePane;
                grille.add(casePane, colIndex, rowIndex);
            }
        }
        root.setCenter(grille);

        // --- BAS (TEXTE & LORE) ---
        VBox bottomBox = new VBox(10);
        statusLabel = new Label();
        statusLabel.setTextFill(Color.WHITE);
        statusLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13)); // Police légèrement réduite pour tout afficher

        zoneTexte = new TextArea();
        zoneTexte.setEditable(false);
        zoneTexte.setFocusTraversable(false);
        zoneTexte.setPrefHeight(200);
        zoneTexte.setWrapText(true);
        zoneTexte.setStyle("-fx-control-inner-background: #34495e; -fx-text-fill: white; -fx-font-family: 'Consolas', monospace;");

        afficherIntro(nomJoueur);

        bottomBox.getChildren().addAll(statusLabel, zoneTexte);
        root.setBottom(bottomBox);


        Scene scene = new Scene(root, 700, 850);
        scene.setOnKeyPressed(event -> {
            if (joueur.getVie()) {
                gererMouvement(event.getCode());
                rafraichirVue();
            }
        });

        rafraichirVue();
        primaryStage.setTitle("Labyrinthe RPG - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
        grille.requestFocus();
    }

    // --- LOGIQUE DU JEU ---

    private void gererMouvement(KeyCode code) {
        if (joueur.getFaim() > 0) joueur.setFaim(joueur.getFaim() - 10);
        
        if (joueur.getFaim() <= 0) {
            afficherMessage("🍎 FAMINE ! Vous n'avez plus de force...");
            joueur.setVie(false);
            statusLabel.setText("MORT DE FAIM");
            return;
        }

        int nextX = joueur.getX();
        int nextY = joueur.getY();

        switch (code) {
            case LEFT -> nextX--;
            case RIGHT -> nextX++;
            case DOWN -> nextY--;
            case UP -> nextY++;
            default -> { return; }
        }

        if (plateau.estPiege(nextX, nextY)) {
            afficherMessage("⚠️ PIÈGE ! Vous tombez et revenez au départ !");
            joueur.setX(0);
            joueur.setY(0);
            return;
        }

        joueur.setX(nextX);
        joueur.setY(nextY);
        afficherMessage("Déplacement en [" + nextX + "," + nextY + "]");

        Entite entite = plateau.getEntite(nextX, nextY);
        if (entite != null) {
            traiterInteraction(entite);
        }
    }

    private void traiterInteraction(Entite entite) {
        if (entite instanceof Monstre) {
            Monstre m = (Monstre) entite;
            afficherMessage("⚔️ MONSTRE ! C'est un " + m.getName() + " !");
            afficherMessage("Cri : \"" + m.criDeGuerre() + "\"");
            gererCombatAutomatique(m);
            
        } else if (entite instanceof Arme) {
            Arme a = (Arme) entite;
            afficherMessage("✨ Arme trouvée : " + a.getNomA());
            
            boolean reussite = poserEnigmeGraphique(a);
            
            if (reussite) {
                changerClasse(a);
                plateau.removeEntite(joueur.getX(), joueur.getY());
            } else {
                afficherMessage("🔒 Raté ! L'arme reste au sol.");
            }
            
        } else if (entite.getName().equals("Tresor")) {
            afficherMessage("💎 VICTOIRE ! Vous avez trouvé les sujets d'examen de COOPOO 2025 !");
            afficherMessage("Félicitations, vous avez gagné la partie (et votre année !)");
            joueur.setVie(false);
            statusLabel.setText("VICTOIRE - SUJETS TROUVÉS !!!");
        }
    }

    private boolean poserEnigmeGraphique(Arme a) {
        String question = "";
        List<String> choix = null;
        String bonneReponse = "";

        if (a instanceof Epee) {
            question = "Quelle épée légendaire a été reforgée pour Aragorn (Andúril) ?";
            choix = Arrays.asList("1. Narsil", "2. Escalibur", "3. Durandal");
            bonneReponse = "1. Narsil";
        } else if (a instanceof Arc) {
            question = "Quel jeu se déroule dans un monde préhistorique avec des dinosaures ?";
            choix = Arrays.asList("1. Ark : Survival Evolved", "2. Minecraft", "3. Skyrim");
            bonneReponse = "1. Ark : Survival Evolved";
        } else if (a instanceof Baguette) {
            question = "Dans quel univers retrouve-t-on la célèbre baguette de Sureau ?";
            choix = Arrays.asList("1. Harry Potter", "2. Le Seigneur des Anneaux", "3. Narnia");
            bonneReponse = "1. Harry Potter";
        } else if (a instanceof Baton) {
            question = "Quel objet utilise un chef d'orchestre pour diriger ?";
            choix = Arrays.asList("1. Une baguette", "2. Une trompette", "3. Une guitare");
            bonneReponse = "1. Une baguette";
        } else {
            return true;
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(choix.get(1), choix);
        dialog.setTitle("Énigme du Gardien");
        dialog.setHeaderText("Pour prendre " + a.getNomA() + " :");
        dialog.setContentText(question);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (result.get().equals(bonneReponse)) {
                afficherMessage("✅ Bonne réponse !");
                return true;
            }
        }
        return false;
    }

    private void gererCombatAutomatique(Monstre m) {
        boolean victoire = false;

        if (m instanceof Dragon && joueur instanceof Archer) victoire = true;
        else if (m instanceof Squelette && joueur instanceof Chevalier) victoire = true;
        else if (m instanceof Sirene && joueur instanceof Mage) victoire = true;
        else if (m instanceof Chien_enfer && joueur instanceof Sorcier) victoire = true;

        if (victoire) {
            afficherMessage("✅ COUP CRITIQUE ! " + m.getName() + " vaincu.");
            
            // --- GESTION DU LOOT (INVENTAIRE) ---
            if (m instanceof Dragon) {
                afficherMessage("🎁 Loot : Le Casque de Moto de Meneveaux !");
                joueur.setInventaire(Personne.Objets.LeCasqueDeMotoDeMeneveaux);
            } else if (m instanceof Squelette) {
                afficherMessage("🎁 Loot : La Tasse de Café d'Annie !");
                joueur.setInventaire(Personne.Objets.LaTasseDeCafeDAnnie);
            } else if (m instanceof Sirene) {
                afficherMessage("🎁 Loot : La Clé USB de Skapin !");
                joueur.setInventaire(Personne.Objets.LaCléUSBDeSkapin);
            } else if (m instanceof Chien_enfer) { 
                afficherMessage("🎁 Loot : Le KitKat de Fousse !");
                afficherMessage("🍫 Miam ! Vous le mangez direct (+80 Faim).");
                joueur.setFaim(joueur.getFaim() + 80);
                joueur.setInventaire(Personne.Objets.Vide); 
            }

            plateau.removeEntite(joueur.getX(), joueur.getY());
        } else {
            afficherMessage("💀 ECHEC ! Vous fuyez !");
            joueur.setX(0);
            joueur.setY(0);
        }
    }

    private void changerClasse(Arme a) {
        String oldName = joueur.getName();
        int x = joueur.getX();
        int y = joueur.getY();
        boolean v = joueur.getVie();
        int f = joueur.getFaim();
        Personne.Objets inv = joueur.getInventaire();

        if (a instanceof Epee) joueur = new Chevalier(oldName);
        else if (a instanceof Baguette) joueur = new Sorcier(oldName);
        else if (a instanceof Arc) joueur = new Archer(oldName);
        else if (a instanceof Baton) joueur = new Mage(oldName);

        joueur.setX(x);
        joueur.setY(y);
        joueur.setVie(v);
        joueur.setFaim(f);
        joueur.setInventaire(inv); 
        
        afficherMessage("🔄 MÉTAMORPHOSE ! Vous êtes maintenant un " + joueur.getClass().getSimpleName());
    }

    private void rafraichirVue() {
        String vieStr = joueur.getVie() ? "VIVANT" : "MORT";
        // Mise à jour du StatusLabel avec l'Inventaire
        statusLabel.setText(String.format("Héros: %s | Classe: %s | Faim: %d | Inv: %s", 
            joueur.getName(), joueur.getClass().getSimpleName(), joueur.getFaim(), joueur.getInventaire()));

        for (int y = MAX_Y; y >= MIN_Y; y--) {
            for (int x = MIN_X; x <= MAX_X; x++) {
                int col = x - MIN_X;
                int row = MAX_Y - y;
                StackPane pane = grilleGraphique[col][row];
                pane.getChildren().clear();
                
                if (plateau.estPiege(x, y)) pane.setStyle("-fx-background-color: #7f8c8d; -fx-background-radius: 5;");
                else pane.setStyle("-fx-background-color: #ecf0f1; -fx-background-radius: 5;");

                Entite e = plateau.getEntite(x, y);
                if (e != null) {
                    Label labelIcon = new Label(getEmojiFor(e));
                    labelIcon.setFont(Font.font(30));
                    pane.getChildren().add(labelIcon);
                }
            }
        }
        
        int pCol = joueur.getX() - MIN_X;
        int pRow = MAX_Y - joueur.getY();
        if (pCol >= 0 && pCol < grilleGraphique.length && pRow >= 0 && pRow < grilleGraphique[0].length) {
            Label labelJoueur = new Label("👤");
            labelJoueur.setFont(Font.font(30));
            grilleGraphique[pCol][pRow].setStyle("-fx-background-color: #ecf0f1; -fx-border-color: #3498db; -fx-border-width: 3; -fx-background-radius: 5;");
            grilleGraphique[pCol][pRow].getChildren().add(labelJoueur);
        }
    }

    private String getEmojiFor(Entite e) {
        if (e instanceof Dragon) return "🐉";
        if (e instanceof Squelette) return "💀";
        if (e instanceof Sirene) return "🧜‍♀️";
        if (e instanceof Chien_enfer) return "🐕";
        if (e instanceof Epee) return "🗡️";
        if (e instanceof Baguette) return "🪄";
        if (e instanceof Arc) return "🏹";
        if (e instanceof Baton) return "🪵";
        if (e instanceof Fourche) return "🔱";
        if (e.getName().equals("Tresor")) return "💎";
        return "❓";
    }

    private String demanderNomUtilisateur() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Nouveau Personnage");
        dialog.setHeaderText("Bienvenue !");
        dialog.setContentText("Nom du héros :");
        while (true) {
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                if (!result.get().trim().isEmpty()) return result.get().trim();
            } else return null;
        }
    }

    private void afficherIntro(String nom) {
        afficherMessage("====================================================");
        afficherMessage("La Légende de " + nom +" et la Tache de Café\n" +
                "Le Héros : \n" +
                nom + ", modeste paysan du Royaume de KévinLand et éleveur de panais de père en fils.\n " +
                "Un jour, il choisit de prendre sa destinée en main !!!!!\n" +
                "L'Histoire :\n" +
                nom + " n'a jamais voulu être un héros. Son ambition se limitait à avoir les plus beaux légumes du royaume. \n" +
                "Mais hier soir, en courant dans les couloirs de la taverne du B2, un croche pied de Samuel le fait tomber dans le bureau d’Annie La Grande. \n" +
                "Nez à nez avec les copies d’examen, il découvre un vieux parchemin qui servait de cale table. \n" +
                "C'était une carte au trésor légendaire ! \n" +
                "Enfin... c'est ce qu'il pense. Le problème, c'est que quelqu'un a renversé une chope de café et mangé un pain d’épices dessus il y a 30 ans.\n" +
                "La carte du labyrinthe est totalement illisible, c'est une bouillie d'encre. \n" +
                "MAIS, par miracle, au dos du parchemin, les coordonnées exactes du trésor sont écrites proprement : X: 3 | Y: 0.\n" +
                "Armé de sa seule fourche (aussi efficace pour le foin que pour chatouiller un dragon) et de son sens de l'orientation approximatif, " + nom + " entre dans le donjon.\n" +
                "Il sait où est la sortie, mais il ignore totalement quels monstres, pièges et portes magiques se dressent entre lui et la richesse.\n");
        afficherMessage("====================================================");
        afficherMessage("           Bienvenue dans le Labyrinthe !           ");
        afficherMessage("Objectif : Atteindre le TRESOR (3, 0). Attention aux pièges et aux monstres.");
        afficherMessage("Faites attention à ne pas rester trop longtemps dans le labyrinthe ! Vous risquez d'avoir faim...");
        afficherMessage("Vous commencez en (0, 0). Bonne chance !");
        afficherMessage("====================================================");
    }

    private void afficherMessage(String msg) {
        zoneTexte.appendText(msg + "\n");
        zoneTexte.setScrollTop(Double.MAX_VALUE);
    }
}