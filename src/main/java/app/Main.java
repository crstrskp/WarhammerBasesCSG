package app;

import app.Factories.BaseFactory;
import app.Factories.MovementTrayFactory;
import app.config.ThymeleafConfig;
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

        // routing
        app.get("/", ctx -> ctx.render("index.html"));

        app.post("/", ctx -> UserValues.setDefaults(ctx));




        JavaCSG csg = JavaCSGFactory.createDefault();

        BaseFactory bf = new BaseFactory(csg);
        MovementTrayFactory mtf = new MovementTrayFactory(csg);
        WarhammerBaseController.setCsg(csg);

        UserValues.setBaseDiameter_mm(25.5);
        UserValues.setMovementTrayHeight_mm(5);
        UserValues.setBorderWidth_mm(.8);
        UserValues.setSpacing_mm(0);
        UserValues.setBaseHeight_mm(3.5);
        UserValues.setChamfer_mm(1.5);

//        csg.view(mtf.createRectangularMovementTrayWithRoundBases(2, 2));

//        var diameter = UserValues.getBaseDiameter_mm();
//        var height = UserValues.getBaseHeight_mm();
//        var chamfer = UserValues.getChamfer_mm();
//
//        try {
//            var shape = bf.buildCircularBase(diameter, height, chamfer);
//
//            var box = csg.box3D(10, 10, 10, false);
////            csg.cache(shape);
//            csg.saveSTL("OpenSCAD/output/shape.stl", shape);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        csg.saveSTL();
//        csg.view(p1);


    }
}