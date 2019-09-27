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
public class ProjeteisAtivo {

    private int tamanho;
    private NodoProjeteis cabeca;

    public ProjeteisAtivo() {
        this.tamanho = 0;
        this.cabeca = null;
    }

    public boolean inserir(int index_valor, int index) {
        if (index < 0 || index > tamanho) {
            System.out.println("Indice Invalido");
            return false;
        }
        NodoProjeteis novo = new NodoProjeteis(index_valor);
        if (index == 0) {
            novo.prox = cabeca;
            cabeca = novo;
        } else {
            int i = 0;
            NodoProjeteis pont = cabeca;
            while (i != index - 1) {
                i++;
                pont = pont.prox;
            }
            novo.prox = pont.prox;
            pont.prox = novo;
        }
        tamanho++;
        return true;
    }

    public NodoProjeteis buscar(int index_valor) {
        NodoProjeteis elem = cabeca;
        while (elem != null) {
            if (elem.info == index_valor) {
                return elem;
            }
            elem = elem.prox;
        }
        return null;
    }

    public boolean remover(int index_valor) {
        if (tamanho == 0) {
            System.out.println("Lista Vazia");
            return false;
        }
        if (cabeca.info == index_valor) {
            cabeca = cabeca.prox;
            tamanho--;
            return true;
        }
        NodoProjeteis elem = cabeca;
        while (elem.prox != null) {
            if (elem.prox.info == index_valor) {
                break;
            }
            elem = elem.prox;
        }
        if (elem.prox == null) {
            elem.info = 0;
            //System.out.println("Retirado :"+cabeca);
            //cabeca = cabeca.prox;
            //tamanho--;
            return false;
        }
        elem.prox = elem.prox.prox;
        tamanho--;
        return true;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

}
