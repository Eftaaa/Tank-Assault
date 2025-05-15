package Main;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Dimension;

public class GameScreen extends JPanel {


    private Game game;
    private Dimension size;
    private MyMouseListener myMouseListener;
    private KeyListener keyboardListener;


    public GameScreen(Game game)
    {

       this.game=game;

        setPanelSize();


    }
    public void initInputs(){
        myMouseListener=new MyMouseListener(game);
        keyboardListener= new KeyboardListener(game);

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();


    }

    private void setPanelSize() {
        size =new Dimension(800,800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);

    }



    public void paintComponent(Graphics g){
        //Super classJpanel

        super.paintComponent(g);
        game.getRender().render(g);

//        g.drawImage(sprites.get(0).getScaledInstance(32,32,1),0,0,null);
      //  g.drawImage(img.getSubimage(128,0,128,128).getScaledInstance(32,32,1),0,0,null);










    }




}
