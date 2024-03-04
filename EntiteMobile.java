abstract class EntiteMobile extends Entite{
    protected Direction direction;
    protected int x,y;
    public int getX(){ return x;}
    public int getY(){ return y;}
    public void SetXY(int X, int Y){ x = X; y = Y;}
    public abstract String toString(String background);
    public abstract void action(Case courante, Case cible);
}