import java.util.LinkedList;
import java.util.List;

public class People {
    private Statements statement;
    private List<Integer> home = new LinkedList<>();
    private List<Integer> location = new LinkedList<>();
    private int ID;
    private int latencyTime;
    private int infectedTime;
    private int isolatedTime;
    private boolean isolated;


    public People(int id,int x,int y,Statements statement){
        this.ID = id;
        this.home.add(x);
        this.home.add(y);
        this.location.add(x);
        this.location.add(y);
        this.statement=statement;
        this.latencyTime=-1;
        if(statement==Statements.infected){
            this.infectedTime=7;
        }else {
            this.infectedTime=-1;
        }
        this.isolated=false;
    }

    public void goAround(){
        if(!this.isolated){
            this.location.set(0,move(this.location.get(0)));
            this.location.set(1,move(this.location.get(1)));
        }
    }

    public void goAround(int x,int y){
        if(!this.isolated){
            this.location.set(0,x);
            this.location.set(1,y);
        }
    }

    public void goHome(){
        if(!this.isolated){
            this.location.set(0,this.home.get(0));
            this.location.set(1,this.home.get(1));
            oneDayAfter();
            checkTheChange();
            checkIsolated();
        }else {
            this.isolatedTime--;
            keepIsolatedOrCuredOrDead();
        }
    }

    public void keepIsolatedOrCuredOrDead(){
        if(this.isolatedTime==0){
            if(Simulator.rateOfKeepIsolating>(int)(Math.random()*100)){
                this.isolatedTime=7;
            }else if (Simulator.rateOfKeepIsolating+Simulator.rateOfIsolatedToCured>(int)(Math.random()*100)){
                this.isolatedTime--;
                this.statement=Statements.susceptible;
                this.isolated=false;
                this.location.set(0,this.home.get(0));
                this.location.set(1,this.home.get(1));
            }else {
                this.statement=Statements.death;
                this.isolated=false;
                this.isolatedTime--;
                this.location.set(0,this.home.get(0));
                this.location.set(1,this.home.get(1));
            }
        }
    }

    private void checkTheChange(){
        if(this.infectedTime==0){
            int rate = (int)(Math.random()*100);
            if(Simulator.rateOfDeath>rate){
                this.statement=Statements.death;
                this.infectedTime--;
            }else if(Simulator.rateOfDeath+Simulator.rateOfCured>rate){
                this.statement=Statements.susceptible;
                this.infectedTime--;
            }else if(Simulator.rateOfDeath+Simulator.rateOfCured+Simulator.rateOfAntibody>rate){
                this.statement=Statements.antibody;
                this.infectedTime--;
            }
            else{
                this.statement=Statements.infected;
                this.infectedTime=Simulator.infectiousPeriod;
            }
            return ;
        }
        if(this.latencyTime==0){
            int rate = (int)(Math.random()*100);
            if(Simulator.rateOfKeepHiding>rate){
                this.statement=Statements.hided;
                this.latencyTime=Simulator.hidedPeriod;
            }else if(Simulator.rateOfKeepHiding+Simulator.rateOfHideToCured>rate){
                this.statement=Statements.susceptible;
                this.latencyTime--;
            }else if(Simulator.rateOfDeath+Simulator.rateOfCured+Simulator.rateOfAntibody>rate){
                this.statement=Statements.antibody;
                this.latencyTime--;
            }else{
                this.statement=Statements.infected;
                this.latencyTime--;
                this.infectedTime=Simulator.infectiousPeriod;
            }
        }
    }

    public int getLocationX(){
        return this.location.get(0);
    }

    public int getLocationY(){
        return this.location.get(1);
    }

    public boolean infectious(){
        if(this.statement==Statements.infected||this.statement == Statements.hided||this.statement==Statements.antibody){
            return true;
        }
        return false;
    }

    public Statements getStatement(){
        return this.statement;
    }

    public void setStatement(Statements statement){
        if(statement == Statements.infected){
            int rate = (int)(Math.random()*100);
            this.infectedTime=Simulator.infectiousPeriod;
            if(rate<Simulator.rateOfHide){
                statement=Statements.hided;
                this.latencyTime=Simulator.hidedPeriod;
                this.infectedTime=-1;
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

    public void oneDayAfter(){
        if(this.infectedTime>=0)
            this.infectedTime--;
        if(this.latencyTime>=0)
            this.latencyTime--;
    }

    public void setIsolated(boolean state){
        this.isolated=state;
    }

    public boolean getIsolated(){
        return this.isolated;
    }

    public int getID(){
        return this.ID;
    }

    private void checkIsolated(){
        if(this.infectedTime==Simulator.infectiousPeriod-Simulator.timeToIsolated){
            setIsolated(true);
            this.infectedTime=-1;
            this.location.set(0,0);
            this.location.set(1,0);
            this.isolatedTime=7;
        }
    }
}