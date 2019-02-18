public class MyTest {
    public static void main(String[] args) {
        Ticket myThread=new Ticket();

        new Thread(myThread,"窗口1").start();
        new Thread(myThread,"窗口2").start();
        new Thread(myThread,"窗口3").start();

    }
}
