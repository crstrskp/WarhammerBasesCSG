package app.controllers;

import app.UserValues;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class MovementTraySquareController
{
    public static void addRoutes(Javalin app) {
        app.get("/squaremovementtrays", ctx -> ctx.render("movementtrayssquare.html"));

        app.post("/downloadMovementTraySquare", ctx -> {
            downloadMovementTray(ctx);
        });
    }
    public static void downloadMovementTray(Context ctx)
    {
        var chamfer =               ctx.formParam("chamfer_mm");
        var baseDiameter =          ctx.formParam("baseDiameter_mm");
        var baseWidth =             ctx.formParam("baseWidth_mm");
        var baseLength =            ctx.formParam("baseLength_mm");
        var baseHeight =            ctx.formParam("baseHeight_mm");
        var borderWidth =           ctx.formParam("borderWidth_mm");
        var spacing =               ctx.formParam("spacing_mm");
        var movementTrayHeight =    ctx.formParam("movementTrayHeight_mm");
        var addHoleForMagnet =      ctx.formParam("addHoleForMagnet");

        var magnetDiameter =        ctx.formParam("magnetDiameter_mm");
        var magnetHoleHeight =      ctx.formParam("magnetHeight_mm");


        UserValues.setChamfer_mm(Double.parseDouble(chamfer));
        UserValues.setBaseWidth_mm(Double.parseDouble(baseWidth));
        UserValues.setBaseLength_mm(Double.parseDouble(baseLength));
        UserValues.setBaseDiameter_mm(-1);
        UserValues.setBaseHeight_mm(Double.parseDouble(baseHeight));
        UserValues.setBorderWidth_mm(Double.parseDouble(borderWidth));
        UserValues.setSpacing_mm(Double.parseDouble(spacing));
        UserValues.setMovementTrayHeight_mm(Double.parseDouble(movementTrayHeight));
        UserValues.setAddHoleForMagnet(addHoleForMagnet != null ? true : false);
        UserValues.setMagnetDiameter(Double.parseDouble(magnetDiameter));
        UserValues.setMagnetHoleHeight(Double.parseDouble(magnetHoleHeight));

        UserValues.setMovementTrayX(Integer.parseInt(ctx.formParam("movementTrayX")));
        UserValues.setMovementTrayY(Integer.parseInt(ctx.formParam("movementTrayY")));

        ctx.attribute("selectedFile", "squaremovementtray.stl");
        FileController.generateFile(ctx);
        FileController.downloadFile(ctx);
    }
}
