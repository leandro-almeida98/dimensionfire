package controle;


import static controle.Principal.ALTURA_TELA;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class  Projetil extends Personagem {
    
    private BufferedImage direita_baixo;
    private BufferedImage direita_cima;
    private BufferedImage esquerda_baixo;
    private BufferedImage esquerda_cima;
    private BufferedImage direita;
    private BufferedImage esquerda;
    private BufferedImage cima;
    private BufferedImage baixo;
    private int posX;
    private int posY;
    private int raio;
    private int velX;
    private int velY;
    private BufferedImage imgAtual;
    private int velocidade;
    private String direcao;
    private boolean ativo = false;
    private int dano;
    int i=0;
    private Som som; 
    
    public Projetil() {

        try {
            this.direita_cima   =   ImageIO.read(getClass().getResource("/imgs/projetil/projetil_up-right.gif"));
            this.direita_baixo  =   ImageIO.read(getClass().getResource("/imgs/projetil/projetil_down-right.gif"));
            this.esquerda_cima  =   ImageIO.read(getClass().getResource("/imgs/projetil/projetil_up-left.gif"));
            this.esquerda_baixo =   ImageIO.read(getClass().getResource("/imgs/projetil/projetil_down-left.gif"));
            this.baixo      =   ImageIO.read(getClass().getResource("/imgs/projetil/projetil_down.gif"));
            this.cima       =   ImageIO.read(getClass().getResource("/imgs/projetil/projetil_up.gif"));
            this.direita    =   ImageIO.read(getClass().getResource("/imgs/projetil/projetil_right.gif"));
            this.esquerda   =   ImageIO.read(getClass().getResource("/imgs/projetil/projetil_left.gif"));
        } catch (IOException e) {
            System.out.println(e);
        }
        
        som = new Som();
        this.raio = 12;
        this.velX = 0;
        this.velY = 0;
        this.velocidade = 10;
        this.dano = 15;
    }
    public int dono(){
        return id;
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
    
    
    // MOVE O PROJETIL
    public void mover() {

            switch(getDirecao()){
                case "up": 
                    setPosY(getPosY()-getVelocidade());
                    setImgAtual(getCima());
                    //System.out.println("UP : "+ getPosY());
                    break;
                case "up-right": 
                    setPosY(getPosY()-getVelocidade());
                    setPosX(getPosX()+getVelocidade());
                    setImgAtual(getDireita_cima());
                    //System.out.println("UP-RIGHT : X"+getPosX()+" Y"+ getPosY());
                    break;
                case "up-left": 
                    setPosY(getPosY()-getVelocidade());
                    setPosX(getPosX()-getVelocidade());
                    setImgAtual(getEsquerda_cima());
                    //System.out.println("UP-LEFT : X"+getPosX()+" Y"+ getPosY());
                    break;
                case "right": 
                    setPosX(getPosX()+getVelocidade());
                    setImgAtual(getDireita());
                    //System.out.println("RIGHT : X"+getPosX()+" Y"+ getPosY());
                    break;
                case "left": 
                    setPosX(getPosX()-getVelocidade());
                    setImgAtual(getEsquerda());
                    //System.out.println("LEFT : X"+getPosX()+" Y"+ getPosY());
                    break;    
                case "down-left": 
                    setPosY(getPosY()+getVelocidade());
                    setPosX(getPosX()-getVelocidade());
                    setImgAtual(getEsquerda_baixo());
                    //System.out.println("DOWN-LEFT : X"+getPosX()+" Y"+ getPosY());
                    break;
                case "down": 
                    setPosY(getPosY()+getVelocidade());
                    setImgAtual(getBaixo());
                    //System.out.println("DOWN : X"+getPosX()+" Y"+ getPosY());
                    break;
                
                case "down-right": 
                    setPosY(getPosY()+getVelocidade());
                    setPosX(getPosX()+getVelocidade());
                    setImgAtual(getDireita_baixo());
                    //System.out.println("DOWN-RIGHT : X"+getPosX()+" Y"+ getPosY());
                    break;    
            }
            if(getPosX() >= Principal.LARGURA_TELA || getPosY() >= Principal.ALTURA_TELA || getPosX() <=0 || getPosY() <= 0 ){
                this.ativo = false;
                this.i = 0;
                setDirecao("invisivel");
            }
    }
    
    
    public int getVelocidade() {
        return velocidade;
    }
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        if(!this.ativo){
            getSom().Shoot();
        }
        this.ativo = ativo;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public Som getSom() {
        return som;
    }

    public void setSom(Som som) {
        this.som = som;
    }
}
