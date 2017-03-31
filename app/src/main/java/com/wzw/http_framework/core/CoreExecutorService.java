package com.wzw.http_framework.core;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Http请求线程池
 * Created by Henry on 2017/3/24.
 */

public class CoreExecutorService {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int MAXIMUM_POOL_SIZE = 50;
    private static final long KEEP_ALIVE_TIME = CPU_COUNT;

    private static BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<>(128);

    private static ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"CoreExecutorService --" + mCount.getAndIncrement());
        }
    };

    public static final ExecutorService THREAD_POOL_EXECUTOR =
            new ThreadPoolExecutor(CORE_POOL_SIZE,MAXIMUM_POOL_SIZE,KEEP_ALIVE_TIME
            , TimeUnit.SECONDS,sPoolWorkQueue,sThreadFactory);

    public static ExecutorService getExecutorService(){
        return THREAD_POOL_EXECUTOR;
    }

    public static void cancel(Runnable task){
        sPoolWorkQueue.remove(task);
    }
}
