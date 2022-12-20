public class Train extends Thread{
    private final int capacity;
    private int currentCapacity = 0;
    private boolean available = true;

    public Train(int capacity) {
        this.capacity = capacity;
    }

    public synchronized boolean addContainer() {
        boolean added = false;

        if (currentCapacity < capacity) {
            currentCapacity += 1;
            System.out.println("Kontener zostal pomyslnie wyladowany na kolej.");
            added = true;
        }
        if (currentCapacity == capacity && available) {
            available = false;
            System.out.println("Kolej odjechala.");

            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(30000);
                    currentCapacity = 0;
                    available = true;
                    System.out.println("Kolej przyjechala.");
                    this.interrupt();
                } catch (InterruptedException ignored) {}
            });

            thread.start();
        }
        else if (!available){
            System.out.println("--------------------------");
            System.out.println("Kolej jest pelna, prosze czekac na przyjazd nowej.");
        }
        return added;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
}
