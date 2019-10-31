/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Habilidades;

/**
 *
 * @author Leandro
 */
public class Correr {

    private final int ESTAMINA;
    private final int COOLDOWN;
    private final int velocidade;
    private int estamina;
    private int cooldown;
    private boolean ativo;
    private boolean em_cooldown;
    private boolean movimento;

    public Correr() {
        this.velocidade = 25;
        this.COOLDOWN = 100;
        this.ESTAMINA = 5;

        this.estamina = ESTAMINA; // o quanto ele pode correr
        this.cooldown = 0; // o quanto ele terá que esperar para correr novamente
    }

    public void setCooldown() {

        if (movimento) {
            if (estamina < 1) {
                cooldown = COOLDOWN;
                ativo = false;
                
            }
        }else{
            
            // SE NÃO TIVER EM MOVIMENTO REGENERA ESTAMINA
            //if (!ativo) {
                //System.out.println("estamina: "+estamina);
                if (estamina < ESTAMINA) {
                    System.out.println("Recuperando estamina");
                    estamina++;
                }
            //}
        }
        
        
        if (cooldown > 0) {
            cooldown--;
            System.out.println("Aguarde, Em cooldown: " + cooldown);
        }
          
        movimento = false;
    }

    public int correr(int velocidade_padrao) {
        movimento = true;
        if (estamina > 0 && cooldown == 0) {
            ativo = true;
        }
        if (estamina > 0 && ativo && cooldown == 0) {
            this.estamina--;
//            System.out.println("Correndo, est:" + estamina);
            return this.velocidade;
        }

        return velocidade_padrao;
    }

}
