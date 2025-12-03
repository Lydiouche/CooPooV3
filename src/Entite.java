public abstract class Entite{
    private int x ;
    private int y ;
    private String name ;

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
}
