import java.util.HashSet;
import java.util.Set;

public class WholePeople {
    private int numberOfSusceptible;
    private int numberOfInfected;
    private int numberOfHided;
    private int numberOfDead;
    private int numberOfAntibody;
    private int numberOfIsolated;
    private Set<People> wholePeople;

    public WholePeople(){
        this.numberOfDead=0;
        this.numberOfHided=0;
        this.numberOfAntibody=0;
        this.numberOfInfected=Simulator.initialInfected;
        this.numberOfSusceptible=Simulator.population-Simulator.initialInfected;
        this.numberOfIsolated=0;
        this.wholePeople=new HashSet<>();
        createPeople();
    }

    public void checkInformation(){
        clearAll();
        for(People people : this.wholePeople){
            switch (people.getStatement()){
                case infected:
                    this.numberOfInfected++;
                    break;
                case hided:
                    this.numberOfHided++;
                    break;
                case susceptible:
                    this.numberOfSusceptible++;
                    break;
                case death:
                    this.numberOfDead++;
                    break;
                case antibody:
                    this.numberOfAntibody++;
            }
            if(people.getIsolated()) this.numberOfIsolated++;
        }
    }

    private void clearAll(){
        this.numberOfInfected=0;
        this.numberOfSusceptible=0;
        this.numberOfIsolated=0;
        this.numberOfDead=0;
        this.numberOfHided=0;
        this.numberOfAntibody=0;
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

    public void goAroundWholePeople(WholeArea wholeArea){
        wholeArea.deletePeople();
        for(People people : this.wholePeople){
            people.goAround();
            wholeArea.setPeople(people);
        }
    }

    public void goHomeWholePeople(WholeArea wholeArea){
        wholeArea.deletePeople();
        for(People people:this.wholePeople){
            people.goHome();
            wholeArea.setPeople(people);
        }
    }

    public void getMessage(){
        checkInformation();
        System.out.println("******");
        System.out.println("Susceptible:"+this.numberOfSusceptible);
        System.out.println("Infected   :"+this.numberOfInfected);
        System.out.println("Hided      :"+this.numberOfHided);
        System.out.println("Antibody   :"+this.numberOfAntibody);
        System.out.println("Dead       :"+this.numberOfDead);
        System.out.println("Isolated   :"+this.numberOfIsolated);
    }
}
