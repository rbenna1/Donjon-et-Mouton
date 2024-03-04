public class Obstacle extends Entite{

    Obstacle(){ super(3); }
    Obstacle(int r){ super(r);}

    public String toString(String background){ 
        if ( this.resistance >= 3 ){
             return "@@@";
        } else if ( this.resistance == 1 ){
             return  Character.toString(background.charAt(0))  + '@' + Character.toString(background.charAt(2));
        } else if ( this.resistance == 2){
             return '@' + Character.toString(background.charAt(0)) + '@';
        }
        return "   ";
     }

}