import org.sql2o.Connection;

import java.util.List;

public class Sightings {
    public int id;
    public int animalId;
    public String location;
    public  String rangerName;

    public  Sightings(int animalId,String location,String rangerName){
        this.animalId=animalId;
        this.location=location;
        this.rangerName=rangerName;

    }

    public int getAnimalId() {
        return animalId;
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return rangerName;
    }

    @Override
    public boolean equals(Object otherSighting) {
        if (!(otherSighting instanceof Sightings)) {
            return false;
        } else {
            Sightings newSighting = (Sightings) otherSighting;
            return this.getAnimalId()==(newSighting.getAnimalId());
//            return this.getLocation().equals(newSighting.getLocation());
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animalId,location,rangerName) VALUES (:animalId,:location,:rangerName)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalId", this.animalId)
                    .addParameter("location", this.location)
                    .addParameter("rangerName", this.rangerName)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Sightings> all() {
        String sql = "SELECT * FROM sightings;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(Sightings.class);
        }
    }
}
