package MtBullerWinterResort;

// Accommodation class for places to stay
public class Accommodation {
  
  // Private fields for accom details
  private int accommodationNo; // ID for each accommodation
  private String type; // Type of accommodation (e.g., Hotel, Cabin, Lodge)
  private String bedType; // Type of bed configuration (e.g., Single, Double, King)
  private double pricePerDay; // Price per day for accom
  private boolean availability = true; // Availability of the accommodation
  static int nextId = 1; // variable to generate accommodation numbers

  // Default constructor
  public Accommodation() {
    accommodationNo = nextId++; // Assign next available ID to the accommodation
  }
  
  // Parameterized constructor
  public Accommodation(String type, String bedType, double pricePerDay, boolean availability) {
    // Initialize accommodation with the provided details
    this.type = type;
    this.bedType = bedType;
    this.pricePerDay = pricePerDay;
    this.availability = availability;
    accommodationNo = nextId++; // Assign the next available ID to the accommodation
  }

  // Getter method for accommodation number
  public int getAccommodationNo() {
    return accommodationNo;
  }
  
  // Getter method for accommodation type
  public String getType() {
    return type;
  }

  // Getter method for bed type
  public String getbedType() {
    return bedType;
  }
  
  // Getter method for price per day
  public double getPricePerDay() {
    return pricePerDay;
  }
  
  // Getter method for availability status
  public boolean getAvailability() {
    return availability;
  }
  
  // Setter method for accommodation type
  public void setType(String type) {
    this.type = type;
  }

  // Setter method for bed type
  public void setbedType(String bedType) {
    this.bedType = bedType;
  }
  
  // Setter method for price per day
  public void setPricePerDay(double pricePerDay) {
    this.pricePerDay = pricePerDay;
  }
  
  // Setter method for availability status
  public void setAvailability(boolean availability) {
    this.availability = availability;
  }
  
  // Method to check if accommodation is available
  public boolean isAvailable() {
    return availability;
  }
  
  // Method to represent Accommodation object as a string
  public String toString() {
    return "Accommodation #: " + accommodationNo + ", type: " + type + ", Configuration: " + bedType + ", price per day: " + pricePerDay + ", Is available? " + availability;
  }
}
