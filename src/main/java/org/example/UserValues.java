package org.example;

public class UserValues
{
    // Shared Values
    private static double chamfer_mm;
    private static double baseDiameter_mm;
    private static double baseHeight_mm;


    // Rectangular Movement Tray
    private static double borderWidth_mm;
    private static double spacing_mm;
    private static double movementTrayHeight_mm;




    public static double getBaseHeight_mm() {
        return baseHeight_mm;
    }

    public static void setBaseHeight_mm(double baseHeight_mm) {
        UserValues.baseHeight_mm = baseHeight_mm;
    }

    public static double getBorderWidth_mm()
    {
        return borderWidth_mm;
    }

    public static void setBorderWidth_mm(double borderWidth_mm)
    {
        UserValues.borderWidth_mm = borderWidth_mm;
    }

    public static double getChamfer_mm() {
        return chamfer_mm;
    }

    public static void setChamfer_mm(double chamfer_mm) {
        UserValues.chamfer_mm = chamfer_mm;
    }

    public static double getSpacing_mm()
    {
        return spacing_mm;
    }

    public static void setSpacing_mm(double spacing_mm)
    {
        UserValues.spacing_mm = spacing_mm;
    }

    public static double getBaseDiameter_mm()
    {
        return baseDiameter_mm;
    }

    public static void setBaseDiameter_mm(double baseDiameter_mm)
    {
        UserValues.baseDiameter_mm = baseDiameter_mm;
    }

    public static double getMovementTrayHeight_mm()
    {
        return movementTrayHeight_mm;
    }

    public static void setMovementTrayHeight_mm(double movementTrayHeight_mm)
    {
        UserValues.movementTrayHeight_mm = movementTrayHeight_mm;
    }
}
