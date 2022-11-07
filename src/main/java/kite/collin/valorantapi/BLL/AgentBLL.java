package kite.collin.valorantapi.BLL;

import kite.collin.valorantapi.Model.AgentModel;

public class AgentBLL extends BLL<AgentModel> {

    public AgentBLL() {
        super("agents.json"); //File to read/write to
    }
}
