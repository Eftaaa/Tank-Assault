package managers;

import enemies.Enemy;
import helps.LoadSave;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class TowerManager {

    private Playing playing;
    private BufferedImage[] towerImgs;

private ArrayList<Tower> towers=new ArrayList<>();
private int towerAmount=0;

    public TowerManager(Playing playing){
this.playing=playing;
loadTowerImgs();


    }



    private void loadTowerImgs() {
    BufferedImage atlas= LoadSave.getSpriteAtlas();
    towerImgs=new BufferedImage[9];
    for(int i =0;i<9;i++){
        towerImgs[i]=atlas.getSubimage((6+i)*32,0,32,32);

    }
    }

    public void addTower(Tower selectedTower,int xPos,int yPos) {
    towers.add(new Tower(xPos,yPos,towerAmount++,selectedTower.getTowerType()));

    }

    public void removeTower(Tower displayedTower) {
    for(int i =0;i<towers.size();i++)
    {
        if(towers.get(i).getId()==displayedTower.getId())
            towers.remove(i);
    }


    }
    public void upgradeTower(Tower displayedTower) {
        for(Tower t:towers)
            if(t.getId()==displayedTower.getId())
                t.upgradeTower();
    }
    public void update(){
        for(Tower t:towers) {
            t.update();

            attackEnemyIfClose(t);


        }



        }

    private void attackEnemyIfClose(Tower t) {
        //tower shoot enemy

            for(Enemy e:playing.getEnemyManager().getEnemies()){

                if(e.isAlive())
                if(isEnemyInRange(t,e))
                {
                    if(t.isCooldownOver())
                   playing.shootEnemy(t,e);
                    t.resetCooldown();
                }
                else {
                //we do nothing

                }
            }

    }

    private boolean isEnemyInRange(Tower t, Enemy e) {

        int range=helps.Utilities.GetHypoDistance(t.getX(),t.getY(),e.getX(),e.getY());
        return range<t.getRange();


    }

    public void draw(Graphics g){

        for(Tower t:towers)
        {
        g.drawImage(towerImgs[t.getTowerType()],t.getX(),t.getY(),null );

        }


      //  g.drawImage(towerImgs[CANNON_1],tower.getX(),tower.getY(),null);


        }

    public Tower getTowerAt(int x, int y) {
    for(Tower t: towers)
        if(t.getX()==x)
            if(t.getY()==y)
                return t;
    return null;

    }

public BufferedImage[] getTowerImgs(){
        return towerImgs;
}



public void reset(){
        towers.clear();
        towerAmount=0;
}
}


