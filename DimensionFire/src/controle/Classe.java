/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Leandro
 */
public class Classe {

    private Personagem personagem;
    private Atributo atributo;
    private Habilidade habilidade;

    Random entrada = new Random();

    public Classe() {
    }

    public Personagem Matias() {
        personagem = new Personagem();
        atributo = new Atributo();
        habilidade = new Habilidade();
        
        personagem.setIdPerson(entrada.nextLong());
        personagem.setPosX(500);
        
        atributo.setDano(25);
        atributo.setHp(100);
        atributo.setVelocidade(2);
        
        //habilidade.setTeleporte(true);
        atributo.setHabilidade(habilidade);
        personagem.setAtributo(atributo);
        
        return personagem;
    }
    
    public Personagem Julios(){
        personagem = new Personagem();
        atributo = new Atributo();
        habilidade = new Habilidade();
        int x = entrada.nextInt(Principal.LARGURA_TELA);
        int y = entrada.nextInt(Principal.ALTURA_TELA);
        personagem.setIdPerson(entrada.nextLong());
        personagem.setPosX(x>personagem.getRaio()?(x-personagem.getRaio()):(x+personagem.getRaio()));
        personagem.setPosY(y>personagem.getRaio()?(y-personagem.getRaio()):(y+personagem.getRaio()));
        try {
             personagem.setImagem(ImageIO.read(getClass().getResource("/imgs/personagem/zombie.png")));
        } catch (Exception e) {
        }
        atributo.setDano(10);
        atributo.setHp(100);
        atributo.setVelocidade_padrao(3);
        atributo.setVelocidade(3);
        habilidade.setCorrer(true);
        atributo.setHabilidade(habilidade);
        personagem.setAtributo(atributo);
        return personagem;
    }

    public Habilidade getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(Habilidade habilidade) {
        this.habilidade = habilidade;
    }
}
