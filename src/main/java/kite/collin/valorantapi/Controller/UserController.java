package kite.collin.valorantapi.Controller;

import kite.collin.valorantapi.BLL.UserBLL;
import kite.collin.valorantapi.Model.UserModel;
import kite.collin.valorantapi.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user")
public class UserController {
    private UserBLL ub = new UserBLL();

    @Autowired
    public InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public PasswordEncoder passwordEncoder;

    //Create

    @RequestMapping(path="", method= RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public void createUser(@RequestBody UserModel User) {
        inMemoryUserDetailsManager.createUser(org.springframework.security.core.userdetails.User.withUsername(User.getName()).password(passwordEncoder.encode(User.getPassword())).roles(User.getAccess()).build());
        ub.add(User);
    }

    //Retrieve
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public UserModel findUserByID(@PathVariable int id)
    {
        return ub.findById(id);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<UserModel> findAllUsers()
    {
        return ub.findAll();
    }
    //Update
    @RequestMapping(path="/{id}", method= RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public void updateUser(@PathVariable int id, @RequestBody UserModel User)
    {

        inMemoryUserDetailsManager.updateUser(org.springframework.security.core.userdetails.User.withUsername(User.getName()).password(passwordEncoder.encode(User.getPassword())).roles(User.getAccess()).build());
        ub.update(id, User);
    }

    //Delete
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable int id)
    {
        inMemoryUserDetailsManager.deleteUser(ub.findById(id).getName());
        ub.delete(id);
    }


}

