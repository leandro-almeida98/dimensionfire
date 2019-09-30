/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Leandro
 */
public class Mapa  {
    private Obstrucoes obstaculo;
    
    ArrayList<Obstrucoes> lista = new ArrayList<Obstrucoes>();


    
    public ArrayList<Obstrucoes> map(){
        try {
            obstaculo = new Obstrucoes();
            obstaculo.setRaio(50);
            obstaculo.setImg(ImageIO.read(getClass().getResource("/imgs/parada2.gif")));
            obstaculo.setPosX(338);
            obstaculo.setPosY(352);
            lista.add(obstaculo);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            obstaculo = new Obstrucoes();
            obstaculo.setRaio(50);
            obstaculo.setImg(ImageIO.read(getClass().getResource("/imgs/parada2.gif")));
            obstaculo.setPosX(150);
            obstaculo.setPosY(130);
            lista.add(obstaculo);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            obstaculo = new Obstrucoes();
            obstaculo.setRaio(50);
            obstaculo.setImg(ImageIO.read(getClass().getResource("/imgs/parada2.gif")));
            obstaculo.setPosX(50);
            obstaculo.setPosY(400);
             lista.add(obstaculo);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  lista; 
    }
}
