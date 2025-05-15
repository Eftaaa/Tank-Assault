package helps;

public class Constants {
    public static class Projectiles{

        public static final int BULLET_CANNON=0 ;
        public static final int BULLET_MG=1 ;

        public static final int MISSLE=2 ;
        public static float GetSpeed(int type)
        {
            switch(type){
                case BULLET_CANNON:
                    return 4f;

                case BULLET_MG:
                    return 8f;

                    case MISSLE:
                    return 1f;
            }
            return 0f;
        }

    }

    public static class Towers{
        public static final int CANNON_1=0;
        public static final int CANNON_2=1;
        public static final int CANNON_3=2;

        
        public static final int MACHINE_GUN_1=3;
        public static final int MACHINE_GUN_2=4;
        public static final int MACHINE_GUN_3=5;
        public static final int ROCKET_LAUNCHER_1=6;
        public static final int ROCKET_LAUNCHER_2=7;
        public static final int ROCKET_LAUNCHER_3=8;

        public static int GetTowerCost(int towerType){
            switch (towerType){
                case CANNON_1:
                    return 100;
                case CANNON_2:
                    return 115;
                case CANNON_3:
                    return 125;

                case MACHINE_GUN_1:
                    return 76;
                case MACHINE_GUN_2:
                    return 84;
                case MACHINE_GUN_3:
                    return 92;
                case ROCKET_LAUNCHER_1:
                    return 150;
                case ROCKET_LAUNCHER_2:
                    return 165;
                case ROCKET_LAUNCHER_3:
                    return 178;

            }
            return 0;
        }
        public static String GetName(int towerType){

            switch (towerType){
                case CANNON_1:
                return "CANNON_1";
                case CANNON_2:
                    return "CANNON_2";
                case CANNON_3:
                    return "CANNON_3";

                case MACHINE_GUN_1:
                    return "MACHINE_GUN_1";
                case MACHINE_GUN_2:
                    return "MACHINE_GUN_2";
                case MACHINE_GUN_3:
                    return "MACHINE_GUN_3";
                case ROCKET_LAUNCHER_1:
                    return "ROCKET_LAUNCHER_1";
                case ROCKET_LAUNCHER_2:
                    return "ROCKET_LAUNCHER_2";
                case ROCKET_LAUNCHER_3:
                    return "ROCKET_LAUNCHER_3";

            }
            return "";
        }

        public static int GetStartDmg(int towerType){
            switch (towerType){
                case CANNON_1:
                    return 10;
                case CANNON_2:
                    return 12;
                case CANNON_3:
                    return 15;

                
                case MACHINE_GUN_1:
                    return 2;
                case MACHINE_GUN_2:
                    return 4;
                case MACHINE_GUN_3:
                    return 6;
                case ROCKET_LAUNCHER_1:
                    return 20;
                case ROCKET_LAUNCHER_2:
                    return 25;
                case ROCKET_LAUNCHER_3:
                    return 30;

            }
            return 0;
        }
        public static float GetDefaultRange(int towerType)
        {
            switch (towerType){
                case CANNON_1:
                return 100;
                case CANNON_2:
                    return 102;
                case CANNON_3:
                    return 105;


               
                case MACHINE_GUN_1:
                    return 106;
                case MACHINE_GUN_2:
                    return 107;
                case MACHINE_GUN_3:
                    return 108;
                case ROCKET_LAUNCHER_1:
                    return 80;
                case ROCKET_LAUNCHER_2:
                    return 84;
                case ROCKET_LAUNCHER_3:
                    return 88;
            }
            return 0;
        }
        public static float GetDefaultCooldown(int towerType)
        {
            switch (towerType){
                case CANNON_1:
                return 20;
                case CANNON_2:
                    return 20;
                case CANNON_3:
                    return 20;

                case MACHINE_GUN_1:
                    return 15;
                case MACHINE_GUN_2:
                    return 15;
                case MACHINE_GUN_3:
                    return 15;
                case ROCKET_LAUNCHER_1:
                    return 30;
                case ROCKET_LAUNCHER_2:
                    return 30;
                case ROCKET_LAUNCHER_3:
                    return 30;

            }
            return 0;
        }
    }
    public static class Direction{
        public static final int LEFT=0;
        public static final int UP=1;
        public static final int RIGHT=2;
        public static final int DOWN=3;
    }
    public static class Enemies{
        public static final int HULL_1=0;
        public static final int HULL_2=1;
        public static final int HULL_3=2;
        public static final int HULL_4=3;

        public static int GetReward(int enemyType){

            switch(enemyType){
                case HULL_1:
                    return 25;
                case HULL_2:
                    return 20;
                case HULL_3:
                    return 15;
                case HULL_4:
                    return 10;
            }
return 0;
        }
        public static float GetSpeed(int enemyType){
            switch (enemyType){
                case HULL_1:
                    return 0.3f;
                case HULL_2:
                    return 0.65f;
                case HULL_3:
                    return 0.5f;
                case HULL_4:
                    return 0.75f;


            }
            return 0;
        }

        public static int GetStartHealth(int enemyType) {


            switch (enemyType){
                case HULL_1:
                    return 100;
                case HULL_2:
                    return 60;
                case HULL_3:
                    return 250;
                case HULL_4:
                    return 85;
            }
            return 0;


        }
    }
    public static class Tiles{

        public static final int WATER_TILE=0;
        public static final int GRASS_TILE=1;
        public static final int ROAD_TILE=2;

    }




}
