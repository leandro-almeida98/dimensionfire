package controle;


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
    private double posX;
    private double posY;
    private double raio;
    private int velX;
    private int velY;
    private BufferedImage imgAtual;
    private int velocidade;
    private boolean ativo = false;
    int i=0;
    private Som som; 
    private Personagem personagem;
    private String direcao;
    
    // VARIAVEIS DO CALCULO DO TRAJETIL
    private double catetoX;
    private double catetoY;
    private double propocaoX;
    private double propocaoY;
    private double hipotenusa;
    private double angulo;
    private double angleRad;
    private double angleDeg;
    
    
    public Projetil() {

        try {
            String url = "/imgs/projetil/projetil_left.gif";
            this.direita_cima   =   ImageIO.read(getClass().getResource(url));
            this.direita_baixo  =   ImageIO.read(getClass().getResource(url));
            this.esquerda_cima  =   ImageIO.read(getClass().getResource(url));
            this.esquerda_baixo =   ImageIO.read(getClass().getResource(url));
            this.baixo      =   ImageIO.read(getClass().getResource(url));
            this.cima       =   ImageIO.read(getClass().getResource(url));
            this.direita    =   ImageIO.read(getClass().getResource(url));
            this.esquerda   =   ImageIO.read(getClass().getResource(url));
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
            
            //REGRA DE SINAIS, A multiplicação der positivo, + com + = +, se der negativo + com - da MENOS            
            setPosX(getPosX()+ (propocaoX * getVelocidade()));
            setPosY(getPosY()+ (propocaoY * getVelocidade()));
            
            if (propocaoY < 0) {
                setImgAtual(getCima());
                if (propocaoX < 0) {
                    setImgAtual(getEsquerda_cima());
                } else if (propocaoX > 0) {
                    setImgAtual(getDireita_cima());
                }
            } else if (propocaoY>0) {
                setImgAtual(getBaixo());
                 if (propocaoX < 0) {
                    setImgAtual(getEsquerda_baixo());
                } else if (propocaoX > 0) {
                    setImgAtual(getDireita_baixo());
                }
            } else if (propocaoX > 0) { 
                setImgAtual(getDireita());

            } else if (propocaoX < 0) { 
                setImgAtual(getEsquerda());
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

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getRaio() {
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

    public void setDirecao( int destinoX, int destinoY) {
        setPosX(personagem.getPosX()+personagem.getRaio());
        setPosY(personagem.getPosY()+personagem.getRaio());
        catetoX = (destinoX-getRaio()) - getPosX() ;
        catetoY = (destinoY-getRaio()) - getPosY();
        hipotenusa = Math.sqrt((catetoX*catetoX)+(catetoY*catetoY));
        propocaoX = catetoX  / hipotenusa ; 
        propocaoY = catetoY  / hipotenusa ; 
        
        angleRad = Math.atan2(catetoX,catetoY);
        angleDeg = Math.toDegrees(angleRad) -90; 
        if (angleDeg < 0) 
            angleDeg += 360; 
        else if (angleDeg > 360) 
            angleDeg -= 360;
        setAngulo(-angleDeg);
    }
    public double getAngulo() {
        return angulo;
    }
    public void setAngulo(double angulo){
        this.angulo = angulo;
    }
}
