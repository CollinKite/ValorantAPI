package kite.collin.valorantapi.BLL;

import kite.collin.valorantapi.Model.MapModel;

public class MapBLL extends BLL<MapModel> {
    public MapBLL() {
        super("maps.json");
    }
}
