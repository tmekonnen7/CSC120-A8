/**
 * Represents a campus cafe building.
 * Sells coffee and restricts navigation to the ground floor only
 * (upper floors are employee/storage only).
 */
public class Cafe extends Building implements CafeRequirements {

    private int nCoffeeOunces;
    private int nSugarPackets;
    private int nCreams;
    private int nCups;

    /**
     * Full constructor.
     * @param name Name of the cafe
     * @param address Street address
     * @param nFloors Number of floors
     * @param nCoffeeOunces Ounces of coffee in stock
     * @param nSugarPackets Sugar packets in stock
     * @param nCreams Cream portions in stock
     * @param nCups Cups in stock
     */
    public Cafe(String name, String address, int nFloors,
                int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        super(name, address, nFloors);
        this.nCoffeeOunces = nCoffeeOunces;
        this.nSugarPackets = nSugarPackets;
        this.nCreams = nCreams;
        this.nCups = nCups;
    }

    /**
     * Overloaded constructor and starts with default inventory of 100 each
     * @param name name of the cafe
     * @param address Street address
     * @param nFloors Number of floors
     */
    public Cafe(String name, String address, int nFloors) {
        this(name, address, nFloors, 100, 100, 100, 100);
    }

    /**
     * Overloaded constructor, with one floor only, default inventory
     * @param name Name of the cafe
     * @param address Street address
     */
    public Cafe(String name, String address) {
        this(name, address, 1, 100, 100, 100, 100);
    }

    /**
     * Restocks the cafe's supplies
     * @param nCoffeeOunces Ounces of coffee to add
     * @param nSugarPackets Sugar packets to add
     * @param nCreams Cream portions to add
     * @param nCups Cups to add
     */
    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        this.nCoffeeOunces += nCoffeeOunces;
        this.nSugarPackets += nSugarPackets;
        this.nCreams += nCreams;
        this.nCups += nCups;
        System.out.println(this.name + " has been restocked.");
    }

    /**
     * Sells a cup of coffee, consuming inventory
     * Restocks automatically if supply runs low
     * @param size Size in ounces
     * @param nSugarPackets Number of sugar packets requested
     * @param nCreams Number of creams requested
     */
    public void sellCoffee(int size, int nSugarPackets, int nCreams) {
        if (this.nCups < 1 || this.nCoffeeOunces < size
                || this.nSugarPackets < nSugarPackets || this.nCreams < nCreams) {
            System.out.println("Running low — restocking before sale...");
            restock(100, 100, 100, 100);
        }
        this.nCups-= 1;
        this.nCoffeeOunces-= size;
        this.nSugarPackets-= nSugarPackets;
        this.nCreams -= nCreams;
        System.out.println("Sold a " + size + "oz coffee with "
            + nSugarPackets + " sugar packet(s) and " + nCreams + " cream(s). Enjoy!");
    }

    /**
     * Overloaded sellCoffee, sells a black coffee with no sugar and cream
     * @param size Size in ounces
     */
    public void sellCoffee(int size) {
        sellCoffee(size, 0, 0);
    }

    /**
     * Overloaded sellCoffee sells a medium (12 oz) black coffee (no sugar, no cream)
     */
    public void sellCoffee() {
        sellCoffee(12, 0, 0);
    }

    /**
     * Overrides goToFloor: customers may only be on floor 1
     * Upper floors are storage/employees only.
     * @param floorNum The target floor (must be 1)
     */
    @Override
    public void goToFloor(int floorNum) {
        if (floorNum != 1) {
            throw new RuntimeException(
                "Customers may only access the ground floor of " + this.name + "."
            );
        }
        super.goToFloor(floorNum);
    }

    /**
     * Overrides showOptions to include Cafe-specific methods.
     */
    @Override
    public void showOptions() {
        super.showOptions();
        System.out.println(" + sellCoffee(int size, int nSugarPackets, int nCreams)\n + sellCoffee(int size)\n + sellCoffee()");
    }

    @Override
    public String toString() {
        return super.toString() + " It is a café with "
            + nCoffeeOunces + " oz of coffee, "
            + nSugarPackets + " sugar packets, "
            + nCreams + " creams, and "
            + nCups + " cups in stock.";
    }

    public static void main(String[] args) {
        Cafe compass = new Cafe("Compass Cafe", "Neilson Library, Northampton, MA 01063", 1);
        System.out.println(compass);
        compass.showOptions();
        compass.enter();
        compass.sellCoffee(16, 2, 1);
        compass.sellCoffee(12);
        compass.sellCoffee();

        // Test floor restriction
        try {
            compass.goToFloor(2);
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
        }
        compass.exit();
    }
}