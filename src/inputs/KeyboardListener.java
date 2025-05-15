package inputs;

import Main.Game;
import Main.GameStates;
import scenes.Playing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static Main.GameStates.*;
public class KeyboardListener implements KeyListener {

    private Game game;
   public KeyboardListener(Game game){

       this.game=game;
   }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

//        if(e.getKeyCode()==KeyEvent.VK_A)
//            GameStates.gameState=MENU;
//        else
//            if(e.getKeyCode()==KeyEvent.VK_S)
//                GameStates.gameState= PLAYING;
//            else
//            if(e.getKeyCode()==KeyEvent.VK_D)
//                GameStates.gameState=SETTINGS;
    if(GameStates.gameState==EDIT)
        game.getEditor().keyPressed(e);

    else if(GameStates.gameState==PLAYING)
    {
        game.getPlaying().keyPressed(e);
    }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
