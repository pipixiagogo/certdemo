public class Ticket implements Runnable {

    int ticket=50;

    @Override
    public void run() {
        while (true){
            synchronized (this){
                if(ticket>0){
                    try {
                        System.out.println("正在执行的线程名称为"+Thread.currentThread());
                        Thread.sleep(100);
                    }catch (Exception e){
                        System.out.println("-----");
                    }
                    System.out.println("正在卖"+(ticket--)+"张票");
                }
            }

        }

    }
}
