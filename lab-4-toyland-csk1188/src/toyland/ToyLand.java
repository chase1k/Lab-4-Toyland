package toyland;

import toys.IToy;
import toys.ToyFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The main program is run on the command line with a toy file:
 *
 * $ java ToyLand filename
 *
 * 1. The toys are read into the file into a list of active toys.
 * 2. The toys are played with a random amount of time.  While
 *      active each one gets put on the back of the active toy list.
 *      When they retire after reaching maximum happiness, they
 *      move to a list of retired toys.
 * 3. Some statistics are displayed about the running of the simulation
 *      and the toys that were played with.
 *
 * @author RIT CS
 */
public class ToyLand {
    /** Seed the random number generator for generating random minutes of play time */
    private final static int RANDOM_SEED = 42;
    /** The minimum amount of time a toy is played with in a single play time */
    private final static int MIN_TIME = 20;
    /** The maximum amount of time a toy is played with in a single play time */
    private final static int MAX_TIME = 50;

    /** The active toys */
    private final ArrayList<IToy> activeToys;
    /** The retired toys */
    private final ArrayList<IToy> retiredToys;

    /** The seeded random number generator */
    private static final Random rng = new Random(RANDOM_SEED);

    /** Generate the next random time in the range MIN_TIME to MAX_TIME */
    public static int getRandomTime() {
        return rng.nextInt(MAX_TIME - MIN_TIME) + MIN_TIME;
    }

    /**
     * Read the toys in from filename and store in the active toy list.
     *
     * @param filename the toy filename
     */
    public ToyLand(String filename) {
        this.activeToys = new ArrayList<>();
        this.retiredToys = new ArrayList<>();
        try (Scanner in = new Scanner(new File(filename))) {
            while (in.hasNextLine()) {
                // the specific kind of toys are created in the factory
                this.activeToys.add(ToyFactory.makeToy(in.nextLine()));
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error opening toy file: " + filename);
        } // <3 Jim
    }

    /**
     * Play with the toys until they are all retired.
     *
     * 1. Remove the toy from the front of the active toys
     * 2. Generate the next random time and play the toy for that amount
     * 3. If the toy is not retired, add it to the back of the active toy list,
     *    otherwise add it to the back of the retired toys
     */
    public void playTime() {

        while(activeToys.size() > 0) {
            IToy currToy = activeToys.get(0);
            activeToys.remove(0);
            currToy.play(getRandomTime());
            if (!currToy.isRetired()) {
                activeToys.add(currToy);
            } else {
                retiredToys.add(currToy);
            }
        }
    }

    /**
     * Generate the following statistics about the simulation that was just run.
     *
     * For each retired toy, print the final state of the toy.
     * Print the total number of toys
     * Print the total happiness of all toys
     * Print the average wear of all toys
     */
    public void displayStatistics() {
        int totHappy = 0;
        double avgWear = 0.0;
        System.out.println("Retired toys:");
        for(IToy toy: retiredToys) {
            System.out.println("\t"+toy);
            totHappy += toy.getHappiness();
            avgWear += toy.getWear();
        }
        avgWear = avgWear/retiredToys.size();
        System.out.println("Total toys: "+retiredToys.size());
        System.out.println("Total happiness: "+totHappy);
        System.out.println("Average wear: "+avgWear);
    }

    /**
     * The main program requires the toy data file be provided.  It then
     * load the toys, plays with them, and displays some final statistics.
     *
     * @param args command line arguments (the filename)
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ToyLand toy-file");
        } else {
            ToyLand toyLand = new ToyLand(args[0]);
            toyLand.playTime();
            toyLand.displayStatistics();
        }
    }
}
