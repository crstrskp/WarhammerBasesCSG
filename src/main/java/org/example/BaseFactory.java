package org.example;

import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.Vector3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseFactory {
    private static JavaCSG csg;


    public BaseFactory(JavaCSG csg)
    {
        this.csg = csg;
    }


   public static Geometry3D buildRectangularBase(double width, double height, double chamfer_mm, double borderWidth_mm, double baseHeight_mm)
   {
       ArrayList<Vector3D> vertices = new ArrayList<>();
       vertices.add(csg.vector3D(-chamfer_mm,-chamfer_mm, 0));
       vertices.add(csg.vector3D(width+chamfer_mm, -chamfer_mm, 0));
       vertices.add(csg.vector3D(-chamfer_mm, height+chamfer_mm, 0));
       vertices.add(csg.vector3D(width+chamfer_mm, height+chamfer_mm, 0));

       vertices.add(csg.vector3D(0,0, baseHeight_mm));
       vertices.add(csg.vector3D(width, 0, baseHeight_mm));
       vertices.add(csg.vector3D(0, height, baseHeight_mm));
       vertices.add(csg.vector3D(width, height, baseHeight_mm));

       // faces
       ArrayList<List<Integer>> faces = new ArrayList<>();
       faces.add(Arrays.asList(2, 0, 1, 3 ));   // bottom face
       faces.add(Arrays.asList(4, 6, 7, 5 ));   // top face
       faces.add(Arrays.asList(0, 4, 5, 1 ));   // front face
       faces.add(Arrays.asList(2, 3, 7, 6 ));   // back face
       faces.add(Arrays.asList(0, 2, 6, 4 ));   // left face
       faces.add(Arrays.asList(3, 1, 5, 7 ));   // right face

       var base = csg.polyhedron3D(vertices, faces);
       base = csg.translate3D(-borderWidth_mm, -borderWidth_mm, 0).transform(base);

       return base;
   }

}