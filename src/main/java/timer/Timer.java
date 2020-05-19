package timer;

public class Timer {

    private final Thread thread;
    private volatile boolean running = true;

    private long before;

    public double fps = 0;
    private double deltaFps = 0;
    private double countFps = 0;

    public Timer(long callbackTime, TimerCallback callback) {
        this.before = System.currentTimeMillis();
        this.thread = new Thread(() -> {

            final long sleep = 4;
            while(running) {
                long now = System.currentTimeMillis();
                long delta = now - before;
                if(delta > callbackTime){
                    callback.onTimer(delta);
                    before = now;

                    //fps
                    deltaFps += (double)delta/1000;
                    countFps++;
                    if(deltaFps > 1){
                        fps = countFps;
                        deltaFps = 0;
                        countFps = 0;
                    }

                }
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException ignored) {
                }
            }
        });

    }

    public void start(){
        thread.start();
    }

    public void stop(){
        running = false;
    }

}

