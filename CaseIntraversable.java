class CaseIntraversable extends Case{
	
	CaseIntraversable(int l, int c){ super(l,c); }
	
	public boolean estLibre(){ return false; }
	public String toString(){ return "###";}
}
