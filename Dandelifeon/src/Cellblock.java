public class Cellblock {
    private boolean isAlive;
    private int age;
    //构造函数
    public Cellblock() {
        isAlive =false;age=0;
    }
    public Cellblock(boolean isAlive, int age) {
        this.isAlive = isAlive;
        this.age = age;
    }
    //判断死活
    public boolean isAlive() {
        return isAlive;
    }
    //设置死活
    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }
    //获取年龄
    public int getAge() {
        return age;
    }
    //改变年龄
    public void setAge(int age) {
        this.age = age;
    }
    public void addAge(int x){
        age=age+1;
    }
}
