/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineshopping.DAO;

import com.onlineshopping.model.Category;
import java.util.List;

/**
 *
 * @author iamsu
 */
public interface CategoryDAO {
    public void create(Category c);
    public Category read(Category c);
    public List<Category> read();
    public void update(Category c);
    public void delete(Category c);
}
