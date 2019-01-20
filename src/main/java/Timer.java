public class Timer {

    private long begin;
    private long end;

    Timer() {
        reset();
    }

    public void reset() { begin = System.nanoTime(); }

    @Override
    public String toString() {
        end = System.nanoTime();
        return String.valueOf((end - begin) / 1000000);
    }
}
