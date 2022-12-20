import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Warehouse extends Thread {
    private int capacity;
    private List<Container> containerList = new CopyOnWriteArrayList<>();

    public Warehouse(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void run() {
        Container container;
        while (!Thread.interrupted()) {
            ListIterator<Container> iterator = containerList.listIterator();
            while (iterator.hasNext()) {
                container = iterator.next();
                try {
                    IrresponsibleSenderWithDangerousGoods.checkForUtilization(container, container.getTimeAdded());
                } catch (IrresponsibleSenderWithDangerousGoods e) {
                    System.out.println(e.getMessage());
                    container.getSender().setWarningCounter(container.getSender().getWarningCounter()+1);
                    containerList.remove(container);
                }
            }
        }
    }

    public synchronized void addContainer(Container container) throws Overburden {

        if (container.getSender().getWarningCounter() == 2) {
            System.out.println("Nadawca ma limit ostrzezen, konter zostaje na statku.");
            return;
        }

        if (capacity > containerList.size() && !containerList.contains(container)) {
            containerList.add(container);
            System.out.println("Kontener zostal pomyslnie zaladowany do magazynu.");
        } else {
            throw new Overburden("Brak miejsca na kontener.");
        }

        Collections.sort(containerList, new Comparator<Container>() {
            @Override
            public int compare(Container o1, Container o2) {
                if (o1.getTimeAdded() > o2.getTimeAdded())
                    return 1;

                else if (o1.getTimeAdded() == o2.getTimeAdded())
                    return 0;

                else return -1;
            }
        });
    }

    public List<Container> getContainerList() {
        return containerList;
    }

}

