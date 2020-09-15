public class WholeArea {
    private Area wholeArea[][];

    public WholeArea(){
        int num=Simulator.lengthOfAnArea;
        this.wholeArea=new Area[num][num];
        for(int i=0;i<num;i++)
            for (int j=0;j<num;j++)
                wholeArea[i][j].setLocationXY(i,j);
    }

    public void spread(){
        for(int i=2;i<Simulator.lengthOfAnArea-2;i++){
            for(int j=2;j<Simulator.lengthOfAnArea-2;j++){
                if(wholeArea[i][j].findInfected()){
                    wholeArea[i][j].infect(wholeArea[i][j],1);
                    if(!Simulator.maskOrNot){
                        spreadAround(i,j);
                    }
                }
            }
        }
    }

    private void spreadAround(int x,int y){
        wholeArea[x][y].infect(wholeArea[x-1][y],2);
        wholeArea[x][y].infect(wholeArea[x-1][y-1],2);
        wholeArea[x][y].infect(wholeArea[x-1][y+1],2);
        wholeArea[x][y].infect(wholeArea[x][y-1],2);
        wholeArea[x][y].infect(wholeArea[x][y+1],2);
        wholeArea[x][y].infect(wholeArea[x+1][y+1],2);
        wholeArea[x][y].infect(wholeArea[x+1][y],2);
        wholeArea[x][y].infect(wholeArea[x+2][y-2],4);
        wholeArea[x][y].infect(wholeArea[x+2][y-1],4);
        wholeArea[x][y].infect(wholeArea[x+2][y],4);
        wholeArea[x][y].infect(wholeArea[x+2][y+1],4);
        wholeArea[x][y].infect(wholeArea[x+2][y+2],4);
        wholeArea[x][y].infect(wholeArea[x+1][y+2],4);
        wholeArea[x][y].infect(wholeArea[x+1][y-2],4);
        wholeArea[x][y].infect(wholeArea[x][y+2],4);
        wholeArea[x][y].infect(wholeArea[x][y-2],4);
        wholeArea[x][y].infect(wholeArea[x-1][y+2],4);
        wholeArea[x][y].infect(wholeArea[x-1][y-2],4);
        wholeArea[x][y].infect(wholeArea[x-2][y-2],4);
        wholeArea[x][y].infect(wholeArea[x-2][y-1],4);
        wholeArea[x][y].infect(wholeArea[x-2][y],4);
        wholeArea[x][y].infect(wholeArea[x-2][y+1],4);
        wholeArea[x][y].infect(wholeArea[x-2][y+2],4);
    }
}
