package toys;

public abstract class Toy implements IToy {
    public final static int INITIAL_HAPPINESS = 0;// starting happiness level
    public final static int MAX_HAPPINESS = 100;// maximum happiness level
    public final static double INITIAL_WEAR = 0.0;// starting wear value
    private String name;
    private int productCode;
    private int happiness;
    private boolean retired;
    private double wear;

    /**
     *
     * @param name the name of the toy
     * @param productCode the product code of the toy
     */
    protected Toy(String name, int productCode) {
        this.name = name;
        this.productCode = productCode;
        happiness = INITIAL_HAPPINESS;
        wear = INITIAL_WEAR;
        retired = false;

    }

    /**
     *
     * @return gets product code
     */
    public int getProductCode() {return productCode;}

    /**
     *
     * @return gets name of toy
     */
    public String getName() {return name;}

    /**
     *
     * @return gets happiness level
     */
    public int getHappiness() {return happiness;}

    /**
     *
     * @return true if retired false if not
     */
    public boolean isRetired() {
        retired = false;
        if(happiness >= MAX_HAPPINESS){
            retired = true;
        }
        return retired;
    }

    /**
     *
     * @return gets wear value of toy
     */
    public double getWear() {return wear;}

    /**
     *
     * @param amt wear increases by amt value
     */
    public void increaseWear(double amt) {wear += amt;}

    /**
     *
     * @param time amount of time toy played with
     */
    public void play(int time){
        System.out.println("PLAYING("+time+"): "+this);
        specialPlay(time);
        happiness += time;
        if(happiness > MAX_HAPPINESS){
            System.out.println("RETIRED: "+this);
        }
    }

    /**
     * abstract for subclasses to implement
     * all implementations print out specific text for their toy
     * @param time amount of time toy played with
     */
    protected abstract void specialPlay(int time);// increases happiness

    /**
     *
     * @return String of toy variables and etc
     */
    @Override
    public String toString() {
        return "Toy{PC:"+productCode+", N:"+name+", H:"+happiness+", R:"+isRetired()+", W:"+wear+"}";
    }
}