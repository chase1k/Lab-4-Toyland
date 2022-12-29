package toys;

public class ActionFigure extends Doll{

    public final static int MIN_ENERGY_LEVEL = 1;// starting value for energy level
    public final static Color HAIR_COLOR = Color.ORANGE;// all action figures default hair is orange
    public int energyLevel;
    public static int pc = 300; //specific product code for action figure starting at 300
    public Color hairColor;

    /**
     *
     * @param name is name of action figure
     * @param age is age of action figure
     * @param speak is catchphrase of action figure
     */
    protected ActionFigure(String name, int age, String speak) {
        super(pc++, name, HAIR_COLOR, age, speak);
        energyLevel = MIN_ENERGY_LEVEL;
        hairColor = HAIR_COLOR;
    }

    /**
     * @return energy level
     */
    public int getEnergyLevel() {
        return energyLevel;
    }

    /**
     * increases energy level
     * @param time amount of time played with
     */
    @Override
    protected void specialPlay(int time) {
        System.out.println("\t"+getName()+" kung foo chops with "+getEnergyLevel()*time+" energy!");
        this.energyLevel++;
        super.specialPlay(time);
    }

    /**
     *
     * @return toStrings of super and specific ActionFigure Attributes
     */
    @Override
    public String toString() {
        return super.toString()+", ActionFigure{E:"+energyLevel+"}";
    }
}
