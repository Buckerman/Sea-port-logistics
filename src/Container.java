public class Container {

    public static int counter;

    protected int id;
    protected Sender sender;
    protected double tare;
    protected double weightNetto;
    protected double weightBrutto;
    protected String certificate;
    private int shipId;
    protected String type;
    protected int timeAdded = 0;

    public Container(Sender sender, double tare, double weightNetto, String certificate) {

        this.id = counter++;
        this.sender = sender;
        this.tare = tare;
        this.weightNetto = weightNetto;
        this.weightBrutto = weightNetto + tare;
        this.certificate = certificate;
        this.type = "Podstawowy";
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public double getTare() {
        return tare;
    }

    public void setTare(double tare) {
        this.tare = tare;
    }

    public double getWeightNetto() {
        return weightNetto;
    }

    public void setWeightNetto(double weightNetto) {
        this.weightNetto = weightNetto;
    }

    public double getWeightBrutto() {
        return weightBrutto;
    }

    public void setWeightBrutto(double weightBrutto) {
        this.weightBrutto = weightBrutto;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public int getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(int seconds) { this.timeAdded = seconds; }

    @Override
    public String toString() {
        return "S" + "|" + id +
                "|" + sender.getId() +
                "|" + tare +
                "|" + weightNetto +
                "|" + weightBrutto +
                "|" + certificate +
                "|" + timeAdded +
                "|" + shipId;
    }

    public String print() {
        return id + "." + "Podstawowy " +
                "|id nadawcy= " + sender.getId() +
                "|tara= " + tare +
                "|waga netto= " + weightNetto +
                "|waga brutto= " + weightBrutto +
                "|certyfikat='" + certificate + '\'';
    }


}



