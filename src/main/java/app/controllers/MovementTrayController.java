package app.controllers;

import app.UserValues;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class MovementTrayController
{
    public static void addRoutes(Javalin app) {
        app.get("/movementtrays", ctx -> ctx.render("movementtrays.html"));

        app.post("/downloadMovementTray", ctx -> {
            downloadMovementTray(ctx);
        });
    }
    public static void downloadMovementTray(Context ctx)
    {
        var chamfer =               ctx.formParam("chamfer_mm");
        var baseDiameter =          ctx.formParam("baseDiameter_mm");
        var baseHeight =            ctx.formParam("baseHeight_mm");
        var borderWidth =           ctx.formParam("borderWidth_mm");
        var spacing =               ctx.formParam("spacing_mm");
        var movementTrayHeight =    ctx.formParam("movementTrayHeight_mm");
        var addHoleForMagnet =      ctx.formParam("addHoleForMagnet");

        UserValues.setChamfer_mm(Double.parseDouble(chamfer));
        UserValues.setBaseDiameter_mm(Double.parseDouble(baseDiameter));
        UserValues.setBaseHeight_mm(Double.parseDouble(baseHeight));
        UserValues.setBorderWidth_mm(Double.parseDouble(borderWidth));
        UserValues.setSpacing_mm(Double.parseDouble(spacing));
        UserValues.setMovementTrayHeight_mm(Double.parseDouble(movementTrayHeight));
        UserValues.setAddHoleForMagnet(addHoleForMagnet != null ? true : false);

        UserValues.setMovementTrayX(Integer.parseInt(ctx.formParam("movementTrayX")));
        UserValues.setMovementTrayY(Integer.parseInt(ctx.formParam("movementTrayY")));

        ctx.attribute("selectedFile", "movementtray.stl");
        FileController.generateFile(ctx);
        FileController.downloadFile(ctx);
    }
}
