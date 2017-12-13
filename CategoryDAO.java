

package com.onlineshopping.DAO;

import com.onlineshopping.model.Category;
import java.util.List;


public interface CategoryDAO {
    public void create(Category c);
    public Category read(Category c);
    public List<Category> read();
    public void update(Category c);
    public void delete(Category c);
}
