import java.util.Random;

public class Personnage extends EntiteMobile{

    public Personnage(int X,int Y){ x= X; y = Y; direction = Direction.random(); }
    public Personnage(int X,int Y,Direction d){ x= X; y = Y; direction = d; }
    
    public String toString(String background){
        String start = Character.toString( background.charAt(0) );
        String end = Character.toString( background.charAt(2) ); 
        if (direction == Direction.nord){
            return start + '^' + end;
        } else if (direction == Direction.sud){
            return start + 'v' + end;
        } else if (direction == Direction.est){
            return start + '>' + end;
        }
    return start + '<' + end;
    }

    public void action(Case courante, Case cible){
        if (cible instanceof CaseTraversable){
            if (courante instanceof Sortie){
                CaseTraversable Tcourante = (CaseTraversable)courante;
                if (Tcourante.getContenu() != null){ Tcourante.getContenu().resistance = -1; }
                Tcourante.vide();
                System.out.println("Sortie !");
            } else if (courante instanceof CaseTraversable){
                CaseTraversable Tcourante = (CaseTraversable)courante;
                CaseTraversable Tcible = (CaseTraversable)cible;
                Entite TcibleContenu = Tcible.getContenu();
                if (Tcible.estLibre()){Tcible.entre(this); Tcourante.vide(); this.SetXY(cible.col,cible.lig);
                    //System.out.println("Move Perso to " + cible.col + " " + cible.lig);
                } else if (TcibleContenu instanceof Monstre || TcibleContenu instanceof Berger || TcibleContenu instanceof Personnage ){ this.direction = Direction.random();
                } else if (TcibleContenu instanceof Obstacle){
                    //System.out.println("Obstacle or Perso");
                    TcibleContenu.resistance -= 1;
                    if (TcibleContenu.resistance == 0){ Tcible.vide(); }
                } 
            }
        } else {
            //System.out.println("Change dir");
            this.direction = Direction.random();
        }
    }

}