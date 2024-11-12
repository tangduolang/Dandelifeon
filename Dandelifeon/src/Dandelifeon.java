import java.util.Random;

public class Dandelifeon {
    private int mana;
    private int x;
    private int y;
    private Cellblock[][] miniboard;
    //构造函数
    public Dandelifeon() {
        mana=0;
        miniboard=new Cellblock[25][25];
    }
    public Dandelifeon(int mana,int x,int y) {
        this.mana = mana;
        this.x=x;
        this.y=y;
    }
    //设置小棋盘
    public void setMiniboard(Cellblock[][] board,int length,int width){
        for (int i = x-12; i < x+13; i++) {
            if(i>=0&&i<length)
            for (int j = y-12;j < y+13; j++) {
                if(j>=0&&j<width){
                    Cellblock newcell=new Cellblock();
                    miniboard[i][j]=newcell;
                    miniboard[i][j].setAge(board[x-12+i][y-12+j].getAge());
                    miniboard[i][j].setAlive(board[x-12+i][y-12+j].isAlive());
                }
            }
        }
    }
    //获得、设置坐标
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    //获取魔力
    public int getMana() {
        return mana;
    }
    //改变魔力
    public void setMana(int mana) {
        this.mana = mana;
    }
    //找周围细胞年龄之和
    public int findsum(Chessboardworldsystem system,int time){
        int sum=0;
        for(int i=-1;i<=1;i++){
            for (int j=-1;j<=1;j++) {
                if(i==0&&j==0) continue;
                if(x+i>=0&&x+i<system.getLength()&&y+j>=0&&y+j<system.getWidth()){
                    sum+=miniboard[x+i][y+j].getAge();
                    if(time==1){
                        miniboard[x+i][y+j].setAlive(false);
                        miniboard[x+i][y+j].setAge(0);
                    }
                }
            }
        }
        return sum;
    }
    //生命游戏判断
    public int lifeGameCheck(Chessboardworldsystem system,int time){
        int situation;
        //重置小棋盘
        for(int i=x-12;i<x+13;i++){
            if(i>=0&&i<system.getLength()){
                for (int j=y-12;j<y+13;j++) {
                    if(i==x&&j==y) continue;
                    if(j>=0&&j<system.getWidth()){
                        situation=system.Check(i,j);
                        //删
                        if(situation==1){
                            miniboard[x-12+i][y-12+j].setAlive(false);
                            miniboard[x-12+i][y-12+j].setAge(0);
                        }
                        //年龄加一
                        else if(situation==2){
                            miniboard[x-12+i][x-12+j].addAge(1);
                        }
                        //第三种取周围三个的年龄最大值
                        else if(situation==3){
                            miniboard[x-12+i][y-12+j].setAge(system.findmax(i,j));
                            miniboard[x-12+i][y-12+j].setAlive(true);
                        }
                    }
                }
            }
        }
        for (int j = x-12; j < x+13; j++) {
            if(j>=0&&j<system.getLength())
                for (int k = y-12; k < y+13; k++) {
                    if(k>=0&&k<system.getWidth())
                        if(j == x && k == y) System.out.printf("@\t");
                        else System.out.printf(miniboard[j][k].getAge() + "\t");
                }
            System.out.printf("\n");
        }
        System.out.printf("\n");
        int sum=findsum(system,time);
        //重置大棋盘
        for(int i=x-12;i<x+13;i++){
            if(i>=0&&i<system.getLength()){
                for (int j=y-12;j<y+13;j++) {
                    if(j>=0&&j<system.getWidth()){
                        system.getBoard()[i][j].setAlive(miniboard[x-12+i][y-12+j].isAlive());
                        system.getBoard()[i][j].setAge(miniboard[x-12+i][y-12+j].getAge());
                    }
                }
            }
        }
        if(time==1) return 0;
        if(sum>0) {
            mana+=sum;
            System.out.println(sum+" "+mana);
            return -1;
        }
        return 0;
    }

}
