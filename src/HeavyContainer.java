public class HeavyContainer extends Container {
    protected int numLocks;
    private int shipId;
    public HeavyContainer(Sender sender, double tare, double weightNetto, String certificate, int numLocks) {
        super(sender, tare, weightNetto, certificate);
        this.numLocks = numLocks;
    }

    @Override
    public String toString() {
        return "H" + "|" + id +
                "|" + sender.getId() +
                "|" + tare +
                "|" + weightNetto +
                "|" + weightBrutto +
                "|" + certificate +
                "|" + numLocks +
                "|" + timeAdded +
                "|" + shipId;
    }

    public String print() {
        return id +"." + "Ciezki " +
                "|id nadawcy= " + sender.getId() +
                "|tara= " + tare +
                "|waga netto= " + weightNetto +
                "|waga brutto= " + weightBrutto +
                "|certyfikat='" + certificate + '\'' +
                "|liczba klodek= " + numLocks;
    }

    public void setShipId(int shipId){
        this.shipId=shipId;
    }
}
