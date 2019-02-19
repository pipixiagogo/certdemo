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
        byte b = (byte) (0x80|0x04);
        String s = toHexString(b);
        System.out.println(s);
        byte b1 = parseHexString(s);
        System.out.println(b1);
    }
    //单个字节转为16进制的字符串
    public static String toHexString(byte b) {
        String s = Integer.toHexString(b & 0xFF);
        int len = s.length();
        if (len < 2) {
            s = "0" + s;
        }
        return s.toUpperCase();
    }
    //字符串转为16进制的单个字节
    public static byte parseHexString(String s) {
        int i = Integer.parseInt(s, 16);
        return (byte) i;
    }
}
