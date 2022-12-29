package toys;

public interface IToy {

    int getProductCode();//method for getting product code

    String getName();//method for getting name

    int getHappiness();//method for getting happiness

    boolean isRetired();//method if toy is retired

    double getWear();//method to get amount of wear on a toy

    void increaseWear(double amt);//method to increase wear on a toy

    void play(int time);//general play method for toys
}
