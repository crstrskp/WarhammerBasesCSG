package app.controllers;
import app.UserValues;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class WarhammerBaseController {

    public static void addRoutes(Javalin app) {
        app.get("/warhammerbase", ctx -> ctx.render("warhammerbase.html"));

        app.get("/downloadFile", ctx -> download(ctx));
    }

    private static void download(Context ctx)
    {

        var chamfer = ctx.queryParam("chamfer_mm");
        var baseDiameter = ctx.queryParam("baseDiameter_mm");
        var baseHeight = ctx.queryParam("baseHeight_mm");
        var borderWidth = ctx.queryParam("borderWidth_mm");
        var spacing = ctx.queryParam("spacing_mm");
        var movementTrayHeight = ctx.queryParam("movementTrayHeight_mm");

        UserValues.setChamfer_mm(Double.parseDouble(chamfer));
        UserValues.setBaseDiameter_mm(Double.parseDouble(baseDiameter));
        UserValues.setBaseHeight_mm(Double.parseDouble(baseHeight));
        UserValues.setBorderWidth_mm(Double.parseDouble(borderWidth));
        UserValues.setSpacing_mm(Double.parseDouble(spacing));
        UserValues.setMovementTrayHeight_mm(Double.parseDouble(movementTrayHeight));

//        ctx.result(UserValues.getSTL());


        ctx.header("Content-Disposition", "attachment; filename=warhammerbase.stl");

    }

}
