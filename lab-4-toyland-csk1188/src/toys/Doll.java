package toys;

public class Doll extends Toy{
    public Color hairColor;
    public int age;
    public String speak;

    public static int pc = 200;// product code specific for dolls

    /**
     *
     * @param name the name of the doll
     * @param color the hair color of the doll
     * @param age the age of the doll
     * @param speak the catchphrase of the doll
     */
    protected Doll(String name, Color color, int age, String speak) {
        super(name, pc++);
        this.age = age;
        this.speak = speak;
        this.hairColor = color;
    }

    /**
     *
     * @param productCode the product code of doll
     * @param name name of doll
     * @param hairColor hair color of doll
     * @param age age of doll
     * @param speak catchphrase of doll
     */
    protected Doll(int productCode, String name, Color hairColor, int age, String speak){
        super(name,productCode);
        this.hairColor = hairColor;
        this.age = age;
        this.speak = speak;
    }

    /**
     *
     * @return gets hair color
     */
    public Color getHairColor() {
        return hairColor;
    }

    /**
     *
     * @return gets age
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @return gets catchphrase
     */
    public String getSpeak() {
        return speak;
    }

    /**
     * increases wear
     * @param time amount of time toy played with
     */
    @Override
    protected void specialPlay(int time) {
        System.out.println("\t"+getName()+" brushes their "+this.hairColor+" hair and says, \""+this.speak+"\"");
        super.increaseWear(this.age);
    }

    /**
     *
     * @return super toString and doll specific attributes
     */
    @Override
    public String toString() {
        return super.toString()+", Doll{HC:"+this.hairColor+", A:"+this.age+", S:"+this.speak+"}";
    }
}
