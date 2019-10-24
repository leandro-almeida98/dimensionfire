/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

/**
 *
 * @author Leandro
 */
public class Atributo {
    private int velocidade;
    private int velocidade_padrao;
    private int dano;
    private int hp;
    private Habilidade habilidade;

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Habilidade getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(Habilidade habilidade) {
        this.habilidade = habilidade;
    }

    public int getVelocidade_padrao() {
        return velocidade_padrao;
    }

    public void setVelocidade_padrao(int velocidade_padrao) {
        this.velocidade_padrao = velocidade_padrao;
    }
    
}
