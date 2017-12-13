
package com.onlineshopping.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author iamsu
 */
public class Cart {
    private int Id;
    private User user;
    private Set<CartItem> cartItems=new HashSet<>();

    public Cart() {
    }
    
    public Cart(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    
    
}
