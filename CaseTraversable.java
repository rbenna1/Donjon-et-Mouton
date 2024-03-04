class CaseTraversable extends Case{
	
    protected Entite contenu;
    
    CaseTraversable(int l, int c){ super(l,c); }
    public boolean estLibre(){ if (contenu == null ){return true;} return false; }
    public Entite getContenu(){ return this.contenu; }
    public void vide(){ this.contenu = null; }  
    public void entre(Entite e){ this.contenu = e; }
 
}
