/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.Habilidades;

import controle.Habilidade;
import controle.Personagem;

/**
 *
 * @author Leandro
 */
public class Teleporte {
    private final int cooldown_habilidade;
    private int cooldown;
    private int distancia_salto;
    private boolean disponivel;
    
    public Teleporte(){
        this.cooldown_habilidade = 2000;
        this.distancia_salto = 180;
        this.disponivel = true;
    }


    public int getDistancia_salto() {
        return distancia_salto;
    }

    public void setDistancia_salto(int distancia_salto) {
        this.distancia_salto = distancia_salto;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown() {
        if(!isDisponivel()){
            System.out.println("Cooldown: "+cooldown);
            this.cooldown += 1;
            if(this.cooldown>=cooldown_habilidade){
                setDisponivel(true);
            }
        }
    }
    public String Teleportar(String direcao, int x, int y){
        if(isDisponivel()){
            switch(direcao){
                case "up": 
                    y -=distancia_salto; 
                    break;
                case "down": 
                    y += distancia_salto;
                    break;
                case "right": 
                    x+=distancia_salto;
                    break;
                case "left": 
                    x-=distancia_salto;
                    break;
                case "up-left": 
                    y -= distancia_salto;
                    x-=distancia_salto;
                    break;
                case "up-right": 
                    y -= distancia_salto;
                    x+=distancia_salto;
                    break;
                case "down-left": 
                    y += distancia_salto;
                    x-=distancia_salto;
                    break;
                case "down-right": 
                    y += distancia_salto;
                    x+=distancia_salto;
                    break;
            }
            setDisponivel(false);
            this.cooldown = 0;
            return x+":"+y;
        }else{
        }
        return x+":"+y;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    
    
    
}
