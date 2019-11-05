package controle;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class  Projetil  {
    
    private double posX;
    private double posY;
    private double raio;
    private int velX;
    private int velY;
    private BufferedImage imagem;
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
    
    // desenhar
    private Graphics2D graphics2D;
    private AffineTransform af;
    
    public Projetil() {

        try {
            this.imagem =   ImageIO.read(getClass().getResource("/imgs/projetil/projetil_left.gif"));
        } catch (IOException e) {
            System.out.println(e);
        }
        
        som = new Som();
        this.raio = 12;
        this.velX = 0;
        this.velY = 0;
        this.velocidade = 10;
        af = new AffineTransform();
    }

    
    
    // MOVE O PROJETIL
    public void mover() {     
            
            //REGRA DE SINAIS, A multiplicação der positivo, + com + = +, se der negativo + com - da MENOS            
            setPosX(getPosX()+ (propocaoX * getVelocidade()));
            setPosY(getPosY()+ (propocaoY * getVelocidade()));
            
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

    public BufferedImage getImagem() {
        return imagem;
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
    
    public AffineTransform affinetransform(Graphics g){
        graphics2D = (Graphics2D)g;
        af = new AffineTransform();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        af.translate(getPosX(), getPosY()); // PEGA A POSIÇÃO DA IMAGEM QUE VAI SER ROTACIONADA
        af.rotate(Math.toRadians(getAngulo()), getRaio(), getRaio()); // GIRAR A IMAGEM
        return af;
    }
}
