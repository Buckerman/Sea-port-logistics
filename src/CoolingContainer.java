public class CoolingContainer extends HeavyContainer{
    private int numFans;
    private int shipId;

    public CoolingContainer(Sender sender, double tare, double weightNetto,String certificate,int numLocks, int numFans) {
        super(sender, tare, weightNetto, certificate,numLocks);
        this.numFans = numFans;
        this.shipId = getShipId();
    }

    @Override
    public String toString() {
        return "C" + "|" + id +
                "|" + sender.getId() +
                "|" + tare +
                "|" + weightNetto +
                "|" + weightBrutto +
                "|" + certificate+
                "|" + numLocks +
                "|" + numFans +
                "|" + timeAdded +
                "|" + shipId;
    }

    public String print() {
        return id +"." + "Chlodniczy " +
                "|id nadawcy= " + sender.getId() +
                "|tara= " + tare +
                "|waga netto= " + weightNetto +
                "|waga brutto= " + weightBrutto +
                "|certyfikat='" + certificate+ '\'' +
                "|liczba klodek" + numLocks +
                "|liczba wiatrakow= " + numFans;
    }

    @Override
    public void setShipId(int shipId){
        this.shipId=shipId;
    }
}
