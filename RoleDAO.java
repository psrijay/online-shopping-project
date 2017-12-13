

package com.onlineshopping.DAO;
import com.onlineshopping.model.Role;

public interface RoleDAO {
    public void createRole(Role role);
    public Role[] readRole(Role role);
    public void updateRole(Role role);
    public void deleteRole(Role role);
}
