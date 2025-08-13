class NumberPrinter {
    private boolean running = false;
    private int countNumber = 1;
    private final Object lock = new Object();

    public void start() {
        synchronized (lock) {
            running = true;
            lock.notify();
        }
    }

    public void stop() {
        synchronized (lock) {
            running = false;
        }
    }

    public void runProgram() {
        while (true) {
            synchronized (lock) {
                while (!running) { 
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Thread interrupted");
                    }
                }
            }

            System.out.println(countNumber++);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Sleep interrupted");
            }
        }
    }
}

