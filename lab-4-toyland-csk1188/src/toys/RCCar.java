package toys;

public class RCCar extends BatteryPowered{

    public final static int STARTING_SPEED = 10;// initial amount for speed
    public final static int SPEED_INCREASE = 5;// amount to increase speed
    public static int pc = 400;// product code specifically for RCCars
    public int speed;// speed of RCCar

    /**
     *
     * @param name the name of RCCar
     * @param numBatteries number of batteries RCCar has
     */
    protected RCCar(String name, int numBatteries){
        super(pc++, name, numBatteries);
        this.speed = STARTING_SPEED;
    }

    /**
     *
     * @return gets speed
     */
    public int getSpeed(){
        return speed;
    }

    /**
     *uses batteries, increases wear, increases speed
     * @param time amount of time toy played with
     */
    protected void specialPlay(int time){
        System.out.println("\t"+getName()+" races around at "+speed+"mph!");
        super.useBatteries(time);
        super.increaseWear(speed);
        speed += SPEED_INCREASE;
    }

    /**
     *
     * @return super toStrings and specific RCCar attributes
     */
    @Override
    public String toString() {
        return super.toString()+", RCCar{S:"+speed+"}";
    }
}
