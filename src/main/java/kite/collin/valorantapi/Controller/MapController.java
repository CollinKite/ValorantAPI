package kite.collin.valorantapi.Controller;

import kite.collin.valorantapi.BLL.MapBLL;
import kite.collin.valorantapi.BLL.MapBLL;
import kite.collin.valorantapi.Model.MapModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/map")
public class MapController {

    private MapBLL mb = new MapBLL();

    //Create
    @RequestMapping(path="", method= RequestMethod.POST)
    public void createMap(@RequestBody MapModel Map)
    {
        mb.add(Map);
    }
    //Retrieve
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public MapModel findMapByID(@PathVariable int id)
    {
        return mb.findById(id);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<MapModel> findAllMaps()
    {
        return mb.findAll();
    }
    //Update
    @RequestMapping(path="/{id}", method= RequestMethod.PUT)
    public void updateMap(@PathVariable int id, @RequestBody MapModel Map)
    {
        mb.update(id, Map);
    }

    //Delete
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteMap(@PathVariable int id)
    {
        mb.delete(id);
    }

}
