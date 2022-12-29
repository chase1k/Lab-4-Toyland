package toys;

public class PlayDough extends Toy{

    public final static double WEAR_MULTIPLIER = .05;
    // wear is halved for playdough
    public Color color;

    public static int pc = 100;

    /**
     *
     * @param name the name of playdough
     * @param color the color of playdough
     */
    protected PlayDough(String name, Color color){
        super(name, pc++);
        this.color = color;
    }

    /**
     *
     * @return gets color
     */
    public Color getColor() {
        return color; //colour
    }

    /**
     * increases wear
     * @param time amount of time toy played with
     */
    @Override
    protected void specialPlay(int time) {
        System.out.println("\tArts and crafting with "+this.color+" "+getName());
        super.increaseWear(time * WEAR_MULTIPLIER);
    }

    /**
     *
     * @return super toStrings and playdough specific attributes
     */
    @Override
    public String toString() {
        return super.toString()+", PlayDough{C:"+color+"}";
    }
}
