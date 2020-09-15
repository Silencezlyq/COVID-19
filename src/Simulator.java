public class Simulator {
    public static final int period = 28;
    public static final int Susceptibility = 20;
    public static final int rateOfHide = 20;
    public static final int rateOfDeath = 10;
    public static final int rateOfCured = 70;
    public static final int rateOfAntibody = 5;
    public static final int rateOfHideToCured = 40;
    public static final int rateOfKeepHiding = 45;
    public static final int hidedPeriod = 7;
    public static final int infectiousPeriod = 7;
    public static final int initialInfected = 100;
    public static final int hospitalResources = 10;
    public static final int population = 10000;
    public static final int lengthOfAnArea = 304;
    public static boolean maskOrNot = false;
    public static WholePeople wholePeople = new WholePeople();
    public static WholeArea wholeArea = new WholeArea();
    public static Hospital hospital = new Hospital();

    public static void main(String[] args){
        wholePeople.setWholePeople(wholeArea);
        wholePeople.getMessage();
        for(int i=0;i<period;i++){
            randomMovementWithNothingToDoInOneDay();
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
}
