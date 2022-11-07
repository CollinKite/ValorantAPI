package kite.collin.valorantapi.Model;

public class WeaponModel extends ModelAbstract {
    private WeaponType type;
    private int cost;


    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public WeaponModel(int id, String name, WeaponType type, int cost) {
        setId(id);
        setName(name);
        this.type = type;
        this.cost = cost;
    }

    public WeaponModel() {
    }
}

enum WeaponType {
    PRIMARY,
    SECONDARY,
    MELEE
}
