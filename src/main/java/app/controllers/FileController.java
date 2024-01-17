package app.controllers;

import app.Factories.BaseFactory;
import app.Factories.MovementTrayFactory;
import app.UserValues;
import io.javalin.http.Context;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

import java.io.*;

public class FileController {
    private static JavaCSG csg;

    public static void generateFile(Context ctx)
    {
        Geometry3D shape = null;

        if (ctx.attribute("selectedFile").equals("base.stl"))
        {
            shape = BaseFactory.buildCircularBase(
                    UserValues.getBaseDiameter_mm(),
                    UserValues.getBaseHeight_mm(),
                    UserValues.getChamfer_mm());
        }
        else if (ctx.attribute("selectedFile").equals("movementtray.stl"))
        {
            shape = MovementTrayFactory.createRectangularMovementTrayWithRoundBases(
                    UserValues.getMovementTrayX(),
                    UserValues.getMovementTrayY());
        }
        else
        {
            ctx.result("Error: No file selected");
        }


        try
        {
            var name = ctx.attribute("selectedFile");
            csg.saveSTL("OpenSCAD/output/"+name, shape);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (NullPointerException e)
        {
            throw new RuntimeException(e);
        }

        downloadFile(ctx);
    }

    public static void downloadFile(Context ctx)
    {
        var file = new File("OpenSCAD/output/" + ctx.attribute("selectedFile"));
        try
        {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            ctx.header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            ctx.header("Content-Length", String.valueOf(file.length()));
            ctx.result(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void setCsg(JavaCSG csg) {
        FileController.csg = csg;
    }
}
