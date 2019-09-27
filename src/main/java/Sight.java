
import org.sql2o.Connection;
        import java.sql.Timestamp;
        import java.util.List;

public class Sight {
    public int id;
    public String animalName;
    public String sightLocation;
    public  String ranger;
    private Timestamp spotted;

    public  Sight(String animalName,String sightLocation,String ranger){
        this.animalName=animalName;
        this.sightLocation=sightLocation;
        this.ranger=ranger;
        this.spotted=spotted;

    }

    public int getId() {
        return id;
    }

    public String getSightLocation() {
        return sightLocation;
    }

    public String getRanger() {
        return ranger;
    }

    public Timestamp getSpotted() {
        return spotted;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animalName,sightLocation,ranger,spotted) VALUES (:animalName,:sightLocation,:ranger,now())";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalName", this.animalName)
                    .addParameter("sightLocation", this.sightLocation)
                    .addParameter("ranger", this.ranger)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Sight> all() {
        String sql = "SELECT * FROM sightings;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(Sight.class);
        }
    }
}