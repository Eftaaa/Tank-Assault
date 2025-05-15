package enemies;
import managers.EnemyManager;

import static helps.Constants.Enemies.HULL_1;
public class Hull_01 extends Enemy{
    public Hull_01(float x, float y, int ID, EnemyManager em){

        super (x,y,ID,HULL_1,em);
        health=50;
    }
}
