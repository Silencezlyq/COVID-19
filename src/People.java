import java.util.LinkedList;
import java.util.List;

public class People {
    private Statements statement;
    private List<Integer> home = new LinkedList<>();
    private List<Integer> location = new LinkedList<>();
    private int ID;
    private int latencyTime;

    public People(int id,int x,int y,Statements statement){
        this.ID = id;
        this.home.add(x);
        this.home.add(y);
        this.location.add(x);
        this.location.add(y);
        this.statement=statement;
        this.latencyTime=-1;
    }

    private void goAround(){
        this.location.set(0,move(this.location.get(0)));
        this.location.set(1,move(this.location.get(1)));
    }

    private void goAround(int x,int y){
        this.location.set(0,x);
        this.location.set(1,y);
    }

    private void goHome(){
        this.location.set(0,this.home.get(0));
        this.location.set(1,this.home.get(1));
    }

    public boolean infectious(){
        if(this.statement==Statements.infected||this.statement == Statements.hided){
            return true;
        }
        return false;
    }

    private Statements getStatement(){
        return this.statement;
    }

    public void setStatement(Statements statement){
        if(statement == Statements.infected){
            int rate = (int)(Math.random()*100);
            if(rate<Simulator.rateOfIncubation){
                statement=Statements.hided;
            }
        }
        this.statement=statement;
    }

    private int move(int base){
        int rate=(int)(Math.random()*3);
        switch (rate){
            case 0:
                if(base-1>1)
                return base-1;
            case 1:
                return base;
            case 2:
                if(base+1<Simulator.lengthOfAnArea-2)
                return base+1;
        }
        return base;
    }
}