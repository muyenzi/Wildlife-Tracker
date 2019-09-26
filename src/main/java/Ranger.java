import org.sql2o.Connection;

import java.util.List;

public class Ranger {
    public  int id;
    public  String name;
    public  int contact;
    public  int badgeNumber;

    public Ranger(String name, int contact, int badgeNumber) {
        this.name = name;
        this.contact = contact;
        this.badgeNumber = badgeNumber;
    }

    public String getName() {
        return name;
    }

    public int getContact() {
        return contact;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO ranger (name,contact,badgeNumber) VALUES (:name,:contact,:badgeNumber)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("contact", this.contact)
                    .addParameter( "badgeNumber" ,this.badgeNumber)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Ranger> all() {
        String sql = "SELECT * FROM ranger;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(Ranger.class);
        }
    }
}
