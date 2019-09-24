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

    public Personagem personagem; // criar um objeto a classe personagem
    public Inimigo inimigo;
    public Projetil projetil;
    private boolean person_k_cima = false;
    private boolean person_k_baixo = false;
    private boolean person_k_direita = false;
    private boolean person_k_esquerda = false;
    private boolean inimigo_k_cima = false;
    private boolean inimigo_k_baixo = false;
    private boolean inimigo_k_direita = false;
    private boolean inimigo_k_esquerda = false;
    private boolean person_k_disparo = false;
    private boolean clicado = false;

    public Game() {
        
        personagem = new Personagem(); // inicializa o objeto personagem
        inimigo = new Inimigo(); // inicializa o objeto inimigo
        projetil = new Projetil();
        setFocusable(true);
        setLayout(null);

        new Thread(new Runnable() {
            @Override
            public void run() {
                gameloop();
            }
        }).start();
        
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        person_k_cima = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        person_k_baixo = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        person_k_esquerda = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        person_k_direita = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        person_k_disparo = true;
                        break;
                }
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        inimigo_k_cima = true;
                        break;
                    case KeyEvent.VK_S:
                        inimigo_k_baixo = true;
                        break;
                    case KeyEvent.VK_A:
                        inimigo_k_esquerda = true;
                        break;
                    case KeyEvent.VK_D:
                        inimigo_k_direita = true;
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        person_k_cima = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        person_k_baixo = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        person_k_esquerda = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        person_k_direita = false;
                        break;
                }
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        inimigo_k_cima = false;
                        break;
                    case KeyEvent.VK_S:
                        inimigo_k_baixo = false;
                        break;
                    case KeyEvent.VK_A:
                        inimigo_k_esquerda = false;
                        break;
                    case KeyEvent.VK_D:
                        inimigo_k_direita = false;
                        break;
                }
            }
        });
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
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex + "aq");
            }
        }
    }
    public void disparar(){
        
    }

    public void handlerEvents() {
        personagem.setVelX(0);
        personagem.setVelY(0);
        inimigo.setVelX(0);
        inimigo.setVelY(0);
        
        if(person_k_disparo){
            projetil.setDirecao(personagem.getDirecao());
            projetil.setPosX(personagem.getPosX()+personagem.getRaio());
            projetil.setPosY(personagem.getPosY()+personagem.getRaio());
            projetil.setAtivo(true);
            person_k_disparo=false;
        }
        
        personagem.mover(person_k_cima,person_k_direita, person_k_baixo ,person_k_esquerda);
        inimigo.mover(inimigo_k_cima,inimigo_k_direita, inimigo_k_baixo ,inimigo_k_esquerda);
    }

    
    public void update() {
        personagem.setPosX(personagem.getPosX() + personagem.getVelX());
        personagem.setPosY(personagem.getPosY() + personagem.getVelY());
        inimigo.setPosX(inimigo.getPosX() + inimigo.getVelX());
        inimigo.setPosY(inimigo.getPosY() + inimigo.getVelY());
        
        if(projetil.getAtivo()){
            projetil.mover();
        }
        testeColisoes();
    }

    public void render() {
        repaint();
    }

    // OUTROS MÉTODOS ------------------------------------------
    public void testeColisoes() {
        
        // COLISÃO NAS BORDA DA TELA TELA
        if (personagem.getPosX() + (personagem.getRaio() * 2) >= Principal.LARGURA_TELA) { // lado direito
            personagem.setPosX(Principal.LARGURA_TELA - (personagem.getRaio() * 2));
        } else if (personagem.getPosX() <= 0) { // lado esquerdo
            personagem.setPosX(0);
        }
        if (personagem.getPosY() + (personagem.getRaio() * 2) >= Principal.ALTURA_TELA) { // lado infeiror
            personagem.setPosY(Principal.ALTURA_TELA - (personagem.getRaio() * 2));
        } else if (personagem.getPosY() <= 0) { // lado superior
            personagem.setPosY(0);
        }
        
        if (inimigo.getPosX() + (inimigo.getRaio() * 2) >= Principal.LARGURA_TELA) { // lado direito
            inimigo.setPosX(Principal.LARGURA_TELA - (inimigo.getRaio() * 2));
        }else if (inimigo.getPosX() <= 0) { // lado esquerdo
            inimigo.setPosX(0);
        }
        if (inimigo.getPosY() + (inimigo.getRaio() * 2) >= Principal.ALTURA_TELA) { // lado infeiror
            inimigo.setPosY(Principal.ALTURA_TELA - (inimigo.getRaio() * 2));
        } else if (inimigo.getPosY() <= 0) { // lado superior
            inimigo.setPosY(0);
        }
        

        
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.LIGHT_GRAY);
        g.setColor(Color.RED);
        g.drawImage(personagem.getImgAtual(), personagem.getPosX(), personagem.getPosY(), null);
        g.drawImage(inimigo.getImgAtual(), inimigo.getPosX(), inimigo.getPosY(), null);
        if(projetil.getAtivo()){
             g.drawImage(projetil.getImgAtual(), projetil.getPosX(), projetil.getPosY(), null);
        }
    }
}
