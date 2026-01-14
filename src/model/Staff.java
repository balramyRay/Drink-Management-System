
package model;


public class Staff {
    
    // Variables
    private String name;
    private String address;
    private String username;
    private String password;
 
    
    //Constructor
    public Staff(String name, String address, String username, String password)
    {
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;
       
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

  
}
