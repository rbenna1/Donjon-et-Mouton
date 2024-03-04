class CaseLibre extends CaseTraversable{

	CaseLibre(int l, int c){ super(l,c); }
	CaseLibre(int l, int c, Entite e){ super(l,c); contenu = e;}
	
	public boolean estLibre(){ if(contenu == null ){return true;} return false; }
	public String toString(){
		if (contenu != null){
			return contenu.toString("   ");
		}
		 return "   " ;
	}
}
