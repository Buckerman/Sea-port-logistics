public class IrresponsibleSenderWithDangerousGoods extends Exception {

    public IrresponsibleSenderWithDangerousGoods (String msg) {
        super (msg);
    }

    public static void checkForUtilization(Container container, int timeJoined) throws IrresponsibleSenderWithDangerousGoods {
        if (container instanceof ExplosiveContainer && Time.getSeconds() - timeJoined >= 25)
            throw new IrresponsibleSenderWithDangerousGoods("Kontener z materialami wybuchowymi zostal zutylizowany.");
        else if (container instanceof ToxicContainer){
            if (container.getType().equals("Liquid") && Time.getSeconds() - timeJoined >= 50)
                throw new IrresponsibleSenderWithDangerousGoods("Kontener Toksyczny Ciekly został zutylizowany.");
            else if (container.getType().equals("Powdery") && Time.getSeconds() - timeJoined >= 70)
                throw new IrresponsibleSenderWithDangerousGoods("Kontener Toksyczny Sypki został zutylizowany.");
        }
    }
}
