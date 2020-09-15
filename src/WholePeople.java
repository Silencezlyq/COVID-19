import java.util.HashSet;
import java.util.Set;

public class WholePeople {
    private int numberOfSusceptible;
    private int numberOfInfected;
    private int numberOfHided;
    private int numberOfDead;
    private int numberOfRecovered;
    private int numberOfIsolated;
    private Set<People> wholePeople;

    public WholePeople(){
        this.numberOfDead=0;
        this.numberOfHided=0;
        this.numberOfRecovered=0;
        this.numberOfInfected=Simulator.initialInfected;
        this.numberOfSusceptible=Simulator.population-Simulator.initialInfected;
        this.numberOfIsolated=0;
        this.wholePeople=new HashSet<>();
    }

    public void createPeople(){
        int i=1;
        for(;i<=Simulator.initialInfected;i++){
            People people = new People(i,(int)(Math.random()*(Simulator.lengthOfAnArea-4))+1,(int)(Math.random()*(Simulator.lengthOfAnArea-4))+1,Statements.infected);
            this.wholePeople.add(people);
        }
        for(;i<=Simulator.population;i++){
            People people = new People(i,(int)(Math.random()*(Simulator.lengthOfAnArea-4))+1,(int)(Math.random()*(Simulator.lengthOfAnArea-4))+1,Statements.susceptible);
            this.wholePeople.add(people);
        }
    }

    public void setWholePeople(WholeArea wholeArea){
        for(People people : this.wholePeople){
            wholeArea.setPeople(people);
        }
    }

    public void getMessage(){
        System.out.println("******");
        System.out.println("Susceptible:"+this.numberOfSusceptible);
        System.out.println("Infected   :"+this.numberOfInfected);
        System.out.println("Hided      :"+this.numberOfHided);
        System.out.println("Recovered  :"+this.numberOfRecovered);
        System.out.println("Dead       :"+this.numberOfDead);
        System.out.println("Isolated   :"+this.numberOfIsolated);
    }
}
