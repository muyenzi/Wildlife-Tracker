import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;
import static spark.Spark.get;
import static spark.Spark.post;



public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

           //animal form
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        //new animal display and save in db
        post("/new-animal", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name=request.queryParams("animalName");
            Animal newAnimal= new Animal(name);
            newAnimal.save();
            model.put("name",newAnimal.getName());
            model.put("Animal",newAnimal);
            return new ModelAndView(model, "new-animal.hbs");
        }, new HandlebarsTemplateEngine());


          // list all animals
        get("/animals", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animal> animals = Animal.all();
            model.put("animals", animals);
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        //sighting form
        get("/new-sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "sightings-form.hbs");
        }, new HandlebarsTemplateEngine());

        //new sighting display and save in db
        post("/new-sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int animalId=Integer.parseInt(request.queryParams("animalId"));
            String location=request.queryParams("sightLocation");
            String rangerName=request.queryParams("rangerName");
            Sightings newSightings= new Sightings(animalId,location,rangerName);
            newSightings.save();
            model.put("animalId",newSightings.getAnimalId());
            model.put("location",newSightings.getLocation());
            model.put("rangerName",newSightings.getRangerName());
            model.put("Sightings",newSightings);
            return new ModelAndView(model, "new-sighting.hbs");
        }, new HandlebarsTemplateEngine());

        // list all sightings
        get("/sightings", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sightings> sightings = Sightings.all();
            model.put("sightings", sightings);
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

    }
}