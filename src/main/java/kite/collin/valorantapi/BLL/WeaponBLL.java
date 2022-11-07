package kite.collin.valorantapi.BLL;

import kite.collin.valorantapi.Model.WeaponModel;

public class WeaponBLL extends BLL<WeaponModel> {

    public WeaponBLL() {
        super("weapons.json");
    }
}
