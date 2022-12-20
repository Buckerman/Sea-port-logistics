import java.util.LinkedList;

public class Ship implements Comparable<Ship> {
    public static int counter;

    private int id;
    private String name;
    private String homePort;
    private String origin;
    private String destination;
    private int capacity;
    private double maximumWeight;
    private int numHeavyContainers;
    private int numPoweredContainers;
    private int numTEContainers;
    private LinkedList<Container> containerList = new LinkedList<>();


    public Ship(String name, String homePort, String origin, String destination, int capacity, double maximumWeight, int numHeavyContainers, int numPoweredContainers, int numTEContainers) {

        this.id = counter++;
        this.name = name;
        this.homePort = homePort;
        this.origin = origin;
        this.destination = destination;
        this.capacity = capacity;
        this.maximumWeight = maximumWeight;
        this.numHeavyContainers = numHeavyContainers;
        this.numPoweredContainers = numPoweredContainers;
        this.numTEContainers = numTEContainers;

    }

    public void addContainer(Container container) throws Overburden {
        double sumWeight = 0;
        int heavycounter = 1;
        int coolingcounter = 1;
        int TEcounter = 1;

        for (Container c : containerList) {
            sumWeight += c.getWeightBrutto();
            if (container instanceof HeavyContainer)
                heavycounter++;
            if (container instanceof CoolingContainer)
                coolingcounter++;
            if (container instanceof ExplosiveContainer || container instanceof ToxicContainer)
                TEcounter++;
        }

        if (container.getWeightBrutto() + sumWeight <= maximumWeight && capacity > containerList.size() &&
                heavycounter <= numHeavyContainers && coolingcounter <= numPoweredContainers && TEcounter <= numTEContainers && !containerList.contains(container)) {
            containerList.add(container);
            System.out.println("Kontener zostal pomyslnie zaladowany na statek " + name + ".");
        } else {
            throw new Overburden("Brak miejsca na kontener.");
        }

    }

    public void removeContainer(Container container) {
        containerList.remove(container);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomePort() {
        return homePort;
    }

    public void setHomePort(String homePort) {
        this.homePort = homePort;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getMaximumWeight() {
        return maximumWeight;
    }

    public void setMaximumWeight(double maximumWeight) {
        this.maximumWeight = maximumWeight;
    }

    public int getNumTEContainers() {
        return numTEContainers;
    }

    public void setNumTEContainers(int numTEContainers) {
        this.numTEContainers = numTEContainers;
    }

    public LinkedList<Container> getContainerList() {
        return containerList;
    }

    public void setContainerList(LinkedList<Container> containerList) {
        this.containerList = containerList;
    }

    public void shipDepart(LinkedList<Ship> ships) {
        ships.remove(this);
    }

    @Override
    public String toString() {
        return
                id +
                        "|" + name +
                        "|" + homePort +
                        "|" + origin +
                        "|" + destination +
                        "|" + capacity +
                        "|" + maximumWeight +
                        "|" + numHeavyContainers +
                        "|" + numPoweredContainers +
                        "|" + numTEContainers;
    }

    public String printInfo() {
        return
                "id=" + id +
                        "| nazwa='" + name + '\'' +
                        "| port macierzysty='" + homePort + '\'' +
                        "| lokalizacja poczatkowa='" + origin + '\'' +
                        "| destynacja='" + destination + '\'' +
                        "| pojemnosc= " + capacity +
                        "| ladownosc= " + maximumWeight;
    }

    public String print() {
        return
                "id=" + id +
                        "| nazwa='" + name + '\'';

    }





    @Override
    public int compareTo(Ship o) {
        return o.getName().compareTo(this.getName());
    }
}

