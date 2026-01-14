
package model;

public class Drink {
    // Private Variable
    private int drinkID;
    private String drinkName;
    private double drinkPrice;
    private int drinkQuantity;
    // Constructor
    public Drink(int drinkID, String drinkName, double drinkPrice, int drinkQuantity)
    {
        this.drinkID = drinkID;
        this.drinkName = drinkName;
        this.drinkPrice = drinkPrice;
        this.drinkQuantity = drinkQuantity;
    }
    // Getter and setter Methods
    public int getDrinkID() {
        return drinkID;
    }

    public void setDrinkID(int newID) {
        this.drinkID = newID;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String newName) {
        this.drinkName = newName;
    }

    public double getDrinkPrice() {
        return drinkPrice;
    }

    public void setDrinkPrice(double newPrice) {
        this.drinkPrice = newPrice;
    }

    public int getDrinkQuantity() {
        return drinkQuantity;
    }

    public void setDrinkQuantity(int newQuantity) {
        this.drinkQuantity = newQuantity;
    }
    
    
}
