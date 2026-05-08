import java.util.ArrayList;

public class CampusMap {

    ArrayList<Building> buildings;

    /* Default constructor, initializes empty ArrayList */
    public CampusMap() {
        buildings = new ArrayList<Building>();
    }

    /**
     * Adds a Building to the map
     * @param b the Building to add
     */
    public void addBuilding(Building b) {
        System.out.println("Adding building...");
        buildings.add(b);
        System.out.println("-->Successfully added " + b.getName() + " to the map.");
    }

    /**
     * Removes a Building from the map
     * @param b the Building to remove
     * @return the removed Building
     */
    public Building removeBuilding(Building b) {
        System.out.println("Removing building...");
        buildings.remove(b);
        System.out.println("-->Successfully removed " + b.getName() + " to the map.");
        return b;
    }

    public String toString() {
        String mapString = "DIRECTORY of BUILDINGS";

        for (int i = 0; i < this.buildings.size(); i ++) {
            mapString += "\n  " + (i+1) + ". "+ this.buildings.get(i).getName() + " (" + this.buildings.get(i).getAddress() + ")";
        }
        return mapString;
    }

    public static void main(String[] args) {
        CampusMap myMap = new CampusMap();
       
         // Plain buildings
        myMap.addBuilding(new Building("Ford Hall", "100 Green St Northampton, MA 01063", 4));
        myMap.addBuilding(new Building("Bass Hall", "4 Tyler Ct Northampton, MA 01063", 4));
        myMap.addBuilding(new Building("Seelye Hall", "2 Seelye Dr Northampton, MA 01063", 4));
 
        // Houses with constructor (name, address, floors, diningRoom, elevator)
        House chase = new House("Chase House",    "9 Paradise Rd Northampton, MA 01063",    4, true,  false);
        House tyler = new House("Tyler House",    "4 Tyler Ct Northampton, MA 01063",        3, true,  false);
        // Overloaded: no elevator argument
        House sessions = new House("Sessions House", "12 Elm St Northampton, MA 01063",         2, false);
        // Overloaded: minimal (name + address only)
        House small = new House("Small Cottage",  "15 Green St Northampton, MA 01063");
 
        myMap.addBuilding(chase);
        myMap.addBuilding(tyler);
        myMap.addBuilding(sessions);
        myMap.addBuilding(small);
 
        // Demo House overloads
        Student alice = new Student("Alice", "001", 2027);
        Student bob = new Student("Bob",   "002", 2026);
        chase.moveIn(alice);
        // moveIn(ArrayList) overload
        ArrayList<Student> newStudents = new ArrayList<>();
        newStudents.add(bob);
        chase.moveIn(newStudents);
        System.out.println("Chase residents: " + chase.nResidents());
 
        //  Library 
        // Full constructor (name, address, floors, hasElevator)
        Library neilson = new Library("Neilson Library",  "7 Neilson Dr Northampton, MA 01063", 5, true);
        // Overloaded: elevator assumed present
        Library hillyer = new Library("Hillyer Art Library", "8 Elm St Northampton, MA 01063",  2);
        // Overloaded: minimal
        Library science = new Library("Young Science Library", "122 Green St Northampton, MA 01063");
 
        myMap.addBuilding(neilson);
        myMap.addBuilding(hillyer);
        myMap.addBuilding(science);
 
        // Demo Library overloads
        neilson.addTitle("Beloved");                                       // single title
        neilson.addTitle(new String[]{"1984", "The Great Gatsby", "Jane Eyre"}); // array overload
        neilson.enter();
        neilson.goToFloor(4); //this has elevator
        neilson.checkOut("1984");
        neilson.printCollection();
        neilson.goToFloor(1);
        neilson.exit();
 
        //  Cafe 
        // Full constructor (name, address, floors, oz, sugar, cream, cups)
        Cafe compass = new Cafe("Compass Cafe", "7 Neilson Dr Northampton, MA 01063",    1, 200, 150, 150, 200);
        // Overloaded: default inventory
        Cafe hewitt = new Cafe("Hewitt Cafe",  "200 Elm St Northampton, MA 01063",       1);
        // Overloaded: minimal
        Cafe kiosk = new Cafe("Green St Kiosk", "1 Green St Northampton, MA 01063");
 
        myMap.addBuilding(compass);
        myMap.addBuilding(hewitt);
        myMap.addBuilding(kiosk);
 
        // Demo Cafe overloads
        compass.enter();
        compass.sellCoffee(16, 2, 1); // full with size, sugar, cream
        compass.sellCoffee(12); // overloade with size only (black)
        compass.sellCoffee(); // overload: medium black (no args)
 
        // Test floor restriction
        try {
            compass.goToFloor(2);
        } catch (RuntimeException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
        compass.exit();
 
        // Print the full directory 
        System.out.println("\n" + myMap);
    }
    }
    
}
