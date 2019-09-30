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
public class ProjeteisAtivo {

    private int tamanho;
    private NodoProjeteis cabeca;

    public ProjeteisAtivo() {
        this.tamanho = 0;
        this.cabeca = null;
    }

    /*public boolean inserir(int index_valor, int index) {
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
    }*/
    public boolean inserir(int elemento) {
        NodoProjeteis novo = new NodoProjeteis(elemento);
        NodoProjeteis pont = cabeca;
        if (tamanho == 0) {
            novo.prox = novo;
            cabeca = novo;
        } else {
            while (pont.prox != cabeca) {
                pont = pont.prox;
            }
            pont.prox = novo;
            novo.ant = pont;
            novo.prox = cabeca;
            cabeca.ant = novo;
        }
        tamanho++;
        return true;
    }

    public NodoProjeteis buscar(int index_valor) {
        NodoProjeteis elem = cabeca;
        while (elem != null) {
            if (elem.index == index_valor) {
                return elem;
            }
            elem = elem.prox;
        }
        return null;
    }

    public void printList() {
        NodoProjeteis elem = cabeca;
        if (elem == null) {
            System.out.println(" -------------------LISTA VAZIA---------------------");
            return;
        }
        for (int i = 0; i < tamanho;) {
            System.out.println("Elemento valor Anterior :" + elem.ant.elem);
            System.out.println("Valor:" + elem.elem);
            System.out.println("Elemento valor Proximo :" + elem.prox.elem);
            System.out.println(" ------");
            elem = elem.prox;
            i++;
        }
    }

    public boolean remover(int valor) {
        //printList();
        if (tamanho == 0) {
            System.out.println("Lista Vazia");
            return false;
        }
        if (cabeca.elem == valor) {
            cabeca = cabeca.prox;
            //System.out.println("Retirou da cabeça");
            tamanho--;
            return true;
        }
        if (tamanho == 1) {
            System.out.println("Lista Vazia");
            return false;
        }
        NodoProjeteis elem = cabeca;
        int i = 0;
        while (elem.prox != null) {
            if (elem.prox.elem == valor) {
                break;
            }
            if (i > tamanho) {
                //System.out.println("Elemento não existe");
                return false;
            }
            i++;
            elem = elem.prox;
        }
        elem.prox = elem.prox.prox;
        elem.prox.ant = elem;
        System.out.println("Retirou depois da cabeça");
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
