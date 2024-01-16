package app.Factories;

import org.abstractica.javacsg.*;
import app.UserValues;

import java.util.ArrayList;
import java.util.List;

public class MovementTrayFactory {

    private static JavaCSG csg;
    public static MovementTrayFactory instance;

    public MovementTrayFactory(JavaCSG csg)
    {
        instance = this;
        MovementTrayFactory.csg = csg;
    }
    public static Geometry3D createRectangularMovementTrayWithRoundBases(int x, int y)
    {
        double width = (2 * UserValues.getBorderWidth_mm()) + ((x-1) * UserValues.getSpacing_mm()) + (x * UserValues.getBaseDiameter_mm());
        double depth = (2 * UserValues.getBorderWidth_mm()) + ((y-1) * UserValues.getSpacing_mm()) + (y * UserValues.getBaseDiameter_mm());

        Geometry3D base = BaseFactory.buildRectangularBase(width, depth, UserValues.getChamfer_mm(), UserValues.getBorderWidth_mm(), UserValues.getMovementTrayHeight_mm());

        // Holes
        var holeCutout = csg.cylinder3D(UserValues.getBaseDiameter_mm(), UserValues.getBaseHeight_mm(), 128, false);
        holeCutout = csg.translate3D((0.5* UserValues.getBaseDiameter_mm()),(0.5* UserValues.getBaseDiameter_mm()), UserValues.getMovementTrayHeight_mm()- UserValues.getBaseHeight_mm()).transform(holeCutout);

        List<Geometry3D> holes = new ArrayList<>();

        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
                Geometry3D hole = csg.translate3D
                (
                        (UserValues.getSpacing_mm() + UserValues.getBaseDiameter_mm()) * i,
                        (UserValues.getSpacing_mm() + UserValues.getBaseDiameter_mm()) * j,
                        0
                ).transform(holeCutout);

                holes.add(hole);
            }
        }
        base = csg.difference3D(base, holes);

        return base;
    }
}
