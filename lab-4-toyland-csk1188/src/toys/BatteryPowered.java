package toys;

public abstract class BatteryPowered extends Toy{

    public final static int FULLY_CHARGED = 100;// Max for battery level
    public final static int DEPLETED = 0;// Min for battery level
    public int numBatteries;
    public int batteryLevel;

    /**
     *
     * @param productCode product code used for battery powered implementations
     * @param name name of battery powered object
     * @param numBatteries number of batteries object has
     */
    protected BatteryPowered(int productCode, String name, int numBatteries){
        super(name, productCode);
        this.numBatteries = numBatteries;
        this.batteryLevel = FULLY_CHARGED;

    }

    /**
     *
     * @return gets battery level
     */
    public int getBatteryLevel(){
        return batteryLevel;
    }

    /**
     *
     * @return gets number of batteries
     */
    public int getNumBatteries() {
        return numBatteries;
    }

    /**
     * prints Depleted and Recharged if batterylevel <= 0
     * @param time amount of time played with toy
     */
    public void useBatteries(int time){
        batteryLevel -= time + numBatteries;
        if(batteryLevel <= DEPLETED){
            batteryLevel = 0;
            System.out.println("\tDEPLETED:"+ this);
            batteryLevel = FULLY_CHARGED;
            System.out.println("\tRECHARGED:"+ this);// tabs may be a problem
        }
    }

    /**
     *
     * @return super toStrings and specific battery powered attributes
     */
    @Override
    public String toString() {
        return super.toString()+", BatteryPowered{BL:"+batteryLevel+", NB:"+numBatteries+"}";
    }
}
