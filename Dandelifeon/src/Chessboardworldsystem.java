import java.util.*;

public class Chessboardworldsystem {
    private Cellblock[][] board=new Cellblock[1000][1000];
    private int time;
    // 构造棋盘
    public Chessboardworldsystem() {
    }
    public Chessboardworldsystem(Cellblock[][] board) {
        this.board = board;
    }
    //找周围八格之内细胞
    public int Check(int x,int y){
        int sum=0;
        for(int i=-1;i<=1;i++){
            for (int j=-1;j<=1;j++) {
                if(i==0&&j==0) continue;
                if(x+i>=0&&x+i<100&&y+j>=0&&y+j<100){
                    if(board[x+i][y+j].isTemp()) sum++;
                }
            }
        }
        if(board[x][y].isTemp()){
            if(sum<2||sum>4) return 1;
            else return 2;
        }
        else if(sum==3) return 3;
        return 0;
    }
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
    //开始游戏
    public void newgame(){
        Dandelifeon flower=new Dandelifeon();
        Scanner sc=new Scanner(System.in);
        do {
            for (int i = 0; i <100; i++) {
                for (int j = 0; j <100; j++) {
                    Cellblock c = new Cellblock();
                    board[i][j] = c;
                    if (i == flower.getX() && j == flower.getY()) continue;
                    Random r = new Random();
                    board[i][j].setTemp(r.nextBoolean());
                }
            }
            time = 0;
            do {
                time++;
                int[][] a = Findcellsituation(flower.getX(),flower.getY());
                /*for (int i = 0; i < 25; i++) {
                    for (int j = 0;j < 25; j++) {
                            if(i==12&&j==12)System.out.printf("@="+a[i][j]+"\t");
                            else
                            System.out.printf(a[i][j] + "\t");
                    }

                    System.out.printf("\n");
                }
                System.out.printf("\n");*/
                board=setcell(a,flower.getX(),flower.getY());
                for (int i = flower.getX()-12; i < flower.getX()+13; i++) {
                    for (int j = flower.getY()-12;j < flower.getY()+13; j++) {
                        if(i == flower.getX() && j == flower.getY()) System.out.printf("@\t");
                        else
                        System.out.printf(board[i][j].getAge() + "\t");
                    }

                    System.out.printf("\n");
                }
                System.out.printf("\n");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            while (flower.lifeGameCheck(board, time) != -1);
            System.out.println("游戏结束，退出请按0，按1继续");
        }
        while(sc.next()!="0");
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
                        board[i][j].setTemp(true);
                        board[i][j].setAge(this.board[i][j].getAge());
                        board[i][j].addAge(1);
                    }
                    //第三种加上周围三个的年龄
                    else if(a[i-x+12][j-y+12]==3){
                        board[i][j].setAge(Check2(i,j));
                        board[i][j].setTemp(true);
                    }
                }
                else{
                    board[i][j].setAge(this.board[i][j].getAge());
                    board[i][j].setTemp(this.board[i][j].isTemp());
                }
            }
        }
        return board;
    }
    //删除细胞
    public void replacecell(Cellblock x){
        x.setTemp(false);
        x.setAge(0);
    }
}
