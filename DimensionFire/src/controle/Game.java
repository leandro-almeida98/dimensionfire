package controle;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class Game extends JPanel {

    public Personagem personagem; // criar um objeto a classe personagem
    public Inimigo inimigo;
    public ArrayBlockingQueue<Projetil> estoqueMunicao;
    public ArrayBlockingQueue<Projetil> estoqueMunicaoAtiva;
    public Obstaculos obstaculo;
    public ObstaculoList obsLista;
    public Som som ;
    public ProjetilsAtivo projetilAtivo;
    private boolean person_k_cima = false;
    private boolean person_k_baixo = false;
    private boolean person_k_direita = false;
    private boolean person_k_esquerda = false;
    private boolean inimigo_k_cima = false;
    private boolean inimigo_k_baixo = false;
    private boolean inimigo_k_direita = false;
    private boolean inimigo_k_esquerda = false;
    private boolean person_k_disparo = false;
    private boolean person_k_correr = false;
    private boolean clicado = false;

    public Game() {
        personagem = new Personagem(); // inicializa o objeto personagem
        inimigo = new Inimigo(); // inicializa o objeto inimigo
        projetilAtivo = new ProjetilsAtivo();
        setFocusable(true);
        setLayout(null);
        
        // inicializa o estoque de munição
        estoqueMunicao = new ArrayBlockingQueue<Projetil>(20);
        estoqueMunicaoAtiva = new ArrayBlockingQueue<Projetil>(20);
        for(int i = 0; i < 20; i++){
            try{
                estoqueMunicao.put(new Projetil());
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        
        obsLista = new ObstaculoList();
        Random gerador = new Random();
        /*for (int i = 0; i < 5;) {
            obstaculo = new Obstaculos();
            obstaculo.setRaio(gerador.nextInt(30));

            try {
                obstaculo.setImg(ImageIO.read(getClass().getResource("/imgs/parada.gif")));
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            obstaculo.setPosX(gerador.nextInt(Principal.LARGURA_TELA));
            obstaculo.setPosY(gerador.nextInt(Principal.LARGURA_TELA));
            obsLista.setObstaculo_list(obstaculo);
            i++;
        }*/

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
                    case KeyEvent.VK_SHIFT:
                        person_k_correr = true;
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
                    case KeyEvent.VK_SHIFT:
                        person_k_correr = false;
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

    public void disparar() {

    }

    public void handlerEvents() {
        //personagem.setVelocidade(0);
        personagem.setVelX(0);
        personagem.setVelY(0);
        inimigo.setVelX(0);
        inimigo.setVelY(0);

        // A CADA VEZ QUE PRESIONAR ESPAÇO, UM NOVO PROJETIL É CRIADO
        if (person_k_disparo) {
            som = new Som();
             
            try{
                Projetil projetil = estoqueMunicao.take();
                projetil.setDirecao(personagem.getDirecao());
                projetil.setPosX(personagem.getPosX() + personagem.getRaio());
                projetil.setPosY(personagem.getPosY() + personagem.getRaio());
                projetil.setAtivo(true);
                person_k_disparo = false;
                estoqueMunicaoAtiva.add(projetil);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            
        }
        if(person_k_correr){
            personagem.setVelocidade(6);
        }else{
            personagem.setVelocidade(3);
        }

        personagem.mover(person_k_cima, person_k_direita, person_k_baixo, person_k_esquerda);
        inimigo.mover(inimigo_k_cima, inimigo_k_direita, inimigo_k_baixo, inimigo_k_esquerda);
    }

    public void update() {
        //MOVIMENTA O PERSONAGEM
        personagem.setPosX(personagem.getPosX() + personagem.getVelX());
        personagem.setPosY(personagem.getPosY() + personagem.getVelY());
        inimigo.setPosX(inimigo.getPosX() + inimigo.getVelX());
        inimigo.setPosY(inimigo.getPosY() + inimigo.getVelY());
        // VERIFICA SE O PROJETIL ESTÁ NO ESTADO ATIVO, SE ESTIVER, ELE MOVIMENTA ESSE PROJETIL
        // O PROJETIL É ATIVO ENQUANTO A SUA POSIÇÃO FOR DIFERENTE DA POSIÇÃO DO SEU DESTINO
        
        for(Iterator i = estoqueMunicaoAtiva.iterator(); i.hasNext();){
            Projetil atual = (Projetil) i.next();
            atual.mover();
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
        } else if (inimigo.getPosX() <= 0) { // lado esquerdo
            inimigo.setPosX(0);
        }
        if (inimigo.getPosY() + (inimigo.getRaio() * 2) >= Principal.ALTURA_TELA) { // lado infeiror
            inimigo.setPosY(Principal.ALTURA_TELA - (inimigo.getRaio() * 2));
        } else if (inimigo.getPosY() <= 0) { // lado superior
            inimigo.setPosY(0);
        }
        // COLISÃO CIRCULAR
        int catetoH = personagem.getPosX() - inimigo.getPosX();
        int catetoV = personagem.getPosY() - inimigo.getPosY();
        double hipotenusa = Math.sqrt(Math.pow(catetoH, 2) + Math.pow(catetoV, 2));
        if (hipotenusa <= personagem.getRaio() + inimigo.getRaio()) { // verifica se houve colisão circular
            personagem.setPosX(personagem.getPosX() - personagem.getVelX()); // desfaz o movimento
            personagem.setPosY(personagem.getPosY() - personagem.getVelY()); // desfaz o movimento
        }

        //percorrer todos os projeteis ativos para testar as suas colisões
       for(Iterator i = estoqueMunicaoAtiva.iterator(); i.hasNext();){
            Projetil atual = (Projetil) i.next();
            // checar a colisão do projétil as tela
            if(atual.getPosX()>= Principal.LARGURA_TELA ||
                    atual.getPosX() <=0 ||
                    atual.getPosY() >= Principal.ALTURA_TELA ||
                    atual.getPosY() <= 0){
                try{
                    estoqueMunicao.put(estoqueMunicaoAtiva.take());
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            
            // checar a colisão do projétil com o personagem
            catetoH = atual.getPosX() - inimigo.getPosX();
            catetoV = atual.getPosY() - inimigo.getPosY();
            hipotenusa = Math.sqrt(Math.pow(catetoH, 2) + Math.pow(catetoV, 2));
            if(hipotenusa <= atual.getRaio() + inimigo.getRaio()){
                if (inimigo.getVivo()) {
                    inimigo.recebeDano(atual.getDano()); // RETIRA HP DO INIMIGO
                    System.out.println("Hp: " + inimigo.getHp());   
                    if (!inimigo.getVivo()) {
                        System.out.println("Dead");
                    }  
                }
            }
            
        }
                
           /* for (int i = 0; i < projetilAtivo.getlist_ativos().size();) {
                catetoH = projetilAtivo.getlist_ativos().get(i).getPosX() - inimigo.getPosX();
                catetoV = projetilAtivo.getlist_ativos().get(i).getPosY() - inimigo.getPosY();
                hipotenusa = Math.sqrt(Math.pow(catetoH, 2) + Math.pow(catetoV, 2));
                if (hipotenusa <= projetilAtivo.getlist_ativos().get(i).getRaio() + inimigo.getRaio()) { // verifica se houve colisão circula
                    if (inimigo.getVivo()) {
                        inimigo.recebeDano(projetilAtivo.getlist_ativos().get(i).getDano()); // RETIRA HP DO INIMIGO
                            System.out.println("Hp: " + inimigo.getHp());   
                        if (!inimigo.getVivo()) {
                            System.out.println("Dead");
                        }  
                    }
                }
                i++;
            }
        }
        //COLISÃO DO PROJETIL COM OS OBSTACULOS
        if (projetilAtivo.getlist_ativos().size() > 0) {
            for (int i = 0; i < projetilAtivo.getlist_ativos().size();) {
                for (int y = 0; y < obsLista.getObstaculo_list().size();) {
                    catetoH = projetilAtivo.getlist_ativos().get(i).getPosX() - obsLista.getObstaculo_list().get(y).getPosX();
                    catetoV = projetilAtivo.getlist_ativos().get(i).getPosY() - obsLista.getObstaculo_list().get(y).getPosY();
                    hipotenusa = Math.sqrt(Math.pow(catetoH, 2) + Math.pow(catetoV, 2));
                    if (hipotenusa <= projetilAtivo.getlist_ativos().get(i).getRaio() + obsLista.getObstaculo_list().get(y).getRaio()) { // verifica se houve colisão circular
                        projetilAtivo.getlist_ativos().get(i).setAtivo(false);
                    }
                    y++;
                }    
                i++;
            }
        }
        */
        // COLISÃO DO PERSONAGEM COM OS OBSTACULO DO MAPA
        for (int i = 0; i < obsLista.getObstaculo_list().size();) {
            catetoH = personagem.getPosX() - obsLista.getObstaculo_list().get(i).getPosX() ;
            catetoV = personagem.getPosY() - obsLista.getObstaculo_list().get(i).getPosY() ;
            hipotenusa = Math.sqrt(Math.pow(catetoH, 2) + Math.pow(catetoV, 2));
            if (hipotenusa <= obsLista.getObstaculo_list().get(i).getRaio() + personagem.getRaio()) { // verifica se houve colisão circular
              personagem.setPosX(personagem.getPosX() - personagem.getVelX()); // desfaz o movimento
                personagem.setPosY(personagem.getPosY() - personagem.getVelY()); // desfaz o movimento
            }
            i++;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        //g.setColor(Color.RED);
        g.drawImage(personagem.getImgAtual(), personagem.getPosX(), personagem.getPosY(), null);
        g.drawImage(inimigo.getImgAtual(), inimigo.getPosX(), inimigo.getPosY(), null);
       for(Iterator i = estoqueMunicaoAtiva.iterator(); i.hasNext();){
            Projetil atual = (Projetil) i.next();
            g.drawImage(atual.getImgAtual(), atual.getPosX(), atual.getPosY(), null);
        }      
      
        //GERADOR DOS OBSTACULOS
        for (int i = 0; i < obsLista.getObstaculo_list().size();) {
            g.drawImage(obsLista.getObstaculo_list().get(i).getImg(), obsLista.getObstaculo_list().get(i).getPosX(), obsLista.getObstaculo_list().get(i).getPosY(), null);
            i++;
        }

    }
}
