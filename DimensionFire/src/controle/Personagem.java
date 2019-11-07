package controle;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;

public class Personagem extends Classe{
    protected int idCliente;
    private BufferedImage imagem;
    private boolean movimento;
    
    
    private int posX;
    private int posY;
    private int ultPosX;
    private int ultPosY;
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
    private Atributo atributo;
    
    
    private long idPerson;

    private AffineTransform af;
    
    public Personagem() {
        try {
            this.imagem         =   ImageIO.read(getClass().getResource("/imgs/personagem/persona_1.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        this.posX = 100;
        this.posY = 300;
        this.raio = 50;
        this.velX = 0;
        this.velY = 0;
        this.vivo = true;
        this.idPerson = 000000;
        
    }
    
    
    StringTokenizer pegarXY;
    public void habilidade_1(boolean ativo){
        if(atributo.getHabilidade().isCorrer()){ 
            if(ativo){
                atributo.setVelocidade(atributo.getHabilidade().getCorrer().correr(atributo.getVelocidade_padrao()));
                //atributo.getHabilidade().getCorrer().setPersonagem_parado(this.movimento);
            }else{
                atributo.setVelocidade(atributo.getVelocidade_padrao());
                //atributo.getHabilidade().getCorrer().setPersonagem_parado(this.movimento);
            }
        }
        if(atributo.getHabilidade().isTeleporte()){
             if(ativo){
                pegarXY = new StringTokenizer(atributo.getHabilidade().getTeleporte().Teleportar(getDirecao(), getPosX(), getPosY()));
                // Verifica o próximo token
                setPosX(Integer.parseInt(pegarXY.nextToken(":")));
                setPosY(Integer.parseInt(pegarXY.nextToken(":"))); 
                
            }
        }
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
        return atributo.getVelocidade();
    }
    
    public void mover(boolean kup,  boolean kright, boolean kdown, boolean kleft) {
        //System.out.println(kup+""+kright+""+kdown+""+kleft);
        this.movimento = false;
        if (kup == true) {
            this.movimento = true;
            setVelY(-atributo.getVelocidade());
            setDirecao("up");
            if (kleft == true) {
                setVelX(-atributo.getVelocidade());
                setDirecao("up-left");
            } else if (kright == true) {
                setVelX(atributo.getVelocidade());
                setDirecao("up-right");
            }
        } else if (kdown == true) {
            this.movimento = true;
            setVelY(atributo.getVelocidade());
            setDirecao("down");
            if (kleft == true) {
                setVelX(-atributo.getVelocidade());
                setDirecao("down-left");
            } else if (kright == true) {
                setVelX(atributo.getVelocidade());
                setDirecao("down-right");
            }
        } else if (kright == true) {
            this.movimento = true;
            setVelX(atributo.getVelocidade());
            setDirecao("right");
            
        } else if (kleft == true) {
            this.movimento = true;
            setVelX(-atributo.getVelocidade());
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

    public boolean isMovimento() {
        return movimento;
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
        ultPosX = getPosX();
        ultPosY = getPosY();
        return af;
    }
}
