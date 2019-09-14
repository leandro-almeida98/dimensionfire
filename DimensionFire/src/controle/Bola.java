package controle;


import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Bola {

    public BufferedImage parada;
    public BufferedImage direita_baixo;
    public BufferedImage direita_cima;
    public BufferedImage esquerda_baixo;
    public BufferedImage esquerda_cima;
    public BufferedImage direita;
    public BufferedImage esquerda;
    public BufferedImage cima;
    public BufferedImage baixo;
    public int posX;
    public int posY;
    public int raio;
    public int velX;
    public int velY;
    public BufferedImage imgAtual;

    public Bola() {

        try {
            parada = ImageIO.read(getClass().getResource("/imgs/parada.gif"));
            direita_cima = ImageIO.read(getClass().getResource("/imgs/direita_cima.gif"));
            direita_baixo = ImageIO.read(getClass().getResource("/imgs/direita_baixo.gif"));
            esquerda_cima = ImageIO.read(getClass().getResource("/imgs/esquerda_cima.gif"));
            esquerda_baixo = ImageIO.read(getClass().getResource("/imgs/esquerda_baixo.gif"));
            baixo = ImageIO.read(getClass().getResource("/imgs/baixo.gif"));
            cima = ImageIO.read(getClass().getResource("/imgs/cima.gif"));
            direita = ImageIO.read(getClass().getResource("/imgs/direita.gif"));
            esquerda = ImageIO.read(getClass().getResource("/imgs/esquerda.gif"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        posX = 300;
        posY = 200;
        raio = 50;
        velX = 0;
        velY = 0;

    }

    /*public BufferedImage obterImagem(){
    
     if (velX<0){//bola se move para esquerda
     if(velY<0){//bola subindo
     return esquerda_cima;
     }else{ // bola descendo
     return esquerda_baixo;            
     }
            
     }else{// se move para a direita
     if(velY <0){ // bola subindo
     return direita_cima;
                 
     }else{ // bola descendo
     return direita_baixo;
     }
     }
        
     }   */
}
