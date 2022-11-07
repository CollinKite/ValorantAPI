package kite.collin.valorantapi.Model;

public class AgentModel extends ModelAbstract {


    private String type;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AgentModel(int id, String name, String type) {
        setId(id);
        setName(name);
        this.type = type;
    }

    public AgentModel() {
    }
}

enum agentType {
    DUELIST,
    INITIATOR,
    CONTROLLER,
    SENTINEL
}
