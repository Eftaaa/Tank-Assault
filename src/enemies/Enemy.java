package enemies;
import helps.Constants;
import managers.EnemyManager;

import static helps.Constants.Direction.*;
import java.awt.*;

public abstract class Enemy {

    protected EnemyManager enemyManager;
    protected float x,y;
    protected Rectangle bounds;
    protected int health;

    protected int maxHealth;
    protected int ID;
    protected int enemyType;
    protected int lastDir;
    protected boolean alive=true;

    public Enemy(float x, float y, int ID, int enemyType, EnemyManager enemyManager)
    {

this.x=x;
this.y=y;
this.ID=ID;
this.enemyType=enemyType;
this.enemyManager=enemyManager;
bounds=new Rectangle((int)x,(int)y,32,32);
lastDir=-1;

setStartHealth();
    }



    private void setStartHealth(){


      health=  Constants.Enemies.GetStartHealth(enemyType);
        maxHealth = health;
    }

    public void hurt(int dmg) {
        this.health -= dmg;

        if (health <= 0) {
            alive = false;
            enemyManager.rewardPlayer(enemyType);


        }
    //
    }
    public void kill(){
        //Omoara inamicul atunci cand ajunge la sfarsit
        alive=false;
        health=0;
    }
public void move(float speed,int dir){
     lastDir=dir;


      switch (dir){
          case LEFT:
              this.x-=speed;

          break;
          case UP:
              this.y-=speed;
              break;
          case RIGHT:
              this.x+=speed;
              break;
          case DOWN:
              this.y+=speed;
              break;
      }
      updateHitbox();

}

    private void updateHitbox() {
        bounds.x=(int)x;
        bounds.y=(int)y;
    }

    public void setPos(int x,int y){
       //Nu folosi asta pentru miscare, asta este pentru rezolvarea pozitiei

        this.x=x;
        this.y=y;

}

public float getHealthBarFloat(){
        return health/(float)maxHealth;
}
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }

    public int getID() {
        return ID;
    }

    public int getEnemyType() {
        return enemyType;
    }
    public int getLastDir(){
        return lastDir;
    }

    public boolean isAlive() {
        return alive;
    }
}
