import java.util.HashSet;
import java.util.Set;

public class Area {
    private Set<People> peopleInTheArea;
    private int locationX;
    private int locationY;

    public Area(){
        this.peopleInTheArea = new HashSet<>();
        this.locationX=0;
        this.locationY=0;
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

    public void setLocationXY(int X,int Y) {
        this.locationX = X;
        this.locationY = Y;
    }

    public Set<People> getPeopleInTheArea() {
        return peopleInTheArea;
    }

    public void getMessage(){
        if(!this.peopleInTheArea.isEmpty()){
            System.out.println("("+this.locationX+","+this.locationY+"):");
            for(People people : this.peopleInTheArea){
                System.out.print(people.getID()+",");
            }
            System.out.println();
        }

    }

}
