public class Berger extends EntiteMobile{

    public String toString(String background){
        String start = Character.toString( background.charAt(0) );
        String end = Character.toString( background.charAt(2) ); 
        if (direction == Direction.nord){
            return start + 'U' + end;
        } else if (direction == Direction.sud){
            return start + 'D' + end;
        } else if (direction == Direction.est){
            return start + 'E' + end;
        }
    return start + 'O' + end;
    }

    public void action(Case courante, Case cible){
        if (cible instanceof CaseTraversable){
                CaseTraversable Tcourante = (CaseTraversable)courante;
                CaseTraversable Tcible = (CaseTraversable)cible;
                Entite TcibleContenu = Tcible.getContenu();
                if (Tcible.estLibre()){Tcible.entre(this); Tcourante.vide(); this.SetXY(cible.col,cible.lig);
                    //System.out.println("Move Perso to " + cible.col + " " + cible.lig);
                } else if (TcibleContenu instanceof Obstacle){
                 //System.out.println("Obstacle or Perso");
                 TcibleContenu.resistance -= 1;
                if (TcibleContenu.resistance == 0){ Tcible.vide(); }
             } 
         }
    }

    public Berger(int X,int Y){ x= X; y = Y; direction = Direction.random(); }
    public Berger(int X,int Y,int r){ x= X; y = Y; direction = Direction.random(); resistance = r; }

}