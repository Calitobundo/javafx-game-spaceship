package timer;

@FunctionalInterface
public interface TimerCallback {

    void onTimer(long delta);

}
