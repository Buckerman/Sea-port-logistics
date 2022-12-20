public class LiquidContainer extends Container implements Liquid{
    private int numBarrel;
    private int shipId;


    public LiquidContainer(Sender sender, double tare, double weightNetto, String certificate,int numBarrel) {
        super(sender,tare, weightNetto, certificate);
        this.numBarrel = numBarrel;
    }

    @Override
    public int getNumBarrel() {
        return numBarrel;
    }

    @Override
    public String toString() {
        return "L" + "|"+ id +
                "|" + sender.getId() +
                "|" + tare +
                "|" + weightNetto +
                "|" + weightBrutto +
                "|" + certificate +
                "|" + numBarrel +
                "|" + weightNetto/numBarrel +
                "|" + timeAdded +
                "|" + shipId;
    }

    public String print() {
        return id +"." + "Na ciecz " +
                "|id nadawcy= " + sender.getId() +
                "|tara= " + tare +
                "|waga netto= " + weightNetto +
                "|waga brutto= " + weightBrutto +
                "|certyfikat='" + certificate + '\'' +
                "|liczba beczek= " + numBarrel +
                "|waga na beczke= " + weightNetto/numBarrel;
    }

    public void setShipId(int shipId){
        this.shipId=shipId;
    }
}
