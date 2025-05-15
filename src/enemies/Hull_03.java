package enemies;
import managers.EnemyManager;

import static helps.Constants.Enemies.HULL_3;

public class Hull_03 extends Enemy{
    public Hull_03(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID,HULL_3,em);
    }
}
