/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Leandro
 */
public class Comunicar {
    private final int idCliente;
    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;
    private boolean person_k_disparo = false;
    
    public Comunicar(int idCliente){
        this.idCliente = idCliente;
    }
    
    public void setMovimento(boolean up, boolean down, boolean left, boolean right){
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }
    
    
}
