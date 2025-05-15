package UI;

import helps.Constants;
import helps.LoadSave;
import objects.Tower;
import scenes.GameOver;
import scenes.Playing;

import java.awt.*;
import java.text.DecimalFormat;

import static Main.GameStates.*;


public class ActionBar extends Bar {
    private Playing playing;
    private MyButton bMenu,bPause, bSave;
    private MyButton[] towerButtons;
    private Tower selectedTower;
    private Tower displayedTower;
    private MyButton sellTower,upgradeTower;
    private DecimalFormat formatter;
    private int gold=100;
    private boolean showTowerCost;
    private int towerCostType;

    private int lives = 1;
    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x,y,width,height);
        this.playing = playing;
        formatter=new DecimalFormat("0.0");

        initButtons();
    }
    public void resetEverything() {
lives =25;
towerCostType=0;
showTowerCost=false;
gold=100;
selectedTower=null;
displayedTower=null;

    }

    private void initButtons() {
        bMenu = new MyButton("Menu", 2, 642, 100, 30);
        bPause= new MyButton("Pause", 2, 680, 100, 30);
        bSave=new MyButton("Save",2,720,100,30);


        towerButtons=new MyButton[9];
        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffset = (int) (w * 1.1f);

        for(int i=0;i<towerButtons.length;i++)
        {
            towerButtons[i]=new MyButton("",xStart+xOffset*i,yStart,w,h,i);
        }
       // sellTower,upgradeTower;

        sellTower=new MyButton("Sell",560,760,50,30);
        upgradeTower=new MyButton("Upgrade",650,760,65,30);

    }
    public void removeOneLife(){
        lives--;
        if(lives<=0)
           SetGameState(GAME_OVER);
    }
    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bPause.draw(g);
        bSave.draw(g);

        for(MyButton b: towerButtons)
        {
            g.setColor(Color.gray);
            g.fillRect(b.x,b.y,b.width,b.height);
            g.drawImage(playing.getTowerManager().getTowerImgs()[b.getId()],b.x,b.y,b.width,b.height,null );

       drawButtonFeedback(g,b);

        }


    }

    public void draw(Graphics g)
    {
        //Background
        g.setColor(new Color(233,233,122));
        g.fillRect(x,y,width,height);
        //Butoane


        drawButtons(g);

        //Displayedtower
        drawDisplayedTower(g);
        // Wave info
        drawWaveInfo(g);
        //Gold info
        drawGoldAmount(g);
        //Draw Tower Cost
if(showTowerCost)
        drawTowerCost(g);
// Game pause text
        if(playing.isGamePaused())
        {
            g.setColor(Color.black);
            g.drawString("Game is Paused!",400,750);
        }
        //Lives
        g.setColor(Color.black);
        g.drawString("Lives: "+lives,100,750);

    }

    private void drawTowerCost(Graphics g) {
    g.setColor(Color.gray);
    g.fillRect(280,730,120,50);
    g.setColor(Color.black);
    g.drawRect(280,730,120,50);
    g.drawString(""+getTowerCostName(),285,750);
    g.drawString("Cost: "+getTowetCostCost()+"g",285,772);

//Show this if player lacks gold for the selected tower.
        if(isTowerCostMoreThanCurrentGold())
        {
            g.setColor(Color.RED);
            g.drawString("You don't have enough gold!",280,720);
        }
    }

    private boolean isTowerCostMoreThanCurrentGold() {

        return getTowetCostCost()>gold;

    }


    private String getTowerCostName() {

    return Constants.Towers.GetName(towerCostType);
    }
    private int getTowetCostCost() {

        return Constants.Towers.GetTowerCost(towerCostType);

    }
    private void drawGoldAmount(Graphics g) {
   g.drawString("Gold:"+gold,110,725);

    }

    private void drawWaveInfo(Graphics g) {
        g.setColor(Color.black);

        g.setFont(new Font("LucidaSans", Font.BOLD,15));

        drawWaveTimerInfo(g);
        drawEnemiesLeftInfo(g);
        drawWavesLeftInfo(g);

    }

    private void drawWavesLeftInfo(Graphics g) {
        int current=playing.getWaveManager().getWaveIndex();
        int size = playing.getWaveManager().getWaves().size();
    g.drawString("Wave "+(current+1) +"/" + size,100,770);

    }

    private void drawEnemiesLeftInfo(Graphics g)
    {
        int remaining = playing.getEnemyManager().getAmountOfAliveEnemies();

   g.drawString("Enemies Left:"+ remaining,100,785);

    }

    private void drawWaveTimerInfo(Graphics g){
        if(playing.getWaveManager().isWaveTimerStarted())
        {

            float timeLeft=playing.getWaveManager().getTimeLeft();

            String formatedText= formatter.format(timeLeft);

            g.drawString("Time Left: "+formatedText,250,750);
        }
    }

    private void drawDisplayedTower(Graphics g) {
   if(displayedTower!=null){
g.setColor(Color.gray);
g.fillRect(500,710,220,85);

   g.setColor(Color.black);
   g.drawRect(500,710,220,85);
   g.drawRect(502,745,50,50);


       g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()],500,745,50,50,null );

       g.setFont(new Font("LucidaSans", Font.BOLD,15));
       g.drawString(""+ Constants.Towers.GetName(displayedTower.getTowerType()),530,728);
       g.drawString("ID: "+ displayedTower.getId(),580,750);

       g.drawString("Tier: "+ displayedTower.getTier(),620,750);

       drawDisplayedTowerBorder(g);
        drawDisplayedTowerRange(g);

        //Sell button

       sellTower.draw(g);
       drawButtonFeedback(g,sellTower);
if(sellTower.isMouseOver()){
    //sell for x amount
    g.setColor(Color.RED);
g.drawString("Sell for: "+getSellAmount(displayedTower)+"g",255,730);
} else if (upgradeTower.isMouseOver()&& gold>=getUpgradeAmount(displayedTower)) {
    //upgrade for x amount
    g.setColor(Color.blue);
    g.drawString("Upgrade for: "+getUpgradeAmount(displayedTower)+"g",255,730);


}

       //Upgrade Button
if(displayedTower.getTier()<3 && gold >= getUpgradeAmount(displayedTower)){



        upgradeTower.draw(g);
        drawButtonFeedback(g,upgradeTower);
   }
   }

    }

    private int getUpgradeAmount(Tower displayedTower) {
    return (int)(Constants.Towers.GetTowerCost(displayedTower.getTowerType())*0.25f);

    }

    private int getSellAmount(Tower displayedTower) {

        int upgradeCost=(displayedTower.getTier()-1)*getUpgradeAmount(displayedTower);
       upgradeCost*=0.5f;

        return Constants.Towers.GetTowerCost(displayedTower.getTowerType())/2+upgradeCost;
    }

    private void drawDisplayedTowerRange(Graphics g) {
   g.setColor(Color.white);
   g.drawOval(displayedTower.getX()+16-(int)displayedTower.getRange()*2/2,
           displayedTower.getY()+16-(int)(displayedTower.getRange()*2)/2, (int)displayedTower.getRange()*2,(int)displayedTower.getRange()*2);

    }

    private void drawDisplayedTowerBorder(Graphics g) {

   g.setColor(Color.CYAN);
   g.drawRect(displayedTower.getX(), displayedTower.getY(), 32,32);
    }

    public void displayTower(Tower t) {

        displayedTower=t;


    }


    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            SetGameState(MENU);
        else if (bPause.getBounds().contains(x,y)) {

            togglePause();
        }
        else if (bSave.getBounds().contains(x, y))
            saveLevel();
        else {
            if(displayedTower != null)
            {
                if(sellTower.getBounds().contains(x,y))
                {
                    sellTowerClicked();
                    return;
                }
                else if (upgradeTower.getBounds().contains(x,y)&&displayedTower.getTier()<3 && gold>=getUpgradeAmount(displayedTower))
                {
upgradeTowerClicked();
                    return;
                }
            }


            for(MyButton b:towerButtons)
            {
                if(b.getBounds().contains(x,y))
                {
                    if(!isGoldEnoughForTower(b.getId()))
                        return;
                    selectedTower=new Tower(0,0,-1,b.getId());
                    playing.setSelectedTower(selectedTower);

                    return;
                }
            }
        }


    }

    private void saveLevel() {
        playing.saveLevel();
    }

    private void togglePause() {
 //   playing.setGamePaused(true);

//    if(playing.isGamePaused())
//    {
//        playing.setGamePaused(false);
//    }
//    else
//        playing.setGamePaused(true);
    playing.setGamePaused(!playing.isGamePaused());

    if(playing.isGamePaused())
        bPause.setText("Unpause");
    else
        bPause.setText("Pause");


    }


    private void sellTowerClicked() {
        playing.removeTower(displayedTower);
        gold+=Constants.Towers.GetTowerCost(displayedTower.getTowerType())/2;
        int upgradeCost=(displayedTower.getTier()-1)*getUpgradeAmount(displayedTower);
        upgradeCost*=0.5f;
        gold+=upgradeCost;
        displayedTower=null;
    }
    private void upgradeTowerClicked() {

playing.upgradeTower(displayedTower);
gold-=getUpgradeAmount(displayedTower);
    }

    private boolean isGoldEnoughForTower(int towerType) {

        return gold>=helps.Constants.Towers.GetTowerCost(towerType);

    }


    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bPause.setMouseOver(false);
        bSave.setMouseOver(false);
        showTowerCost=false;
        sellTower.setMouseOver(false);
        upgradeTower.setMouseOver(false);

        for(MyButton b:towerButtons)
        {
            b.setMouseOver(false);
        }
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
        else if (bSave.getBounds().contains(x, y))
            bSave.setMouseOver(true);
        else
        if(bPause.getBounds().contains(x,y))
        bPause.setMouseOver(true);

        {

            if(displayedTower != null)
            {
                if(sellTower.getBounds().contains(x,y))
                {
                    sellTower.setMouseOver(true);
                    return;
                }
                else if (upgradeTower.getBounds().contains(x,y)&&displayedTower.getTier()<3)
                {

                    upgradeTower.setMouseOver(true);
                    return;
                }
            }
            for(MyButton b:towerButtons)
            if(b.getBounds().contains(x,y)) {
                b.setMouseOver(true);
               showTowerCost=true;
                towerCostType=b.getId();
                return;
            }
            }

    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
        else if(bPause.getBounds().contains(x,y))
            bPause.setMousePressed(true);
        else if (bSave.getBounds().contains(x, y))
            bSave.setMousePressed(true);
        else {

            if(displayedTower!=null)
            {
if(sellTower.getBounds().contains(x,y))
{
    sellTower.setMousePressed(true);
    return;
} else if (upgradeTower.getBounds().contains(x,y)&&displayedTower.getTier()<3) {
    upgradeTower.setMousePressed(true);
    return;
}

            }

            for (MyButton b : towerButtons)
                if (b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                    return;
                }


        }
    }
    
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bPause.resetBooleans();
        bSave.resetBooleans();
        for(MyButton b:towerButtons)
         b.resetBooleans();

        sellTower.resetBooleans();
        upgradeTower.resetBooleans();

    }


    public void payForTower(int towerType) {
   this.gold-=helps.Constants.Towers.GetTowerCost(towerType);

    }

    public void addGold(int getReward) {
   this.gold += getReward;


    }

    public int getLives(){
        return lives;
    }



}
