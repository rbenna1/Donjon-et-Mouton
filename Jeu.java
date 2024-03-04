import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;
import java.awt.event.KeyAdapter;              
import java.awt.event.KeyEvent;

public class Jeu {

    Terrain terrain;
    double sortis;
    boolean FinDeJeu = false;
    int MaxAppeal = 50;
    /* Initialisation d'un jeu avec le terrain initial décrit dans
       le fichier [f] donné en paramètre */
    public Jeu(String f) {
        this.terrain = new Terrain(f);
        this.sortis = 0;
    }

    public void tour(){
        int Index = 0;
        int PersonnageIndex = 0;
        for (EntiteMobile e : terrain.entites) {
            //System.out.print(e.getX() + " " + e.getY() + "   " + (e.getY()-1) + " " + (e.getX()));
            //System.out.println(e.resistance);
            if (e != null && e.resistance > 0 && ! (e instanceof Berger)){
                switch(e.direction){
                    case nord : if (terrain.CheckValidCoord(e.getX(),e.getY()+1)){e.action(terrain.carte[e.getY()][e.getX()], terrain.carte[e.getY()-1][e.getX()]);} break;
                    case sud : if (terrain.CheckValidCoord(e.getX(),e.getY()-1)){e.action(terrain.carte[e.getY()][e.getX()], terrain.carte[e.getY()+1][e.getX()]);} break;
                    case est : if (terrain.CheckValidCoord(e.getX()+1,e.getY())){e.action(terrain.carte[e.getY()][e.getX()], terrain.carte[e.getY()][e.getX()+1]);} break;
                    default: if (terrain.CheckValidCoord(e.getX()-1,e.getY())){e.action(terrain.carte[e.getY()][e.getX()], terrain.carte[e.getY()][e.getX()-1]);} break;
                }
                if (e.resistance <= 0){sortis += Math.abs(e.resistance); e = null; }
                Index += 1;
                if (e instanceof Personnage){ PersonnageIndex +=1; }
            } else { 
                //System.out.println(e + "  est Hors jeu"); 
            }
        }  
        if (Index == 0 || PersonnageIndex == 0 ){ FinDeJeu = true; } 
        if (terrain.Joueur != null){
            if (terrain.Joueur.resistance <= 0){ FinDeJeu = true;}    
        }
    } 

    public void OnKeyboardInput(int keyCode){
        if (terrain.Joueur != null){
            if (terrain.Joueur.resistance > 0){
                EntiteMobile e = terrain.Joueur;
                if (keyCode == KeyEvent.VK_UP) {
                    //System.out.println("Up Arrrow-Key is pressed!");
                    if (terrain.CheckValidCoord(e.getX(),e.getY()+1)){e.action(terrain.carte[e.getY()][e.getX()], terrain.carte[e.getY()-1][e.getX()]);}
                  }
                  else if (keyCode == KeyEvent.VK_DOWN) {
                    //System.out.println("Down Arrrow-Key is pressed!");
                    if (terrain.CheckValidCoord(e.getX(),e.getY()-1)){e.action(terrain.carte[e.getY()][e.getX()], terrain.carte[e.getY()+1][e.getX()]);}
                  }
                  else if (keyCode == KeyEvent.VK_RIGHT) {
                    //System.out.println("Left Arrrow-Key is pressed!");
                    if (terrain.CheckValidCoord(e.getX()+1,e.getY())){e.action(terrain.carte[e.getY()][e.getX()], terrain.carte[e.getY()][e.getX()+1]);}
                  }
                  else if (keyCode == KeyEvent.VK_LEFT) {
                   //System.out.println("Right Arrrow-Key is pressed!");
                   if (terrain.CheckValidCoord(e.getX()-1,e.getY())){e.action(terrain.carte[e.getY()][e.getX()], terrain.carte[e.getY()][e.getX()-1]);}
                  } else if (keyCode == KeyEvent.VK_SPACE) {
                    if (terrain.carte[e.getY()][e.getX()] instanceof Sortie){ FinDeJeu = true; }
                   }
            } 
        }
    }

    public boolean partieFinie(){ return FinDeJeu; }

    public static void main(String[] args) {
        Jeu j = new Jeu("laby1.txt");
        int TourCount = 0;
        while (! j.FinDeJeu && TourCount <= j.MaxAppeal){
            j.terrain.print();
           // j.terrain.printentities();
            j.tour();
            TourCount += 1;
        }
        j.terrain.print();
        System.out.println("Fin en : " + TourCount + " Tours avec " + j.sortis + " Personnages sauvé");
    } 
}
