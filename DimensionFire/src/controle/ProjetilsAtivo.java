/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.ArrayList;

/**
 *
 * @author Leandro
 */
public class ProjetilsAtivo {
    private ArrayList<Projetil> list_ativos = new ArrayList();

    public ArrayList<Projetil> getlist_ativos() {
        return list_ativos;
    }

    public void setlist_ativos(Projetil projetil) {
        list_ativos.add(projetil);
    }
}
