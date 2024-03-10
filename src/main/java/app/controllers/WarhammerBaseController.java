package app.controllers;
import app.Factories.BaseFactory;
import app.UserValues;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.abstractica.javacsg.JavaCSG;

import java.io.*;

public class WarhammerBaseController {

    public static void addRoutes(Javalin app) {
        app.get("/warhammerbase", ctx -> ctx.render("warhammerbase.html"));

        app.post("/downloadBase", ctx -> downloadBase(ctx));
    }

    private static void downloadBase(Context ctx)
    {
        var chamfer =               ctx.formParam("chamfer_mm");
        var baseDiameter =          ctx.formParam("baseDiameter_mm");
        var baseHeight =            ctx.formParam("baseHeight_mm");

        var addHoleForMagnet =      ctx.formParam("addHoleForMagnet");

        var magnetDiameter =        ctx.formParam("magnetDiameter_mm");
        var magnetHoleHeight =      ctx.formParam("magnetHeight_mm");

        var makeHollow =            ctx.formParam("makeHollow");
        var thickness =             ctx.formParam("thickness_mm");

        UserValues.setChamfer_mm(Double.parseDouble(chamfer));
        UserValues.setBaseDiameter_mm(Double.parseDouble(baseDiameter));
        UserValues.setBaseHeight_mm(Double.parseDouble(baseHeight));

        UserValues.setAddHoleForMagnet(addHoleForMagnet != null ? true : false);
        UserValues.setMagnetDiameter(Double.parseDouble(magnetDiameter));
        UserValues.setMagnetHoleHeight(Double.parseDouble(magnetHoleHeight));

        UserValues.setHollowBase(makeHollow != null ? true : false);
        UserValues.setBaseThickness_mm(Double.parseDouble(thickness));

        ctx.attribute("selectedFile", "base.stl");
        FileController.generateFile(ctx);
        FileController.downloadFile(ctx);
    }
}
