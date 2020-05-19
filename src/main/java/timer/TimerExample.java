package timer;

public class TimerExample {

    public TimerExample() throws InterruptedException {

        TimerCallback callback2 = new MyCallback();

        Timer timer =  new Timer(300, callback2);
        timer.start();

        Thread.sleep(3000);
        timer.stop();

    }

    public class MyCallback implements TimerCallback {

        @Override
        public void onTimer(long delta) {
            System.out.println("delta: " + delta);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new TimerExample();
    }

}














