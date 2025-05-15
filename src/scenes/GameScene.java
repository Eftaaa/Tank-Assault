package scenes;

import Main.Game;

import java.awt.image.BufferedImage;

public class GameScene {

    protected int animationIndex;
    protected int ANIMATION_SPEED=25;
    protected int tick;
    protected Game game;

    public GameScene(Game game) {
        this.game = game;
    }
   
    public Game getGame() {
        return game;
    }  
    
    protected boolean isAnimation(int spriteID) {
        return game.getTileManager().isSpriteAnimation(spriteID);

    }
    protected void updateTick() {
        tick++;
        if(tick>50){
            tick=0;
            animationIndex++;
            if(animationIndex>=2){
                animationIndex=0;
            }
        }
    }


    
     protected BufferedImage getSprite(int spriteID) {
        return game.getTileManager().getSprite(spriteID);
    }
    protected BufferedImage getSprite(int spriteID,int animationIndex) {
        return game.getTileManager().getAniSprite(spriteID,animationIndex);
    }
    }
