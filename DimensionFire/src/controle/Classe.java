/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.ArrayList;
import java.util.Random;

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
        
        atributo.setDano(15);
        atributo.setHp(100);
        atributo.setVelocidade(2);
        
        habilidade.setTeleporte(true);
        
        atributo.setHabilidade(habilidade);
        
        personagem.setAtributo(atributo);
        
        return personagem;
    }
    
    public Personagem Julios(){
        personagem = new Personagem();
        atributo = new Atributo();
        habilidade = new Habilidade();
        
        personagem.setIdPerson(entrada.nextLong());
        personagem.setPosX(700);
        
        atributo.setDano(10);
        atributo.setHp(200);
        atributo.setVelocidade_padrao(3);
        
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
