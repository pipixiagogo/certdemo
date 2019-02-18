import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThread {
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,
             10, 200, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(5));

    public static void main(String[] args) {
        for (int i=0;i<15;i++){
            MyRun myRun= new MyRun(i);
            threadPoolExecutor.execute(myRun);
            System.out.println("线程池中线程数目："+threadPoolExecutor.getPoolSize()+"，队列中等待执行的任务数目："+
                    threadPoolExecutor.getQueue().size()+"，已执行完别的任务数目："+threadPoolExecutor.getCompletedTaskCount());
        }

        }
}
