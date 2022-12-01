package kite.collin.valorantapi.BLL;

import kite.collin.valorantapi.Model.UserModel;

public class UserBLL extends BLL<UserModel> {

    public UserBLL() {
        super("users.json");
    }
}
