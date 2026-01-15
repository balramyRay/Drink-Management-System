/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import model.Drink;
import model.DrinkStore;
import controller.DrinkController;
import controller.AdminStaffController;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;


public class AdminDashboard extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminDashboard.class.getName());

    private DrinkStore store;
    private DrinkController drinkController;
    private AdminStaffController adminStaffController;
    /**
     * Creates new form AdminDashboard
     */
        
   
    public AdminDashboard() {
        
        initComponents();
        this.setLocationRelativeTo(null);
        
       
        ensureButtonsVisible();
        //Show only home panel initially
        showPanel(homeTabPanel);
        //Create store with 5 default drinks
        store = new DrinkStore();
       
        
        // Create controller to handle operations
        drinkController = new DrinkController(store, this);
        adminStaffController = new AdminStaffController(this);

        
        
        // Load default drinks to table
        loadDrinksToTable();
        
        drinkController.updateRecentDrinks();
        
         
        updateRecentDrinksTable();
        setTextOnAdminHomePage();
        
    }
    
    private void showPanel(JPanel panelToShow)
    {
        // Hide all panels
        homeTabPanel.setVisible(false);
        drinkTabPanel.setVisible(false);
        staffTabPanel.setVisible(false);
       
    
        // Show the requested panel
        panelToShow.setVisible(true);
    }
   
    private void ensureButtonsVisible()
    {
        btnAddDrink.setVisible(true);
        btnUpdate.setVisible(true);
        btndeleteDrink.setVisible(true);
        btnClear.setVisible(true);
        
    }
    // home tab panel table
    // Method to update recent drinks table on home page

    public void updateRecentDrinksTable() {
        DefaultTableModel model = (DefaultTableModel) homeTabPanelTable.getModel();
        model.setRowCount(0); // Clear table
       
    
        // Get recent drinks from queue
        for (Drink drink : store.getRecentDrinks()) {
            Object[] row = {
                drink.getDrinkID(),
                drink.getDrinkName(),
                drink.getDrinkPrice(),
                drink.getDrinkQuantity()
            };
            model.addRow(row);
        }
    }
    
    public void setTextOnAdminHomePage()
    {
        ArrayList<Drink> drinks = store.getDrinks();
        int totalQuantity = 0;
        int numberOfTypes = 0;
        double totalInvestment = 0;
        for (Drink drink : drinks)
        {
            // Calculate for each drink
        totalQuantity += drink.getDrinkQuantity();
        numberOfTypes += 1;
        // Investment = quantity * price per unit
        totalInvestment += (drink.getDrinkQuantity() * drink.getDrinkPrice());
        }
        JLabelTotalDrinkQuantity.setText(String.valueOf(totalQuantity));
        JLabelDrinkType.setText(String.valueOf(numberOfTypes));
        JLabelTotalInvestment.setText(String.valueOf(totalInvestment));
    }
    
    // methods related to Drnks management by Admin
    // Method to load drinks from ArrayList to JTable
    public void loadDrinksToTable() {
        // Get table's data model
        DefaultTableModel model = (DefaultTableModel) drinkTabPanelTable.getModel();
        
        // Clear all existing rows from table
        model.setRowCount(0);
        
        // Get all drinks from store
        ArrayList<Drink> drinks = store.getDrinks();
        
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
        setTextOnAdminHomePage();
    }
    // getter for textfield of Drink of AdminDashboard.
    
    // return textfield of DRink ID
    public String getDrinkId() {
        return textFieldDrinkID.getText();
    }
    
    // return textfield of DRink Name
    public String getDrinkName() {
        return textFieldDrinkName.getText();
    }
    
    public String getDrinkPrice() {
        return textFieldDrinkPrice.getText();
    }
    
    public String getDrinkQuantity() {
        return textFiledDrinkQuantity.getText();
    }
    
    // Get table reference
    public javax.swing.JTable getTblDrinks() {
        return drinkTabPanelTable;
    }
    
    // Clear all text fields
    public void clearDrinkForm() {
        textFieldDrinkID.setText("");
        textFieldDrinkName.setText("");
        textFieldDrinkPrice.setText("");
        textFiledDrinkQuantity.setText("");
        textFieldDrinkID.requestFocus(); // Put cursor back in ID field
        textFieldDrinkID.setEditable(true);
        textFieldDrinkName.setEditable(true);
        // Clear selection
        drinkTabPanelTable.clearSelection();
    }
    // This method fills form fields when user clicks a table row
    private void autoFillForm() 
    {
        // Get the selected row from table
        int selectedRow = drinkTabPanelTable.getSelectedRow();
    
        // Check if a row is selected
        if (selectedRow >= 0)
        {
            // Get the table model
            DefaultTableModel model = (DefaultTableModel) drinkTabPanelTable.getModel();
        
            // Fill all text fields with data from selected row
            textFieldDrinkID.setText(model.getValueAt(selectedRow, 0).toString());
            textFieldDrinkName.setText(model.getValueAt(selectedRow, 1).toString());
            textFieldDrinkPrice.setText(model.getValueAt(selectedRow, 2).toString());
            textFiledDrinkQuantity.setText(model.getValueAt(selectedRow, 3).toString());
        
            // Put cursor in price field for easy typing
            textFieldDrinkPrice.requestFocus();
            textFieldDrinkPrice.selectAll(); // Select all text to make it easy to replace
            
            // Make ID and Name non-editable (can't change)
            textFieldDrinkID.setEditable(false);
            textFieldDrinkName.setEditable(false);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adminHeadingPanel = new javax.swing.JPanel();
        jLabelAdmin = new javax.swing.JLabel();
        jLabelDahboard = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        homeTab = new javax.swing.JPanel();
        jLabelHomeTab = new javax.swing.JLabel();
        drinkTab = new javax.swing.JPanel();
        jLabelDrinkTab = new javax.swing.JLabel();
        staffTab = new javax.swing.JPanel();
        jLabelStaffTab = new javax.swing.JLabel();
        adminLogoutBtn = new javax.swing.JButton();
        adminDisplayPanel = new javax.swing.JPanel();
        homeTabPanel = new javax.swing.JPanel();
        jLabelWelcomeMessage = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        homeTabPanelTable = new javax.swing.JTable();
        totalDrinkQuantity = new javax.swing.JPanel();
        JLabelTotalDrinkQuantity = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        numberOfDrinkTypePanel = new javax.swing.JPanel();
        JLabelDrinkType = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        totalInvestment = new javax.swing.JPanel();
        JLabelTotalInvestment = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        drinkTabPanel = new javax.swing.JPanel();
        jLabel_introduction = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel_search = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel_sortBy = new javax.swing.JLabel();
        btnSortByName = new javax.swing.JButton();
        btnSortByPrice = new javax.swing.JButton();
        btnSortByQuantity = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        drinkTabPanelTable = new javax.swing.JTable();
        btnAddDrink = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btndeleteDrink = new javax.swing.JButton();
        jLabel_Id = new javax.swing.JLabel();
        textFieldDrinkID = new javax.swing.JTextField();
        jLabel_name = new javax.swing.JLabel();
        textFieldDrinkName = new javax.swing.JTextField();
        jLabel_price = new javax.swing.JLabel();
        textFieldDrinkPrice = new javax.swing.JTextField();
        jLabel_quantity = new javax.swing.JLabel();
        textFiledDrinkQuantity = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnRestoreDeletedDrink = new javax.swing.JButton();
        btnSortByID = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        staffTabPanel = new javax.swing.JPanel();
        staffTabheadingLabel = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        panelToaddStaff = new javax.swing.JPanel();
        jLabelAddStaff = new javax.swing.JLabel();
        labelAddress = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        labelName = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        labelUserName = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        btnAddNewStaff = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        staffPanelTable = new javax.swing.JTable();
        btnRemoveStaff = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADMIN DASHBOARD");

        adminHeadingPanel.setBackground(new java.awt.Color(51, 51, 255));

        jLabelAdmin.setBackground(new java.awt.Color(51, 51, 255));
        jLabelAdmin.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabelAdmin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdmin.setText("Admin");

        jLabelDahboard.setBackground(new java.awt.Color(51, 51, 255));
        jLabelDahboard.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabelDahboard.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDahboard.setText("DashBoard");

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));

        homeTab.setBackground(new java.awt.Color(204, 204, 204));
        homeTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeTabMouseClicked(evt);
            }
        });

        jLabelHomeTab.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelHomeTab.setText("HOME");

        javax.swing.GroupLayout homeTabLayout = new javax.swing.GroupLayout(homeTab);
        homeTab.setLayout(homeTabLayout);
        homeTabLayout.setHorizontalGroup(
            homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeTabLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabelHomeTab)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        homeTabLayout.setVerticalGroup(
            homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelHomeTab, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        drinkTab.setBackground(new java.awt.Color(204, 204, 204));
        drinkTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drinkTabMouseClicked(evt);
            }
        });

        jLabelDrinkTab.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelDrinkTab.setText("DRINKS");

        javax.swing.GroupLayout drinkTabLayout = new javax.swing.GroupLayout(drinkTab);
        drinkTab.setLayout(drinkTabLayout);
        drinkTabLayout.setHorizontalGroup(
            drinkTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(drinkTabLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabelDrinkTab, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        drinkTabLayout.setVerticalGroup(
            drinkTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, drinkTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelDrinkTab, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        staffTab.setBackground(new java.awt.Color(204, 204, 204));
        staffTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staffTabMouseClicked(evt);
            }
        });

        jLabelStaffTab.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelStaffTab.setText("STAFF");

        javax.swing.GroupLayout staffTabLayout = new javax.swing.GroupLayout(staffTab);
        staffTab.setLayout(staffTabLayout);
        staffTabLayout.setHorizontalGroup(
            staffTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staffTabLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabelStaffTab, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        staffTabLayout.setVerticalGroup(
            staffTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, staffTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelStaffTab, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        adminLogoutBtn.setBackground(new java.awt.Color(255, 102, 102));
        adminLogoutBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        adminLogoutBtn.setText("LOG OUT");
        adminLogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminLogoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout adminHeadingPanelLayout = new javax.swing.GroupLayout(adminHeadingPanel);
        adminHeadingPanel.setLayout(adminHeadingPanelLayout);
        adminHeadingPanelLayout.setHorizontalGroup(
            adminHeadingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminHeadingPanelLayout.createSequentialGroup()
                .addGroup(adminHeadingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adminHeadingPanelLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabelDahboard))
                    .addGroup(adminHeadingPanelLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabelAdmin))
                    .addGroup(adminHeadingPanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(adminHeadingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, adminHeadingPanelLayout.createSequentialGroup()
                            .addGap(43, 43, 43)
                            .addComponent(homeTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(adminHeadingPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(adminHeadingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(drinkTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(staffTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(adminLogoutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        adminHeadingPanelLayout.setVerticalGroup(
            adminHeadingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminHeadingPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabelAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDahboard, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(homeTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(drinkTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(staffTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(adminLogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        adminDisplayPanel.setBackground(new java.awt.Color(255, 255, 255));

        homeTabPanel.setBackground(new java.awt.Color(204, 204, 204));
        homeTabPanel.setPreferredSize(new java.awt.Dimension(682, 555));
        homeTabPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelWelcomeMessage.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelWelcomeMessage.setText("Welcome Admin!");
        homeTabPanel.add(jLabelWelcomeMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(338, 10, -1, 36));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        homeTabPanel.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 52, 727, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Recently Added Drinks");
        homeTabPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 244, 206, 40));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        homeTabPanel.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 222, 727, -1));

        homeTabPanelTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "     Drink ID", "      Drink Name", "       Price", "     Quantity"
            }
        ));
        jScrollPane1.setViewportView(homeTabPanelTable);

        homeTabPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 296, 480, 190));

        totalDrinkQuantity.setBackground(new java.awt.Color(255, 153, 255));
        totalDrinkQuantity.setBorder(javax.swing.BorderFactory.createMatteBorder(18, 0, 0, 0, new java.awt.Color(255, 51, 102)));

        JLabelTotalDrinkQuantity.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        javax.swing.GroupLayout totalDrinkQuantityLayout = new javax.swing.GroupLayout(totalDrinkQuantity);
        totalDrinkQuantity.setLayout(totalDrinkQuantityLayout);
        totalDrinkQuantityLayout.setHorizontalGroup(
            totalDrinkQuantityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
            .addGroup(totalDrinkQuantityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(totalDrinkQuantityLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(JLabelTotalDrinkQuantity)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        totalDrinkQuantityLayout.setVerticalGroup(
            totalDrinkQuantityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
            .addGroup(totalDrinkQuantityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(totalDrinkQuantityLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(JLabelTotalDrinkQuantity)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        homeTabPanel.add(totalDrinkQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 94, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("TOTAL DRINK STOCKS");
        homeTabPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 67, -1, -1));

        numberOfDrinkTypePanel.setBackground(new java.awt.Color(255, 153, 255));
        numberOfDrinkTypePanel.setBorder(javax.swing.BorderFactory.createMatteBorder(18, 0, 0, 0, new java.awt.Color(255, 51, 102)));

        JLabelDrinkType.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        javax.swing.GroupLayout numberOfDrinkTypePanelLayout = new javax.swing.GroupLayout(numberOfDrinkTypePanel);
        numberOfDrinkTypePanel.setLayout(numberOfDrinkTypePanelLayout);
        numberOfDrinkTypePanelLayout.setHorizontalGroup(
            numberOfDrinkTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
            .addGroup(numberOfDrinkTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(numberOfDrinkTypePanelLayout.createSequentialGroup()
                    .addGap(0, 83, Short.MAX_VALUE)
                    .addComponent(JLabelDrinkType)
                    .addGap(0, 83, Short.MAX_VALUE)))
        );
        numberOfDrinkTypePanelLayout.setVerticalGroup(
            numberOfDrinkTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
            .addGroup(numberOfDrinkTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(numberOfDrinkTypePanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(JLabelDrinkType)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        homeTabPanel.add(numberOfDrinkTypePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 94, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("DRINK TYPE PRESENT");
        homeTabPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 67, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("TOTAL INVESTMENT");
        homeTabPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 67, -1, -1));

        totalInvestment.setBackground(new java.awt.Color(255, 153, 255));
        totalInvestment.setBorder(javax.swing.BorderFactory.createMatteBorder(18, 0, 0, 0, new java.awt.Color(255, 51, 102)));

        JLabelTotalInvestment.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        javax.swing.GroupLayout totalInvestmentLayout = new javax.swing.GroupLayout(totalInvestment);
        totalInvestment.setLayout(totalInvestmentLayout);
        totalInvestmentLayout.setHorizontalGroup(
            totalInvestmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
            .addGroup(totalInvestmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, totalInvestmentLayout.createSequentialGroup()
                    .addContainerGap(43, Short.MAX_VALUE)
                    .addComponent(JLabelTotalInvestment, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE)))
        );
        totalInvestmentLayout.setVerticalGroup(
            totalInvestmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
            .addGroup(totalInvestmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, totalInvestmentLayout.createSequentialGroup()
                    .addContainerGap(16, Short.MAX_VALUE)
                    .addComponent(JLabelTotalInvestment, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(36, Short.MAX_VALUE)))
        );

        homeTabPanel.add(totalInvestment, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 94, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Picture/homeAdminfinal.png"))); // NOI18N
        homeTabPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -4, 780, 560));

        drinkTabPanel.setBackground(new java.awt.Color(204, 204, 204));
        drinkTabPanel.setPreferredSize(new java.awt.Dimension(684, 555));
        drinkTabPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_introduction.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_introduction.setText("Drink Management");
        drinkTabPanel.add(jLabel_introduction, new org.netbeans.lib.awtextra.AbsoluteConstraints(403, 6, -1, -1));

        jSeparator4.setForeground(new java.awt.Color(255, 51, 255));
        drinkTabPanel.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 32, 652, -1));

        jLabel_search.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel_search.setText("Search :");
        drinkTabPanel.add(jLabel_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 47, -1, -1));
        drinkTabPanel.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(497, 47, 166, -1));

        btnSearch.setBackground(new java.awt.Color(102, 0, 102));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        drinkTabPanel.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(687, 47, -1, -1));

        btnReset.setBackground(new java.awt.Color(102, 0, 102));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        drinkTabPanel.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(767, 47, -1, -1));

        jLabel_sortBy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel_sortBy.setText("Sort By :");
        drinkTabPanel.add(jLabel_sortBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 47, 59, -1));

        btnSortByName.setBackground(new java.awt.Color(102, 0, 102));
        btnSortByName.setForeground(new java.awt.Color(255, 255, 255));
        btnSortByName.setText("Name");
        btnSortByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortByNameActionPerformed(evt);
            }
        });
        drinkTabPanel.add(btnSortByName, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 47, -1, -1));

        btnSortByPrice.setBackground(new java.awt.Color(102, 0, 102));
        btnSortByPrice.setForeground(new java.awt.Color(255, 255, 255));
        btnSortByPrice.setText("Price");
        btnSortByPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortByPriceActionPerformed(evt);
            }
        });
        drinkTabPanel.add(btnSortByPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 47, -1, -1));

        btnSortByQuantity.setBackground(new java.awt.Color(102, 0, 102));
        btnSortByQuantity.setForeground(new java.awt.Color(255, 255, 255));
        btnSortByQuantity.setText("Quantity");
        btnSortByQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortByQuantityActionPerformed(evt);
            }
        });
        drinkTabPanel.add(btnSortByQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 47, -1, -1));

        drinkTabPanelTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "     ID", "     Name", "      Price", "      Quantity"
            }
        ));
        drinkTabPanelTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drinkTabPanelTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(drinkTabPanelTable);

        drinkTabPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 94, 594, 272));

        btnAddDrink.setBackground(new java.awt.Color(204, 255, 204));
        btnAddDrink.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddDrink.setText("ADD DRINK");
        btnAddDrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDrinkActionPerformed(evt);
            }
        });
        drinkTabPanel.add(btnAddDrink, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 443, 147, 34));

        btnUpdate.setBackground(new java.awt.Color(255, 255, 204));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setText("Update Selected");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        drinkTabPanel.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 443, 163, 34));

        btndeleteDrink.setBackground(new java.awt.Color(255, 102, 153));
        btndeleteDrink.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btndeleteDrink.setText("Delete Selected");
        btndeleteDrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteDrinkActionPerformed(evt);
            }
        });
        drinkTabPanel.add(btndeleteDrink, new org.netbeans.lib.awtextra.AbsoluteConstraints(427, 443, 163, 34));

        jLabel_Id.setText("Drink ID :");
        drinkTabPanel.add(jLabel_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(766, 94, -1, -1));
        drinkTabPanel.add(textFieldDrinkID, new org.netbeans.lib.awtextra.AbsoluteConstraints(726, 116, 123, -1));

        jLabel_name.setText("Drink Name :");
        drinkTabPanel.add(jLabel_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(753, 144, -1, -1));
        drinkTabPanel.add(textFieldDrinkName, new org.netbeans.lib.awtextra.AbsoluteConstraints(726, 166, 123, -1));

        jLabel_price.setText("Price :");
        drinkTabPanel.add(jLabel_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(775, 194, -1, -1));
        drinkTabPanel.add(textFieldDrinkPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(726, 216, 123, -1));

        jLabel_quantity.setText("Quantity :");
        drinkTabPanel.add(jLabel_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(757, 244, -1, -1));
        drinkTabPanel.add(textFiledDrinkQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(726, 266, 123, -1));

        btnClear.setBackground(new java.awt.Color(204, 255, 204));
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setText("CLEAR");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        drinkTabPanel.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(726, 300, 123, 34));

        btnRestoreDeletedDrink.setBackground(new java.awt.Color(204, 204, 255));
        btnRestoreDeletedDrink.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRestoreDeletedDrink.setText("Undo Deleted");
        btnRestoreDeletedDrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestoreDeletedDrinkActionPerformed(evt);
            }
        });
        drinkTabPanel.add(btnRestoreDeletedDrink, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 443, 163, 34));

        btnSortByID.setBackground(new java.awt.Color(102, 0, 102));
        btnSortByID.setForeground(new java.awt.Color(255, 255, 255));
        btnSortByID.setText("ID");
        btnSortByID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortByIDActionPerformed(evt);
            }
        });
        drinkTabPanel.add(btnSortByID, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 47, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/netbeanPhoto.png"))); // NOI18N
        drinkTabPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -4, 860, 570));

        staffTabPanel.setBackground(new java.awt.Color(204, 204, 204));

        staffTabheadingLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        staffTabheadingLabel.setForeground(new java.awt.Color(51, 51, 51));
        staffTabheadingLabel.setText("STAFF MANAGEMENT");

        jSeparator5.setForeground(new java.awt.Color(153, 153, 153));

        panelToaddStaff.setBackground(new java.awt.Color(204, 204, 255));

        jLabelAddStaff.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAddStaff.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAddStaff.setForeground(new java.awt.Color(0, 51, 0));
        jLabelAddStaff.setText("ADD STAFF");

        labelAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelAddress.setText("Address :");

        labelName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelName.setText("Name :");

        labelUserName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelUserName.setText("UserName :");

        labelPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelPassword.setText("Password :");

        btnAddNewStaff.setBackground(new java.awt.Color(0, 0, 0));
        btnAddNewStaff.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddNewStaff.setForeground(new java.awt.Color(204, 204, 0));
        btnAddNewStaff.setText("ADD");
        btnAddNewStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewStaffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelToaddStaffLayout = new javax.swing.GroupLayout(panelToaddStaff);
        panelToaddStaff.setLayout(panelToaddStaffLayout);
        panelToaddStaffLayout.setHorizontalGroup(
            panelToaddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelToaddStaffLayout.createSequentialGroup()
                .addGroup(panelToaddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelToaddStaffLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelToaddStaffLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelToaddStaffLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(labelAddress)
                        .addGap(18, 18, 18)
                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelToaddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelToaddStaffLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(panelToaddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelToaddStaffLayout.createSequentialGroup()
                                .addComponent(labelPassword)
                                .addGap(26, 26, 26))))
                    .addGroup(panelToaddStaffLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(labelUserName)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelToaddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(140, 140, 140))
            .addGroup(panelToaddStaffLayout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(btnAddNewStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelToaddStaffLayout.setVerticalGroup(
            panelToaddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelToaddStaffLayout.createSequentialGroup()
                .addComponent(jLabelAddStaff)
                .addGap(18, 18, 18)
                .addGroup(panelToaddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelToaddStaffLayout.createSequentialGroup()
                        .addGroup(panelToaddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelUserName)
                            .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelToaddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPassword)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelAddress)))
                    .addComponent(labelName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddNewStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        staffPanelTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "   Name", "    Address", "    Username", "     Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(staffPanelTable);

        btnRemoveStaff.setBackground(new java.awt.Color(255, 0, 51));
        btnRemoveStaff.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRemoveStaff.setForeground(new java.awt.Color(255, 255, 255));
        btnRemoveStaff.setText("Remove Staff");
        btnRemoveStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveStaffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout staffTabPanelLayout = new javax.swing.GroupLayout(staffTabPanel);
        staffTabPanel.setLayout(staffTabPanelLayout);
        staffTabPanelLayout.setHorizontalGroup(
            staffTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, staffTabPanelLayout.createSequentialGroup()
                .addGap(0, 75, Short.MAX_VALUE)
                .addComponent(panelToaddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(staffTabPanelLayout.createSequentialGroup()
                .addGroup(staffTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(staffTabPanelLayout.createSequentialGroup()
                        .addGap(332, 332, 332)
                        .addComponent(staffTabheadingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(staffTabPanelLayout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(staffTabPanelLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(staffTabPanelLayout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(btnRemoveStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        staffTabPanelLayout.setVerticalGroup(
            staffTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staffTabPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(staffTabheadingLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelToaddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRemoveStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout adminDisplayPanelLayout = new javax.swing.GroupLayout(adminDisplayPanel);
        adminDisplayPanel.setLayout(adminDisplayPanelLayout);
        adminDisplayPanelLayout.setHorizontalGroup(
            adminDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(staffTabPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(adminDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(drinkTabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 855, Short.MAX_VALUE))
            .addGroup(adminDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(homeTabPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE))
        );
        adminDisplayPanelLayout.setVerticalGroup(
            adminDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(staffTabPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(adminDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(drinkTabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 563, Short.MAX_VALUE))
            .addGroup(adminDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(homeTabPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(adminHeadingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminDisplayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminHeadingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(adminDisplayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void homeTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeTabMouseClicked

        showPanel(homeTabPanel);
    }//GEN-LAST:event_homeTabMouseClicked

    private void drinkTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drinkTabMouseClicked
        // TODO add your handling code here:
        
         showPanel(drinkTabPanel);
    }//GEN-LAST:event_drinkTabMouseClicked

    private void staffTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffTabMouseClicked
        // TODO add your handling code here:
     
       showPanel(staffTabPanel);  
    }//GEN-LAST:event_staffTabMouseClicked
    
    
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
       drinkController.updateDrink();
       
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void adminLogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminLogoutBtnActionPerformed

        // close the current frame
        this.dispose();
        
        // Open Home screen
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.setVisible(true);
        
    }//GEN-LAST:event_adminLogoutBtnActionPerformed

    private void btnAddDrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDrinkActionPerformed
        // TODO add your handling code here:
         drinkController.addDrink();
    }//GEN-LAST:event_btnAddDrinkActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
      clearDrinkForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btndeleteDrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteDrinkActionPerformed
        // TODO add your handling code here:
        drinkController.deleteDrink();
     
    }//GEN-LAST:event_btndeleteDrinkActionPerformed

    private void drinkTabPanelTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drinkTabPanelTableMouseClicked
        // TODO add your handling code here:
        autoFillForm();// method to fill form when we click any of the row
    }//GEN-LAST:event_drinkTabPanelTableMouseClicked

    private void btnRestoreDeletedDrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreDeletedDrinkActionPerformed
        // TODO add your handling code here:
        drinkController.undoDelete();
    }//GEN-LAST:event_btnRestoreDeletedDrinkActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        // Get text from search box
        String searchText = txtSearch.getText().trim();
        
        // Check if search box is empty
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(AdminDashboard.this, 
                "Please enter text to search");
            return;
        }
        try
        {
            // Convert search text to integer
            int searchId = Integer.parseInt(searchText);
            
            // CALL BINARY SEARCH METHOD
            Drink foundDrink = drinkController.binarySearchById(searchId);
            
            // Check if drink was found
            if (foundDrink != null)
            {
                // Clear table to show only found drink
                DefaultTableModel model = (DefaultTableModel) drinkTabPanelTable.getModel();
                model.setRowCount(0);
                
                // Add the found drink to table
                Object[] row = 
                {
                    foundDrink.getDrinkID(),
                    foundDrink.getDrinkName(),
                    foundDrink.getDrinkPrice(),
                    foundDrink.getDrinkQuantity()
                };
                model.addRow(row);
                
                return;
            }
            else
            {
                JOptionPane.showMessageDialog(AdminDashboard.this,
                        "No drink found with ID: " + searchId + "\n"
                        + "(Binary Search Method was used)");

                // Clear table since nothing was found
                DefaultTableModel model = (DefaultTableModel) drinkTabPanelTable.getModel();
                model.setRowCount(0);

                return;
            }
        }
        catch(NumberFormatException e)
        {
            //we will use LINEAR SEARCH below
        }
        // Call linear search method
        ArrayList<Drink> results = drinkController.searchDrinks(searchText);
        
        // Check if any drinks found
        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(AdminDashboard.this, 
                "No drinks found for: " + searchText);
            return;
        }
        
        // Show search results in table
        DefaultTableModel model = (DefaultTableModel) drinkTabPanelTable.getModel();
        model.setRowCount(0); // Clear table
        
        for (Drink drink : results) {
            Object[] row = {
                drink.getDrinkID(),
                drink.getDrinkName(),
                drink.getDrinkPrice(),
                drink.getDrinkQuantity()
            };
            model.addRow(row);
        }
        
        // Show search info
        JOptionPane.showMessageDialog(AdminDashboard.this, 
            "Found " + results.size() + " drink\n" +
            "Search: " + searchText);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSortByIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortByIDActionPerformed
        // TODO add your handling code here:
        drinkController.sortDrinksById();
        JOptionPane.showMessageDialog(this, "Sorted by ID using Selection Sort");
    }//GEN-LAST:event_btnSortByIDActionPerformed

    private void btnSortByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortByNameActionPerformed
        // TODO add your handling code here:
        // Call bubble sort method
        drinkController.sortDrinksByName();
        
        // Show sort info
        JOptionPane.showMessageDialog(AdminDashboard.this, 
            "Sorted by Name (A to Z)");
    }//GEN-LAST:event_btnSortByNameActionPerformed

    private void btnSortByPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortByPriceActionPerformed
        // TODO add your handling code here:
        // Call selection sort method
        drinkController.sortDrinksByPrice();
        
        // Show sort info
        JOptionPane.showMessageDialog(AdminDashboard.this, 
            "Sorted by Price (Low to High)");
    }//GEN-LAST:event_btnSortByPriceActionPerformed

    private void btnSortByQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortByQuantityActionPerformed
        // TODO add your handling code here:
         // Call selection sort method
        drinkController.sortDrinksByQuantity();
        
        // Show sort info
        JOptionPane.showMessageDialog(AdminDashboard.this, 
            "Sorted by Quantity (Low to High)");
    }//GEN-LAST:event_btnSortByQuantityActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // 1. Clear search box (reset search)
        txtSearch.setText("");

        // 2. Reload table to show ALL drinks (reset search filter)
        loadDrinksToTable();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnRemoveStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveStaffActionPerformed
        // TODO add your handling code here:
        adminStaffController.removeStaff();
    }//GEN-LAST:event_btnRemoveStaffActionPerformed

    private void btnAddNewStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewStaffActionPerformed
        // TODO add your handling code here:
        adminStaffController.addStaff();
    }//GEN-LAST:event_btnAddNewStaffActionPerformed

    // Methods to get staff form data
    public String getStaffName() {
        return txtName.getText();
    }

    public String getStaffAddress() {
        return txtAddress.getText();
    }

    public String getStaffUsername() {
        return txtUserName.getText();
    }

    public String getStaffPassword() {
        return txtPassword.getText();
    }

    // Method to get staff table
    public javax.swing.JTable getTblStaff() {
        return staffPanelTable;
    }

// Method to clear staff form
    public void clearStaffForm() {
        txtName.setText("");
        txtAddress.setText("");
        txtUserName.setText("");
        txtPassword.setText("");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new AdminDashboard().setVisible(true));
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabelDrinkType;
    private javax.swing.JLabel JLabelTotalDrinkQuantity;
    private javax.swing.JLabel JLabelTotalInvestment;
    private javax.swing.JPanel adminDisplayPanel;
    private javax.swing.JPanel adminHeadingPanel;
    private javax.swing.JButton adminLogoutBtn;
    private javax.swing.JButton btnAddDrink;
    private javax.swing.JButton btnAddNewStaff;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnRemoveStaff;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnRestoreDeletedDrink;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSortByID;
    private javax.swing.JButton btnSortByName;
    private javax.swing.JButton btnSortByPrice;
    private javax.swing.JButton btnSortByQuantity;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btndeleteDrink;
    private javax.swing.JPanel drinkTab;
    private javax.swing.JPanel drinkTabPanel;
    private javax.swing.JTable drinkTabPanelTable;
    private javax.swing.JPanel homeTab;
    private javax.swing.JPanel homeTabPanel;
    private javax.swing.JTable homeTabPanelTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAddStaff;
    private javax.swing.JLabel jLabelAdmin;
    private javax.swing.JLabel jLabelDahboard;
    private javax.swing.JLabel jLabelDrinkTab;
    private javax.swing.JLabel jLabelHomeTab;
    private javax.swing.JLabel jLabelStaffTab;
    private javax.swing.JLabel jLabelWelcomeMessage;
    private javax.swing.JLabel jLabel_Id;
    private javax.swing.JLabel jLabel_introduction;
    private javax.swing.JLabel jLabel_name;
    private javax.swing.JLabel jLabel_price;
    private javax.swing.JLabel jLabel_quantity;
    private javax.swing.JLabel jLabel_search;
    private javax.swing.JLabel jLabel_sortBy;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel labelAddress;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelUserName;
    private javax.swing.JPanel numberOfDrinkTypePanel;
    private javax.swing.JPanel panelToaddStaff;
    private javax.swing.JTable staffPanelTable;
    private javax.swing.JPanel staffTab;
    private javax.swing.JPanel staffTabPanel;
    private javax.swing.JLabel staffTabheadingLabel;
    private javax.swing.JTextField textFieldDrinkID;
    private javax.swing.JTextField textFieldDrinkName;
    private javax.swing.JTextField textFieldDrinkPrice;
    private javax.swing.JTextField textFiledDrinkQuantity;
    private javax.swing.JPanel totalDrinkQuantity;
    private javax.swing.JPanel totalInvestment;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
