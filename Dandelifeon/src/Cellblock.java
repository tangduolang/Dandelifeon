public class Cellblock {
    private boolean temp;
    private int age;
    //构造函数
    public Cellblock() {
    }
    public Cellblock(boolean temp, int age) {
        this.temp = temp;
        this.age = age;
    }
    //判断死活
    public boolean isTemp() {
        return temp;
    }
    //设置死活
    public void setTemp(boolean temp) {
        this.temp = temp;
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
