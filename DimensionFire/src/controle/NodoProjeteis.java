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
public class NodoProjeteis {
    public int info;
    public NodoProjeteis prox;
    public NodoProjeteis (int valor){
        this.info = valor;
        this.prox = null;
    }
}
