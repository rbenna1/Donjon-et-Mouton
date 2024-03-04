abstract class  Entite{
	protected int resistance;
	Entite(int r){ this.resistance = r; }
	Entite(){ this.resistance = 3; }
	public abstract String toString(String background);
}
