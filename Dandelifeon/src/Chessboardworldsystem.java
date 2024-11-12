import java.util.*;

public class Chessboardworldsystem {
    private Cellblock[][] board;
    private int length,width;

    public Cellblock[][] getBoard() {
        return board;
    }

    public void setBoard(Cellblock[][] board) {
        this.board = board;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    // 构造棋盘
    public Chessboardworldsystem(int length,int width) {
        this.length=length;
        this.width=width;
        Cellblock[][] board=new Cellblock[length][width];
        for (int i = 0; i <length; i++) {
            for (int j = 0; j < width; j++) {
                Cellblock newcell=new Cellblock();
                board[i][j]=newcell;
            }
        }
        this.board=board;
    }
    public Chessboardworldsystem(Cellblock[][] board) {
        this.board = board;
    }
    //找周围细胞状态
    public int Check(int x,int y){
        int sum=0;
        for(int i=-1;i<=1;i++){
            for (int j=-1;j<=1;j++) {
                if(i==0&&j==0) continue;
                if(x+i>=0&&x+i<getLength()&&y+j>=0&&y+j<getWidth()){
                    if(getBoard()[x+i][y+j].isAlive()) sum++;
                }
            }
        }
        if(getBoard()[x][y].isAlive()){
            if(sum<2||sum>4) return 1;
            else return 2;
        }
        else if(sum==3) return 3;
        return 0;
    }
    public int findmax(int x,int y){
        int max=0;
        for(int i=-1;i<=1;i++){
            for (int j=-1;j<=1;j++) {
                if(i==0&&j==0) continue;
                if(x+i>=0&&x+i<getLength()&&y+j>=0&&y+j<getWidth()){
                    if(max<board[x+i][y+j].getAge())max=board[x+i][y+j].getAge();
                }
            }
        }
        return max;
    }/*
    //找不同状态细胞
    public int[][] Findcellsituation(int x,int y){
        int[][] a=new int[25][25];
        for (int i =0; i <25; i++) {
            for (int j =0; j <25; j++) {
                a[i][j]=Check(x-12+i,y-12+j);
            }
        }
        a[12][12]=0;
        return a;
    }
    public int Check2(int x,int y){
        int sum=0;
        for(int i=-1;i<=1;i++){
            for (int j=-1;j<=1;j++) {
                if(i==0&&j==0) continue;
                if(x+i>=0&&x+i<100&&y+j>=0&&y+j<100){
                    sum+=board[x+i][y+j].getAge();
                }
            }
        }
        return sum;
    }
    //设置细胞
    public Cellblock[][] setcell(int[][] a,int x,int y){
        Cellblock[][] board=new Cellblock[1000][1000];
        for (int i = 0; i <100; i++) {
            for (int j = 0; j <100; j++) {
                Cellblock newcell=new Cellblock();
                board[i][j]=newcell;
                if(i>=x-12&&i<x+13&&j>=y-12&&j<y+13){
                    //第一种状态不用管
                    //第二种年龄加一
                    if(a[i-x+12][j-y+12]==2){
                        board[i][j].setAlive(true);
                        board[i][j].setAge(this.board[i][j].getAge());
                        board[i][j].addAge(1);
                    }
                    //第三种加上周围三个的年龄
                    else if(a[i-x+12][j-y+12]==3){
                        board[i][j].setAge(Check2(i,j));
                        board[i][j].setAlive(true);
                    }
                }
                else{
                    board[i][j].setAge(this.board[i][j].getAge());
                    board[i][j].setAlive(this.board[i][j].isAlive());
                }
            }
        }
        return board;
    }
    //删除细胞
    public void replacecell(Cellblock x){
        x.setAlive(false);
        x.setAge(0);
    }*/
}
