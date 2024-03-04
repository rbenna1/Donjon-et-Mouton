class Sortie extends CaseTraversable{
	
	Sortie(int l, int c){ super(l,c); this.contenu = null;}
	
	public boolean estLibre(){ if (contenu == null ){return true;} return false; }
	public String toString(){
		if (contenu != null){
			return contenu.toString("( )");
		}
		 return "( )" ;
	}
}
