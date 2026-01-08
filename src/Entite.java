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

    public Entite(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
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

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
