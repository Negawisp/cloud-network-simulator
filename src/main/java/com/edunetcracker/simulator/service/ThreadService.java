package com.edunetcracker.simulator.service;

import com.edunetcracker.simulator.model.element.NetworkElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.*;

@Service
public class ThreadService {

   /**
    * Processes to run
    * A Future representing the pending results of the task.
    * The Future's get method will return the task's result upon successful completion
    */
    @Getter
    @Setter
    private HashMap<NetworkElement, Future> tasks;

    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * Adding new thread
     * @param ne a Network Element
     * @return true, if the process was added to thread
     *         false, if all thread are busy or smth went wrong
     */
    public boolean addThread(NetworkElement ne){

        try {
            tasks.put(ne, threadPoolExecutor.submit((Objects.requireNonNull(ne))));
            return true;
        }
        catch (RejectedExecutionException re){
            return false;
        }

    }

    /**
     * Terminates all threads
     */
    public void shutDownThreads() {
        threadPoolExecutor.shutdown();
    }

    ThreadService() {
        //Number of threads for the processes
        int threadBound = 15;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, threadBound,
                0L, TimeUnit.SECONDS, new SynchronousQueue<>());
        tasks = null;
    }
}
