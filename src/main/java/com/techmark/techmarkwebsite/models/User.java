package com.techmark.techmarkwebsite.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Comparable<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private int userId;
    
    //@NotNull
    @Column(name = "FirstName")
    private String firstName;
    
    //@NotNull
    @Column(name = "LastName")
    private String lastName;
    
    //@NotNull
    @Size(min=8, max = 15)
    @Column(name = "Username")
    private String username;
    
    //@NotNull
    @Size(min=8, max = 20)
    @Column(name = "Password")
    private String password;
    
    //Note: Without @JsonIgnore or @JsonBackReference we will get the json info coming from the mapped class (i.e., Order class and all related to the given user orders will be visualized within the json of the User class); Here, using @JsonIgnore or @JsonBackReference we will not show any info from the json of the Ordere class and no related info about the orders of the given user will be visualized within the user's json
		//Note: Cascade properties are also set in HeidiSQL -> foreign keys defition/ on UPDATE & on DELETE properties*/
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Order> orders;
    
    public User() {
    
    }
    
    public User(int userId, String firstName, String lastName, String username, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
    
    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
    
    public User(int userId) {
        this.userId = userId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Order> getOrders() {
        return orders;
    }
    
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    @Override
    public String toString() {
        return "User: " +
            "id = " + userId +
            ", username = " + username +
            ", password = " + password +
            ", fullName = " + firstName + " " + lastName;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof User) {
            User user = (User) object;
            return this.getUsername().equals(user.getUsername());
        }
        
        return false;
    }
    
    @Override
    public int compareTo(User o) {
        return this.getUsername().compareTo(o.getUsername());
    }
}
