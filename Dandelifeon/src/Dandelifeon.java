public class Dandelifeon {
    private int mana;
    //构造函数
    public Dandelifeon() {
        mana=0;
    }
    public Dandelifeon(int mana) {
        this.mana = mana;
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
            for (int j=-1;j<1;j++) {
                if(i==0&&j==0) continue;
                if(board[12+i][12+j].getAge()>100)
                    sum+=100;
                else
                sum+=board[12+i][12+j].getAge();
                if(time==1){
                    board[12+i][12+j].setTemp(false);
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
