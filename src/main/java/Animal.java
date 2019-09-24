import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
    public String name;
    public int id;
    public boolean  endangered;

//    public Animal(String name) {
//        this.name = name;
//    }

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
        if (name.equals("") ) {
            throw new IllegalArgumentException("Please enter a name.");
        }
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,endangered) VALUES (:name,:endangered)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter( "endangered",this.endangered)
                    .executeUpdate()
                    .getKey();
        }
    }
//    public static List<Animal> all() {
//        String sql = "SELECT * FROM animals;";
//        try (Connection con = DB.sql2o.open()) {
//            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(Animal.class);
//        }
//    }
}
