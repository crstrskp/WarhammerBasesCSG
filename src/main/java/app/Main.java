package app;

import app.Factories.BaseFactory;
import app.Factories.MovementTrayFactory;
import app.config.ThymeleafConfig;
import app.controllers.FileController;
import app.controllers.MovementTrayController;
import app.controllers.MovementTraySquareController;
import app.controllers.WarhammerBaseController;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            JavalinThymeleaf.init(ThymeleafConfig.templateEngine());
        }).start(7070);

        // routing
        WarhammerBaseController.addRoutes(app);
        MovementTrayController.addRoutes(app);
        MovementTraySquareController.addRoutes(app);
        FileController.spinnerRouting(app);

        app.get("/", ctx -> ctx.render("index.html"));

        app.post("/", ctx -> UserValues.setDefaults(ctx));

        JavaCSG csg = JavaCSGFactory.createDefault();

        BaseFactory bf = new BaseFactory(csg);
        MovementTrayFactory mtf = new MovementTrayFactory(csg);
        FileController.setCsg(csg);


        ////////////////////////////////////////////////////
        /////////////////// TESTING CSG ////////////////////
        ////////////////////////////////////////////////////
//        JavaCSG csg = JavaCSGFactory.createDefault();
//        BaseFactory bf = new BaseFactory(csg);
//        MovementTrayFactory mtf = new MovementTrayFactory(csg);
//
////        UserValues.setBaseDiameter_mm(25.5);
//        UserValues.setBaseWidth_mm(30.5);
//        UserValues.setBaseLength_mm(60.5);
//        UserValues.setMovementTrayHeight_mm(5);
//        UserValues.setBorderWidth_mm(.8);
//        UserValues.setSpacing_mm(1);
//        UserValues.setBaseHeight_mm(3.5);
//        UserValues.setChamfer_mm(1.5);
//        UserValues.setAddHoleForMagnet(true);
////        UserValues.setMovementTrayX(3);
////        UserValues.setMovementTrayY(5);
//        UserValues.setMagnetDiameter(3.6);
//        UserValues.setMagnetHoleHeight(1);
//
//        csg.view(mtf.createRectangularMovementTray(1, 1));

    }
}