package ch05;

interface RunnableTest {
    void postponeComputation(int delay, Runnable computation);
}

class RunnableTestImpl implements RunnableTest {
    @Override
    public void postponeComputation(int delay, Runnable computation) {
        try {
            Thread.sleep(1000);
            computation.run();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

