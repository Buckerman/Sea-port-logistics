import java.util.*;
import java.io.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static HashMap<Integer, String> stickerTypes = new HashMap<>() {{
        put(1, "Wybuch masowy");
        put(2, "Rozrzut");
        put(3, "Pozar");
        put(4, "Wybuch po zapaleniu");
        put(5, "Do detonacji");
        put(6, "Niewrazliwe");

    }};
    public static LinkedList<Sender> senders = new LinkedList<>();
    public static LinkedList<Ship> ships = new LinkedList<>();
    public static LinkedList<Container> allContainers = new LinkedList<>();

    static Warehouse warehouse = new Warehouse(10);
    static Train train = new Train(10);


    public static void main(String[] args) throws Overburden {

        Time timer = new Time();
        timer.start();
        warehouse.start();

        String path4 = "data/currentState.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path4)); //linijka po linijce, znak po znaku

            br.readLine();

            String[] tmp = br.readLine().split("\\|");
            timer.setSeconds(Integer.parseInt(tmp[0]));
            System.out.println("Aktualny czas trwania programu " + Integer.parseInt(tmp[0])/5 + " dni.");
            train.setCurrentCapacity(Integer.parseInt(tmp[1]));

        } catch (IOException e) {
            e.printStackTrace();
        }

        String path3 = "data/senders.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path3));

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] attr = line.split("\\|");

                Sender sender = new Sender(attr[1], attr[2], attr[3], attr[4]);
                sender.setId(Integer.parseInt(attr[0]));
                senders.add(sender);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String path2 = "data/ships.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path2));

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] attr = line.split("\\|");

                Ship ship = new Ship(attr[1], attr[2], attr[3], attr[4], Integer.parseInt(attr[5]), Double.parseDouble(attr[6]), Integer.parseInt(attr[7]), Integer.parseInt(attr[8]), Integer.parseInt(attr[9]));
                ships.add(ship);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String path = "data/containersOnShips.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String pierwszy = line.split("\\|")[0];
                String[] attr = line.split("\\|");

                switch (pierwszy) {
                    case "S":
                        Container container = new Container(senders.get(Integer.parseInt(attr[2])), Double.parseDouble(attr[3]), Double.parseDouble(attr[4]), attr[6]);
                        int shipId = Integer.parseInt(attr[attr.length - 1]);
                        container.setShipId(shipId);
                        for (Ship ship : ships) {
                            if (ship.getId() == shipId)
                                ship.addContainer(container);
                        }
                        break;
                    case "C":

                        container = new CoolingContainer(senders.get(Integer.parseInt(attr[2])), Double.parseDouble(attr[3]), Double.parseDouble(attr[4]), attr[6], Integer.parseInt(attr[7]), Integer.parseInt(attr[8]));
                        shipId = Integer.parseInt(attr[attr.length - 1]);
                        container.setShipId(shipId);
                        for (Ship ship : ships) {
                            if (ship.getId() == shipId)
                                ship.addContainer(container);
                        }
                        break;
                    case "E":

                        container = new ExplosiveContainer(senders.get(Integer.parseInt(attr[2])), Double.parseDouble(attr[3]), Double.parseDouble(attr[4]), attr[6], Integer.parseInt(attr[7]), attr[8]);
                        shipId = Integer.parseInt(attr[attr.length - 1]);
                        container.setShipId(shipId);
                        for (Ship ship : ships) {
                            if (ship.getId() == shipId)
                                ship.addContainer(container);
                        }
                        break;
                    case "H":

                        container = new HeavyContainer(senders.get(Integer.parseInt(attr[2])), Double.parseDouble(attr[3]), Double.parseDouble(attr[4]), attr[6], Integer.parseInt(attr[7]));
                        shipId = Integer.parseInt(attr[attr.length - 1]);
                        container.setShipId(shipId);
                        for (Ship ship : ships) {
                            if (ship.getId() == shipId)
                                ship.addContainer(container);
                        }
                        break;
                    case "L":

                        container = new LiquidContainer(senders.get(Integer.parseInt(attr[2])), Double.parseDouble(attr[3]), Double.parseDouble(attr[4]), attr[6], Integer.parseInt(attr[7]));
                        shipId = Integer.parseInt(attr[attr.length - 1]);
                        container.setShipId(shipId);
                        for (Ship ship : ships) {
                            if (ship.getId() == shipId)
                                ship.addContainer(container);
                        }
                        break;
                    case "Tl":

                        container = new ToxicContainer(senders.get(Integer.parseInt(attr[2])), Double.parseDouble(attr[3]), Double.parseDouble(attr[4]), attr[6], Integer.parseInt(attr[7]), Integer.parseInt(attr[8]));
                        ((ToxicContainer) container).setNumBarrel(Integer.parseInt(attr[9]));
                        ((ToxicContainer) container).setToxicType(attr[11]);
                        shipId = Integer.parseInt(attr[attr.length - 1]);
                        container.setShipId(shipId);
                        for (Ship ship : ships) {
                            if (ship.getId() == shipId)
                                ship.addContainer(container);
                        }
                        break;
                    case "Tp":

                        container = new ToxicContainer(senders.get(Integer.parseInt(attr[2])), Double.parseDouble(attr[3]), Double.parseDouble(attr[4]), attr[6], Integer.parseInt(attr[7]), Integer.parseInt(attr[8]));
                        ((ToxicContainer) container).setToxicType(attr[9]);
                        shipId = Integer.parseInt(attr[attr.length - 1]);
                        container.setShipId(shipId);
                        for (Ship ship : ships) {
                            if (ship.getId() == shipId)
                                ship.addContainer(container);
                        }
                        break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String path5 = "data/containersInWarehouse.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path5));

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String pierwszy = line.split("\\|")[0];
                String[] attr = line.split("\\|");

                switch (pierwszy) {
                    case "S":
                        Container container = new Container(senders.get(Integer.parseInt(attr[2])), Double.parseDouble(attr[3]), Double.parseDouble(attr[4]), attr[6]);
                        int shipId = Integer.parseInt(attr[attr.length - 1]);
                        container.setShipId(shipId);
                        container.setTimeAdded(Integer.parseInt(attr[attr.length-2]));
                        warehouse.addContainer(container);

                        break;
                    case "C":

                        container = new CoolingContainer(senders.get(Integer.parseInt(attr[2])), Double.parseDouble(attr[3]), Double.parseDouble(attr[4]), attr[6], Integer.parseInt(attr[7]), Integer.parseInt(attr[8]));
                        shipId = Integer.parseInt(attr[attr.length - 1]);
                        container.setShipId(shipId);
                        container.setTimeAdded(Integer.parseInt(attr[attr.length-2]));
                        warehouse.addContainer(container);

                        break;
                    case "E":

                        container = new ExplosiveContainer(senders.get(Integer.parseInt(attr[2])), Double.parseDouble(attr[3]), Double.parseDouble(attr[4]), attr[6], Integer.parseInt(attr[7]), attr[8]);
                        shipId = Integer.parseInt(attr[attr.length - 1]);
                        container.setShipId(shipId);
                        container.setTimeAdded(Integer.parseInt(attr[attr.length-2]));
                        warehouse.addContainer(container);

                        break;
                    case "H":

                        container = new HeavyContainer(senders.get(Integer.parseInt(attr[2])), Double.parseDouble(attr[3]), Double.parseDouble(attr[4]), attr[6], Integer.parseInt(attr[7]));
                        shipId = Integer.parseInt(attr[attr.length - 1]);
                        container.setShipId(shipId);
                        container.setTimeAdded(Integer.parseInt(attr[attr.length-2]));
                        warehouse.addContainer(container);

                        break;
                    case "L":

                        container = new LiquidContainer(senders.get(Integer.parseInt(attr[2])), Double.parseDouble(attr[3]), Double.parseDouble(attr[4]), attr[6], Integer.parseInt(attr[7]));
                        shipId = Integer.parseInt(attr[attr.length - 1]);
                        container.setShipId(shipId);
                        container.setTimeAdded(Integer.parseInt(attr[attr.length-2]));
                        warehouse.addContainer(container);

                        break;
                    case "Tl":

                        container = new ToxicContainer(senders.get(Integer.parseInt(attr[2])), Double.parseDouble(attr[3]), Double.parseDouble(attr[4]), attr[6], Integer.parseInt(attr[7]), Integer.parseInt(attr[8]));
                        ((ToxicContainer) container).setNumBarrel(Integer.parseInt(attr[9]));
                        ((ToxicContainer) container).setToxicType(attr[11]);
                        shipId = Integer.parseInt(attr[attr.length - 1]);
                        container.setShipId(shipId);
                        container.setTimeAdded(Integer.parseInt(attr[attr.length-2]));
                        warehouse.addContainer(container);
                        break;
                    case "Tp":

                        container = new ToxicContainer(senders.get(Integer.parseInt(attr[2])), Double.parseDouble(attr[3]), Double.parseDouble(attr[4]), attr[6], Integer.parseInt(attr[7]), Integer.parseInt(attr[8]));
                        ((ToxicContainer) container).setToxicType(attr[9]);
                        shipId = Integer.parseInt(attr[attr.length - 1]);
                        container.setShipId(shipId);
                        container.setTimeAdded(Integer.parseInt(attr[attr.length-2]));
                        warehouse.addContainer(container);
                        break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String input = "1";

        while (!(input).equals("0")) {

            System.out.println("-----------Menu-----------");
            System.out.println("""
                    1. Stworz Nadawce
                    2. Stworz Statek
                    3. Stworz Kontener
                    4. Wyswietl Dane Statku
                    5. Wyswietl Stan Magazynu
                    6. Zutylizuj Kontener
                    7. Zaladuj Kontener
                    8. Wyladuj Kontener
                    0. Zapisz i Zakoncz Program""");
            System.out.println("--------------------------");
            System.out.print("Wejscie: ");
            input = scanner.nextLine();
            System.out.println("--------------------------");

            switch (input) {
                case "1":
                    try {
                        createSender();
                    } catch (WrongInput e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    createShip();
                    break;
                case "3":
                    createContainer();
                    break;
                case "4":
                    shipDetails();
                    break;
                case "5":
                    warehouseDetails();
                    break;
                case "6":
                    utilization();
                    break;
                case "7":
                    try {
                        shipLoad();
                    } catch (Overburden e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "8":
                    shipUnload();
                    break;
                case "0":
                    Collections.sort(ships);
                    save("ships", ships);
                    save("senders", senders);
                    save("containersInWarehouse", warehouse.getContainerList());
                    save("containersOnShips", allContainersOnShip());
                    save("currentState", new LinkedList<>(Arrays.asList(Time.getSeconds(), train.getCurrentCapacity())));
                    System.out.println("Dane zostaly zapisane.");
                    warehouse.interrupt();
                    System.out.println("See Ya ^_^");
                    break;
                default:
                    System.out.println("Zly input.");
            }
        }
    }

    public static void createSender() throws WrongInput {
        System.out.print("Imie: ");
        String name = scanner.nextLine();
        System.out.print("Nazwisko: ");
        String surname = scanner.nextLine();
        System.out.print("Adres: ");
        String address = scanner.nextLine();
        System.out.print("PESEL: ");
        String pesel = scanner.nextLine();

        if (pesel.length() == 11 && isNumeric(pesel)) {
            Sender sender = new Sender(name, surname, address, pesel);
            senders.add(sender);
        } else {
            throw new WrongInput("Zle podany pesel.");
        }
    }

    public static void shipDetails() {
        if (!(ships.isEmpty())) {
            System.out.println("Wybierz Statek:");
            AtomicInteger i = new AtomicInteger();
            ships.stream().forEach(e -> System.out.println(i.incrementAndGet() - 1 + " -> " + e.print()));
            System.out.print("Wejscie: ");
            String input;
            Ship ship = correctInputObject(ships);
            System.out.println("Dane o statku: " + "\n" + ship.printInfo());
            if (ship.getContainerList().isEmpty()) {
                System.out.println("Brak kontenerow.");
            } else {
                System.out.println("Lista kontenerów:");
                ship.getContainerList().stream().forEach(e -> System.out.println(e.print()));
            }
            System.out.println("--------------------------");
            System.out.println("""
                    Czy chcialbys go wyslac w dalsza droge?
                    tak/nie""");
            System.out.println("--------------------------");

            System.out.print("Wejscie: ");
            input = correctInput(new LinkedList<>(Arrays.asList("tak", "nie")));
            if (input.equals("tak")) {
                ship.shipDepart(ships);
                System.out.println("Statek " + ship.getName() + " odplynal do " + ship.getDestination() + ".");
            }
        } else System.out.println("Brak statkow w porcie.");
    }

    public static void shipLoad() throws Overburden {
        System.out.println("Wybierz Statek:");
        AtomicInteger i = new AtomicInteger();
        ships.stream().forEach(e -> System.out.println(i.incrementAndGet() - 1 + " -> " + e.printInfo()));

        System.out.print("Wejscie: ");
        String input;
        Ship ship = correctInputObject(ships);

        System.out.println("--------------------------");
        System.out.println("""
                Skad chcesz wziac kontener:
                1. Port
                2. Magazyn""");
        System.out.println("--------------------------");

        System.out.print("Wejscie: ");
        input = correctInput(new LinkedList<>(Arrays.asList("1", "2")));
        if (input.equals("1")) chooseLoad(allContainers, ship, " porcie.");
        else if (input.equals("2")) chooseLoad(warehouse.getContainerList(), ship, " magazynie.");
    }

    public synchronized static void chooseLoad(List<Container> list, Ship ship, String where) throws Overburden {

        if (!(list.isEmpty())) {
            System.out.println("Wybierz Kontener:");
            AtomicInteger i = new AtomicInteger();
            list.stream().forEach(e -> System.out.println(i.incrementAndGet() - 1 + " -> " + e.print()));
            System.out.print("Wejscie: ");

            Container container = correctInputObject(list);

            ship.addContainer(container);
            container.setShipId(ship.getId());
            list.remove(container);

        } else System.out.println("Brak kontenerow w" + where);

    }

    public static void shipUnload() throws Overburden {
        if (!(ships.isEmpty())) {
            System.out.println("Wybierz Statek:");
            AtomicInteger i = new AtomicInteger();
            ships.stream().forEach(e -> System.out.println(i.incrementAndGet() - 1 + " -> " + e.printInfo()));
            System.out.print("Wejscie: ");
            String input;
            Ship ship = correctInputObject(ships);

            if (!(ship.getContainerList().isEmpty())) {
                System.out.println("Wybierz Kontener:");
                AtomicInteger d = new AtomicInteger();
                ship.getContainerList().stream().forEach(e -> System.out.println(d.incrementAndGet() - 1 + " -> " + e.print()));
                System.out.print("Wejscie: ");
                Container container = correctInputObject(ship.getContainerList());

                System.out.println("--------------------------");
                System.out.println("""
                        Gdzie chcesz wyladowac kontener:
                        1. Magazyn
                        2. Kolej""");
                System.out.println("--------------------------");

                System.out.print("Wejscie: ");
                input = correctInput(new LinkedList<>(Arrays.asList("1", "2")));
                switch (input) {
                    case "1" -> {
                        container.setTimeAdded(Time.getSeconds());
                        warehouse.addContainer(container);
                        ship.removeContainer(container);
                    }
                    case "2" -> {
                        if(train.addContainer())
                            ship.removeContainer(container);
                    }
                }
            } else System.out.println("Brak kontenerow na statku.");
        } else System.out.println("Brak statkow w porcie.");
    }

    public static void warehouseDetails() {
        System.out.println("Dane o magazynie: " + "\n");
        System.out.println("Liczba kontenerów: " + warehouse.getContainerList().size() + "/10." );
        for (Container container: warehouse.getContainerList()){

            if (container instanceof ExplosiveContainer)
                System.out.println(container.print() + ", pozostaly czas: " + (25-Time.getSeconds()+container.getTimeAdded()));
            else if (container instanceof ToxicContainer){
                if (container.getType().equals("Liquid"))
                    System.out.println(container.print() + ", pozostaly czas: " + (50-Time.getSeconds()+container.getTimeAdded()));
                else if (container.getType().equals("Powdery"))
                    System.out.println(container.print() + ", pozostaly czas: " + (70-Time.getSeconds()+container.getTimeAdded()));
            }
            else{
                System.out.println(container.print() + " pozostalo dni: nieokreslono");
            }
        }
    }

    public static void utilization() {

        if (!warehouse.getContainerList().isEmpty()) {
            System.out.println("Wybierz kontener do zutylizowania: ");
            AtomicInteger i = new AtomicInteger();
            warehouse.getContainerList().stream().forEach(e -> System.out.println(i.incrementAndGet() - 1 + " -> " + e.print()));
            System.out.print("Wejscie: ");

            Container container = correctInputObject(warehouse.getContainerList());
            warehouse.getContainerList().remove(container);

            System.out.println("Utylizacja sie powiodla.");

        } else System.out.println("Brak kontenerow w magazynie.");


    }

    public static void createShip() {
        System.out.print("Nazwa: ");
        String name = scanner.nextLine();
        System.out.print("Port Macierzysty: ");
        String homeport = scanner.nextLine();
        System.out.print("Lokalizacja Poczatkowa: ");
        String origin = scanner.nextLine();
        System.out.print("Destynacja: ");
        String destination = scanner.nextLine();
        System.out.print("Max ilosc ciezkich kontenerow: ");
        int numHeavyContainers = Integer.parseInt(stringInput());
        System.out.print("Max ilosc zasilanych kontenerow: ");
        int numPoweredContainers = Integer.parseInt(stringInput());
        System.out.print("Max ilosc specjalnych kontenerow: ");
        int numTEContainers = Integer.parseInt(stringInput());
        System.out.print("Pojemnosc: ");
        int capacity = Integer.parseInt(stringInput());
        while (numHeavyContainers + numPoweredContainers + numTEContainers > capacity) {
            System.out.print("Podaj wieksza pojemnosc statku: ");
            capacity = Integer.parseInt(stringInput());
        }
        System.out.print("Ladownosc: ");
        double maximumWeight = Double.parseDouble(stringInput());

        Ship ship = new Ship(
                name,
                homeport,
                origin,
                destination,
                capacity,
                maximumWeight,
                numHeavyContainers,
                numPoweredContainers,
                numTEContainers);
        ships.add(ship);
    }

    public static List<Container> allContainersOnShip() {

        List<Container> containerList = new ArrayList<>();
        for (Ship ship : ships) {
            containerList.addAll(ship.getContainerList());
        }
        containerList.sort((o1, o2) -> {
            if (o1.getWeightBrutto() > o2.getWeightBrutto())
                return 1;

            else if (o1.getWeightBrutto() == o2.getWeightBrutto())
                return 0;

            else return -1;
        });
        return containerList;
    }

    public static void createContainer() {
        if (senders.isEmpty()) {
            System.out.println("Prosze wpierw stworzyc nadawce.");
            try {
                createSender();
            } catch (WrongInput e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("--------------------------");
            System.out.print("""
                    Wybierz rodzaj kontenera:\s
                    1. Podstawowy
                    2. Ciezki
                    3. Chlodniczy
                    4. Na materialy ciekle
                    5. Na materialy wybuchowe
                    6. Na materialy toksyczne
                    """);
            System.out.println("--------------------------");
            System.out.print("Wejscie: ");
            String input = correctInput(new LinkedList<>(Arrays.asList("1", "2", "3", "4", "5", "6")));
            System.out.println("Wybierz Nadawce:");
            AtomicInteger i = new AtomicInteger();
            senders.stream().forEach(e -> System.out.println(i.incrementAndGet() - 1 + " -> " + e.print()));//przetwarzanie dużych zbiorów danych może być dużo szybsze
            System.out.print("Wejscie: ");

//            List<String> idList = new ArrayList<>();
//            for (int j = 0; j < senders.size(); j++) {
//                idList.add(String.valueOf(j));
//            }
            Sender sender = correctInputObject(senders);
//            int senderId = Integer.parseInt(correctInputObject(idList));

            System.out.print("Tara: ");
            double tare = Double.parseDouble(stringInput());
            System.out.print("Waga Netto: ");
            double weightNetto = Double.parseDouble(stringInput());
            System.out.print("Certyfikat: ");
            String certificate = scanner.nextLine();

            Container container = null;

            switch (input) {
                case "1":
                    container = new Container(sender, tare, weightNetto, certificate);
                    break;
                case "2":
                    System.out.print("Ilosc Klodek: ");
                    int numLocks = Integer.parseInt(stringInput());
                    container = new HeavyContainer(sender, tare, weightNetto, certificate, numLocks);
                    break;
                case "3":
                    System.out.print("Ilosc Klodek: ");
                    numLocks = Integer.parseInt(scanner.nextLine());
                    System.out.print("Ilosc Wiatrakow: ");
                    int numFans = Integer.parseInt(stringInput());
                    container = new CoolingContainer(sender, tare, weightNetto, certificate, numLocks, numFans);
                    break;
                case "4":
                    System.out.print("Ilosc Beczek: ");
                    int numBarrel = Integer.parseInt(stringInput());
                    container = new LiquidContainer(sender, tare, weightNetto, certificate, numBarrel);
                    break;
                case "5":
                    System.out.println(stickerTypes);
                    System.out.print("Rodzaj Materialu Wybuchowego: ");
                    int stickerType = Integer.parseInt(correctInput(new LinkedList<>(Arrays.asList("1", "2", "3", "4", "5", "6"))));
                    System.out.print("Ilosc Klodek: ");
                    numLocks = Integer.parseInt(scanner.nextLine());
                    container = new ExplosiveContainer(sender, tare, weightNetto, certificate, numLocks, stickerTypes.get(stickerType));
                    break;
                case "6":
                    System.out.print("Ilosc Klodek: ");
                    numLocks = Integer.parseInt(stringInput());
                    System.out.print("Grubosc Izolacji: ");
                    int isolationThickness = Integer.parseInt(stringInput());
                    container = new ToxicContainer(sender, tare, weightNetto, certificate, numLocks, isolationThickness);

                    System.out.println("--------------------------");
                    System.out.print("""
                            Wybierz rodzaj toksycznosci:\s
                            1. Liquid
                            2. Powdery
                            """);
                    System.out.println("--------------------------");

                    System.out.print("Wejscie: ");
                    String type = correctInput(new LinkedList<>(Arrays.asList("Liquid", "Powdery")));
                    ((ToxicContainer) container).setToxicType(type);
                    if (type.equals("Liquid")) {
                        System.out.print("Podaj ilosc beczek: ");
                        int numBarrels = Integer.parseInt(stringInput());
                        ((ToxicContainer) container).setNumBarrel(numBarrels);
                    }
                    break;
            }
            System.out.println("Kontener zostal utworzony.");
            allContainers.add(container);
        }
    }

    public static <T> void save(String fileName, List<T> listName) {
       try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/" + fileName + ".txt")));//pdf, obrazy,excel
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data/" + fileName + ".txt"));

            switch (fileName){
                case "ships" -> bufferedWriter.write("id|nazwa|port macierzysty|lokalizacja poczatkowa|destynacja|pojemnosc|ladownosc|ilosc ciezkich|ilosc wybuchowych|ilosc toksycznych\n");
                case "senders" -> bufferedWriter.write("id|imie|nazwisko|adres|pesel|data urodzin\n");
                case "containersInWarehouse", "containersOnShips" -> bufferedWriter.write("typ|id|id nadawcy|tara|waga netto|waga brutto|certyfikat|unikalne|czas dodania|id statku\n");
                case "currentState" -> {
                    bufferedWriter.write("czas|capacity\n");
                    bufferedWriter.write(listName.get(0) + "|" + listName.get(1));
                    bufferedWriter.close();
                    return;
                }
            }

            for (T t : listName) {
                bufferedWriter.write(t + "\n");
            }
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T correctInputObject(List<T> list) { // zeby nie miec ciagle while, wybieram dobry obiekt z listy wgl indeksu z atomic integer
        boolean correct = false;
        T object = null;
        while (!correct) {
            try {
                object = list.get(Integer.parseInt(scanner.nextLine()));
                correct = true;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Zly wybor.");
                System.out.println("--------------------------");
                System.out.print("Wybierz jeszcze raz: ");
            }
        }
        return object;

    }

    public static String correctInput(LinkedList<String> list) { // wybieram ze slow w liscie ktora tworze
        boolean correct = false;
        String input = "";
        while (!correct) {
            input = scanner.nextLine();
            if (list.contains(String.valueOf(input))) {
                correct = true;
            } else {
                System.out.println("Zly wybor.");
                System.out.println("--------------------------");
                System.out.print("Wybierz jeszcze raz: ");
            }
        }
        return String.valueOf(input);
    }

    //https://www.baeldung.com/java-check-string-number
    public static boolean isNumeric(String string) { // zwraca bolean tak lub nie czy w stringu znajduja sie same cyfry
        if (string == null) {
            return false;
        }
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static String stringInput() { //sprawdza czy input jest cyfra, jesli tak pozwoli przejsc dalej
        boolean valid = false;
        String input = "";
        while (!valid) {
            input = scanner.nextLine();
            if (isNumeric(input)) {
                valid = true;
            } else {
                System.out.println("Zly input.");
                System.out.println("--------------------------");
                System.out.print("Wpisz jeszcze raz: ");
            }
        }
        return input;
    }
}