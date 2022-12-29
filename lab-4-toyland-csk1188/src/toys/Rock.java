package toys;

public class Rock extends Toy{

    private static final int INITIAL_AGE = 0;// starting age for rock
    private static final int MAX_AGE = 100;// maximum age for rock
    public int age;// age of rock

    public static int pc= 800;// specific product code for rocks

    public boolean rocky;

    /**
     *
     * @param name the name for rock
     * @param rocky is rock rocky or not
     */
    protected Rock(String name, boolean rocky) {
        super(name, pc++);
        age = INITIAL_AGE;
        this.rocky = rocky;
    }

    /**
     *
     * @return gets age
     */
    public int getAge() {
        return age;
    }

    /**
     * if age > max rock it decomposes then recomposes and age sets back to 0
     * @param time amount of time played with age increses by time
     */
    public void increaseAge(int time){
        age += time;
        if(age < MAX_AGE){
            age = 0;
            System.out.println("\tDECOMPOSED:"+ this);
            age = INITIAL_AGE;
            System.out.println("\tRECOMPOSED:"+ this);
        }
    }

    /**
     * increases wear, increases age
     * @param time amount of time toy played with
     */
    @Override
    protected void specialPlay(int time) {
        System.out.println("\tMy Rock "+getName()+" is "+age+" year(s) old");
        super.increaseWear(time);
        increaseAge(time);
    }

    /**
     *
     * @return super toString and specific rock attributes
     */
    @Override
    public String toString() {
        return super.toString()+", Rock{A:"+getAge()+", R:"+rocky+"}";
    }
}
