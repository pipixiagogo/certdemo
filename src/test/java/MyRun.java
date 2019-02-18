public class MyRun implements Runnable {

    private Integer index;
    private static Object lock=new Object();
    private int sum=13;
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public MyRun(Integer index) {
        this.index = index;
    }

    @Override
    public void run() {
       // synchronized (this){
        while (true){
            synchronized (lock){
                try {
                    if(sum>0){
                        System.out.println("开始处理线程");
                        Thread.sleep(index*100);
                        System.out.println("MyRun"+index+"执行完毕");
                        System.out.println("sum的值为"+sum--);
                    }
//            System.out.println(this.toString());
//            System.out.println(Thread.currentThread());
                }catch (Exception e){
                    System.out.println("出错了");
                }
            }
            }


            // System.out.println(System.identityHashCode(Thread.currentThread()));

       // }
        }

}
