package org.example;

import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

// Press Shift twice to open the Search Everywhere dialog
public class Main {
    public static void main(String[] args) {
        JavaCSG csg = JavaCSGFactory.createDefault();

        BaseFactory bf = new BaseFactory(csg);
        MovementTrayFactory mtf = new MovementTrayFactory(csg);

        UserValues.setBaseDiameter_mm(25.5);
        UserValues.setMovementTrayHeight_mm(5);
        UserValues.setBorderWidth_mm(.8);
        UserValues.setSpacing_mm(0);
        UserValues.setBaseHeight_mm(3.5);
        UserValues.setChamfer_mm(1.5);

        csg.view(mtf.createRectangularMovementTrayWithRoundBases(2, 2));

//        csg.saveSTL();
//        csg.view(p1);


    }
}