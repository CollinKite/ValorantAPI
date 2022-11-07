package kite.collin.valorantapi.Model;

public class AgentModel extends ModelAbstract {


    private String role;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AgentModel(int id, String name, String Role) {
        setId(id);
        setName(name);
        this.role = Role;
    }

    public AgentModel() {
    }
}

