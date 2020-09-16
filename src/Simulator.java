import java.util.LinkedList;
import java.util.List;

public class Simulator {
    public static int period = 28;
    public static int Susceptibility = 20;
    public static int rateOfHide = 20;
    public static int rateOfDeath = 10;
    public static int rateOfCured = 70;
    public static int rateOfAntibody = 5;
    public static int rateOfHideToCured = 40;
    public static int rateOfKeepHiding = 45;
    public static int hidedPeriod = 7;
    public static int infectiousPeriod = 7;
    public static int initialInfected = 100;
    public static int hospitalResources = 10;
    public static int population = 10000;
    public static int lengthOfAnArea = 304;
    public static int rateOfGoingToCenter = 10;
    public static List<Integer> center = new LinkedList<>(){{add(150);add(150);}};
    public static boolean maskOrNot = false;
    public static boolean isolatedOrNot = false;
    public static WholePeople wholePeople = new WholePeople();
    public static WholeArea wholeArea = new WholeArea();
    public static Hospital hospital = new Hospital();

    public static void main(String[] args){
        wholePeople.setWholePeople(wholeArea);
        wearAMask();
        System.out.println("Initial situation:");
        wholePeople.getMessage();
        for(int i=0;i<period;i++){
            randomMovementWithNothingToDoInOneDay();
//            randomMovementWithOneCenterToGoInOneDay();
            System.out.println("Day "+i+1+":");
            wholePeople.getMessage();
        }

    }

    public static void randomMovementWithNothingToDoInOneDay(){
        for(int i= 0;i<5;i++){
            wholeArea.spread();
            wholePeople.goAroundWholePeople(wholeArea);
        }
        wholeArea.spread();
        wholePeople.goHomeWholePeople(wholeArea);
    }

    public static void randomMovementWithOneCenterToGoInOneDay(){
        for(int i=0;i<5;i++){
            wholeArea.spread();
            wholePeople.goAroundWithOneCenterWholePeople(wholeArea);
        }
        wholeArea.spread();
        wholePeople.goHomeWholePeople(wholeArea);
    }

    public static void wearAMask(){
        maskOrNot=true;
        Susceptibility=Susceptibility/10;
    }

    public static void isolatedToHospital(){
        isolatedOrNot=true;
    }
}
