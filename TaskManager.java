package Tech.CodingClub.utility;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Coding Club India.
 */
public class TaskManager {

    private static int threadCount;
    private static ExecutorService executorService;

    public TaskManager(int threadCount) {
        this.threadCount = threadCount;
        this.executorService = Executors.newFixedThreadPool(threadCount);
    }

    public  void waitTillQueueIsFreeAndAddTask(Runnable runnable) {
        while (getQueueSize() >= threadCount) {
            try {
                System.out.println("Sleeping");
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        addTask(runnable);
    }

    public void addTask(Runnable runnable) {
        this.executorService.submit(runnable);
    }

    private static int getQueueSize() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) (executorService);
        return executor.getQueue().size();
    }

}
