import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
    public String name;
    public int id;
    public boolean  strange;

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object otherAnimal) {
        if (!(otherAnimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal) otherAnimal;
            return this.getName().equals(newAnimal.getName());
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,strange) VALUES (:name,:strange)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter( "strange",this.strange)
                    .executeUpdate()
                    .getKey();
        }
    }

}