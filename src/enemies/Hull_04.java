package enemies;
import managers.EnemyManager;

import static helps.Constants.Enemies.HULL_4;

public class Hull_04 extends Enemy{
    public Hull_04(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID,HULL_4,em);
    }
}
