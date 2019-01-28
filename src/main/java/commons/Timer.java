package commons;

public class Timer {

    public static final int MILISEC = 1000000;
    private long begin;
    private long end;

    public Timer() {
        reset();
    }

    public void reset() { begin = System.nanoTime(); }

    public final double milisec() {
        end = System.nanoTime();
        return (end - begin) / MILISEC;
    }

    @Override
    public String toString() {
        end = System.nanoTime();
        return String.valueOf((end - begin) / MILISEC);
    }

}
