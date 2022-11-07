package kite.collin.valorantapi.Controller;

import kite.collin.valorantapi.BLL.WeaponBLL;
import kite.collin.valorantapi.Model.WeaponModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weapon")
public class WeaponController {

    private WeaponBLL wb = new WeaponBLL();

    //Create
    @RequestMapping(path="", method= RequestMethod.POST)
    public void createWeapon(@RequestBody WeaponModel weapon)
    {
        wb.add(weapon);
    }
    //Retrieve
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public WeaponModel findWeaponByID(@PathVariable int id)
    {
        return wb.findById(id);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<WeaponModel> findAllWeapons()
    {
        return wb.findAll();
    }
    //Update
    @RequestMapping(path="/{id}", method= RequestMethod.PUT)
    public void updateWeapon(@PathVariable int id, @RequestBody WeaponModel Weapon)
    {
        wb.update(id, Weapon);
    }

    //Delete
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteWeapon(@PathVariable int id)
    {
        wb.delete(id);
    }

}
