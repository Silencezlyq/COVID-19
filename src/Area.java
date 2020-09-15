import java.util.HashSet;
import java.util.Set;

public class Area {
    private Set<People> peopleInTheArea;
    private int locationX;
    private int locationY;

    public Area(){
        this.peopleInTheArea = new HashSet<>();
    }

    public void addPeople(People people){
        this.peopleInTheArea.add(people);
    }

    public void deletePeople(People people){
        this.peopleInTheArea.remove(people);
    }

    public void deleteWholePeople(){
        this.peopleInTheArea.clear();
    }


    public void infect(Area area,int range){
        int rate = Simulator.Susceptibility/range;
        for(People people : area.peopleInTheArea){
            if(!people.infectious()&&rate>(int)(Math.random()*100)){
                people.setStatement(Statements.infected);
            }
        }
    }

    public boolean findInfected(){
        for(People people:this.peopleInTheArea){
            if(people.infectious()) return true;
        }
        return false;
    }

    public void setLocationXY(int locationX,int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public Set<People> getPeopleInTheArea() {
        return peopleInTheArea;
    }

    public void setNewPersonFalse(){
        for(People people : this.peopleInTheArea){
            people.setNewPersonFalse();
        }
    }
}
