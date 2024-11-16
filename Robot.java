
import java.util.ArrayList;

public class Robot implements Contract {

    String name;
    int age;
    String color;
    double size;
    int freeHand;
    String owner;
    int battery = 100;
    ArrayList<String> heldItem = new ArrayList<>();

    final double minSize = 0.5;
    final double maxSize = 5.0;

    /** 
     * Constructor for Robot
     * @param name of the robot
     * @param age of the robot
     * @param color of the robot
     * @param size of the robot
     * @param freeHand number of free hands of the robot
     * @param owner of the robot
     */
    public Robot(String name, int age, String color, double size, int freeHand, String owner) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.size = size;
        this.freeHand = freeHand;
        this.owner = owner;
    }


    /** 
     * Makes the robot grab an item if it has free hands
     * @param item to grab
     */
    public void grab(String item) {
        if (freeHand > 0) {
            this.freeHand -= 1;
            heldItem.add(item);
            this.battery -= 1;
            System.out.println(this.name + " has grabbed " + item + ". Hand(s) available: " + this.freeHand + ". Battery: " + this.battery + "% remaining.");
        } else {
            System.out.println("No free hand to grab " + item + ".");
        }
    }

    /**
     * Makes the robot drop an item if it is holding it
     * @param item to drop
     * @return the dropped item, or null if the item is not being held
     */
    public String drop(String item) {
        if (heldItem.contains(item)) {
            this.freeHand += 1;
            heldItem.remove(item);
            this.battery -= 1;
            System.out.println(this.name + " has dropped " + item + ". Hand(s) available: " + this.freeHand + ". Battery: " + this.battery + "% remaining.");
            return item;
        } else {
            System.out.println(this.name + " is not holding " + item + ".");
            return null;
        }
    }

    /**
     * Makes the robot examine an item
     * @param item to examine
     */
    public void examine(String item) {
        if (this.battery >= 1) { 
            this.battery -= 1;
            System.out.println("Examining how to use " + item + "... Battery: " + this.battery + "% remaining.");
        } else {
            System.out.println("Not enough battery to examine " + item + ". Battery: " + this.battery + "% remaining.");
        }
    }

    /**
     * Makes the robot use an item
     * @param item to use
     */
    public void use(String item) {
        if (this.battery >= 2) {
            this.battery -= 2;
            System.out.println("Using " + item + "... Battery: " + this.battery + "% remaining.");
        } else {
            System.out.println("Not enough battery to use " + item + ". Battery: " + this.battery + "% remaining.");
        }
    }

    /**
     * Makes the robot walk in a specified direction
     * @param direction to walk
     * @return true if the robot successfully walked, false otherwise
     */
    public boolean walk(String direction) {
        if (this.battery >= 2) {
            this.battery -= 2;
            System.out.println("Walking " + direction + ". Battery: " + this.battery + "% remaining.");
            return true;
        } else {
            System.out.println("Not enough battery to walk.");
            return false;
        }
    }

    /**
     * Makes the robot fly for a given number of rounds and minutes
     * @param rounds  the number of rounds to fly
     * @param minutes the number of minutes to fly
     * @return true if the robot successfully flew, false otherwise
     */
    public boolean fly(int rounds, int minutes) {
        if (rounds < 0 || minutes < 0) {
            System.out.println("Rounds and minutes cannot be negative.");
            return false;
        }
        int requiredBattery = rounds + minutes;
        if (this.battery >= requiredBattery) {
            this.battery -= requiredBattery;
            System.out.println("Flying " + rounds + " round(s) for " + minutes + " minute(s). Battery: " + this.battery + "% remaining.");
            return true;
        } else {
            System.out.println("Not enough battery to fly.");
            return false;
        }
    }


    /**
     * Makes the robot shrink to half its size if possible
     * @return the new size of the robot
     */
    public Number shrink() {
        if (this.size / 2 >= minSize && this.battery >= 2) { 
            this.size /= 2;
            this.battery -= 2;
            System.out.println(this.name + " has shrunk to half of its size (" + this.size + "). Battery: " + this.battery + "% remaining.");
        } else if (this.battery < 2) {
            System.out.println("Not enough battery to shrink.");
        } else {
            System.out.println(this.name + " cannot shrink further, as the size cannot go below the minimum allowed size of " + minSize + ".");

        }
        return this.size;
    }

     /**
     * Makes the robot grow to twice its size if possible
     * @return the new size of the robot
     */
    public Number grow() {
        if (this.size * 2 <= maxSize && this.battery >= 2) {
            this.size *= 2;
            this.battery -= 2;
            System.out.println(this.name + " has grown to double its size (" + this.size + "). Battery: " + this.battery + "% remaining.");
        } else if (this.battery < 2) {
            System.out.println("Not enough battery to grow.");
        } else {
            System.out.println(this.name + " cannot grow further. Maximum size (" + maxSize + ") would be exceeded.");
        }
        return this.size;
    }

    /**
     * Recharges the robot's battery to full capacity
     */
    public void rest() {
        if (this.battery < 100) {
            this.battery = 100;
            System.out.println("Recharing ... Battery is now fully charged.");
        } else {
            System.out.println("The battery is already fully charged.");
        }
    }

    /**
     * Throws an exception when undo is called
     */
    public void undo() {
        throw new RuntimeException("Cannot undo!");
    }

    /**
     * Returns a string with the robot's status
     * @return a string containing the robot's information
     */
    public String toString() {
        return "- name: " + name + "\n" + "- age: " + age + "\n" + "- color:" + color + "\n"  + "- size: " + size + "\n"  + "- freeHand:" + freeHand + "\n" 
                + "- owner: " + owner + "\n"  + "- battery: " + battery + "%";
    }

    public static void main(String[] args) {
        Robot Annie = new Robot("Annie", 3, "blue", 1.5, 2, "Nora");
        System.out.println(Annie);
        Annie.grab("toy");
        Annie.drop("toy");
        Annie.drop("toy");
        Annie.examine("desk");
        Annie.use("fridge");
        Annie.walk("north");
        Annie.shrink();
        Annie.shrink();
        Annie.grow();
        Annie.grow();
        Annie.grow();
        Annie.fly(3, 10);
        Annie.rest();
        Annie.rest();
    }
}
