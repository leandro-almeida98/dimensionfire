package controle;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Personagem extends Classe{
    protected int id;
    private BufferedImage parada;
    private BufferedImage direita_baixo;
    private BufferedImage direita_cima;
    private BufferedImage esquerda_baixo;
    private BufferedImage esquerda_cima;
    private BufferedImage direita;
    private BufferedImage esquerda;
    private BufferedImage cima;
    private BufferedImage baixo;
    private BufferedImage imgAtual;
    
    private int posX;
    private int posY;
    private int raio;
    private int velX;
    private int velY;
    
    private String direcao;
    private boolean vivo;
    private Atributo atributo;
    
    
    private long idPerson;
    

    public Personagem() {
        try {
            this.parada         =   ImageIO.read(getClass().getResource("/imgs/parada.gif"));
            this.direita_cima   =   ImageIO.read(getClass().getResource("/imgs/direita_cima.gif"));
            this.direita_baixo  =   ImageIO.read(getClass().getResource("/imgs/direita_baixo.gif"));
            this.esquerda_cima  =   ImageIO.read(getClass().getResource("/imgs/esquerda_cima.gif"));
            this.esquerda_baixo =   ImageIO.read(getClass().getResource("/imgs/esquerda_baixo.gif"));
            this.baixo      =   ImageIO.read(getClass().getResource("/imgs/baixo.gif"));
            this.cima       =   ImageIO.read(getClass().getResource("/imgs/cima.gif"));
            this.direita    =   ImageIO.read(getClass().getResource("/imgs/direita.gif"));
            this.esquerda   =   ImageIO.read(getClass().getResource("/imgs/esquerda.gif"));
        } catch (IOException e) {
            System.out.println(e);
        }
        this.posX = 100;
        this.posY = 300;
        this.raio = 50;
        this.velX = 0;
        this.velY = 0;
        this.vivo = true;
    }

    public BufferedImage getParada() {
        return parada;
    }

    public void setParada(BufferedImage parada) {
        this.parada = parada;
    }

    public BufferedImage getDireita_baixo() {
        return direita_baixo;
    }

    public void setDireita_baixo(BufferedImage direita_baixo) {
        this.direita_baixo = direita_baixo;
    }

    public BufferedImage getDireita_cima() {
        return direita_cima;
    }

    public void setDireita_cima(BufferedImage direita_cima) {
        this.direita_cima = direita_cima;
    }

    public BufferedImage getEsquerda_baixo() {
        return esquerda_baixo;
    }

    public void setEsquerda_baixo(BufferedImage esquerda_baixo) {
        this.esquerda_baixo = esquerda_baixo;
    }

    public BufferedImage getEsquerda_cima() {
        return esquerda_cima;
    }

    public void setEsquerda_cima(BufferedImage esquerda_cima) {
        this.esquerda_cima = esquerda_cima;
    }

    public BufferedImage getDireita() {
        return direita;
    }

    public void setDireita(BufferedImage direita) {
        this.direita = direita;
    }

    public BufferedImage getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(BufferedImage esquerda) {
        this.esquerda = esquerda;
    }

    public BufferedImage getCima() {
        return cima;
    }

    public void setCima(BufferedImage cima) {
        this.cima = cima;
    }

    public BufferedImage getBaixo() {
        return baixo;
    }

    public void setBaixo(BufferedImage baixo) {
        this.baixo = baixo;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getRaio() {
        return raio;
    }

    public void setRaio(int raio) {
        this.raio = raio;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public BufferedImage getImgAtual() {
        return imgAtual;
    }

    public void setImgAtual(BufferedImage imgAtual) {
        this.imgAtual = imgAtual;
    }
    public int getVelocidade() {
        return atributo.getVelocidade();
    }
    
    public void mover(boolean kup,  boolean kright, boolean kdown, boolean kleft) {
        setImgAtual(getParada());
        if (kup == true) {
            setVelY(-getVelocidade());
            setImgAtual(getCima());
            setDirecao("up");
            if (kleft == true) {
                setVelX(-getVelocidade());
                setImgAtual(getEsquerda_cima());
                setDirecao("up-left");
            } else if (kright == true) {
                setVelX(getVelocidade());
                setImgAtual(getDireita_cima());
                setDirecao("up-right");
            }
        } else if (kdown == true) {
            setVelY(getVelocidade());
            setImgAtual(getBaixo());
            setDirecao("down");
            if (kleft == true) {
                setVelX(-getVelocidade());
                setImgAtual(getEsquerda_baixo());
                setDirecao("down-left");
            } else if (kright == true) {
                setVelX(getVelocidade());
                setImgAtual(getDireita_baixo());
                setDirecao("down-right");
            }
        } else if (kright == true) {
            setVelX(getVelocidade());
            setImgAtual(getDireita());
            setDirecao("right");
            
        } else if (kleft == true) {
            setVelX(-getVelocidade());
            setImgAtual(getEsquerda());
            setDirecao("left");
        }
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public int getHp() {
        return atributo.getHp();
    }

    public void setHp(int hp) {
        atributo.setHp(hp);
    }
    public void recebeDano(int dano){
        if(getHp()-dano <1){
            if(getVivo()){
                setVivo(false);
            }
        }else{
            setHp(getHp() - dano);
        }
    }

    public boolean getVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(long idPerson) {
        this.idPerson = idPerson;
    }

    public Atributo getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributo atributos) {
        this.atributo = atributos;
    }
    int distancia;
    public void habilidade_1(){
        if(atributo.getHabilidade().isCorrer()){ 
            atributo.setVelocidade(getVelocidade() +5);
        }
        if(atributo.getHabilidade().isTeleporte()){
            distancia = 20;
            System.out.println("TP");
            switch(getDirecao()){
                case "up": 
                    setPosY(getPosY()-distancia); 
                    break;
                case "down": 
                    setPosY(getPosY()+distancia);
                    break;
                case "right": 
                    setPosX(getPosX()+distancia);
                    break;
                case "left": 
                    setPosX(getPosX()+distancia);
                    break;
                case "up-left": 
                    setPosY(getPosY()-distancia);
                    setPosX(getPosX()-distancia);
                    break;
                case "up-right": 
                    setPosY(getPosY()-distancia);
                    setPosX(getPosX()+distancia);
                    break;
                case "down-left": 
                    setPosY(getPosY()+distancia);
                    setPosX(getPosX()-distancia);
                    break;
                case "down-right": 
                    setPosY(getPosY()+distancia);
                    setPosX(getPosX()+distancia);
                    break;
            }
            ;
        }
    }
}
