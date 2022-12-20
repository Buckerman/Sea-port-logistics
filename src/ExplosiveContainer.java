public class ExplosiveContainer extends HeavyContainer{
    private String stickerType;
    private int shipId;

    public ExplosiveContainer(Sender sender, double tare, double weightNetto, String certificate,int numLocks, String stickerType) {
        super(sender, tare, weightNetto, certificate,numLocks);
        this.stickerType = stickerType;
        this.shipId = getShipId();
    }

    @Override
    public String toString() {
        return "E" + "|" + id +
                "|" + sender.getId() +
                "|" + tare +
                "|" + weightNetto +
                "|" + weightBrutto +
                "|" + certificate +
                "|" + numLocks +
                "|" + stickerType +
                "|" + timeAdded +
                "|" + shipId;
    }

    public String print() {
        return id +"." + "Na materialy wybuchowe " +
                "|id nadawcy= " + sender.getId() +
                "|tara= " + tare +
                "|waga netto= " + weightNetto +
                "|waga brutto= " + weightBrutto +
                "|certyfikat='" + certificate + '\'' +
                "|liczba klodek= " + numLocks +
                "|typ materialow='" + stickerType + '\'';
    }
    public void setShipId(int shipId){
        this.shipId=shipId;
    }
}
