package kite.collin.valorantapi.Controller;

import kite.collin.valorantapi.BLL.AgentBLL;
import kite.collin.valorantapi.Model.AgentModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agent")
public class AgentController {

    private AgentBLL ab = new AgentBLL();

    //Create
    @RequestMapping(path="", method= RequestMethod.POST)
    public void createAgent(@RequestBody AgentModel agent)
    {
        ab.add(agent);
    }
    //Retrieve
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public AgentModel findAgentByID(@PathVariable int id)
    {
        return ab.findById(id);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<AgentModel> findAllAgents()
    {
        return ab.findAll();
    }
    //Update
    @RequestMapping(path="/{id}", method= RequestMethod.PUT)
    public void updateAgent(@PathVariable int id, @RequestBody AgentModel agent)
    {
        ab.update(id, agent);
    }

    //Delete
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteAgent(@PathVariable int id)
    {
        ab.delete(id);
    }

}
