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

           //welcome page
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //animal form
        get("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        //new animal display and save in db
        post("/new-animals", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name=request.queryParams("animalName");
            boolean endangered = request.queryParamsValues("endangered") != null;
               if(endangered) {
                   String health = request.queryParams("health");
                   String age = request.queryParams("age");
                   EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name, health, age);
                   endangeredAnimal.save();
               }
               else {
                   EndangeredAnimal endangeredAnimal= new EndangeredAnimal(name,null,null);
                   endangeredAnimal.save();
               }
            return new ModelAndView(model, "new-animal.hbs");
        }, new HandlebarsTemplateEngine());


          // list all animals
        get("/animals", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.all();
            model.put("animals", endangeredAnimals);
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
            String animalName=request.queryParams("animalName");
            String location=request.queryParams("sightLocation");
            String rangerName=request.queryParams("rangerName");
            Sightings newSightings= new Sightings(animalName,location,rangerName);
            newSightings.save();
            model.put("animalName",newSightings.getAnimalName());
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