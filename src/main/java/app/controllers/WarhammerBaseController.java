package app.controllers;
import app.Factories.BaseFactory;
import app.UserValues;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.abstractica.javacsg.JavaCSG;

import java.io.*;

public class WarhammerBaseController {

    private static JavaCSG csg;
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



        downloadFile(ctx);


    }

    private static void downloadFile(Context ctx)
    {

        var mt = BaseFactory.buildCircularBase(
                UserValues.getBaseDiameter_mm(),
                UserValues.getBaseHeight_mm(),
                UserValues.getChamfer_mm());



        try {
            csg.saveSTL("OpenSCAD/output/warhammerbase.stl", mt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Download the specified file

//            File localFile = new File(context.pathParam("name"));
//            context.header("Content-Disposition", "attachment; filename=\"" + localFile.getName() + "\"");
//            context.result(inputStream);

//        ctx.result("warhammerbase.stl");
        var file = new File("OpenSCAD/output/warhammerbase.stl");
        try{
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            ctx.header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            ctx.header("Content-Length", String.valueOf(file.length()));
            ctx.result(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setCsg(JavaCSG csg) {
        WarhammerBaseController.csg = csg;
    }
}
