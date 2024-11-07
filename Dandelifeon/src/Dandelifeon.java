import java.util.Random;

public class Dandelifeon {
    private int mana;
    private int x;
    private int y;
    //构造函数
    public Dandelifeon() {
        mana=0;
        Random r=new Random();
        x=12+r.nextInt(76);
        y=12+r.nextInt(76);
    }
    public Dandelifeon(int mana,int x,int y) {
        this.mana = mana;
        this.x=x;
        this.y=y;
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
    //生命游戏判断
    public int lifeGameCheck(Cellblock[][] board,int time){
        int sum=0;
        for(int i=-1;i<=1;i++){
            for (int j=-1;j<=1;j++) {
                if(i==0&&j==0) continue;
                if(board[x+i][y+j].getAge()>100)
                    sum+=100;
                else
                sum+=board[x+i][y+j].getAge();
                if(time==1){
                    board[x+i][y+j].setTemp(false);
                    board[x+i][y+j].setAge(0);
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
