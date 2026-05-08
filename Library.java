import java.util.HashMap;

/**
 *This depicts a library building on campus.
 * There is a book collection that is managed with check-out/return functions.
 * There will always be elevator access for floor navigation.
 */
public class Library extends Building implements LibraryRequirements {

    /** Maps title to either available (true) or checked out (false) */
    private HashMap<String, Boolean> collection;
    private boolean hasElevator;

    /**
     * Full constructor.
     * @param name Name of the library
     * @param address Street address
     * @param nFloors number of floors
     * @param hasElevator whether the library has an elevator
     */
    public Library(String name, String address, int nFloors, boolean hasElevator) {
        super(name, address, nFloors);
        this.collection = new HashMap<>();
        this.hasElevator = hasElevator;
    }

    /**
     * Overloaded constructor — elevator assumed present.
     * @param name Name of the library
     * @param address Street address
     * @param nFloors Number of floors
     */
    public Library(String name, String address, int nFloors) {
        this(name, address, nFloors, true);
    }

    /**
     * Overloaded constructor — single-floor library with elevator.
     * @param name name of the library
     * @param address Street address
     */
    public Library(String name, String address) {
        this(name, address, 1, true);
    }

    /**
     * Adds a title to the collection.
     * @param title the title of the book addition
     */
    public void addTitle(String title) {
        collection.put(title, true);
        System.out.println("Added \"" + title + "\" to the collection.");
    }

    /**
     * Overloaded addTitle adds multiple titles at once.
     * @param titles Array of book titles to add
     */
    public void addTitle(String[] titles) {
        for (String title : titles) {
            addTitle(title);
        }
    }

    /**
     * Removes a title from the collection.
     * @param title The book title to remove
     * @return the removed title
     */
    public String removeTitle(String title) {
        if (!collection.containsKey(title)) {
            throw new RuntimeException("\"" + title + "\" is not in the collection.");
        }
        collection.remove(title);
        System.out.println("Removed \"" + title + "\" from the collection.");
        return title;
    }

    /**
     * Checks out a book marking it unavailable
     * @param title The book title to check out
     */
    public void checkOut(String title) {
        if (!collection.containsKey(title)) {
            throw new RuntimeException("\"" + title + "\" is not in the collection.");
        }
        if (!collection.get(title)) {
            throw new RuntimeException("\"" + title + "\" is already checked out.");
        }
        collection.put(title, false);
        System.out.println("\"" + title + "\" has been checked out.");
    }

    /**
     * Returns a book marking it availabe
     * @param title The book title to return
     */
    public void returnBook(String title) {
        if (!collection.containsKey(title)) {
            throw new RuntimeException("\"" + title + "\" doesn't belong to this library.");
        }
        if (collection.get(title)) {
            throw new RuntimeException("\"" + title + "\" is already on the shelf.");
        }
        collection.put(title, true);
        System.out.println("\"" + title + "\" has been returned.");
    }

    /**
     * Checks whether the library owns a title.
     * @param title The title to look up
     * @return true if the collection contains it
     */
    public boolean containsTitle(String title) {
        return collection.containsKey(title);
    }

    /**
     * Checks whether a title is currently available.
     * @param title The title to check
     * @return true if on the shelf (not checked out)
     */
    public boolean isAvailable(String title) {
        return collection.containsKey(title) && collection.get(title);
    }

    /** Prints every title and its availability status. */
    public void printCollection() {
        System.out.println("Collection at " + this.name + ":");
        for (String title : collection.keySet()) {
            System.out.println("  \"" + title + "\" -- " + (collection.get(title) ? "available" : "checked out"));
        }
    }

    /**
     * Overrides goToFloor: libraries with elevators may jump any floor;
     * those without may only move one floor at a time.
     * @param floorNum The target floor
     */
    @Override
    public void goToFloor(int floorNum) {
        if (!hasElevator && Math.abs(floorNum - this.activeFloor) > 1) {
            throw new RuntimeException(
                this.name + " has no elevator. You can only move one floor at a time."
            );
        }
        super.goToFloor(floorNum);
    }

    /**
     * Overrides showOptions to include Library-specific methods.
     */
    @Override
    public void showOptions() {
        super.showOptions();
        System.out.println(" + addTitle(String title)\n + addTitle(String[] titles)\n + removeTitle(String title)\n + checkOut(String title)\n + returnBook(String title)\n + containsTitle(String title)\n + isAvailable(String title)\n + printCollection()");
    }

    @Override
    public String toString() {
        return super.toString() + " It is a library w/ " + collection.size()
            + " title(s) in its collection"
            + (hasElevator ? " and has an elevator." : " and has no elevator.");
    }

    public static void main(String[] args) {
        Library neilson = new Library("Neilson Library", "7 Neilson Dr Northampton, MA 01063", 4);
        System.out.println(neilson);
        neilson.showOptions();

        neilson.addTitle(new String[]{"The Great Gatsby", "1984", "Beloved"});
        neilson.printCollection();

        neilson.enter();
        neilson.goToFloor(3); // elevator — should work
        neilson.checkOut("1984");
        neilson.printCollection();
        neilson.goToFloor(1);
        neilson.exit();
    }
}