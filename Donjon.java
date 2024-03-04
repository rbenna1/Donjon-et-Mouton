import javax.swing.Timer;
import java.awt.event.KeyAdapter;              
import java.awt.event.KeyEvent;

public class Donjon {

    public static void main(String[] args) {
        int tempo = 150;
        Jeu jeu = new Jeu("laby2.txt");

        FenetreJeu graphic = new FenetreJeu(jeu.terrain);
        Timer timer = new Timer(tempo, e -> {
            jeu.tour();
            graphic.repaint();
            if (jeu.partieFinie()) { graphic.ecranFinal(jeu.sortis); }
        });
        timer.setInitialDelay(0);
        timer.start();

        graphic.frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
              int keyCode = e.getKeyCode();
              jeu.OnKeyboardInput(keyCode);
            }
          });
    }
}
