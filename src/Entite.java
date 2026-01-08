public abstract class Entite{
    protected int x ;
    protected int y ;
    protected String name ;

    public Entite(){
        x = 0;
        y = 0;
    }
    public Entite(String name){
        this.name = name;
        x = 0;
        y = 0;
    }

    // CORRECTION du constructeur : les coordonnées sont assignées correctement
    public Entite(String name, int x, int y){
        this.name = name;
        this.x = x; // CORRIGÉ
        this.y = y; // CORRIGÉ
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String getName() {
        return name;
    }

    // AJOUTS NÉCESSAIRES : Les setters pour la position
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
