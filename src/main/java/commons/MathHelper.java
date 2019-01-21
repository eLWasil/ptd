package commons;

public interface MathHelper {

    default double log(double x, double base)
    {
        return (double) (Math.log(x) / Math.log(base));
    }

    default double convHzToSeconds(double Hz) {
        return (1 / Hz);
    }

}
