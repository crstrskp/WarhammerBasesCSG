package app.Factories;

import app.UserValues;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

public class MovementTrayFactoryTest {
    public static void movementTrayTest()
    {
        JavaCSG csg = JavaCSGFactory.createDefault();
        MovementTrayFactory mtf = new MovementTrayFactory(csg);

        UserValues.setBaseDiameter_mm(25.5);
        UserValues.setMovementTrayHeight_mm(5);
        UserValues.setBorderWidth_mm(.8);
        UserValues.setSpacing_mm(0);
        UserValues.setBaseHeight_mm(3.5);
        UserValues.setChamfer_mm(1.5);
        UserValues.setAddHoleForMagnet(true);
        UserValues.setMovementTrayX(3);
        UserValues.setMovementTrayY(5);
        UserValues.setMagnetDiameter(2.2);
        UserValues.setMagnetHoleHeight(1);

        csg.view(mtf.createRectangularMovementTrayWithRoundBases(3, 5));
    }
}
