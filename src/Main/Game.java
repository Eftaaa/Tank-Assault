    package Main;
    import helps.LoadSave;
    import inputs.KeyboardListener;
    import inputs.MyMouseListener;
    import managers.DBManager;
    import managers.TileManager;
    import org.sqlite.core.DB;
    import scenes.*;

    import javax.imageio.ImageIO;
    import javax.swing.*;
    import java.awt.event.KeyListener;
    import java.awt.image.BufferedImage;
    import java.io.IOException;
    import java.io.InputStream;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.stream.StreamSupport;

    public class Game extends JFrame implements Runnable{
       private GameScreen gameScreen;
        private long lastUpdate;
        private double timePerUpdate;


        private Thread gameThread;
        private final double FPS_SET =120.0;
        private final double UPS_SET =60.0;


        //Clase

        private Render render;
        private Menu menu;
        private Playing playing;
        private Settings settings;
        private Editing editing;
        private GameOver gameOver;
        private TileManager tileManager;


        public Game(){


            initClasses();
            createDefaultLevel();
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            setResizable(false);

            //saveTitle("Tower Defense");
            add(gameScreen); //Adauga JPanel in fereastra
            pack();
            setVisible(true);
        }
        private void createDefaultLevel() {
            int[]arr=new int[500];
            for(int i=0;i<arr.length;i++)
                arr[i]=0;
            LoadSave.CreateLevel("new_level",arr);
        }
        private void initClasses() {

            tileManager = new TileManager();

            render= new Render(this);
            gameScreen=new GameScreen(this);
            menu= new Menu(this);
            playing=new Playing(this);
            settings=new Settings(this);

            editing=new Editing(this);

            gameOver=new GameOver(this);

        }




            private void start(){

            gameThread=new Thread(this){};


            gameThread.start();

        }






            private void updateGame()
            {
                switch(GameStates.gameState){

                    case EDIT:
                        editing.update();
                        break;
                    case MENU:
                        break;
                    case PLAYING:
                        playing.update();
                        break;
                    case SETTINGS:
                        break;
                        default:
                break;

                }
            }
        public static void main(String[] args){
            System.out.println(("Start of my Game"));

            Game game=new Game();
            game.gameScreen.initInputs();


            game.start();
            DBManager.connect();
            insert(0,"HULL_1");
            insert(1,"HULL_2");
            insert(2,"HULL_3");
            insert(3,"HULL_4");
    readAllData();

        }
       private static void readAllData()
       {
           Connection con = DBManager.connect();
           PreparedStatement ps =null;
           ResultSet rs=null;
           try {
               String sql="SELECT * FROM tanks";
               ps=con.prepareStatement(sql);
               rs=ps.executeQuery();
               System.out.println("ALL tanks\n");

               while(rs.next())
               {
                   int id=rs.getInt("id");
                   String name=rs.getString("name");
                   System.out.println("Id:"+id);
                   System.out.println("Name:"+name);



               }
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }

        private static void insert (int id, String name)
        {
            Connection con = DBManager.connect();

            PreparedStatement ps=null;

        try{
    String sql="INSERT INTO tanks(id, name)VALUES(?,?)";
    ps=con.prepareStatement(sql);
    ps.setInt(1,id);
    ps.setString(2,name);
    ps.execute();
    System.out.println("Data has been inserted!");
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }

        }

        @Override
        public void run() {



            double timePerFrame = 1000000000.0 / FPS_SET;
            double timePerUpdate=1000000000.0 / UPS_SET;

            long lastFrame = System.nanoTime();
            long lastUpdate=System.nanoTime();
            long lastTimeCheck = System.currentTimeMillis();


            int frames=0;
            int updates=0;
            long now;


            while (true) {

                now = System.nanoTime();
                //Render
                if (now - lastFrame >= timePerFrame) {
                    repaint();
                    lastFrame = now;
                    frames++;

                }           //Update
                if (now - lastUpdate >= timePerUpdate) {
                    updateGame();
                    lastUpdate=now;
                    updates++;
                }
                if(System.currentTimeMillis()-lastTimeCheck>=1000){
                    System.out.println("FPS: "+frames+"| UPS: "+updates);
                    frames=0;
                    updates=0;
                    lastTimeCheck=System.currentTimeMillis();
                }

            }




    }

        //Getters and setters
        public Render getRender(){
            return render;
        }

        public Menu getMenu() {
            return menu;
        }

        public Playing getPlaying() {
            return playing;
        }

        public Settings getSettings() {
            return settings;
        }

        public Editing getEditor(){return editing;}

        public GameOver getGameOver()
        {
            return gameOver;
        }

        public TileManager getTileManager() {
            return tileManager;
        }

    //checking FPS and UPS
        //SQLite database



        }



