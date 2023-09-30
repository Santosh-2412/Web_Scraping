package Tech.CodingClub.utility;

import Tech.CodingClub.RunnableExample;

public class ThreadManager {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager(100);
        for (int i = 0; i < 10000; i++) {

            RunnableExample temp = new RunnableExample("THREAD-No-" + i, 0, 500);
            //TaskManager.waitTillQueueIsFreeAndAddTask(temp);
        }
        System.out.println("#####################################");

    }

}

