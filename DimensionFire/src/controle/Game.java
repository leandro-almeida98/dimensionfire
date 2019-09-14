package controle;


import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class Game extends JPanel {

    public Bola bola; // criar um objeto a classe bola
    private boolean k_cima = false;
    private boolean k_baixo = false;
    private boolean k_direita = false;
    private boolean k_esquerda = false;
    private BufferedImage imgAtual;

    private int mousePosX, mousePosY;
    private boolean clicado = false;

    public Game() {

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (mousePosX >= bola.posX
                        && mousePosX <= bola.posX + (bola.raio * 2)
                        && mousePosY >= bola.posY
                        && mousePosY <= bola.posY + (bola.raio * 2)) {

                }
                clicado = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                clicado = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mousePosX = e.getX();
                mousePosY = e.getY();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mousePosX = e.getX();
                mousePosY = e.getY();
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        k_cima = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        k_baixo = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        k_esquerda = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        k_direita = true;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        k_cima = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        k_baixo = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        k_esquerda = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        k_direita = false;
                        break;
                }
            }
        });
        bola = new Bola(); // inicializa o objeto bola
        setFocusable(true);
        setLayout(null);

        new Thread(new Runnable() {
            @Override
            public void run() {
                gameloop();
            }
        }).start();

    }

    // GAMELOOP ----------------------------------
    public void gameloop() {
        while (true) { // loop infinito
            handlerEvents();
            update();
            render();
            try {
                Thread.sleep(17);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void handlerEvents() {
        bola.velX = 0;
        bola.velY = 0;
        imgAtual = bola.parada;

        if (k_cima == true) {
            bola.velY = -3;
            imgAtual = bola.cima;
            if (k_esquerda == true) {
                bola.velX = -3;
                imgAtual = bola.esquerda_cima;
            } else if (k_direita == true) {
                bola.velX = 3;
                imgAtual = bola.direita_cima;
            }
        } else if (k_baixo == true) {
            bola.velY = 3;
            imgAtual = bola.baixo;
            if (k_esquerda == true) {
                bola.velX = -3;
                imgAtual = bola.esquerda_baixo;
            } else if (k_direita == true) {
                bola.velX = 3;
                imgAtual = bola.direita_baixo;
            }
        } else if (k_direita == true) {
            bola.velX = 3;
            imgAtual = bola.direita;
        } else if (k_esquerda == true) {
            bola.velX = -3;
            imgAtual = bola.esquerda;
        }
    }

    public void update() {
        /*if(clicado==true){
         bola.posX = mousePosX - bola.raio;
         bola.posY = mousePosY - bola.raio;
         }*/
        if (clicado == true) {
            int vel = 3;
            if (bola.posX < mousePosX - bola.raio) {
                bola.posX = bola.posX + vel;
                cima_baixo(vel);
            } else if (bola.posX > mousePosX - bola.raio) {
                bola.posX = bola.posX - vel;
                cima_baixo(vel);
            } else {
                //bola.posX = bola.posX;
            }
            /*if (bola.posY > mousePosY - bola.raio) {
                bola.posY = bola.posY - vel;
            } else if (bola.posY < mousePosY - bola.raio) {
                bola.posY = bola.posY + vel;
            } else {
                bola.posY = bola.posY;
            }*/
        }

        //bola.posX = bola.posX+bola.velX;
        //bola.posY = bola.posY+bola.velY;
        testeColisoes();

        System.out.println(mousePosX + "-" + mousePosY);
    }
    public void cima_baixo(int vel){
        if (bola.posY > mousePosY - bola.raio) {
                bola.posY = bola.posY - vel;
            } else if (bola.posY < mousePosY - bola.raio) {
                bola.posY = bola.posY + vel;
            } else {
                bola.posY = bola.posY;
            }
    }

    public void render() {
        repaint();
    }

    // OUTROS MÉTODOS ------------------------------------------
    public void testeColisoes() {
        if (bola.posX + (bola.raio * 2) >= Principal.LARGURA_TELA) { // lado direito
            bola.posX = Principal.LARGURA_TELA - (bola.raio * 2);
        } else if (bola.posX <= 0) { // lado esquerdo
            bola.posX = 0;
        }

        if (bola.posY + (bola.raio * 2) >= Principal.ALTURA_TELA) { // lado infeiror
            bola.posY = Principal.ALTURA_TELA - (bola.raio * 2);
        } else if (bola.posY <= 0) { // lado superior
            bola.posY = 0;
        }
        /*
         if(bola.posX+(bola.raio*2)>=Principal.LARGURA_TELA || bola.posX<=0){
         bola.posX = bola.posX-bola.velX;
         }
         if(bola.posY+(bola.raio*2)>=Principal.ALTURA_TELA || bola.posY<=0){
         bola.posY =bola.posY-bola.velY;
         }*/
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.LIGHT_GRAY);
        g.setColor(Color.RED);
        //g.fillOval(bola.posX, bola.posY, bola.raio*2, bola.raio*2);
        g.drawImage(imgAtual, bola.posX, bola.posY, null);
    }
}
