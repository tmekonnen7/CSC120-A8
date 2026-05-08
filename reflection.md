Use this file to record your reflection on this assignment.

- Which methods did you decide to `overload`, and why?
- What worked, what didn't, what advice would you give someone taking this course in the future?

For the House class: 
The moveIn(ArrayList<Student>) overloads moveIn(Student s). This is a method that I chose to overload because moving agroup of students into a house is more natural and iterating for each student manually is opretty tedious. The constructor overloads to three versions for name, address, floors, diningRoom, elevator: without elevator the default is false, and minimal which means that there is only name and address, a single floor, no dining room  and no elevator. This is pretty much a reflection on the details that depends on what a person can describe a house has. 

For the Library class: 
I overloaded the addTitle with an array to delegate more easily to the single-title method. The constructor overloads has three versions: full library with an elevator flag, an elevator-assumed one, and a minimal library constructor with only name and address. This is similar to the house class.

For the cafe class:
THe sellCoffee(int size) overloads the full sellCoffe method. The plain black coffee is pretty common, so instead of having to pass 0,0 everytime this makes it shorter. the sellCoffee() method further overloads with no arguments for a standard medium black coffee which is just quicker for ordering. The constructor overloads also had three versions: with the full inventory, default inventory (would be 100 of sugar, cream packets, and cups), and lastly, the minimal would only require a name and address with the default inventory.

Using super() saved time so that there wouldn't have to be null checks. The HashMap also helped to keep the checkOUt and returnBook methods symmetric.

The tricky part was that the activeFloor field in Building is protected so the subclass overrides of the goToFloor can read it, I was going to use a getter but reading Building carefully saves time. It was also hard for me to choose which methods to overload because I wanted to focus on ones that had real use cases instead of just choosing any signatures.

I would addvise future students to just make sure to reread and triple read the parent classes completely before writing any subclass. I would also recommend writing main methods in each class to check that there aren'y any bugs before moving forward.