package UI;

import helps.LoadSave;
import objects.Tile;
import scenes.Editing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Main.GameStates.MENU;
import static Main.GameStates.SetGameState;

public class Toolbar extends Bar {
	private Editing editing;
	private MyButton bMenu, bSave;

	private MyButton bPathStart,bPathEnd;
	private BufferedImage pathStart,pathEnd;
	private Tile selectedTile;

	//private ArrayList<MyButton> tileButtons = new ArrayList<>();

	private Map<MyButton, ArrayList<Tile>> map = new HashMap<MyButton, ArrayList<Tile>>();
	private MyButton bGrass,bWater,bRoadS,bTurns,bBeaches,bBeaches2;
	private MyButton currentButton;
	private int currentIndex=0;

	public Toolbar(int x, int y, int width, int height, Editing editing) {
		super(x, y, width, height);
		this.editing=editing;
		initPathImgs();
		initButtons();	}
	private void initPathImgs(){
		pathStart= LoadSave.getSpriteAtlas().getSubimage(0,32*5,32,32);
		pathEnd=LoadSave.getSpriteAtlas().getSubimage(32,5*32,32,32);
	}
	private void initButtons() {
		bMenu = new MyButton("Menu", 2, 642, 100, 30);
		bSave=new MyButton("Save",2,674,100,30);
		int w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 650;
		int xOffset = (int) (w * 1.1f);
		int i = 0;
		bGrass=new MyButton("Grass",xStart,yStart,w,h,i++);
		bWater=new MyButton("Water",xStart+xOffset,yStart,w,h,i++);
		initMapButton(bRoadS,editing.getGame().getTileManager().getRoadsS(),xStart,yStart,xOffset,w,h,i++);
		initMapButton(bBeaches2,editing.getGame().getTileManager().getBeaches2(),xStart,yStart,xOffset,w,h,i++);
		initMapButton(bBeaches,editing.getGame().getTileManager().getBeaches(),xStart,yStart,xOffset,w,h,i++);
		initMapButton(bTurns,editing.getGame().getTileManager().getTurns(),xStart,yStart,xOffset,w,h,i++);

		bPathStart=new MyButton("PathStart",xStart,yStart+xOffset,w,h,i++);
		bPathEnd=new MyButton("PathEnd",xStart+xOffset,yStart+xOffset,w,h,i++);

	}

private void initMapButton(MyButton b, ArrayList<Tile> list, int x, int y, int xOff, int w, int h, int id) {
		b = new MyButton("", x + xOff * id, y, w, h, id);
		map.put(b, list);
	}




	private void saveLevel() {
		editing.saveLevel();
	}


	public void rotateSprite() {

		currentIndex++;
		if (currentIndex >= map.get(currentButton).size())
			currentIndex = 0;
		selectedTile = map.get(currentButton).get(currentIndex);
		editing.setSelectedTile(selectedTile);

	}



	public void draw(Graphics g)
	{
		//Background
		g.setColor(new Color(233,233,122));
		g.fillRect(x,y,width,height);
		//Butoane
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		bSave.draw(g);
		//   drawTileButtons(g);

		drawPathButton(g,bPathStart,pathStart);
		drawPathButton(g,bPathEnd,pathEnd);


	//	bPathStart.draw(g);
	//	bPathEnd.draw(g);

		drawNormalButton(g,bGrass);
		drawNormalButton(g,bWater);
		drawSelectedTile(g);
		drawMapButtons(g);
	}

	private void drawPathButton(Graphics g, MyButton b, BufferedImage img) {
				g.drawImage(img, b.x, b.y, b.width, b.height, null);
		drawButtonFeedback(g,b);


	}

	private void drawNormalButton(Graphics g, MyButton b) {
		g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);
		drawButtonFeedback(g,b);
	}












	private void drawMapButtons(Graphics g) {
		for (Map.Entry<MyButton, ArrayList<Tile>> entry : map.entrySet()) {
			MyButton b = entry.getKey();
			BufferedImage img = entry.getValue().get(0).getSprite();

			g.drawImage(img, b.x, b.y, b.width, b.height, null);
			drawButtonFeedback(g, b);
		}

	}


	private void drawSelectedTile(Graphics g) {

		if (selectedTile != null) {
			g.drawImage(selectedTile.getSprite(), 550, 700, 50, 50, null);
			g.setColor(Color.black);
			g.drawRect(550, 700, 50, 50);
		}

	}

	public BufferedImage getButtImg(int id) {
		return editing.getGame().getTileManager().getSprite(id);
	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);
		else if (bSave.getBounds().contains(x, y))
			saveLevel();
		else if (bWater.getBounds().contains(x, y)) {
			selectedTile = editing.getGame().getTileManager().getTile(bWater.getId());
			editing.setSelectedTile(selectedTile);
			return;
		} else if (bGrass.getBounds().contains(x, y)) {
			selectedTile = editing.getGame().getTileManager().getTile(bGrass.getId());
			editing.setSelectedTile(selectedTile);
			return;
		}
		else if(bPathStart.getBounds().contains(x,y))
		{
			selectedTile= new Tile(pathStart,-1,-1);
			editing.setSelectedTile(selectedTile);
		
		}
		else if(bPathEnd.getBounds().contains(x,y))
		{
			selectedTile= new Tile(pathEnd,-2,-2);
			editing.setSelectedTile(selectedTile);
		
		}


		else {
			for (MyButton b : map.keySet())
				if (b.getBounds().contains(x, y)) {
					selectedTile = map.get(b).get(0);
					editing.setSelectedTile(selectedTile);
					currentButton = b;
					currentIndex = 0;
					return;
				}
		}

	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bSave.setMouseOver(false);
		bWater.setMouseOver(false);
		bGrass.setMouseOver(false);
		bPathStart.setMouseOver(false);
		bPathEnd.setMouseOver(false);
		for (MyButton b : map.keySet())
			b.setMouseOver(false);

		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else if (bSave.getBounds().contains(x, y))
			bSave.setMouseOver(true);
		else if (bWater.getBounds().contains(x, y))
			bWater.setMouseOver(true);
		else if (bGrass.getBounds().contains(x, y))
			bGrass.setMouseOver(true);
		else if(bPathStart.getBounds().contains(x,y))
			bPathStart.setMouseOver(true);
		else if(bPathEnd.getBounds().contains(x,y))
			bPathEnd.setMouseOver(true);
		else {
			for (MyButton b : map.keySet())
				if (b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					return;
				}
		}

	}

	public void mousePressed(int x, int y) {

		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		else if (bSave.getBounds().contains(x, y))
			bSave.setMousePressed(true);
		else if (bWater.getBounds().contains(x, y))
			bWater.setMousePressed(true);
		else if (bGrass.getBounds().contains(x, y))
			bGrass.setMousePressed(true);
		else if (bPathStart.getBounds().contains(x,y))
			bPathStart.setMousePressed(true);
		else if (bPathEnd.getBounds().contains(x,y))
			bPathEnd.setMousePressed(true);
		else {
			for (MyButton b : map.keySet())
				if (b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}
		}

	}

	public void mouseReleased(int x, int y) {

		bMenu.resetBooleans();
		bSave.resetBooleans();
		bGrass.resetBooleans();
		bWater.resetBooleans();
		bPathStart.resetBooleans();
		bPathEnd.resetBooleans();

		for (MyButton b : map.keySet())
			b.resetBooleans();

	}

	public BufferedImage getStartPathImg(){
		return pathStart;
	}
	public BufferedImage getEndPathImg(){
		return pathEnd;
	}


}
