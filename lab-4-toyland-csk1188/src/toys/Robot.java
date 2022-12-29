package toys;

public class Robot extends BatteryPowered{

    public final static int FLY_SPEED = 25; // flying speed for robot
    public final static int RUN_SPEED = 10;// running speed for robot
    public final static int INITIAL_SPEED = 0;// initial speed for robot
    public static int pc = 500;// specific product code for robots
    public boolean flying;
    public int distance; // distance robot has traveled

    /**
     *
     * @param name the name for the robbot
     * @param numBatteries the number of batteries a robot has
     * @param flying if robot flies or not
     */
    protected Robot(String name, int numBatteries, boolean flying){
        super(pc++, name, numBatteries);
        this.flying = flying;
        distance = INITIAL_SPEED;

    }

    /**
     *
     * @return true if flying false if not
     */
    public boolean isFlying() {
        return flying;
    }

    /**
     *
     * @return gets distance
     */
    public int getDistance(){
        return distance;
    }

    /**
     * distance changes based on speeds based on if flying or not
     * uses batteries aswell
     * @param time amount of time toy played with
     */
    protected void specialPlay(int time){
        if(isFlying()){
            distance += time * FLY_SPEED;
            System.out.println("\t"+getName()+" is flying around with total distance: "+distance);
            increaseWear(FLY_SPEED);
        }
        else{
            distance += time * RUN_SPEED;
            System.out.println("\t"+getName()+" is running around with total distance: "+distance);
            increaseWear(RUN_SPEED);
        }
        useBatteries(time);

    }

    /**
     *
     * @return super toStrings and robot specific attributes
     */
    @Override
    public String toString() {
        return super.toString()+", Robot{F:"+flying+", D:"+distance+"}";
    }
}
