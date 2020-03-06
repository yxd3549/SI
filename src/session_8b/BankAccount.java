package session_8b;

public class BankAccount{

    private double money;
    private String owner;
    private Object lock = new Object();

    public BankAccount(double money, String owner){
        this.money = money;
        this.owner = owner;
    }

    public void deposit(double newMoney){
        try {
            Thread.sleep(100); // sleep() is static so we call it on the class!
        } catch(InterruptedException ie){
            System.out.println(ie.getMessage());
        }
//        synchronized (lock) {
//            money += newMoney;
//        }
        money += newMoney;
        System.out.println("New Balance:" + money);
    }

    public void withdraw(double newMoney){
        money -= newMoney;
    }

    public double checkAmount(){
        return money;
    }
}
