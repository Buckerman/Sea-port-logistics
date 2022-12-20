public class ToxicContainer extends HeavyContainer implements Liquid {
    private int isolationThickness;
    private String toxicType;
    private int numBarrel;
    private int shipId;

    public ToxicContainer(Sender sender, double tare, double weightNetto, String certificate, int numLocks, int isolationThickness) {
        super(sender, tare, weightNetto, certificate, numLocks);
        this.isolationThickness = isolationThickness;
    }

    @Override
    public int getNumBarrel() {
        return numBarrel;
    }

    public void setNumBarrel(int numBarrel) {
        this.numBarrel = numBarrel;
    }

    public void setToxicType(String toxicType) {
        this.toxicType = toxicType;
    }

    @Override
    public String getType() {
        return toxicType;
    }

    @Override
    public String toString() {
        if (toxicType.equals("Liquid")) {
            return "Tl" + "|" + id +
                    "|" + sender.getId() +
                    "|" + tare +
                    "|" + weightNetto +
                    "|" + weightBrutto +
                    "|" + certificate +
                    "|" + numLocks +
                    "|" + isolationThickness +
                    "|" + numBarrel +
                    "|" + weightNetto / numBarrel +
                    "|" + toxicType +
                    "|" + timeAdded +
                    "|" + shipId;


        } else return "Tp" + "|" + id +
                "|" + sender.getId() +
                "|" + tare +
                "|" + weightNetto +
                "|" + weightBrutto +
                "|" + certificate +
                "|" + numLocks +
                "|" + isolationThickness +
                "|" + toxicType +
                "|" + timeAdded +
                "|" + shipId;
    }

    public String print() {
        if (toxicType.equals("Liquid")) {
            return id + "." + "Toksyczny ciekly " +
                    "|id nadawcy= " + sender.getId() +
                    "|tara= " + tare +
                    "|waga netto= " + weightNetto +
                    "|waga brutto= " + weightBrutto +
                    "|certyfikat='" + certificate + '\'' +
                    "|liczba klodek= " + numLocks +
                    "|grubosc izolacji= " + isolationThickness +
                    "|liczba beczek= " + numBarrel +
                    "|waga na beczke= " + weightNetto / numBarrel;

        } else return id + "." + "Toksyczny sypki " +
                "|id nadawcy= " + sender.getId() +
                "|tara= " + tare +
                "|waga netto= " + weightNetto +
                "|waga brutto= " + weightBrutto +
                "|certyfikat='" + certificate + '\'' +
                "|liczba klodek= " + numLocks +
                "|grubosc izolacji= " + isolationThickness;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }
}
