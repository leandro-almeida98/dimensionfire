package controle;


import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Principal {

    //public static final int LARGURA_TELA = 1600;
    //public static final int ALTURA_TELA = 900;
    public static final int LARGURA_TELA = 1024;
    public static final int ALTURA_TELA = 768;
    public static JProgressBar progresso = new JProgressBar();

    public Principal() throws InterruptedException {
        JFrame janela = new JFrame("Dimension Fire");
        Game game = new Game();
        game.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
        //progresso.setSize(300, 50);
        //janela.add(progresso);
        //janela.setLocationRelativeTo(null);
        //setCursor(new Cursor(Cursor.WAIT_CURSOR));
        //progresso.setVisible(true);
        //progresso.setToolTipText("Aguarde...");
        janela.getContentPane().add(game);
        //janela.setResizable(false);
        janela.setResizable(false);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocation(100, 100);
        janela.setVisible(true);
        janela.pack();

    }

    public static void main(String[] args) throws InterruptedException {
        //new Inicio_tela().setVisible(true);
        new Principal();
        //Syst
        /*Conexao con = new Conexao();
        con.ConectarMysql();
        System.out.println(con.statusConection());*/
    }

}
