package kite.collin.valorantapi.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(AgentModel.class),
        @JsonSubTypes.Type(MapModel.class),
        @JsonSubTypes.Type(WeaponModel.class)
    })

 public abstract class ModelAbstract {
     private int id;
     private String name;

     public int getId() {
         return id;
     }

     public void setId(int id) {
            this.id = id;
     }

     public String getName() {
            return name;

     }

     public void setName(String name) {
            this.name = name;
     }
 }
