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

    public int index;
    public int elem;
    public NodoProjeteis prox;
    public NodoProjeteis ant;

    public NodoProjeteis(int valor) {
        this.elem = valor;
        this.prox = null;
        this.ant = null;
    }
}
