/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import controle.Habilidades.Correr;
import controle.Habilidades.Teleporte;

/**
 *
 * @author Leandro
 */
public class Habilidade {    
    private boolean b_correr;
    private boolean b_teleporte;
    private Teleporte teleporte;
    private Correr correr;
    public boolean isCorrer() {
        return b_correr;
    }

    public void setCorrer(boolean b_correr) {
        this.b_correr = b_correr;
        if(this.b_correr){
            correr = new Correr();
        }
    }

    public boolean isTeleporte() {
        return b_teleporte;
    }

    public void setTeleporte(boolean b_teleporte) {
        this.b_teleporte = b_teleporte;
        if(this.b_teleporte){
            teleporte = new Teleporte();
        }
    }    

    public Teleporte getTeleporte() {
        return teleporte;
    }

    public void setTeleporte(Teleporte teleporte) {
        this.teleporte = teleporte;
    }

    public Correr getCorrer() {
        return correr;
    }

    public void setCorrer(Correr correr) {
        this.correr = correr;
    }

}
