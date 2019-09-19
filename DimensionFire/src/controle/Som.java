package controle;

import java.applet.Applet;
import java.io.InputStream;
import javafx.scene.media.AudioClip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.SwingUtilities;

public class Som {

    public void tela_Inicial() {
        try {
            // Carrega o arquivo de áudio (não funciona com .mp3, só .wav) 
            String resource = "/sons/som_tela_inicial.wav";
            InputStream input = getClass().getResourceAsStream(resource);
            Clip oClip = AudioSystem.getClip();
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(input);
            oClip.open(audioInput);
            System.out.println("Toque");
            oClip.loop(10); // Toca varias vezes
            //clip.loop(Clip.LOOP_CONTINUOUSLY); // Toca continuamente (para o caso de músicas)
            // Para a execução (senão o programa termina antes de você ouvir o som)
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                }
            });
            
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    
}
