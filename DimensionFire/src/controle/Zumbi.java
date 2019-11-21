package controle;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;

public class Zumbi{
    private BufferedImage imagem;    
    private int posX;
    private int posY;
    private int raio;
    private int velX;
    private int velY;
    
    // VARIAVEIS DO CALCULO DO TRAJETIL
    private double angulo;
    private double angleRad;
    private double angleDeg;
    private double catetoX;
    private double catetoY;  
    
    private String direcao;
    private boolean vivo;
    private int hp;

    private AffineTransform af;
    
    public Zumbi() {
        try {
            this.imagem         =   ImageIO.read(getClass().getResource("/imgs/personagem/persona_1.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        this.posX = 400;
        this.posY = 100;
        this.raio = 50;
        this.velX = 0;
        this.velY = 0;
        this.vivo = true;
        
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

    public BufferedImage getImagem() {
        return imagem;
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }
    public int getVelocidade() {
        return 3;
    }
    
    public void mover(int PosX, int PosY) {
        
       
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }
    
    
    public void setDirecaoMouse( int destinoX, int destinoY) {
        catetoX = (destinoX-getRaio()) - getPosX() ;
        catetoY = (destinoY-getRaio()) - getPosY();
        angleRad = Math.atan2(catetoX,catetoY);
        angleDeg = Math.toDegrees(angleRad) -90; 
        if (angleDeg < 0) 
            angleDeg += 360; 
        else if (angleDeg > 360) 
            angleDeg -= 360;
        setAngulo(-angleDeg);
    }
    
    public AffineTransform affinetransform(){
        af = new AffineTransform();
        af.translate(getPosX(), getPosY());
        af.rotate(Math.toRadians(getAngulo()),getRaio(),getRaio());
        return af;
    }
}
