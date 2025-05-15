package scenes;

import Main.Game;
import UI.MyButton;

import java.awt.*;

import static Main.GameStates.*;

public class GameOver extends GameScene implements SceneMethods {

    private MyButton bReplay,bMenu;

    public GameOver(Game game) {
        super(game);
        initButtons();

    }

    private void initButtons() {
        int w=150;
        int h=w/3;
        int x=800/2-w/2;
        int y=150;
        int yOffset=100;


        bMenu=new MyButton("Menu",x,y,w,h);
        bReplay=new MyButton("Replay",x,y+yOffset,w,h);

    }

    @Override
    public void render(Graphics g) {
//game over text
        g.setFont(new Font("LucidaSans", Font.BOLD,30));

        g.setColor(Color.RED);
        g.drawString("Game Over!",320,50);
        //buttons
        g.setFont(new Font("LucidaSans", Font.BOLD,30));

        bMenu.draw(g);
bReplay.draw(g);
        //buttons



        //replay

        //menu
    }
    private void replayGame() {
    //reset everything

    resetAll();

    //change state to playing
SetGameState(PLAYING);
    }

    private void resetAll(){
        game.getPlaying().resetEverything();
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x,y)){
            SetGameState(MENU);

resetAll();

        }

        else if(bReplay.getBounds().contains(x,y))
           replayGame();

    }



    @Override
    public void mouseMoved(int x, int y) {

        bMenu.setMouseOver(false);
        bReplay.setMouseOver(false);

        if(bMenu.getBounds().contains(x,y))
            bMenu.setMouseOver(true);
        else if(bReplay.getBounds().contains(x,y))
            bReplay.setMouseOver(true);
    }

    @Override
    public void mousePressed(int x, int y) {
        if(bMenu.getBounds().contains(x,y))
            bMenu.setMousePressed(true);
        else if(bReplay.getBounds().contains(x,y))
            bReplay.setMousePressed(true);
    }

    @Override
    public void mouseReleased(int x, int y) {
bMenu.resetBooleans();
bReplay.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
