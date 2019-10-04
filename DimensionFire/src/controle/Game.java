package controle;

import static controle.Principal.progresso;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel {

    public Projetil projetil;
    public Obstrucoes obstaculo;
    public ObstaculoList obsLista;
    public Personagem personagem;
    public Som som;

    private boolean person_k_cima = false;
    private boolean person_k_baixo = false;
    private boolean person_k_direita = false;
    private boolean person_k_esquerda = false;
    private boolean inimigo_k_cima = false;
    private boolean inimigo_k_baixo = false;
    private boolean inimigo_k_direita = false;
    private boolean inimigo_k_esquerda = false;
    private boolean person_k_disparo = false;
    private boolean inimigo_k_disparo = false;
    private boolean person_k_correr = false;
    private boolean clicado = false;
    int catetoH;
    int catetoV;
    double hipotenusa;
    Classe ha = new Classe();

    ArrayList<Projetil> projeteis = new ArrayList<>();
    ArrayList<Personagem> personagens = new ArrayList<>();

    private final int NumPerson = 2;
    private final int NumProjeteis = 200;

    public Game() {
        Random entrada = new Random();
        setFocusable(true);
        setLayout(null);

        // inicializa o objeto personagem
        for (int i = 0; i < NumPerson; ) {
            personagem = new Personagem();
            personagem.setIdPerson(entrada.nextLong());
            personagem.setPosX(i==0?500:700);
            personagens.add(personagem);
            i++;
        }
        //POPULA OS PROJETEIS NA MEMORIA
        for (int i = 0; i < NumProjeteis; ) {
            projeteis.add( new Projetil());
            i++;
        }
        obsLista = new ObstaculoList();
        Mapa mapa = new Mapa();
        obsLista.setObstaculo_list(mapa.map());


        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_W:
                        person_k_cima = true;
                        //System.out.println("Cima");
                        break;
                    case KeyEvent.VK_S:
                        person_k_baixo = true;
                        //System.out.println("Baixo");
                        break;
                    case KeyEvent.VK_A:
                        person_k_esquerda = true;
                        //System.out.println("Esquerda");
                        break;
                    case KeyEvent.VK_D:
                        person_k_direita = true;
                        //System.out.println("Direita");
                        break;
                    case KeyEvent.VK_SPACE:
                        person_k_disparo = true;
                        //System.out.println("Disparo");
                        break;
                    //INIMIGO
                    case KeyEvent.VK_UP:
                        inimigo_k_cima = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        inimigo_k_baixo = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        inimigo_k_esquerda = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        inimigo_k_direita = true;
                        break; 
                    case KeyEvent.VK_NUMPAD0:
                        inimigo_k_disparo = true;
                        break;
                    
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        person_k_cima = false;
                        break;
                    case KeyEvent.VK_S:
                        person_k_baixo = false;
                        break;
                    case KeyEvent.VK_A:
                        person_k_esquerda = false;
                        break;
                    case KeyEvent.VK_D:
                        person_k_direita = false;
                        break;
                    case KeyEvent.VK_SHIFT:
                        person_k_correr = false;
                        break;
                    //INIMIGO
                    case KeyEvent.VK_UP:
                        inimigo_k_cima = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        inimigo_k_baixo = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        inimigo_k_esquerda = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        inimigo_k_direita = false;
                        break; 
                    case KeyEvent.VK_NUMPAD0:
                        inimigo_k_disparo = false;
                        break;
                }
                /*if(e.getKeyCode() == KeyEvent.VK_W){
                     person_k_cima = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    person_k_baixo = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_A){
                     person_k_esquerda = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_D){
                     person_k_direita = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                     person_k_disparo = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_UP){
                     inimigo_k_cima = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                     inimigo_k_baixo = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                     inimigo_k_esquerda = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                     inimigo_k_direita = false;
                }
                 if(e.getKeyCode() == KeyEvent.VK_NUMPAD0){
                     inimigo_k_disparo = false;
                }
                */
            }

            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
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
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex + "aq");
            }
        }
    }

    public void handlerEvents() {
        for (int i = 0; i < personagens.size();) {
            personagens.get(i).setVelX(0);
            personagens.get(i).setVelY(0);
            i++;
        }
        if (person_k_correr) {
            //ha.correr();
            //personagem.setVelocidade(11);
        } else {
            //personagens.get(0).setVelocidade(3);
        }
        personagens.get(0).mover(person_k_cima, person_k_direita, person_k_baixo, person_k_esquerda);
        personagens.get(1).mover(inimigo_k_cima, inimigo_k_direita, inimigo_k_baixo, inimigo_k_esquerda);

        // A CADA VEZ QUE PRESIONAR ESPAÇO, UM NOVO PROJETIL É CRIADO
        if (person_k_disparo) {
            for(int i = 0; i < projeteis.size();) {
                if (!projeteis.get(i).isAtivo()) {
                    projeteis.get(i).setDirecao(personagens.get(0).getDirecao());
                    projeteis.get(i).setPosX(personagens.get(0).getPosX() + personagens.get(0).getRaio());
                    projeteis.get(i).setPosY(personagens.get(0).getPosY() + personagens.get(0).getRaio());
                    projeteis.get(i).setIdPerson(personagens.get(0).getIdPerson());
                    projeteis.get(i).setAtivo(true);
                    projeteis.get(i).setDano(15);
                    break;
                }
                i++;
            }
            person_k_disparo = false;
        }
        if (inimigo_k_disparo) {
            for (int i = 0; i < projeteis.size();) {
                if (!projeteis.get(i).isAtivo()) {
                    projeteis.get(i).setDirecao(personagens.get(1).getDirecao());
                    projeteis.get(i).setPosX(personagens.get(1).getPosX() + personagens.get(1).getRaio());
                    projeteis.get(i).setPosY(personagens.get(1).getPosY() + personagens.get(1).getRaio());
                    projeteis.get(i).setAtivo(true);
                    projeteis.get(i).setIdPerson(personagens.get(1).getIdPerson());
                    projeteis.get(i).setDano(15);
                    break;
                }
                i++;
            }
            inimigo_k_disparo = false;
        }
    }

    public void update() {
        //MOVIMENTA O PERSONAGEM
        for (int i = 0; i < personagens.size();) {
            personagens.get(i).setPosX(personagens.get(i).getPosX() + personagens.get(i).getVelX());
            personagens.get(i).setPosY(personagens.get(i).getPosY() + personagens.get(i).getVelY());
            i++;
        }
        //VERIFICA SE O PROJETIL ESTÁ NO ESTADO ATIVO, SE ESTIVER, ELE MOVIMENTA ESSE PROJETIL
        //O PROJETIL É ATIVO ENQUANTO A SUA POSIÇÃO FOR DIFERENTE DA POSIÇÃO DO SEU DESTINO
        for (int i = 0; i < projeteis.size();) {
            if (projeteis.get(i).isAtivo()) {
                projeteis.get(i).mover();
                colisaoProjeteis(i);
                colisaoProjeteisObstaculos(i);
            }
            i++;
        }
        testeColisoes();
    }

    public void render() {
        repaint();
    }

    // OUTROS MÉTODOS ------------------------------------------
    public void testeColisoes() {
        // COLISÃO NAS BORDA DA TELA TELA
        for (int i = 0; i < personagens.size();) {
            if (personagens.get(i).getPosX() + (personagens.get(i).getRaio() * 2) >= Principal.LARGURA_TELA) { // lado direito
                personagens.get(i).setPosX(Principal.LARGURA_TELA - (personagens.get(i).getRaio() * 2));
            } else if (personagens.get(i).getPosX() <= 0) { // lado esquerdo
                personagens.get(i).setPosX(0);
            }
            if (personagens.get(i).getPosY() + (personagens.get(i).getRaio() * 2) >= Principal.ALTURA_TELA) { // lado infeiror
                personagens.get(i).setPosY(Principal.ALTURA_TELA - (personagens.get(i).getRaio() * 2));
            } else if (personagens.get(i).getPosY() <= 0) { // lado superior
                personagens.get(i).setPosY(0);
            }
            // COLISÃO CIRCULAR
            for (int y = 0; y < personagens.size();) {
                if (personagens.get(i).getIdPerson() != personagens.get(y).getIdPerson() && personagens.get(y).getVivo()) {
                    catetoH = personagens.get(i).getPosX() - personagens.get(y).getPosX();
                    catetoV = personagens.get(i).getPosY() - personagens.get(y).getPosY();
                    hipotenusa = Math.sqrt(Math.pow(catetoH, 2) + Math.pow(catetoV, 2));
                    if (hipotenusa <= personagens.get(i).getRaio() + personagens.get(y).getRaio()) { // verifica se houve colisão circular
                        personagens.get(i).setPosX(personagens.get(i).getPosX() - personagens.get(i).getVelX()); // desfaz o movimento
                        personagens.get(i).setPosY(personagens.get(i).getPosY() - personagens.get(i).getVelY()); // desfaz o movimento
                    }
                }
                y++;
            }

            i++;
        }

        // COLISÃO DO PERSONAGEM COM OS OBSTACULO DO MAPA
        for (int i = 0; i < personagens.size();) {
            for (int y = 0; y < obsLista.getObstaculo_list().size();) {
                if (obsLista.getObstaculo_list().get(y).isAtivo()) {
                    catetoH = personagens.get(i).getPosX() - obsLista.getObstaculo_list().get(y).getPosX();
                    catetoV = personagens.get(i).getPosY() - obsLista.getObstaculo_list().get(y).getPosY();
                    hipotenusa = Math.sqrt(Math.pow(catetoH, 2) + Math.pow(catetoV, 2));
                    if (hipotenusa <= obsLista.getObstaculo_list().get(y).getRaio() + personagens.get(i).getRaio()) { // verifica se houve colisão circular
                            personagens.get(i).setPosX(personagens.get(i).getPosX() - personagens.get(i).getVelX()); // desfaz o movimento
                            personagens.get(i).setPosY(personagens.get(i).getPosY() - personagens.get(i).getVelY()); // desfaz o movimento

                    }
                }
                y++;
            }
            i++;
        }
    }

    public void colisaoProjeteisObstaculos(int i) {
        for (int y = 0; y < obsLista.getObstaculo_list().size();) {
            catetoH = projeteis.get(i).getPosX() - obsLista.getObstaculo_list().get(y).getPosX();
            catetoV = projeteis.get(i).getPosY() - obsLista.getObstaculo_list().get(y).getPosY();
            hipotenusa = Math.sqrt(Math.pow(catetoH, 2) + Math.pow(catetoV, 2));
            if (hipotenusa <= projeteis.get(i).getRaio() + obsLista.getObstaculo_list().get(y).getRaio()) { // verifica se houve colisão circular
                if (projeteis.get(i).getDano() < obsLista.getObstaculo_list().get(y).getResistencia()) {
                    projeteis.get(i).setAtivo(false);
                } else {
                    obsLista.getObstaculo_list().get(y).setAtivo(false);
                }
            }
            y++;
        }
    }

    public void colisaoProjeteis(int i) {
        for (int y = 0; y < personagens.size();) {
            if (personagens.get(y).getVivo() && personagens.get(y).getIdPerson() != projeteis.get(i).getIdPerson()) {
                catetoH = projeteis.get(i).getPosX() - personagens.get(y).getPosX();
                catetoV = projeteis.get(i).getPosY() - personagens.get(y).getPosY();
                hipotenusa = Math.sqrt(Math.pow(catetoH, 2) + Math.pow(catetoV, 2));
                if (hipotenusa <= projeteis.get(i).getRaio() + personagens.get(y).getRaio()) { // verifica se houve colisão circula
                    personagens.get(y).recebeDano(projeteis.get(i).getDano()); // RETIRA HP DO INIMIGO
                    System.out.println("ID: "+personagens.get(y).getIdPerson()+" Hp: " + personagens.get(y).getHp());
                    progresso.setValue(personagens.get(y).getHp());
                    projeteis.get(i).setAtivo(false);
                    if (!personagens.get(y).getVivo()) {
                         System.out.println("ID: "+personagens.get(y).getIdPerson()+" Dead");
                    }
                }
            }
            y++;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        //g.setColor(Color.RED);

        //PERSONAGENS
        for (int i = 0; i < personagens.size();) {
            if (personagens.get(i).getVivo()) {
                g.drawImage(personagens.get(i).getImgAtual(), personagens.get(i).getPosX(), personagens.get(i).getPosY(), null);
            }
            i++;
        }
        //PROJETEIS
        for (int i = 0; i < projeteis.size();) {
            if (projeteis.get(i).isAtivo()) {
                g.drawImage(projeteis.get(i).getImgAtual(), projeteis.get(i).getPosX(), projeteis.get(i).getPosY(), null);
            }
            i++;
        }
        //MOSTRA OS OBSTACULOS
        for (int i = 0; i < obsLista.getObstaculo_list().size();) {
            if (obsLista.getObstaculo_list().get(i).isAtivo()) {
                g.drawImage(obsLista.getObstaculo_list().get(i).getImg(), obsLista.getObstaculo_list().get(i).getPosX(), obsLista.getObstaculo_list().get(i).getPosY(), null);
            }
            i++;
        }
    }
}
