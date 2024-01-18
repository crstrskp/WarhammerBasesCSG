package app.Factories;

import app.UserValues;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

public class BaseFactoryTest {
    public static void circleBaseTest()
    {
        JavaCSG csg = JavaCSGFactory.createDefault();
        BaseFactory bf = new BaseFactory(csg);

        UserValues.setBaseDiameter_mm(25);
        UserValues.setBaseHeight_mm(3.5);
        UserValues.setChamfer_mm(1.5);
        UserValues.setAddHoleForMagnet(true);
        UserValues.setMagnetDiameter(2.2);
        UserValues.setMagnetHoleHeight(1);

        csg.view(bf.buildCircularBase(25, 3.5, 1.5));
    }
}
