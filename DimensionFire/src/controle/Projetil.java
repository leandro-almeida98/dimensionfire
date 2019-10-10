package controle;


import static controle.Principal.ALTURA_TELA;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class  Projetil  {
    
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
    private boolean ativo = false;
    int i=0;
    private Som som; 
    private Personagem personagem;
    
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
    }

    
    // MOVE O PROJETIL
    public void mover() {
            switch(personagem.getDirecao()){
                case "up": 
                    setPosY(getPosY()-getVelocidade());
                    setImgAtual(getCima());
                    break;
                case "up-right": 
                    setPosY(getPosY()-getVelocidade());
                    setPosX(getPosX()+getVelocidade());
                    setImgAtual(getDireita_cima());
                    break;
                case "up-left": 
                    setPosY(getPosY()-getVelocidade());
                    setPosX(getPosX()-getVelocidade());
                    setImgAtual(getEsquerda_cima());
                    break;
                case "right": 
                    setPosX(getPosX()+getVelocidade());
                    setImgAtual(getDireita());
                    break;
                case "left": 
                    setPosX(getPosX()-getVelocidade());
                    setImgAtual(getEsquerda());
                    break;    
                case "down-left": 
                    setPosY(getPosY()+getVelocidade());
                    setPosX(getPosX()-getVelocidade());
                    setImgAtual(getEsquerda_baixo());
                    break;
                case "down": 
                    setPosY(getPosY()+getVelocidade());
                    setImgAtual(getBaixo());
                    break;
                
                case "down-right": 
                    setPosY(getPosY()+getVelocidade());
                    setPosX(getPosX()+getVelocidade());
                    setImgAtual(getDireita_baixo());
                    break;    
            }
            if(getPosX() >= Principal.LARGURA_TELA || getPosY() >= Principal.ALTURA_TELA || getPosX() <=0 || getPosY() <= 0 ){
                this.ativo = false;
                this.i = 0;
            }
    }
    
    
    // METODO QUE INICIA E DA CARACTERISTICAS AO PROJETIL
    public void setAtivo(boolean ativo) {
        if(ativo){
            getSom().Shoot();
            setPosX(personagem.getPosX()+personagem.getRaio());
            setPosY(personagem.getPosY()+personagem.getRaio());
            this.ativo = true;
        }else{
            this.ativo = false;
        }
    }
    
    //
    //GETTER & SETTERS
    //
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
        return velocidade;
    }
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }
    public boolean isAtivo() {
        return ativo;
    }
    
    public int getDano() {
        return personagem.getAtributo().getDano();
    }

    public Som getSom() {
        return som;
    }

    public void setSom(Som som) {
        this.som = som;
    }

    public long getIdPersonagem() {
        return personagem.getIdPerson();
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }


}
