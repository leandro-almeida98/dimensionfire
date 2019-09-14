package controle;


import interfaces.Inicio_tela;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Principal {

    public static final int LARGURA_TELA = 640;
    public static final int ALTURA_TELA = 480;

    public Principal() {

        JFrame janela = new JFrame("Jogo2D");
        Game game = new Game();
        game.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
        janela.getContentPane().add(game);
        //janela.setResizable(false);
        janela.setResizable(false);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocation(100, 100);
        janela.setVisible(true);
        janela.pack();
    }

    public static void main(String[] args) {
        
        //new Inicio_tela().setVisible(true);
        new Principal();
        //Syst
    }

}
