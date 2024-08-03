package MtBullerWinterResort;

import java.io.Serializable;
import java.time.LocalDate;

// TravelPackage class is for the trip details of customers
public class TravelPackage implements Serializable {
  
  // Private fields for travel package details
  private int packageId; // ID for each travel package
  private int custId; // Customer ID associated with the package
  private int accommodationNo; // Accommodation number of package
  private LocalDate startDate; // Start date 
  private int duration; // Duration of the stay
  private double totalCost = 0; // Total cost of the package
  private int noOfLessons; // Number of skiing lessons added to the package
  private String lessonLevel; // Skill level for skiing lessons
  private int skiPass = 0; // Number of ski passes added to the package
  static int nextID = 1; // Static variable for package IDs

  // Default constructor
  public TravelPackage() {
    packageId = nextID++; // Assign the next available ID to the package
  }

  // Constructor with parameters
  public TravelPackage(Customer customer, LocalDate startDate, int duration) {
    this.custId = customer.getCustId(); // Get the customer ID from customer object
    this.startDate = startDate;
    this.duration = duration;
    this.totalCost = 0; // Initialize totalCost to 0
    this.accommodationNo = 0; // Initialize accommodationNo to 0
    this.skiPass = 0; // Initialize skiPass to 0
    this.noOfLessons = 0; // Initialize noOfLessons to 0
    packageId = nextID++; // Assign the next available ID to the package
  }

  // Getters and setters
  public int getPackageId() {
    return packageId;
  }

  public int getCustId() {
    return custId;
  }

  public int getAccommodationNo() {
    return accommodationNo;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public int getDuration() {
    return duration;
  }

  public double getTotalCost() {
    return totalCost;
  }

  public int getNoOfLessons() {
    return noOfLessons;
  }

  public String getLessonLevel() {
    return lessonLevel;
  }

  public int getSkiPass() {
    return skiPass;
  }

  public void setTotalCost(double totalCost) {
    this.totalCost = totalCost;
  }

  public void setNoOfLessons(int noOfLessons) {
    this.noOfLessons = noOfLessons;
  }

  public void setLessonLevel(String lessonLevel) {
    this.lessonLevel = lessonLevel;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public void setSkiPass(int skiPass) {
    this.skiPass = skiPass;
  }

  public void setAccommodationNo(int accommodationNo) {
    this.accommodationNo = accommodationNo;
  }
  
  // Method to represent TravelPackage object as a string
  @Override
  public String toString() {
    return "Package ID: " + packageId + ", customer ID: " + custId + ", start date: " + startDate + ", duration: " + duration +
            "\nAccommodation #: " + accommodationNo + "\n";
  }
}

