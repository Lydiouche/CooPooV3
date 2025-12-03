public abstract class Entiter{
    private int x ;
    private int y ;
    private String name ;

public Entiter(){
    x = 0;
    y = 0;
}
public Entiter(String name){
    this.name = name;
    x = 0;
    y = 0;
}

public Entiter(String name, int x, int y){
    this.name = name;
    this.x = 0;
    this.y = 0;
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
