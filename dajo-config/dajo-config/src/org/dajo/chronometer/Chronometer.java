package org.dajo.chronometer;

public final class Chronometer {

    private final String taskName;

    private long startTime = -1;

    private long endTime = -1;

    public Chronometer(final String taskName) {
        this.taskName = taskName;
    }

    public Chronometer(final Class<?> taskClassName) {
        this.taskName = taskClassName.getName();
    }

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public double getElapsedTime() {
        return (endTime - startTime) / 1000000.0;
    }

    @Override
    public String toString() {
        if (startTime < 0) {
            return "Chronometer [taskName=" + taskName + ", (not started)]";
        }
        else if (startTime < 0) {
            return "Chronometer [taskName=" + taskName + ", (not stopped)]";
        }
        else {
            return "Chronometer [taskName=" + taskName + ", elapsedTime=" + getElapsedTime() + "ms]";
        }
    }

    public String toString(final int steps) {
        if (startTime < 0) {
            return "Chronometer [taskName=" + taskName + ", (not started)]";
        }
        else if (startTime < 0) {
            return "Chronometer [taskName=" + taskName + ", (not stopped)]";
        }
        else {
            double timePerStep = getElapsedTime() / steps;
            return "Chronometer [taskName=" + taskName + ", elapsedTime=" + getElapsedTime() + "ms, # of steps=" + steps +", timePerStep=" + timePerStep + "ms]";
        }
    }

    static public double addElapsedTime(final Chronometer... chronometers) {
        double result = 0;
        for(int i=0; i<chronometers.length; ++i) {
            result += chronometers[i].getElapsedTime();
        }
        return result;
    }

}// class
