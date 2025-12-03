public abstract class Entiter{
private int X ;
private int y ;
private String name ;
public Entiter(int X, int y, String name){
    this.X = X;
    this.y = y;
    this.name = name;
}
public int getX() {
    return X;
}
public int getY() {
    return y;
}
public String getName() {
    return name;
}
}
