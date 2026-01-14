package controller;

import java.util.ArrayList;
import model.Drink;
import model.DrinkStore;
import view.AdminDashboard;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DrinkController {
    
    // Variable to store and manage our drink data collections
    private DrinkStore store;
    // Variable to access the admindashboard screeen
    private AdminDashboard admin;
    
    // Stack variables (for delete/undo)
    private int top = -1;          // top pointer starts at -1
    private int stackSize = 10;    // max 10 deleted drinks

    // Queue variables (for recent drinks)
    private int front = -1;        // front pointer starts at -1  
    private int rear = -1;         // rear pointer starts at -1
    private int queueSize = 3;     // show 3 recent drinks 
    
    public DrinkController(DrinkStore store, AdminDashboard admin)
    {
        this.store = store;
        this.admin = admin;
    }
    
    // method to add drink to AdminDashboard to Drink Tab Table using textfield input
    public void addDrink()
    {
        // First check if all fields are filled
        if (admin.getDrinkId().isEmpty() || 
            admin.getDrinkName().isEmpty() || 
            admin.getDrinkPrice().isEmpty() || 
            admin.getDrinkQuantity().isEmpty()) {
            
            // Display message if any of text field is empty
            JOptionPane.showMessageDialog(admin, 
                "Please fill in all fields!");
            return;
        }
        try {
        // Get data from Drink of AdminDashboard text fields
        int id = Integer.parseInt(admin.getDrinkId());
        String name = admin.getDrinkName();
        double price = Double.parseDouble(admin.getDrinkPrice());
        int quantity = Integer.parseInt(admin.getDrinkQuantity());
        
        // Check if ID is already exist
        boolean idExists = false;
        for (Drink existingDrink : store.getDrinks())
        {
            if (existingDrink.getDrinkID() == id)
            {
                idExists = true;
                break;
            }
        }
        
        if (idExists)
        {
            JOptionPane.showMessageDialog(admin, 
                "Drink ID " + id + " already exists!");
            return;
        }
        
        // VALIDATION: Check if price is positive and greater than zero
        if (id < 0) {
            JOptionPane.showMessageDialog(admin, 
                "ID must be Whole Number!\n" +
                "You entered ID:" + id );
            return;
        }
        
        // VALIDATION: Check if price is positive and greater than zero
        if (price <= 0) {
            JOptionPane.showMessageDialog(admin, 
                "Price must be greater than zero!\n" +
                "You entered RS:" + price );
            return;
        }
        
        // VALIDATION : Check if quantity is greater than zero (not zero, not negative)
        if (quantity <= 0)
        {
            JOptionPane.showMessageDialog(admin, 
                "Quantity must be greater than zero!");
            return;
        }
        
        // check if Drink name already exist
        boolean nameExists = false;
        for (Drink existingDrink : store.getDrinks())
        {
            if (existingDrink.getDrinkName().equalsIgnoreCase(name))
            {
                nameExists = true;
                break;
            }
        }
        
        if (nameExists)
        {
            JOptionPane.showMessageDialog(admin, 
                "Drink Name :" + name + "\n already exists!");
            return;
        }
        
        // Check if ID or name exists in deleted drinks
        if (isInDeletedDrinks(id, name)) {
            JOptionPane.showMessageDialog(admin, 
                "This ID or Name was previously deleted.\n" +
                "Please use UNDO button to restore it.");
            return;
}
        // Create new Drink object
        Drink newDrink = new Drink(id, name, price, quantity);
        
        // Add to ArrayList (main data storage)
        store.getDrinks().add(newDrink);
        
        // Update recent drinks queue
        updateRecentDrinks();
        
        // Get the table model
        DefaultTableModel model = (DefaultTableModel) admin.getTblDrinks().getModel();
        
        // Add new row to JTable
        Object[] rowData = {
            newDrink.getDrinkID(),
            newDrink.getDrinkName(), 
            newDrink.getDrinkPrice(),
            newDrink.getDrinkQuantity()
        };
        model.addRow(rowData);
        
        // Clear all text fields
        admin.clearDrinkForm();
        
        // Show success message
        JOptionPane.showMessageDialog(admin, 
            "Drink added successfully!");
        
    } catch (NumberFormatException e) 
        {
           // Show error if numbers are not valid
           JOptionPane.showMessageDialog(admin, 
            "Please check your input:\n" +
            "- ID must be a positive whole number\n" +
            "- Price must be a positive number \n" +
            "- Quantity must be a positive whole number ");
       }
    }
    
    //method to check deleted drinks
    private boolean isInDeletedDrinks(int id, String name)
    {
        for (Drink d : store.getDeletedDrinks())
        {
            if (d.getDrinkID() == id || d.getDrinkName().equalsIgnoreCase(name))
            {
                return true;
            }
        }
        return false;
    }
    
    // method to update drink table of DrinkPanel of admin dashboard for price or quantity or both
    public void updateDrink()
    {
       // First, check if user has selected any drink from the table
       int selectedRow = admin.getTblDrinks().getSelectedRow();
    
       // If selectedRow is -1, it means no row is selected
       if (selectedRow < 0)  
       {
           JOptionPane.showMessageDialog(admin, 
               "Please select a row from table to update.");
           return; // Stop here if no drink is selected
       }
    
       try
       {
           // Get the drink object that user selected
           Drink drink = store.getDrinks().get(selectedRow);
        
           // Save the current values before updating
           double currentPrice = drink.getDrinkPrice();
           int currentQuantity = drink.getDrinkQuantity();
        
           // Get the new values from text fields
           // These come from the form where user types
           String priceText = admin.getDrinkPrice();
           String quantityText = admin.getDrinkQuantity();
           
           // Check if price field is empty
           // User must enter a new price value
           if (priceText.isEmpty())
           {
               JOptionPane.showMessageDialog(admin, 
                   "Please enter a new price value.");
               return;
           }
        
           // Check if quantity field is empty
           // User must enter a new quantity value
           if (quantityText.isEmpty()) 
           {
               JOptionPane.showMessageDialog(admin, 
                   "Please enter a new quantity value.");
               return;
           }
        
           // Convert text values to numbers
           double newPrice = Double.parseDouble(priceText);
           int newQuantity = Integer.parseInt(quantityText);
        
           // Check if price value is valid
           // Price must be greater than 0
           if (newPrice <= 0)
           {
               JOptionPane.showMessageDialog(admin, 
                   "Price must be greater than 0.");
               return;
           }
        
           // Check if quantity value is valid
           // Quantity must be greater than 0
           if (newQuantity <= 0)
           {
               JOptionPane.showMessageDialog(admin, 
                   "Quantity must be greater than 0.");
               return;
           }
        
           // Check if user actually changed the values
           // Compare new values with old values
           if (newPrice == currentPrice && newQuantity == currentQuantity)
           {
              JOptionPane.showMessageDialog(admin, 
                   "Please change price or quantity values.");
               return;
           }
        
           // Update the drink object with new values
           drink.setDrinkPrice(newPrice);
           drink.setDrinkQuantity(newQuantity);
           
           // Update recent drinks queue
           updateRecentDrinks();
        
           // Refresh the table to show updated values
           admin.loadDrinksToTable();
           
           // Clear the form after update
           admin.clearDrinkForm();
        
           // Show success message to user
           JOptionPane.showMessageDialog(admin, 
               "Drink updated successfully.");
          
        }   
       catch (NumberFormatException e)
       {
           // This happens when user enters text instead of numbers
           JOptionPane.showMessageDialog(admin, 
               "Please enter valid numbers for price and quantity.");
        }
    }
    
    // Method to delete a drink from Drink table
    public void deleteDrink()
    {
        // First check if user selected a drink from table
        int selectedRow = admin.getTblDrinks().getSelectedRow();
        
        // checking condition if no row selected from table
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(admin, 
                "Please select a drink from the table first.");
            return;
        }
        
        // Check for stack overflow (from PPT)
        if (isStackFull()) {
            JOptionPane.showMessageDialog(admin, 
                "Stack is full! Cannot delete more drinks.\n" +
                "Stack Top: " + top);
            return;
        }
        
        // Get the selected drink
        Drink drink = store.getDrinks().get(selectedRow);
        String drinkName = drink.getDrinkName();
        
        // Remove from main drinks list
        store.getDrinks().remove(selectedRow);
        
        // Push to stack
        store.getDeletedDrinks().push(drink);
        
        // Update stack top pointer
        top++; // increment top
        
        // Update recent drinks queue
        updateRecentDrinks();
        
        // Refresh the tables
        admin.loadDrinksToTable();
        admin.clearDrinkForm();
        
        // Show message with stack info
        JOptionPane.showMessageDialog(admin, 
            drinkName + " has been deleted.\n" +
            "Stack Top: " + top);
    }
    
    // Method to undo delete (restore drink)
    public void undoDelete() {
        // Check for stack underflow (from PPT)
        if (isStackEmpty()) {
            JOptionPane.showMessageDialog(admin,
                    "Stack is empty! Nothing to undo.\n"
                    + "Stack Top: " + top);
            return;
        }

        // Pop from stack (LIFO - get last deleted drink)
        Drink drink = store.getDeletedDrinks().pop();
        String drinkName = drink.getDrinkName();

        // Update stack top pointer (from PPT)
        top--; // decrement top

        // Add drink back to main list
        store.getDrinks().add(drink);
        
        // Update recent drinks queue
        updateRecentDrinks();

        // Reset top pointer if stack is now empty
        if (store.getDeletedDrinks().isEmpty()) {
            top = -1;
        }

        // Refresh the tables
        admin.loadDrinksToTable();

        // Show message with stack info
        JOptionPane.showMessageDialog(admin,
                drinkName + " has been restored.\n"
                + "Stack Top: " + top);
    }
    
    //This method sorts drinks by their ID from lowest to highest
    //SElection sort
    public void sortDrinksById() {
        // Get all drinks from store
        ArrayList<Drink>allDrinks = store.getDrinks();
        //get size and store in variable totalDrinks
        int totalDrinks = allDrinks.size();

        // Selection sort for ID numbers
        for (int i = 0; i < totalDrinks - 1; i++) {
            int smallestIndex = i;

            // Find drink with smallest ID
            for (int j = i + 1; j < totalDrinks; j++)
            {
                if (allDrinks.get(j).getDrinkID() < allDrinks.get(smallestIndex).getDrinkID())
                {
                    smallestIndex = j;
                }
            }

            // Swap drinks to put smallest ID at current position
            Drink tempDrink = allDrinks.get(i);
            allDrinks.set(i, allDrinks.get(smallestIndex));
            allDrinks.set(smallestIndex, tempDrink);
        }

        // Update table display
        admin.loadDrinksToTable();
    }
    
    // Search drinks by name, ID, price or quantity
    // Linear chearching
    public ArrayList<Drink> searchDrinks(String searchText) {
        // Create list for search results
        ArrayList<Drink> foundDrinks = new ArrayList<>();

        // Get all drinks from store
        ArrayList<Drink> allDrinks = store.getDrinks();

        // Convert search text to lowercase for case-insensitive search
        String searchLower = searchText.toLowerCase();

        // Linear Search
        for (Drink drink : allDrinks) {
            // Search in drink name
            if (drink.getDrinkName().toLowerCase().contains(searchLower)) {
                foundDrinks.add(drink);
            } // Search in drink ID
            else if (String.valueOf(drink.getDrinkID()).equals(searchText)) {
                foundDrinks.add(drink);
            } // Search in drink price
            else if (String.valueOf(drink.getDrinkPrice()).contains(searchText)) {
                foundDrinks.add(drink);
            } // Search in drink quantity
            else if (String.valueOf(drink.getDrinkQuantity()).contains(searchText)) {
                foundDrinks.add(drink);
            }
        }

        return foundDrinks;
    }

    //BUBBLE SORT BY NAME 
    //  Sort drinks alphabetically by name
    public void sortDrinksByName() {
        // Get the drinks list from store
        ArrayList<Drink> drinksList = store.getDrinks();
        int size = drinksList.size();

        // Bubble Sort Algorithm 
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                // Get names of current and next drink
                String name1 = drinksList.get(j).getDrinkName();
                String name2 = drinksList.get(j + 1).getDrinkName();

                // Compare names alphabetically
                if (name1.compareToIgnoreCase(name2) > 0) {
                    // Swap drinks
                    Drink temp = drinksList.get(j);
                    drinksList.set(j, drinksList.get(j + 1));
                    drinksList.set(j + 1, temp);
                }
            }
        }

        // Update the table display
        admin.loadDrinksToTable();
    }

     //SELECTION SORT BY PRICE 
    //Sort drinks by price from low to high
    public void sortDrinksByPrice() {
        // Get the drinks list from store
        ArrayList<Drink> drinksList = store.getDrinks();
        int size = drinksList.size();

        // Selection Sort Algorithm (Week 7 PPT)
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;

            // Find drink with minimum price
            for (int j = i + 1; j < size; j++) {
                if (drinksList.get(j).getDrinkPrice() < drinksList.get(minIndex).getDrinkPrice()) {
                    minIndex = j;
                }
            }

            // Swap drinks
            Drink temp = drinksList.get(i);
            drinksList.set(i, drinksList.get(minIndex));
            drinksList.set(minIndex, temp);
        }

        // Update the table display
        admin.loadDrinksToTable();
    }

    // SELECTION SORT BY QUANTITY
    // Sort drinks by quantity from low to high
    public void sortDrinksByQuantity() {
        // Get the drinks list from store
        ArrayList<Drink> drinksList = store.getDrinks();
        int size = drinksList.size();

        // Selection Sort Algorithm
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;

            // Find drink with minimum quantity
            for (int j = i + 1; j < size; j++) {
                if (drinksList.get(j).getDrinkQuantity() < drinksList.get(minIndex).getDrinkQuantity()) {
                    minIndex = j;
                }
            }

            // Swap drinks 
            Drink temp = drinksList.get(i);
            drinksList.set(i, drinksList.get(minIndex));
            drinksList.set(minIndex, temp);
        }

        // Update the table display
        admin.loadDrinksToTable();
    }

    // Check if stack is empty (from PPT: top = -1 means empty)
    private boolean isStackEmpty() {
        return top == -1;
    }
    
    // Check if stack is full (from PPT: top = stackSize-1 means full)
    private boolean isStackFull() {
        return top >= stackSize - 1;
    }
    
    // for home tab panel in Admin
    // Simple method to update recent drinks queue
    public void updateRecentDrinks() {
        // Clear queue
        store.getRecentDrinks().clear();

        // Reset pointers
        front = -1;
        rear = -1;

        // Get all drinks
        ArrayList<Drink> allDrinks = store.getDrinks();
        int count = allDrinks.size();

        // Take last 3 drinks
        int start = count - 3;
        if (start < 0) {
            start = 0;
        }

        for (int i = start; i < count; i++) {
            Drink drink = allDrinks.get(i);
            store.getRecentDrinks().add(drink);

            // Update queue pointers
            if (front == -1) {
                front = 0; // first element
            }
            rear = store.getRecentDrinks().size() - 1; // last element
        }

        // Update display
        admin.updateRecentDrinksTable();
    }
}