package session_8b;

public class Donor implements Runnable{

    private BankAccount account;
    private double amount;

    public Donor(BankAccount account, double amount){
        this.account = account;
        this.amount = amount;
    }

    public void run(){
        double accumm = 0;
        while(accumm != amount){
            this.account.deposit(1);
            accumm += 1;
        }
    }

    public static void main(String[] args) {

        BankAccount donorAcc = new BankAccount(100, "Yan");

        Thread[] donorArr = new Thread[10];

        for(int i = 0; i < 10; i++){
            donorArr[i] = new Thread(new Donor(donorAcc, 100));
            donorArr[i].start();
        }
    }
}
