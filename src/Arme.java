public abstract class Arme extends Entite {
    protected boolean estRecuperee;
    public Arme(String name, int x, int y) {
        super(name, x, y);
        this.estRecuperee = false;
    }

    public String getNomA() {
        return super.getName();
    }

    public abstract void enigmeArme();

    public boolean isRecuperee() {
        return estRecuperee;
    }
}
