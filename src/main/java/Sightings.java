import org.sql2o.Connection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;

public class Sightings {
    public int id;
    public int animalId;
    public String animalName;
    public String location;
    public  String rangerName;
    private Timestamp spotted;

    public  Sightings(String animalName,String location,String rangerName){
        this.animalName=animalName;
        this.location=location;
        this.rangerName=rangerName;
        this.spotted=spotted;

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

    public String getAnimalName() {
        return animalName;
    }

    public Timestamp getSpotted() {
        return spotted;
    }

//    public String getFormattedDate() {
//        return DateFormat.getDateTimeInstance().format(spotted);
//    }

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
            String sql = "INSERT INTO sightings (animalName,location,rangerName,spotted) VALUES (:animalName,:location,:rangerName,now())";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalName", this.animalName)
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
