package enemies;
import managers.EnemyManager;

import static helps.Constants.Enemies.HULL_2;

public class Hull_02 extends Enemy {
    public Hull_02(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID,HULL_2,em);
    }
}
