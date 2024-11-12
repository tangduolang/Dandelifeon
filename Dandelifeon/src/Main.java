import java.util.Random;
import java.util.Scanner;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        //设置棋盘大小
        int length=sc.nextInt();
        int width=sc.nextInt();
        Chessboardworldsystem system=new Chessboardworldsystem(length,width);
        //设置花的位置
        Dandelifeon flower=new Dandelifeon();
        int x=sc.nextInt();
        int y=sc.nextInt();
        flower.setX(x);
        flower.setY(y);
        //开始游戏
        //初始细胞
        for (int i = 0; i <system.getLength(); i++) {
            for (int j = 0; j <system.getWidth(); j++) {
                Cellblock c = new Cellblock();
                system.getBoard()[i][j] = c;
                if (i == flower.getX() && j == flower.getY()) continue;
                Random r = new Random();
                system.getBoard()[i][j].setAlive(r.nextBoolean());
            }
        }
        flower.setMiniboard(system.getBoard(),system.getLength(),system.getWidth());
        //
        int time = sc.nextInt();
        for (int i = 1; i <=time ; i++) {
                /*for (int i = 0; i < 25; i++) {
                    for (int j = 0;j < 25; j++) {
                            if(i==12&&j==12)System.out.printf("@="+a[i][j]+"\t");
                            else
                            System.out.printf(a[i][j] + "\t");
                    }

                    System.out.printf("\n");
                }
                System.out.printf("\n");*/
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(flower.lifeGameCheck(system,i)==-1){
                for (int j = 0; j <system.getLength(); j++) {
                    for (int k = 0; k <system.getWidth(); k++) {
                        Cellblock c = new Cellblock();
                        system.getBoard()[j][k] = c;
                        if (j == flower.getX() && k == flower.getY()) continue;
                        Random r = new Random();
                        system.getBoard()[j][k].setAlive(r.nextBoolean());
                    }
                }
                time-=i;
                i=0;
            }
        }
        System.out.println(flower.getMana());
    }
}