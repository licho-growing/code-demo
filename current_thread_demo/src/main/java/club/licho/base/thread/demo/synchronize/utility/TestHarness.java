package club.licho.base.thread.demo.synchronize.utility;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * ClassName:TestHarness
 *使用CountDownLatch类做闭锁，await()方法调用后，当前线程会等待到CountDownLatch的coutter值变为0才继续执行。调用countDown()方法后coutter值
 * 会减1
 * @author licho
 * @create 2018-05-10 22:27
 */
public class TestHarness {
    public long timeTasks(int nThreads,final Runnable runnable)throws InterruptedException{
        final CountDownLatch startGete=new CountDownLatch(1);
        final CountDownLatch endGate=new CountDownLatch(nThreads);
        for(int i=0;i<nThreads;i++){
            Thread t=new Thread(){
                @Override
                public void run() {
                    try{
                        startGete.await();//所有的线程运行到这里等待startGete的值变为0
                        try{
                            runnable.run();
                        }finally {
                            endGate.countDown();//一个线程完成后endGate减1
                        }
                    }catch (InterruptedException e){}
                }
            };
            t.start();
        }
        long start = System.nanoTime();
        startGete.countDown();//减1,所有的工作线程真正开始运行
        endGate.await();//等待所有的线程完成
        long end = System.nanoTime();//
        return start-end;//返回总的执行时间
    }

    public static void main(String[] args) {
        try {
            new TestHarness().timeTasks(5, new Runnable() {
                @Override
                public void run() {
                    //                    //
                    Thread thread = Thread.currentThread();

                    try {
                        Thread.sleep(new Random().nextInt(1000)+1000);
                    } catch (InterruptedException e) {


                    }
                    System.out.println("thread:"+thread.toString()+"end");
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
