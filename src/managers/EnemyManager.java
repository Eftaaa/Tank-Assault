package managers;

import enemies.*;
import helps.Constants;
import helps.LoadSave;
import objects.PathPoint;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.ArrayList;
import static helps.Constants.Direction.*;
import static helps.Constants.Enemies.*;
import static helps.Constants.Tiles.*;



public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImgs;
    private Enemy testEnemy;
    private ArrayList<Enemy> enemies= new ArrayList<>();
  // private float speed=0.5f;
   private PathPoint start,end;
   private int HPbarWidth=20;
    public EnemyManager(Playing playing, PathPoint start,PathPoint end)
    {

        this.playing=playing;
         enemyImgs=new BufferedImage[4];
        this.start =start;
        this.end=end;

//        addEnemy(HULL_1);
//        addEnemy(HULL_2);
//        addEnemy(HULL_3);
//        addEnemy(HULL_4);

        loadEnemyImgs();


    }

    private void loadEnemyImgs() {
        BufferedImage atlas=LoadSave.getSpriteAtlas();

        for(int i=0;i<4;i++)
        {
            enemyImgs[i]= atlas.getSubimage(5*32,i*32,32,32);

        }
    }



    public void draw(Graphics g)
    {
        for(Enemy e : enemies){
            if(e.isAlive()) {
                drawEnemy(e, g);
                drawHealthBar(e, g);
            }
        }
     }

    private void drawHealthBar(Enemy e, Graphics g) {

    g.setColor(Color.red);
    g.fillRect((int)e.getX() + 16 -(getNewBarWidth(e)/2),(int)e.getY()-10,getNewBarWidth(e),3);
    }

    private int getNewBarWidth(Enemy e){
    return (int)  (HPbarWidth*e.getHealthBarFloat());
    }

    private void drawEnemy(Enemy e,Graphics g){
        g.drawImage(enemyImgs[e.getEnemyType()],(int)(e.getX()),(int)(e.getY()),null);



    }

public void addEnemy(int enemyType){
        int x= start.getxCord()*32;
        int y=start.getyCord()*32;


   switch(enemyType){
       case HULL_1:
           enemies.add(new Hull_01(x,y,0,this));
           break;
       case HULL_2:
           enemies.add(new Hull_02(x,y,0,this));

           break;
       case HULL_3:
           enemies.add(new Hull_03(x,y,0,this));

           break;
       case HULL_4:
           enemies.add(new Hull_04(x,y,0,this));

           break;

   }
  //  enemies.add(new Hull_01(x,y,0));

}

    public void udpate() {





        for(Enemy e:enemies)
           if(e.isAlive())
             updateEnemyMove(e);

    }





    public void updateEnemyMove(Enemy e) {

        if(e.getLastDir()==-1)
            setNewDirectionAndMove(e);
        //e pos
        //e dir
        //tile at new possible pos
        int newX=(int)(e.getX() + getSpeedAndWidth(e.getLastDir(),e.getEnemyType()));
        int newY=(int)(e.getY()+getSpeedAndHeight(e.getLastDir(),e.getEnemyType()));

if(getTileType(newX,newY)==ROAD_TILE){
    //continua sa mearga in aceeasi directie
    e.move(GetSpeed(e.getEnemyType()),e.getLastDir());
}else if(isAtEnd(e)){
    //a ajuns la sfarsit
    e.kill();
playing.removeOneLife();


        }
else {
    //gaseste directie noua

    setNewDirectionAndMove(e);

}


    }

    private void setNewDirectionAndMove(Enemy e) {
  int dir=e.getLastDir();


  //move into the current till 100%
        int xCord= (int) (e.getX()/32);
        int yCord= (int) (e.getY()/32);

        fixEnemyOffsetTile(e,dir,xCord,yCord);

        if(isAtEnd(e))
            return;



        if(dir==LEFT || dir==RIGHT){
      int newY=(int)(e.getY()+getSpeedAndHeight(UP,e.getEnemyType()));
      if(getTileType((int)e.getX(),newY)==ROAD_TILE){
          e.move(GetSpeed(e.getEnemyType()),UP);

      }
      else
          e.move(GetSpeed(e.getEnemyType()),DOWN);
  }
  else {
      int newX=(int)(e.getX() + getSpeedAndWidth(RIGHT,e.getEnemyType()));

      if(getTileType(newX,(int)e.getY())==ROAD_TILE)
      {
          e.move(GetSpeed(e.getEnemyType()),RIGHT);
      }
      else
          e.move(GetSpeed(e.getEnemyType()),LEFT);
  }


    }

    private void fixEnemyOffsetTile(Enemy e, int dir, int xCord, int yCord) {
    switch(dir){
//        case LEFT:
//            if(xCord>0)
//                xCord--;
//            break;
//        case UP:
//            if(yCord>0)
//                yCord--;
//            break;
        case RIGHT:
            if(xCord<24)
                xCord++;
            break;
        case DOWN:
            if(yCord<19)
                yCord++;
            break;
    }

    e.setPos(xCord*32,yCord*32);

    }

    private boolean isAtEnd(Enemy e) {

        if(e.getX()==end.getxCord()*32)
            if(e.getY()==end.getyCord()*32)
                return true;


        return false;
    }

    private int getTileType(int x, int y) {
       return playing.getTileType(x,y);


    }

    private float getSpeedAndHeight(int dir,int enemyType) {
        if(dir==UP)
            return -GetSpeed(enemyType);
        else if(dir==DOWN)
            return GetSpeed(enemyType)+32;

        return 0;
    }

    private float getSpeedAndWidth(int dir,int enemyType) {
if(dir==LEFT)
    return -GetSpeed(enemyType);
else if(dir==RIGHT)
    return GetSpeed(enemyType)+32;
        return 0;
    }
    public void spawnEnemy(int nextEnemy) {
addEnemy(nextEnemy);
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }


    public int getAmountOfAliveEnemies() {
   int size=0;
   for(Enemy e: enemies)

       if(e.isAlive())
           size++;

   return size;


    }

    public void rewardPlayer(int enemyType){

        playing.rewardPlayer(enemyType);

    }
    public void reset(){
        enemies.clear();
    }
}
