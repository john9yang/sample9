package concurrent.deadlock;

public class EatNoodleThread2 extends Thread {

    private final String name;

    private final TablewarePair tablewarePair;

    public EatNoodleThread2(String name, TablewarePair tablewarePair) {
        this.name = name;
        this.tablewarePair = tablewarePair;
    }

    @Override
    public void run() {
        while(true) {
            this.eat();
        }
    }

    private void eat() {
        synchronized (tablewarePair) {
            System.out.println(name + " take up " + tablewarePair.getLeftTool() + "(left)");
            System.out.println(name + " take up " + tablewarePair.getRightTool() + "(right)");
            System.out.println(name + " is eating now.");
            System.out.println(name + " put down " + tablewarePair.getRightTool() + "(right)");
            System.out.println(name + " put down " + tablewarePair.getLeftTool() + "(left)");
        }
    }

    public static void main(String[] args) {
        Tableware fork = new Tableware("fork");
        Tableware knife = new Tableware("knife");
        new EatNoodleThread2("A", new TablewarePair(fork, knife)).start();
        new EatNoodleThread2("B", new TablewarePair(knife, fork)).start();
    }
}
