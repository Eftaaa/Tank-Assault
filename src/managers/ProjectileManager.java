package managers;
import enemies.Enemy;
import helps.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helps.Constants.Projectiles.*;
import static helps.Constants.Towers.*;



public class ProjectileManager {
    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
 private ArrayList<Explosion>explosions=new ArrayList<>();
    private BufferedImage[] proj_imgs,explo_imgs;
    private int proj_id=0;

private Point2D.Float exploPos;
    public ProjectileManager(Playing playing){
    this.playing=playing;
    importImgs();

    }
    private void importImgs(){
        BufferedImage atlas= LoadSave.getSpriteAtlas();

       proj_imgs=new BufferedImage[3];
        for(int i=0;i<3;i++)
        {
            proj_imgs[i]=atlas.getSubimage((15+i)*32,0,32,32);
        }
		importExplosion(atlas);
    }

	private void importExplosion(BufferedImage atlas) {

		explo_imgs=new BufferedImage[7];

		for(int i=0;i<7;i++)
			explo_imgs[i]= atlas.getSubimage((18+i)*32,0,32,32);

	}

	public void newProjectile(Tower t, Enemy e) {
		int type = getProjType(t);

		int xDist = (int) (t.getX() - e.getX());
		int yDist = (int) (t.getY() - e.getY());
		int totDist = Math.abs(xDist) + Math.abs(yDist);

		float xPer = (float) Math.abs(xDist) / totDist;

		float xSpeed = xPer * helps.Constants.Projectiles.GetSpeed(type);
		float ySpeed = helps.Constants.Projectiles.GetSpeed(type) - xSpeed;

		if (t.getX() > e.getX())
			xSpeed *= -1;
		if (t.getY() > e.getY())
			ySpeed *= -1;

		float arcValue = (float) Math.atan(yDist / (float) xDist);
		float rotate = (float) Math.toDegrees(arcValue);

		if (xDist < 0)
			rotate += 180;

		for(Projectile p:projectiles)
			if(p.isActive())
				if(p.getProjectileType()==type)
				{
					p.reuse(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDmg(), rotate);

				return;
				}
				projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDmg(), rotate, proj_id++, type));

	}



   	public void update() {
		for (Projectile p : projectiles)
			if (p.isActive()) {
				p.move();
				if (isProjHittingEnemy(p)) {
					p.setActive(false);
					if(p.getProjectileType()==MISSLE)
					explosions.add(new Explosion(p.getPos()));
					explodeOnEnemies(p);

				} else if (isProjOutsideBounds(p)){
					p.setActive(false);

				}
			}
for(Explosion e:explosions)
	if(e.getIndex()<7)
	e.update();

	}



	private void explodeOnEnemies(Projectile p) {
		for(Enemy e:playing.getEnemyManager().getEnemies()){

			if(e.isAlive())
			{
				float radius= 40.0f;

				float xDist=Math.abs(p.getPos().x-e.getX());
				float yDist=Math.abs(p.getPos().y-e.getY());
				float realDist =(float)Math.hypot(xDist,yDist);

				if(realDist<=radius)
				{
					e.hurt((p.getDmg()));
				}
			}

		}

	}

	private boolean isProjHittingEnemy(Projectile p) {
		for (Enemy e : playing.getEnemyManager().getEnemies()) {
			if(e.isAlive())
			if (e.getBounds().contains(p.getPos())) {
				e.hurt(p.getDmg());
				return true;
			}
		}
		return false;
	}
	private boolean isProjOutsideBounds(Projectile p) {
		if(p.getPos().x>0)
			if(p.getPos().x<800)
				if(p.getPos().y>=0)
					if(p.getPos().y<800)
						return false;
		return true;

	}

public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

//		for(int i=0;i<explo_imgs.length;i++)
//			g2d.drawImage(explo_imgs[i],300+i*32,300,null);
		for (Projectile p : projectiles)
			if (p.isActive()) {
				g2d.translate(p.getPos().x, p.getPos().y);
				g2d.rotate(Math.toRadians(p.getRotation()));
				g2d.drawImage(proj_imgs[p.getProjectileType()], -16, -16, null);
				g2d.rotate(-Math.toRadians(p.getRotation()));
				g2d.translate(-p.getPos().x, -p.getPos().y);
			}

	drawExplosions(g2d);

	}

	private void drawExplosions(Graphics2D g2d) {

		for(Explosion e:explosions){
			if(e.getIndex()<7)
			{
				g2d.drawImage(explo_imgs[e.getIndex()],(int)e.getPos().x-16,(int)e.getPos().y-16,null);
			}
		}
	}


	private int getProjType(Tower t) {
switch (t.getTowerType()){
    case CANNON_1:
        return BULLET_CANNON;
    case CANNON_2:
        return BULLET_CANNON;
    case CANNON_3:
        return BULLET_CANNON;
    case MACHINE_GUN_1:
        return BULLET_MG;
    case MACHINE_GUN_2:
        return BULLET_MG;
    case MACHINE_GUN_3:
        return BULLET_MG;
    case ROCKET_LAUNCHER_1:
       return MISSLE;
    case ROCKET_LAUNCHER_2:
        return MISSLE;
    case ROCKET_LAUNCHER_3:
        return MISSLE;




}
return 0;
    }

	public class Explosion{
		private Point2D.Float pos;
		private int exploTick=0,exploIndex=0;
		public Explosion(Point2D.Float pos){
			this.pos=pos;


		}
		public void update(){

				exploTick++;
				if(exploTick>=12)
				{
					exploTick=0;
					exploIndex++;

				}

		}
public int getIndex(){
			return exploIndex;
}
		public Point2D.Float getPos(){
			return pos;
		}



	}
	public void reset(){

		projectiles.clear();
		explosions.clear();

		proj_id=0;

	}

}
