import javax.swing.*;
import java.awt.*;


public class FenetreJeu extends JPanel {
    private Terrain terrain;
    private int tailleCase = 24;
    private int hauteur, largeur;
    public JFrame frame;

    public FenetreJeu(Terrain t) {
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        this.terrain = t;
        System.out.println(largeur * tailleCase + " " + hauteur * tailleCase );
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(largeur * tailleCase, hauteur * tailleCase));

        JFrame frame = new JFrame("Donjon");
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int l=0; l<hauteur; l++) {
            for (int c=0; c<largeur; c++) {
                if (this.terrain.carte[l][c] instanceof CaseIntraversable) {
                     g.setColor(Color.BLACK); 
                     g.fillRect((c*24), (l*24) ,tailleCase,tailleCase);
                } else if (this.terrain.carte[l][c] instanceof Sortie) {
                    g.setColor(Color.GREEN); 
                    g.fillRect((c*24), (l*24) ,tailleCase,tailleCase);
                } else if (this.terrain.carte[l][c] instanceof CaseTraversable){
                    CaseTraversable Tcourante = (CaseTraversable)this.terrain.carte[l][c];
                    if (Tcourante.getContenu() != null) {
                        if ( Tcourante.getContenu() instanceof Obstacle){
                            g.setColor(Color.ORANGE); 
                            g.fillRect((c*24), (l*24) ,tailleCase,tailleCase);
                        }
                    }
                } 
            }
        }
        
        for (EntiteMobile e : terrain.entites) {
            boolean Draw = false;
            if (e instanceof Monstre) { g.setColor(Color.RED); Draw = true; }
            else if (e instanceof Personnage ) { g.setColor(Color.WHITE); if(e.resistance > 0){Draw = true;} }
            else if (e instanceof Berger) { g.setColor(Color.BLUE); Draw = true; }
            if (Draw){g.fillOval( (e.x*24) , (e.y*24) , tailleCase , tailleCase  );}
        }
       
    }

    public void ecranFinal(double n) {
        frame.remove(this);
        if (terrain.Joueur != null){ 
            if(terrain.Joueur.resistance <= 0){ n = n/2; }
        }
        JLabel label = new JLabel("Score " + n);
        label.setFont(new Font("Verdana", 1, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }
}
