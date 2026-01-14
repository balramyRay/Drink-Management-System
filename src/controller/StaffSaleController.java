
package controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Drink;
import view.StaffDashboard;
import model.DrinkStore;


public class StaffSaleController 
{
   // variable to store sale made by staff
   private ArrayList<String> staffSale;
   private StaffDashboard staffDashboard;
   private DrinkStore drinkStore;
    
    
    public StaffSaleController(DrinkStore drinkStore, StaffDashboard staffDashboard)
    {
        staffSale = new ArrayList<>();
        this.staffDashboard = staffDashboard;
        this.drinkStore = drinkStore;
        loadTable();
    }
    
    public void proceedSaleMethod()
    {
       // checking empty
        if (staffDashboard.getID().isEmpty() || staffDashboard.getQuantity().isEmpty()) {
            JOptionPane.showMessageDialog(staffDashboard, 
                "Please fill in all fields!");
            return;
        }
        
        try {
            // get text from textfield
            int id = Integer.parseInt(staffDashboard.getID());
            int requestedQuantity = Integer.parseInt(staffDashboard.getQuantity());
            
            // Get all drinks from store
            ArrayList<Drink> drinks = drinkStore.getDrinks();
            
            // Find the drink with matching ID
            Drink foundDrink = null;
            for (Drink drink : drinks) {
                if (drink.getDrinkID() == id) {
                    foundDrink = drink;
                    break;
                }
            }
            
            // checking if drink not available for entered ID
            if (foundDrink == null) {
                JOptionPane.showMessageDialog(staffDashboard, 
                    "Drink not available for entered ID");
                return;
            }
            
            // Now get the price and quantity from found drink
            double price = foundDrink.getDrinkPrice();
            int availableQuantity = foundDrink.getDrinkQuantity();
            
            // Validate requested quantity
            if (requestedQuantity <= 0) {
                JOptionPane.showMessageDialog(staffDashboard, 
                    "Quantity must be greater than zero!");
                return;
            }
            
            // Check if enough stock is available
            if (requestedQuantity > availableQuantity) {
                JOptionPane.showMessageDialog(staffDashboard, 
                    "Insufficient stock! Only " + availableQuantity + " items available.");
                return;
            }
            
            // Calculate total price
            double totalPrice = price * requestedQuantity;
            
            // Reduce the quantity in inventory (update the drink)
            int newQuantity = availableQuantity - requestedQuantity;
            foundDrink.setDrinkQuantity(newQuantity);
            
            
            // Update the table to show new quantity
            loadTable();
            
            // Clear the form
            staffDashboard.clearForm();
            
            // Show success message with details
            JOptionPane.showMessageDialog(staffDashboard, 
                "Sale Successful!\n" +
                "Item: " + foundDrink.getDrinkName() + "\n" +
                "Price per unit: Rs " + price + "\n" +
                "Quantity: " + requestedQuantity + "\n" +
                "Total Price: Rs " + totalPrice + "\n" +
                "Remaining Stock: " + newQuantity);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(staffDashboard, 
                "Please enter valid numbers for ID and Quantity.");
        }
        
       
        
    }
    
    
    
    public void loadTable()
    {
        // Get table's data model
        DefaultTableModel model = (DefaultTableModel) staffDashboard.getTable().getModel();
        
        // Clear all existing rows from table
        model.setRowCount(0);
        
        // Get all drinks from store
        ArrayList<Drink> drinks = drinkStore.getDrinks();
        
        // Add each drink as a row in table
        for (Drink drink : drinks) {
            // Create row with drink data
            Object[] row = {
                drink.getDrinkID(),
                drink.getDrinkName(),
                drink.getDrinkPrice(),
                drink.getDrinkQuantity()
            };
            // Add row to table
            model.addRow(row);
        }
    }
}
