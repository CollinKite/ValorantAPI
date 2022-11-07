package kite.collin.valorantapi.BLL;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import kite.collin.valorantapi.Model.ModelAbstract;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BLL<T extends ModelAbstract> {

    private String fileName;
    private File file;

    private List<T> entities;

    public void add(T entity) {
        entities.add(entity);
        save();
    }

    public List<T> findAll()
    {
        return entities;
    }

    public void update(int id, T newEntity)
    {
        for(int i = 0; i < entities.size(); i++)
        {
            if(entities.get(i).getId() == id)
            {
                entities.set(i, newEntity);
                save();
                return;
            }
        }
        throw new RuntimeException("Entity not found");
    }

   public void delete(int id) {
        for(T entity : entities)
        {
            if(entity.getId() == id)
            {
                entities.remove(entity);
                save();
                return;
            }
        }
        throw new RuntimeException("Entity not found");
    }


    public T findById(int id)
    {
        return findAll().stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public void save() {
        try {

            ObjectMapper om = new ObjectMapper();
            om.findAndRegisterModules();
            ObjectWriter ow = om.writer(new DefaultPrettyPrinter());
            om.writeValue(new File(fileName), entities);
        }
        catch (IOException e)
        {

            e.printStackTrace();
            System.out.println("Error saving file");
        }
    }

    public BLL(String filename)  {
        try {

            this.fileName = filename;
            file = new File(filename);
            if (!file.exists()) {
                entities = new ArrayList<>();
                return;
            }
            ObjectMapper om = new ObjectMapper();
            om.findAndRegisterModules();
            entities = om.readValue(file, new TypeReference<List<T>>() {
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error loading file");
        }
    }
}
