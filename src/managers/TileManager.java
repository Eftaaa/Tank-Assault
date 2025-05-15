package managers;

import helps.ImgFix;
import helps.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import static helps.Constants.Tiles.*;
public class TileManager {

    public Tile TURN_90,TURN_180,TURN_270,UP_SHORE_90,UP_SHORE_180,UP_SHORE_270,ROAD_90,ROAD_180,ROAD_270,GRASS,WATER,ROAD,TURN,DW_SHORE,UP_SHORE,DW_SHORE_90,DW_SHORE_180,DW_SHORE_270;
  
  
  
    public BufferedImage atlas;
    public ArrayList<Tile> tiles= new ArrayList<>();
    public ArrayList<Tile> roadsS= new ArrayList<>();
    public ArrayList<Tile> turns= new ArrayList<>();
    public ArrayList<Tile> beaches= new ArrayList<>();
    public ArrayList<Tile> beaches2= new ArrayList<>();

    
    
    public TileManager(){

        loadAtlas();
        CreateTiles();
    }

   
    private void CreateTiles() {
        
        int id =0;
        
        tiles.add(GRASS=new Tile(getSprite(0,0),id++,GRASS_TILE));
        tiles.add(WATER=new Tile(getAniSprites(0,4), id++, WATER_TILE));
        
        roadsS.add(ROAD=new Tile(getSprite(0,1), id++, ROAD_TILE));
        roadsS.add(ROAD_90=new Tile(ImgFix.getRotImg(getSprite(0,1),90),id++,ROAD_TILE));
        roadsS.add(ROAD_180=new Tile(ImgFix.getRotImg(getSprite(0,1),180),id++,ROAD_TILE));
        roadsS.add(ROAD_270=new Tile(ImgFix.getRotImg(getSprite(0,1),270),id++,ROAD_TILE));


        beaches.add(DW_SHORE=new Tile(getSprite(2,4),id++,GRASS_TILE));
        beaches.add(DW_SHORE_90=new Tile(ImgFix.getRotImg(getSprite(2,4),90),id++,GRASS_TILE));
        beaches.add(DW_SHORE_180=new Tile(ImgFix.getRotImg(getSprite(2,4),180),id++,GRASS_TILE));
        beaches.add(DW_SHORE_270=new Tile(ImgFix.getRotImg(getSprite(2,4),270),id++,GRASS_TILE));



        beaches2.add(UP_SHORE=new Tile(getSprite(1,4),id++,GRASS_TILE));
        beaches2.add(UP_SHORE_90=new Tile(ImgFix.getRotImg(getSprite(1,4),90),id++,GRASS_TILE));
        beaches2.add(UP_SHORE_180=new Tile(ImgFix.getRotImg(getSprite(1,4),180),id++,GRASS_TILE));
        beaches2.add(UP_SHORE_270=new Tile(ImgFix.getRotImg(getSprite(1,4),270),id++,GRASS_TILE));


        turns.add(TURN=new Tile(getSprite(1,2),id++,ROAD_TILE));
        turns.add(TURN_90=new Tile(ImgFix.getRotImg(getSprite(1,2),90),id++,ROAD_TILE));
        turns.add(TURN_180=new Tile(ImgFix.getRotImg(getSprite(1,2),180),id++,ROAD_TILE));
        turns.add(TURN_270=new Tile(ImgFix.getRotImg(getSprite(1,2),270),id++,ROAD_TILE));

        tiles.addAll(roadsS);

        tiles.addAll(beaches);
        tiles.addAll(beaches2);
        tiles.addAll(turns);
    }
public boolean isSpriteAnimation(int spriteID){
       return tiles.get(spriteID).isAnimation();

}
    private BufferedImage[] getImgs(int firstX, int firstY,int secondX, int secondY)
    {
        return new BufferedImage[]{getSprite(firstX,firstY),getSprite(secondX,secondY)};
    }
    private void loadAtlas() {
    atlas= LoadSave.getSpriteAtlas();
    }

    public Tile getTile(int id) {
        return tiles.get(id);
    }
    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }
    public BufferedImage getAniSprite(int id,int animationIndex) {
        return tiles.get(id).getSprite(animationIndex);
    }
    private BufferedImage[] getAniSprites(int xCord, int yCord) {
        BufferedImage[]arr=new BufferedImage[2];



            arr[0]=getSprite(4,3);
        arr[1]=getSprite(0,4);
        return arr;
    }
    private BufferedImage getSprite(int xCord, int yCord) {
        return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
    }

    public ArrayList<Tile> getRoadsS() {
        return roadsS;
    }

    public ArrayList<Tile> getTurns() {
        return turns;
    }

    public ArrayList<Tile> getBeaches() {
        return beaches;
    }

    public ArrayList<Tile> getBeaches2() {
        return beaches2;
    }
}
