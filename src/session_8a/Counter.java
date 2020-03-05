package session_8a;

public class Counter extends Thread {

    private static int idCount = 0;

    private int goal;
    private int step;
    private int start;
    private int current;
    private int id;

    public Counter(int start, int goal, int step){
        this.start = start;
        this.goal = goal;
        this.step = step;
        this.id = idCount;
        this.current = start;
        idCount++;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "goal=" + goal +
                ", step=" + step +
                ", start=" + start +
                ", current=" + current +
                ", id=" + id +
                '}';
    }

    @Override
    public void run() {
        while(current != goal){
            System.out.println(this);
            current += step;
            try {
                Thread.sleep(10);
            }catch (InterruptedException ie){
                System.out.println(ie.getMessage());
            }
        }
        System.out.println("Counter " + id + " is done!");
    }

    public static void main(String[] args) {
        Counter c0 = new Counter(0, 100, 1);
        Counter c1 = new Counter(0, -200, -5);

        c0.start();
        c1.start();

        try {
            c0.join();
            c0.join();
        }catch (InterruptedException ie){
            System.out.println(ie.getMessage());
        }
    }
}
