package app;

import app.Factories.BaseFactory;
import app.Factories.MovementTrayFactory;
import app.config.ThymeleafConfig;
import app.controllers.FileController;
import app.controllers.MovementTrayController;
import app.controllers.WarhammerBaseController;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog
public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            JavalinThymeleaf.init(ThymeleafConfig.templateEngine());
        }).start(7070);

        WarhammerBaseController.addRoutes(app);
        MovementTrayController.addRoutes(app);
        // routing
        app.get("/", ctx -> ctx.render("index.html"));

        app.post("/", ctx -> UserValues.setDefaults(ctx));


        JavaCSG csg = JavaCSGFactory.createDefault();

        BaseFactory bf = new BaseFactory(csg);
        MovementTrayFactory mtf = new MovementTrayFactory(csg);
        FileController.setCsg(csg);
    }
}