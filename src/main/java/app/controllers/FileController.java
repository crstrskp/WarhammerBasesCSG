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
            System.out.println("Error: No file selected");
            ctx.result("Error: No file selected");
        }

        try
        {
            var name = ctx.attribute("selectedFile");
          //  csg.saveSTL("OpenSCAD/"+name, shape);
            csg.view(shape);
            convertSCADtoSTL();
            System.out.println("File saved as: " + name);
        }
        catch (NullPointerException e)
        {
            System.out.println("Caught NullPointerException:");
            System.out.println(e);
            System.out.println();
            throw new RuntimeException(e);
        }

        downloadFile(ctx);
    }

    public static void downloadFile(Context ctx)
    {
        var file = new File("model.stl");
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

    public static void convertSCADtoSTL()
    {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("openscad --export-format binstl -o model.stl OpenSCAD/View0.scad");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // Read the output from the command
            System.out.println("Standard output:");
            String s;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // Read any errors from the attempted command
            System.out.println("Standard error:");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

            int exitVal = process.waitFor();
            System.out.println("Exit value: " + exitVal);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
