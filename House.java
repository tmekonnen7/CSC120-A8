import java.util.ArrayList;

/**
 * Represents a residential house on campus.
 * Extends Building with resident management and elevator support.
 */

public class House extends Building implements HouseRequirements{
    
    private boolean hasDiningRoom;
    private boolean hasElevator;
    private ArrayList<Student> residents;
 
    /**
     * Full constructor.
     * @param name Name of the house
     * @param address Street address
     * @param nFloor Number of floors of the house
     * @param hasDiningRoom Whether the house has a dining room
     * @param hasElevator Whether the house has an elevator
     */
    public House(String name, String address, int nFloors, boolean hasDiningRoom, boolean hasElevator) {
        super(name, address, nFloors);
        this.hasDiningRoom = hasDiningRoom;
        this.hasElevator = hasElevator;
        this.residents = new ArrayList<>();
    
    }

    /**
     * Overloaded constructor with  no elevator 
     * @param name Name of the house
     * @param address Street address
     * @param nFloors Number of floors
     * @param hasDiningRoom Whether the house has a dining room
     */
    public House(String name, String address, int nFloors, boolean hasDiningRoom) {
        this(name, address, nFloors, hasDiningRoom, false);
    }

    /**
     * Overloaded constructor with one floor preloaded no dining room and no elevator
     * @param name is the name of the house
     * @param address Street Address
     */

    public House(String name, String address) {
        this(name, address, 1, false, false);
    }
    /** @return true if the house has a dining room */
    public boolean hasDiningRoom() {
        return this.hasDiningRoom;
    }
 
    /** @return the number of current residents */
    public int nResidents() {
        return this.residents.size();
    }
 
    /**
     * Moves a student into the house.
     * @param s The student to move in
     */
    public void moveIn(Student s) {
        if (this.residents.contains(s)) {
            throw new RuntimeException(s.getName() + " is already a resident of " + this.name + ".");
        }
        this.residents.add(s);
        System.out.println(s.getName() + " has moved into " + this.name + ".");
    }
 
    /**
     * Overloaded moveIn — moves in multiple students at once.
     * @param students ArrayList of students to move in
     */
    public void moveIn(ArrayList<Student> students) {
        for (Student s : students) {
            moveIn(s);
        }
    }
 
    /**
     * Moves a student out of the house.
     * @param s is the student to move out
     * @return the student who moved out
     */
    public Student moveOut(Student s) {
        if (!this.residents.contains(s)) {
            throw new RuntimeException(s.getName() + " is not a resident of " + this.name + ".");
        }
        this.residents.remove(s);
        System.out.println(s.getName() + " has moved out of " + this.name + ".");
        return s;
    }
 
    /**
     * Checks whether a student is a resident.
     * @param s The student to check
     * @return true if the student lives here
     */
    public boolean isResident(Student s) {
        return this.residents.contains(s);
    }
 
    /**
     * Overrides goToFloor to enforce elevator rule:
     * non-adjacent floor jumps only allowed if the house has an elevator.
     * @param floorNum The  floor number that is the target
     */
    @Override
    public void goToFloor(int floorNum) {
        if (!hasElevator && Math.abs(floorNum - this.activeFloor) > 1) {
            throw new RuntimeException(
                this.name + " has no elevator..."
            );
        }
        super.goToFloor(floorNum);
    }
 
    /**
     * Overrides showOptions to include House-specific methods.
     */
    @Override
    public void showOptions() {
        super.showOptions();
        System.out.println(" + moveIn(Student s)\n + moveIn(ArrayList<Student> students)\n + moveOut(Student s)\n + isResident(Student s)\n + hasDiningRoom()\n + nResidents()");
    }
 
    @Override
    public String toString() {
        return super.toString() + " It is a residential house"
            + (hasDiningRoom ? " with" : " without") + " a dining room"
            + (hasElevator ? " and has an elevator." : " and has no elevator.")
            + " Current residents: " + nResidents() + ".";
    }
 
    public static void main(String[] args) {
        House chase = new House("Chase House", "9 Paradise Rd Northampton, MA 01063", 4, true, false);
        System.out.println(chase);
        chase.showOptions();
        chase.enter();
 
        Student alice = new Student("Alice", "001", 2027);
        Student bob = new Student("Bob", "002", 2026);
        chase.moveIn(alice);
        chase.moveIn(bob);
        System.out.println("Residents: " + chase.nResidents());
        System.out.println("Is Alice a resident? " + chase.isResident(alice));
        chase.moveOut(alice);
 
        // Test elevator restriction
        try {
            chase.goToFloor(3);
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
        }
        chase.goUp();
        chase.exit();
    }

}
