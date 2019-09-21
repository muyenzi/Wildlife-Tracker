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

//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            ArrayList<Animal> squads = (ArrayList<Animal>) animal.all();
//            model.put("animals", animals);
//            return new ModelAndView(model, "animal-form.hbs");
//        }, new HandlebarsTemplateEngine());

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
