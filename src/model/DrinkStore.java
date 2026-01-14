package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DrinkStore {

    //list to store drink object
    private ArrayList<Drink> drinks = new ArrayList<>();
    // strore latest added drink using queue concept(FIFO)
    private Queue<Drink> recentDrinks = new LinkedList<>();
    //// Uses a Stack (LIFO) to store deleted drinks so we can perform an 'Undo'
    private Stack<Drink> deletedDrinks = new Stack<>();

    public DrinkStore() {
        // Initial 5 drinks to be added to table of Drink 
        drinks.add(new Drink(1, "Coke", 100, 50));
        drinks.add(new Drink(2, "Pepsi", 90, 40));
        drinks.add(new Drink(3, "Fanta", 80, 30));
        drinks.add(new Drink(4, "Sprite", 85, 28));
        drinks.add(new Drink(5, "Mountain Dew", 95, 20));
    }
    
    // Getter for drinks ArrayList
    public ArrayList<Drink> getDrinks() {
        return drinks;
    }
     
    // Getter for recent drinks list (Queue)
    public Queue<Drink> getRecentDrinks() {
        return recentDrinks;
    }
    
    // Getter for deleted drinks list (Stack)
    public Stack<Drink> getDeletedDrinks() {
        return deletedDrinks;
    }
}
