package controller;

import model.Admin;
import model.Staff;
import view.AdminDashboard;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

// Main controller for both admin and staff operations
public class AdminStaffController {
    
    // Lists to store admins and staff
    private ArrayList<Admin> adminList;
    private ArrayList<Staff> staffList;
    private AdminDashboard admin;
    
    // Constructor
    public AdminStaffController(AdminDashboard admin) {
        this.admin = admin;
        
        // Create lists
        adminList = new ArrayList<>();
        staffList = new ArrayList<>();
        
        // Add 1 default admin
        adminList.add(new Admin("Balram Ray", "balram", "balram123"));
        
        // Add 2 default staff
        staffList.add(new Staff("Santosh", "Janakpur", "santosh", "santosh123"));
        staffList.add(new Staff("Anil", "PidariTol", "anil", "anil123"));
        
        // Load staff to table if admin exists
        if (admin != null) {
            loadStaffToTable();
        }
    }
    
    // ========== ADMIN METHODS ==========
    
    // Register new admin
    public void registerAdmin(String name, String username, String password) {
        adminList.add(new Admin(name, username, password));
    }
    
    // Get all admins for login check
    public ArrayList<Admin> getAllAdmins() {
        return adminList;
    }
    
    // Check admin login using getters
    public boolean checkAdminLogin(String username, String password) {
        for (Admin admin : adminList) {
            // Use getUsername() and getPassword() getters
            if (admin.getUsername().equals(username) && 
                admin.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    // ========== STAFF METHODS ==========
    
    // Add new staff
    public void addStaff() {
        // Get data from form
        String name = admin.getStaffName();
        String address = admin.getStaffAddress();
        String username = admin.getStaffUsername();
        String password = admin.getStaffPassword();
        
        // Check if any field is empty
        if (name.isEmpty() || address.isEmpty() || 
            username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(admin, "Please fill all fields!");
            return;
        }
        
        // Add new staff to list
        staffList.add(new Staff(name, address, username, password));
        
        // Update table
        loadStaffToTable();
        
        // Clear form
        admin.clearStaffForm();
        
        // Show message
        JOptionPane.showMessageDialog(admin, "Staff added successfully!");
    }
    
    // Remove staff - FIXED VERSION
    public void removeStaff() {
        // Get selected row
        int selectedRow = admin.getTblStaff().getSelectedRow();

        // Check if staff is selected
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(admin, "Please select a staff member first!");
            return;
        }

        // Get the username from the selected row (column 2 is username)
        String usernameToRemove = (String) admin.getTblStaff().getValueAt(selectedRow, 2);

        // Find and remove staff by username
        boolean found = false;
        for (int i = 0; i < staffList.size(); i++) {
            Staff staff = staffList.get(i);
            if (staff.getUsername().equals(usernameToRemove)) {
                staffList.remove(i);  // Remove from ArrayList
                found = true;
                break;
            }
        }

        if (found) {
            // Update table
            loadStaffToTable();

            // Show message
            JOptionPane.showMessageDialog(admin, "Staff removed successfully!");
        } else {
            JOptionPane.showMessageDialog(admin, "Staff not found!");
        }
    }
    
    // Load staff to table
    public void loadStaffToTable() {
        // Get table model
        DefaultTableModel model = (DefaultTableModel) admin.getTblStaff().getModel();
        
        // Clear existing rows
        model.setRowCount(0);
        
        // Add each staff to table using getters
        for (Staff staff : staffList) {
            Object[] row = {
                staff.getName(),      // Use getName() getter
                staff.getAddress(),   // Use getAddress() getter
                staff.getUsername(),  // Use getUsername() getter
                staff.getPassword()   // Use getPassword() getter
            };
            model.addRow(row);
        }
    }
    
    // Get all staff for login check
    public ArrayList<Staff> getAllStaff() {
        return staffList;
    }
    
    // Check staff login using getters
    public boolean checkStaffLogin(String username, String password) {
        for (Staff staff : staffList) {
            // Use getUsername() and getPassword() getters
            if (staff.getUsername().equals(username) && 
                staff.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}