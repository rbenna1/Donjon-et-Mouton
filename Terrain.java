import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Terrain {

    private int hauteur, largeur;
    public Case[][] carte;
    public List<EntiteMobile> entites;
    public EntiteMobile Joueur = null;

    /* Initialisation d'un terrain à partir de la description donnée par
       un fichier texte. Format du fichier de description :
       - hauteur et largeur sur la première ligne
       - puis dessin du terrain (un caractère == une case) avec le codage
         suivant
         '#' pour un mur
         ' ' (espace) pour une case libre
         'o' pour une sortie
         '@' pour une case libre contenant un obstacle
         '^', 'v', '>', '<' pour une case libre contenant un personnage
         'm', 'w', '»', '«' pour une case libre contenant un monstre
    */
    public Terrain(String file) {
        try {
            Scanner sc = new Scanner(new FileInputStream(file));
            this.hauteur = sc.nextInt();
            this.largeur = sc.nextInt();
            this.entites = new ArrayList<>();
            int resistanceJoueur = 0;
            sc.nextLine();
            if (file == "laby2.txt"){
                resistanceJoueur = sc.nextInt(); 
                sc.nextLine();
            }
            this.carte = new Case[hauteur][largeur];
            for (int l=0; l<hauteur; l++) {
                String line = sc.nextLine();
                for (int c=0; c<largeur; c++) {
                    Case cc;
                    Character ch = line.charAt(c);
                    switch (ch) {
                        case '#': cc = new CaseIntraversable(l, c); break;
                        case ' ': cc = new CaseLibre(l, c); break;
                        case 'o': cc = new Sortie(l, c); break;
                        case '@': cc = new CaseLibre(l, c, new Obstacle()); break;
                        case '^': case '>': case 'v': case '<':
                            EntiteMobile En = new Personnage(c, l,Direction.ofChar(ch)) ;cc = new CaseLibre(l, c, En); entites.add(En);
                            break;
                        case 'm': case '»': case 'w': case '«':
                            EntiteMobile Ep = new Monstre(c, l,Direction.ofChar(ch)) ;cc = new CaseLibre(l, c, Ep); entites.add(Ep);
                            break;
                        case 'H': EntiteMobile Eb = new Berger(c, l,resistanceJoueur) ;cc = new CaseLibre(l, c, Eb); entites.add(Eb); Joueur = Eb; break;
                        default:  cc = null; break;
                    }
                    carte[l][c] = cc;
                }
            }
            sc.close();
        }
        catch (IOException e) { e.printStackTrace(); }
    }
    public void print(){
        for (int l=0; l<hauteur; l++) {
            for (int c=0; c<largeur; c++) {  
                System.out.print( this.carte[l][c].toString() + " " );
            }
            System.out.println();
        }
        System.out.println();
	}

    public void printentities(){
        for (EntiteMobile e : entites) {
            System.out.print(e + " : " + e.getX() + " " + e.getY());  
        }
        System.out.println();
    }

    public boolean CheckValidCoord(int X,int Y){
        if ( X <= largeur && Y <= hauteur){return true; }
        return false;
    }

    public int getHauteur(){ return hauteur;}

    public int getLargeur(){ return largeur;}

    public List<EntiteMobile> getentites(){ return entites;}
}
