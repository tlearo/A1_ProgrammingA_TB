package MtBullerWinterResort;

// Customer class for guest info
public class Customer {
  
  // Private fields for customer details
  private int custId; // ID for customers
  private String name; // Name of the customer
  private String skiLevel; // Skiing skill level of the customer
  private boolean firstStay = true; // Flag indicating if it's the customer's first stay
  static int nextID = 4; // Static variable for customer IDs
  
  // Default constructor
  public Customer() {
    custId = nextID++; // Assign the next available ID to the customer
  }
  
  // Parameterized constructor
  public Customer(String name, String skiLevel, boolean firstStay) {
    // Initialize customer with provided details
    this.name = name;
    this.skiLevel = skiLevel;
    this.firstStay = firstStay;
    custId = nextID++; // Assign the next available ID to the customer
  }
  
  // Getter method for customer ID
  public int getCustId() {
    return custId;
  }
  
  // Getter method for customer name
  public String getName() {
    return name;
  }
  
  // Getter method for skiing skill level
  public String getSkiLevel() {
    return skiLevel;
  }
  
  // Getter method for first stay flag
  public boolean isFirstStay() {
    return firstStay;
  }
  
  // Setter method for customer name
  public void setName(String name) {
    this.name = name;
  }
  
  // Setter method for skiing skill level
  public void setSkiLevel(String skiLevel) {
    this.skiLevel = skiLevel;
  }

  // Setter method for first stay flag
  public void setIsFirstStay(Boolean firstStay) {
    this.firstStay = firstStay;
  }
  
  // Method to represent Customer object as a string
  public String toString() {
    return "Customer ID: " + custId + ", name: " + name + ", skiing level: " + skiLevel + ", Is first stay?: " + firstStay;
  }
  
}
