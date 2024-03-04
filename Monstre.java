import java.util.Random;

class Monstre extends EntiteMobile{

    public Monstre(int X, int Y){ x= X; y = Y; direction = Direction.random(); }
    public Monstre(int X, int Y,Direction d){ x= X; y = Y; direction = d; }

    public String toString(String background){
        String start = Character.toString( background.charAt(0) );
        String end = Character.toString( background.charAt(2) ); 
        if (direction == Direction.nord){
            return start + 'm' + end;
        } else if (direction == Direction.sud){
            return start + 'w' + end;
        } else if (direction == Direction.est){
            return start + '»' + end;
        }
    return start + '«' + end;
     } 

     public void action(Case courante, Case cible){    
        if (cible instanceof CaseTraversable){
            if (courante instanceof CaseTraversable){
                CaseTraversable Tcourante = (CaseTraversable)courante;
                CaseTraversable Tcible = (CaseTraversable)cible;
                Entite TcibleContenu = Tcible.getContenu();
                if (Tcible.estLibre()){Tcible.entre(this); Tcourante.vide(); this.SetXY(cible.col,cible.lig); 
                    //System.out.println("Move Monstre to " + cible.col + " " + cible.lig);
                } else if (TcibleContenu instanceof Monstre){ this.direction = Direction.random();
                } else if (TcibleContenu instanceof Obstacle || TcibleContenu instanceof Personnage || TcibleContenu instanceof Berger  ){
                    TcibleContenu.resistance -= 1;
                    //System.out.println(TcibleContenu.resistance);
                    if (TcibleContenu.resistance == 0){ Tcible.vide(); }
                } 
            } 
        } else {
            //System.out.println("Change dir");
            this.direction = Direction.random();
        }
    }
      
}