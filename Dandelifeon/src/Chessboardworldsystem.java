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
            for (int j=-1;j<1;j++) {
                if(i==0&&j==0) continue;
                if(x+i>=0&&x+i<25&&y+j>=0&&y+j<25){
                    if(board[x+i][y+j].isTemp()) sum++;
                }
            }
        }
        if(x==12&&y==12){
            return 0;
        }
        else
        if(board[x][y].isTemp())
        if(sum<2||sum>4) return 1;
        else return 2;
        else if(sum==3) return 3;
        return 0;
    }
    //找不同状态细胞
    public int[][] Findcellsituation(){
        int[][] a=new int[25][25];
        for (int i = 0; i <25; i++) {
            for (int j = 0; j <25; j++) {
                a[i][j]=Check(i,j);
            }
        }
        return a;
    }
    public int Check2(int x,int y){
        int sum=0;
        for(int i=-1;i<=1;i++){
            for (int j=-1;j<1;j++) {
                if(i==0&&j==0) continue;
                if(x+i>=0&&x+i<25&&y+j>=0&&y+j<25){
                    sum+=board[x+i][y+j].getAge();
                }
            }
        }
        return sum;
    }
    //开始游戏
    public void newgame(){
        Dandelifeon flower=new Dandelifeon(0);
        Scanner sc=new Scanner(System.in);
        do {
            for (int i = 0; i < 25; i++) {
                for (int j = 0; j < 25; j++) {
                    Cellblock c = new Cellblock();
                    board[i][j] = c;
                    if (i == 12 && j == 12) continue;
                    Random r = new Random();
                    board[i][j].setTemp(r.nextBoolean());
                }
            }
            time = 0;
            do {
                time++;
                int[][] a = Findcellsituation();
                setcell(board, a);
                for (int i = 0; i < 25; i++) {
                    for (int j = 0; j < 25; j++) {
                        if(i==12&&j==12) System.out.printf("@\t");
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
    public void setcell(Cellblock[][] board,int[][] a){
        for (int i = 0; i <25; i++) {
            for (int j = 0; j <25; j++) {
                if(a[i][j]==1){
                    replacecell(board[i][j]);
                }
                else if(a[i][j]==2){
                    board[i][j].addAge(1);
                }
                else if(a[i][j]==3){
                    board[i][j].setAge(Check2(i,j));
                    board[i][j].setTemp(true);
                }
            }
        }
    }
    //删除细胞
    public void replacecell(Cellblock x){
        x.setTemp(false);
        x.setAge(0);
    }
}
