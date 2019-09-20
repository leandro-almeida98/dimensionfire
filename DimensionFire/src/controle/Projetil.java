package controle;


import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Projetil {
    
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
    private int direction;
    Principal p = new Principal();
    private boolean ativo = false;

    public Projetil() {

        try {
            setDireita_cima(ImageIO.read(getClass().getResource("/imgs/parada2.gif")));
            setDireita_baixo(ImageIO.read(getClass().getResource("/imgs/parada2.gif")));
            setEsquerda_cima(ImageIO.read(getClass().getResource("/imgs/parada2.gif")));
            setEsquerda_baixo(ImageIO.read(getClass().getResource("/imgs/parada2.gif")));
            setBaixo(ImageIO.read(getClass().getResource("/imgs/parada2.gif")));
            setCima(ImageIO.read(getClass().getResource("/imgs/parada2.gif")));
            setDireita(ImageIO.read(getClass().getResource("/imgs/parada2.gif")));
            setEsquerda(ImageIO.read(getClass().getResource("/imgs/parada2.gif")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setRaio(25);
        setVelX(0);
        setVelY(0);
        setVelocidade(3);
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
    
    public void mover() {
        if(ativo){
            switch(getDirection()){
                case 1: 
                    setVelY(-getVelocidade()); 
                    break;
                case 2: break;
                case 3: break;
                case 4: break;
            }
            if(getPosX() >= p.LARGURA_TELA || getPosY() >= p.ALTURA_TELA || getPosX() <=0 || getPosY() <= 0 ){
                ativo = false;
            }
        }
        /*if (direction_up == true) {
            setVelY(-getVelocidade());
            setImgAtual(getCima());
            if (direction_left == true) {
                setVelX(-getVelocidade());
                setImgAtual(getEsquerda_cima());
            } else if (direction_right == true) {
                setVelX(getVelocidade());
                setImgAtual(getDireita_cima());
            }
        } else if (direction_down == true) {
            setVelY(getVelocidade());
            setImgAtual(getBaixo());
            if (direction_left == true) {
                setVelX(-getVelocidade());
                setImgAtual(getEsquerda_baixo());
            } else if (direction_right == true) {
                setVelX(getVelocidade());
                setImgAtual(getDireita_baixo());
            }
        } else if (direction_right == true) {
            setVelX(getVelocidade());
            setImgAtual(getDireita());
        } else if (direction_left == true) {
            setVelX(-getVelocidade());
            setImgAtual(getEsquerda());
        }
        */
    }
    public int getVelocidade() {
        return velocidade;
    }
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }
    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
    public boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
