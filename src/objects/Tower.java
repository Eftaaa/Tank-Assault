package objects;

import helps.Constants;

import static helps.Constants.Towers.*;


public class Tower {

    private int dmg,x,y,id,towerType,cdTick;
    private float  range, cooldown;
    private int tier;
public Tower(int x, int y, int id, int towerType){
    this.x=x;
    this.y=y;
    this.id=id;
    this.towerType=towerType;
tier=1;
    setDefaultDmg();
    setDefaultRange();
    setDefaultCooldown();
}
    public boolean isCooldownOver() {
    return cdTick>=cooldown;

    }
public void update(){
    cdTick++;
}
public void upgradeTower(){
    this.tier++;

    switch (towerType){
        case CANNON_1:
            dmg+=2;
            range+=5;
            cooldown-=5;
            break;
        case CANNON_2:
            dmg+=2;
            range+=5;
            cooldown-=5;
            break;
        case CANNON_3:
            dmg+=2;
            range+=5;
            cooldown-=5;
            break;
        case MACHINE_GUN_1:
            dmg+=2;
            range+=5;
            cooldown-=5;
            break;
        case MACHINE_GUN_2:
            dmg+=2;
            range+=5;
            cooldown-=5;
            break;
        case MACHINE_GUN_3:
            dmg+=2;
            range+=5;
            cooldown-=5;
            break;
        case ROCKET_LAUNCHER_1:
            dmg+=2;
            range+=5;
            cooldown-=5;
            break;
        case ROCKET_LAUNCHER_2:
            dmg+=2;
            range+=5;
            cooldown-=5;
            break;
        case ROCKET_LAUNCHER_3:
            dmg+=2;
            range+=5;
            cooldown-=5;
            break;






    }
}
    public void resetCooldown() {
    cdTick=0;
    }

    private void setDefaultCooldown() {
   cooldown= Constants.Towers.GetDefaultCooldown(towerType);
    }

    private void setDefaultRange() {
   range=     Constants.Towers.GetDefaultRange(towerType);
    }

    private void setDefaultDmg() {
      dmg= (int) Constants.Towers.GetDefaultRange(towerType);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTowerType() {
        return towerType;
    }

    public void setTowerType(int towerType) {
        this.towerType = towerType;
    }


    public int getDmg() {
        return dmg;
    }

    public float getRange() {
        return range;
    }

    public float getCooldown() {
        return cooldown;
    }
public int getTier(){
    return tier;
}

}
