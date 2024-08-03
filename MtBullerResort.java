package MtBullerWinterResort;

//imports
import java.io.*;
import java.security.Provider.Service;
import java.util.*;
import java.time.*;

// Main class
public class MtBullerResort {
  private FileInputStream fis;
  private ObjectInputStream ois;
  private FileOutputStream fos;
  private ObjectOutputStream oos;


// ArrayLists for accommodations, customers, packages, and services
  ArrayList<Accommodation> accommodations ;
  ArrayList<Customer> customers ;
  ArrayList<TravelPackage> packages ;
  ArrayList<Service> services;


    // Constructor initializes ArrayLists
  public  MtBullerResort() {
    accommodations = new ArrayList<Accommodation>();
    customers = new ArrayList<Customer>();   
    packages = new ArrayList<TravelPackage>();
    services = new ArrayList<Service>();
  }


//populate lists
  public void populateLists(){
    // Pre-defined accommodations
    Accommodation[] arrOfAccommodations = {
      new Accommodation("Hotel","2 Singles", 130, true), 
      new Accommodation("Hotel","1 Double", 130, true),
      new Accommodation("Hotel","1 King", 200, true),
      new Accommodation("Hotel","1 Twin", 120, true),
      new Accommodation("Hotel","2 Twins and 1 Queen", 220, true),
      new Accommodation("Cabin","2 Singles", 110, true), 
      new Accommodation("Cabin","1 Double", 110, true),
      new Accommodation("Cabin","1 King", 190, true),
      new Accommodation("Cabin","1 Twin", 120, true),
      new Accommodation("Hotel","2 Twins and 1 Queen", 200, true),
      new Accommodation("Lodge","2 Singles", 220, true), 
      new Accommodation("Lodge","1 Double", 250, true),
      new Accommodation("Lodge","1 King", 280, true),
      new Accommodation("Lodge","1 Twin", 200, true),
      new Accommodation("Lodge","2 Twins and 1 Queen", 280, true),
    };
    // Pre-defined customers
    Customer[] arrOfCustomers = {
      new Customer("Ben", "Beginner", false),
      new Customer("Jen", "Beginner", false),
      new Customer("Sam", "Intermediate", false),
      new Customer("Ella", "Intermediate", false),
      new Customer("Emily", "Advanced", false),
      new Customer("Josh", "Advanced", false),
    };
     for(int i = 0; i < arrOfAccommodations.length; i++)
       accommodations.add(arrOfAccommodations[i]);
     for(int i = 0; i < arrOfCustomers.length; i++)
       customers.add(arrOfCustomers[i]);
  }
  // Main method to run the program
  public void run() {
    // Main menu loop
    boolean flag = true;
    Scanner input = new Scanner(System.in);
    while (flag) {
    // Display menu options
    System.out.println("Mount Buller Resort options\n------------------------\n"+
    "1: Display all accommodations\n" +    
    "2: Display available accommodations\n"+              
    "3: Add customer\n"+    
    "4: List customers\n"+          
    "5: Create a package\n" +        
    "6: List packages\n"+ 
    "7: Add a lift pass to package\n"+  
    "8: Add skiing lesson to package\n"+     
    "9: Save packages to file\n"+  
    "10: Read packages from file\n"+  
    "11: Edit Packages\n"+  
    "12: Quit\n");
    
    System.out.print("Choose an option (Numbers 1-11): ");
    int option = input.nextInt();
    input.nextLine();
    // Switch statement for menu options
    switch (option) {
        case 1:
        displayAllAccommodations();
        break;
        case 2:
        displayAvailableAccommodations();
        break;
        case 3:
        addCustomer();
        break;
        case 4: 
        listCustomers();
        break;
        case 5:
        addPackage();
        break;
        case 6:
        listPackages();
        break;
        case 7:
        addLiftPass();
        break;
        case 8:
        addLeasonFees();
        break;
        case 9:
        savePackages();
        break;
        case 10:
        readPackages();
        break;
        case 11:
        readPackages();
        break;
        case 12:
        flag = false;
        break;
        default:
        System.out.println("Invalid option.");
      }
    
    }
  } 

// Method that adds lesson fees to package
public void addLeasonFees() {
    Scanner input = new Scanner(System.in);
    try {
        // Ask for package ID
        System.out.print("Enter the package ID number(Type X to exit): ");
        String packageIdInput = input.nextLine().trim(); // remove leading/trailing spaces

        if (packageIdInput.equalsIgnoreCase("X")) {
            System.out.println("Returning to main menu.");
            return;
        }

        int packageId = Integer.parseInt(packageIdInput);

        // Search for the package with the given ID
        TravelPackage targetPackage = null;
        for (TravelPackage p : packages) {
            if (p.getPackageId() == packageId) {
                targetPackage = p;
                break;
            }
        }

        // If the package is not found, display a message and return
        if (targetPackage == null) {
            System.out.println("Package with ID " + packageId + " not found.");
            return;
        }

        // Ask for the number of lessons
        System.out.print("Fees per lesson are: ");
        System.out.print("Beginner: $25 ");
        System.out.print("Intermediate: $20 ");
        System.out.print("Expert: $15 ");
        System.out.print("Enter the number of lessons to add (Press X to exit): ");
        String numberOfLessonsInput = input.nextLine().trim();

        if (numberOfLessonsInput.equalsIgnoreCase("X")) {
            System.out.println("Returning to main menu.");
            return;
        }

        int numberOfLessons;
        try {
            numberOfLessons = Integer.parseInt(numberOfLessonsInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number or 'X' to exit.");
            return;
        }

        // set max no of lessons
        if (numberOfLessons > 128) {
            System.out.println("Error: The number of lessons booked at one time cannot exceed 6 months.");
            return;
        }

        // set the number of lessons and calculate the lesson fees
        targetPackage.setNoOfLessons(numberOfLessons);
        double lessonFee = 0;
        switch (targetPackage.getLessonLevel()) {
            case "Expert":
                lessonFee = numberOfLessons * 15;
                break;
            case "Intermediate":
                lessonFee = numberOfLessons * 20;
                break;
            case "Beginner":
                lessonFee = numberOfLessons * 25;
                break;
            default:
                System.out.println("Invalid ski level.");
                return;
        }
        targetPackage.setTotalCost(targetPackage.getTotalCost() + lessonFee);

        // display a success message
        System.out.println("Lesson fees added successfully to package " + packageId + ".");
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid package ID or 'X' to exit.");
    }
}



// Method that adds lift passes to package
public void addLiftPass() {
    Scanner input = new Scanner(System.in);

    // Ask for package ID
    System.out.print("Enter the package ID number (Type X to return back): ");
    String packageIdInput = input.nextLine().trim(); // remove leading/trailing spaces

    if (packageIdInput.equalsIgnoreCase("X")) {
        addPackage();
        return;
    }

    int packageId;
    try {
        packageId = Integer.parseInt(packageIdInput);
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid package ID or 'X' to return back.");
        return;
    }

    // Search for the package with the given ID
    TravelPackage targetPackage = null;
    for (TravelPackage p : packages) {
        if (p.getPackageId() == packageId) {
            targetPackage = p;
            break;
        }
    }

    // If the package is not found, display a message and return
    if (targetPackage == null) {
        System.out.println("Package with ID " + packageId + " not found.");
        return;
    }

    // Ask the user whether they want to buy day passes or a season pass
    System.out.println("Select an option (1 or 2, or type X to exit):");
    System.out.println("1. Buy day passes");
    System.out.println("2. Buy a season pass");
    System.out.print("Enter your choice: ");
    String optionInput = input.nextLine().trim();

    if (optionInput.equalsIgnoreCase("X")) {
        System.out.println("Returning to main menu.");
        return;
    }

    int option;
    try {
        option = Integer.parseInt(optionInput);
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid option.");
        return;
    }

    switch (option) {
        case 1:
            // Ask for the number of days for lift passes
            System.out.print("Enter the number of days you would like lift passes for (Enter 1 to 128 days in numbers or type X to exit): ");
            String numberOfDaysInput = input.nextLine().trim();

            if (numberOfDaysInput.equalsIgnoreCase("X")) {
                System.out.println("Returning to main menu.");
                return;
            }

            int numberOfDays;
            try {
                numberOfDays = Integer.parseInt(numberOfDaysInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number or 'X' to exit.");
                return;
            }

            // Ensure that the number of lift passes does not exceed 128
            if (numberOfDays > 128) {
                System.out.println("Error: The number of lift passes cannot exceed 128 (One ski season worth of passes).");
                return;
            }

            // Calculate the total cost based on the number of days and apply discounts if applicable
            double totalPrice;
            if (numberOfDays >= 5) {
                // Apply 10% discount for lift passes for 5 days or more
                totalPrice = numberOfDays * 26 * 0.9;
            } else {
                // No discount for fewer than 5 days
                totalPrice = numberOfDays * 26;
            }
            // Set the total cost for lift passes
            targetPackage.setTotalCost(totalPrice);
            // Set the number of lift passes for the package
            targetPackage.setSkiPass(numberOfDays);

            // Display a success message
            System.out.println("Lift passes added successfully to package " + packageId + ".");
            break;
        case 2:
            // Set the number of lift passes to unlimited for a season pass and set the total cost
            targetPackage.setSkiPass(Integer.MAX_VALUE);
            targetPackage.setTotalCost(200); // Season pass price is $200
            // Display a success message
            System.out.println("Season pass added successfully to package " + packageId + ".");
            break;
        default:
            System.out.println("Invalid option.");
            break;
    }
}
  public void displayAllAccommodations() {
    for (Accommodation a:accommodations) {
      System.out.println(a);
    }
  }

  public void displayAvailableAccommodations() {
      for (Accommodation a:accommodations) {
        if (a.getAvailability())
          System.out.println(a);
      }
  }
// Method that adds a new customer
public void addCustomer() {
    Scanner input = new Scanner(System.in);
    try {
        System.out.print("Customer name (Type X to exit):");
        String name = input.nextLine().trim();

        if (name.equalsIgnoreCase("X")) {
            System.out.println("Returning to main menu.");
            return;
        }

        System.out.println("Enter skiing skill level:");
        System.out.println("1. Beginner");
        System.out.println("2. Intermediate");
        System.out.println("3. Advanced");
        System.out.print("Enter your choice (Type 1, 2, 3 or X to exit): ");
        String choiceInput = input.nextLine().trim();

        if (choiceInput.equalsIgnoreCase("X")) {
            System.out.println("Returning to main menu.");
            return;
        }

        int choice = Integer.parseInt(choiceInput);
        String skiLevel;
        switch (choice) {
            case 1:
                skiLevel = "Beginner";
                break;
            case 2:
                skiLevel = "Intermediate";
                break;
            case 3:
                skiLevel = "Advanced";
                break;
            default:
                System.out.println("Invalid choice. Setting skiing skill level to Beginner.");
                skiLevel = "Beginner";
                break;
        }

        Customer c = new Customer(name, skiLevel, true);
        customers.add(c);

        System.out.println("Customer added successfully.");
        System.out.println("The customer ID is: " + c.getCustId());
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a customer ID number or 'X' to exit.");
    } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}


  public void listCustomers() {
      for (Customer c:customers) {
      System.out.println(c);
      }
  }
  public void listPackages() {
      for (TravelPackage p:packages) {
      System.out.println(p);
      }
  }
  public Customer searchCustomerById(int custId) {
    for (Customer c : customers) {
        if (c.getCustId() == custId)
            return c;
    }
    return null;
  }
  public void addAccommodation(TravelPackage travelPackage) {
    Scanner input = new Scanner(System.in);
    String accommodationType = "";

    // Ask for accommodation type
    System.out.println("Choose accommodation type:");
    System.out.println("1. Hotel");
    System.out.println("2. Cabin");
    System.out.println("3. Lodge");
    System.out.println("4. All");

    System.out.print("Enter your choice (Type X to return back): ");
    String choiceString = input.nextLine();
    if (choiceString.equalsIgnoreCase("X")) {
        addPackage();
        return;
    }
    int choice = Integer.parseInt(choiceString);

    switch (choice) {
        case 1:
            accommodationType = "Hotel";
            break;
        case 2:
            accommodationType = "Cabin";
            break;
        case 3:
            accommodationType = "Lodge";
            break;
        case 4:
            break;
        default:
            System.out.println("Invalid choice. Defaulting to Hotel.");
            accommodationType = "Hotel";
            break;
    }

    // Filter accommodations by type
    ArrayList<Accommodation> availableAccommodations = new ArrayList<>();
    if (!accommodationType.isEmpty()) {
        for (Accommodation a : accommodations) {
            if (a.getType().equalsIgnoreCase(accommodationType) && a.getAvailability()) {
                availableAccommodations.add(a);
            }
        }
    } else {
        // display all available accommodations
        for (Accommodation a : accommodations) {
            if (a.getAvailability()) {
                availableAccommodations.add(a);
            }
        }
    }

    if (availableAccommodations.isEmpty()) {
        System.out.println("No available accommodations.");
        return;
    }

    // Display available accommodations
    System.out.println("Available accommodations:");
    for (Accommodation a : availableAccommodations) {
        System.out.println(a);
    }

    
    System.out.println("Enter accommodation number (Type X to return back): ");
    String choiceStr = input.nextLine();
    if (choiceStr.equalsIgnoreCase("X")) {
        addPackage();
        return;
    }
    choice = Integer.parseInt(choiceStr);

    // Select accommodation
    int accommodationId = choice;
    // Find accommodation by ID
    Accommodation accommodation = null;
    for (Accommodation a : availableAccommodations) {
        if (a.getAccommodationNo() == accommodationId) {
            accommodation = a;
            break;
        }
    }

    if (accommodation == null) {
        System.out.println("Accommodation not found.");
        addPackage(); // Go back to enter start date
        return;
    }

    // Set accommodation number for the package
    travelPackage.setAccommodationNo(accommodationId);
    accommodation.setAvailability(false); // Mark accommodation as booked
}

public void addPackage() {
    Scanner input = new Scanner(System.in);

    try {
        // Ask for Customer ID
        System.out.print("Enter Customer ID as a number (Type X to exit): ");
        String customerIdInput = input.nextLine().trim();

        if (customerIdInput.equalsIgnoreCase("X")) {
            System.out.println("Returning to main menu.");
            return;
        }

        int customerId = Integer.parseInt(customerIdInput);

        // Find customer by ID
        Customer customer = null;
        for (Customer c : customers) {
            if (c.getCustId() == customerId) {
                customer = c;
                break;
            }
        }

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        // Ask for start date
        System.out.print("Enter start date (yyyy-mm-dd) (Type X to exit): ");
        String startDateStr = input.nextLine().trim();
        
        if (startDateStr.equalsIgnoreCase("X")) {
            System.out.println("Returning to main menu.");
            return;
        }

        LocalDate startDate = LocalDate.parse(startDateStr); // Convert String to LocalDate

        // Validate start date
        LocalDate currentDate = LocalDate.now();
        if (startDate.isBefore(currentDate)) {
            System.out.println("Invalid start date. Please enter a date in the future.");
            return;
        }

        // Ask for duration
        System.out.print("Enter duration (in days) (Type X to exit): ");
        String durationInput = input.nextLine().trim();

        if (durationInput.equalsIgnoreCase("X")) {
            System.out.println("Returning to main menu.");
            return;
        }

        int duration = Integer.parseInt(durationInput);

        // Instantiate the travel package with the provided information
        TravelPackage travelPackage = new TravelPackage(customer, startDate, duration);

        // Add accommodation to the package
        addAccommodation(travelPackage);

        // Add package to the list
        packages.add(travelPackage);

        System.out.println("Package created successfully.");
        System.out.println("The package ID is: " + travelPackage.getPackageId());
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid Customer ID, duration, or 'X' to exit.");
    } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}
public TravelPackage searchPackagesByCustId(int custId) {
    for (TravelPackage p: packages) {
    if (p.getCustId() == custId)
    return p;
    }
    return null;
}


public void editTravelPackageByCustId(int custId) {
    Scanner input = new Scanner(System.in);
    try {
        // Ask for Customer ID
        System.out.print("Enter Customer ID as a number (Type X to exit): ");
        String customerIdInput = input.nextLine().trim();

        if (customerIdInput.equalsIgnoreCase("X")) {
            System.out.println("Returning to main menu.");
            return;
        }

        int customerId = Integer.parseInt(customerIdInput);

        // Find customer by ID
        Customer customer = null;
        for (Customer c : customers) {
            if (c.getCustId() == customerId) {
                customer = c;
                break;
            }
        }

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        TravelPackage p = searchPackagesByCustId(custId);
        if (p != null) {
            System.out.println(p);

            try {
                System.out.println("Select option:\n" +
                        "1. Start date\n" +
                        "2. Duration\n" +
                        "3. Accommodation\n");

                String optionInput = input.nextLine().trim();

                if (optionInput.equalsIgnoreCase("X")) {
                    System.out.println("Returning to main menu.");
                    return;
                }

                int choice = Integer.parseInt(optionInput);

                switch (choice) {
                    case 1:
                        System.out.println("Enter a new start date (yyyy-mm-dd) (Type X to exit): ");
                        String dateStr = input.nextLine().trim();

                        if (dateStr.equalsIgnoreCase("X")) {
                            System.out.println("Returning to main menu.");
                            return;
                        }

                        LocalDate newStartDate = LocalDate.parse(dateStr); // Convert String to LocalDate
                        p.setStartDate(newStartDate);
                        break;
                    case 2:
                        System.out.println("Enter a new duration (As a number)(Type X to exit): ");
                        String newDurationInput = input.nextLine().trim();

                        if (newDurationInput.equalsIgnoreCase("X")) {
                            System.out.println("Returning to main menu.");
                            return;
                        }

                        int newDuration = Integer.parseInt(newDurationInput);
                        p.setDuration(newDuration);
                        break;
                    case 3:
                        displayAvailableAccommodations();
                        System.out.println("Enter a new accommodation number (Type X to exit): ");
                        String newAccommodationNoInput = input.nextLine().trim();

                        if (newAccommodationNoInput.equalsIgnoreCase("X")) {
                            System.out.println("Returning to main menu.");
                            return;
                        }

                        int newAccommodationNo = Integer.parseInt(newAccommodationNoInput);
                        p.setAccommodationNo(newAccommodationNo);
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid option or 'X' to exit.");
            }
        } else {
            System.out.println("No package found for customer ID: " + custId);
        }
    } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}



  public Accommodation searchAccommodationsByAccommodationNo(int accommodationNo){
      for(Accommodation a: accommodations){
      if(a.getAccommodationNo() == accommodationNo)
          return a;
      }
      return null;
  }

  public Service searchServiceByType(String type) {
      for (Service s: services) {
      if (s.getType().equalsIgnoreCase(type))
      return s;
      }
      return null;
  }
      
  public void savePackages() {
      try {
      fos = new FileOutputStream("packages.dat");
      oos = new ObjectOutputStream(fos);
      for (TravelPackage p:packages) {
          oos.writeObject(p);
      }
      fos.close();
      oos.close();
      
      } catch (Exception e) {
      e.printStackTrace();
      }
  }


  public void readPackages() {
  packages.clear();
      try {
      fis = new FileInputStream("packages.dat");
      ois = new ObjectInputStream(fis);
      
      while (true) {
          try {
          Object object = ois.readObject();
          TravelPackage p = (TravelPackage)object;
          //update accommodation status
          int accommodationNo = p.getAccommodationNo();
          Accommodation a = searchAccommodationsByAccommodationNo(accommodationNo);
          a.setAvailability(false);
          //add to array list
          packages.add(p);
          System.out.println(p);
          } catch (EOFException eof) {
          fis.close();
          ois.close();
          break;
          }
      }
      
      } catch (Exception e) {
      e.printStackTrace();
      }

  }
}
    