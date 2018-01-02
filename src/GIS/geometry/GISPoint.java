package GIS.geometry;

import java.awt.geom.Ellipse2D;

/*
 */
/** 
 *  Creates an empty Point Object.
 *  (GISPoint because Point class already exists in Java.awt)
 *  Coordinates can be added with the Method "createPoint()", that will add values to x and y.
 *  To write Feature in DB or CSV the coordinates can be transformed to the right format using "writeAsText()". 
 *  To read it from a DB or CSV use "textToPoint()"
 */
public class GISPoint extends Geometry {
  /* {author=Lukas, version=1.0}*/

  // ATTRIBUTES
  /** 
   *  The X coordinate of a point object, default 0
   */
  public double x = 0;

  /** 
   *  The Y coordinate of a point object, default 0
   */
  public double y = 0;

  
  // METHODS
  /** 
   *  Constructor calls the constructor of the super class (Geometry)
   *  and sets the type
   */
  public GISPoint(){
      super("Point"); 
  }
  
  /** 
   *  assign x and y values derived from the plotting area to the objects attributes:
   *  this.x = x
   *  this.y = y
   *  
   *  return true when processed sucessfull
   * 
     * @param x X coordinate
     * @param y Y coordinate
     * @return true when successful, false when faulty
   */
  public boolean createPoint(double x, double y) {
  /* {author=Lukas, version=1.0}*/
    try {
        this.x = x;
        this.y = y;
        return true;
    }
    catch (NumberFormatException e){
        System.err.println("Error while creating Point from coordinates\n" + e);
        return false;
    }
  }
  
  /** 
   *  write the point so that they can be stored as a string in a DB/CSV and be reconstructed to a Point when loaded.
   *  should be something like that:
   *  this.x = 1.234
   *  this.y = 5.678
   *  getGeometryAsText()
   *  "1.234 5.678"
     * @return String with the Geometry as readable Text
   */
  public String getGeometryAsText() {
  /* {author=Lukas, version=1.0}*/
       String geom = String.valueOf(this.x) + " " + String.valueOf(this.y);
       return geom;
  }

  /** 
   *  when receiving a point from a DB/CSV it will be stored in the string:
   *  "1.234 5.678"
   *  setGeometryFromText("1.234 5.678") will split the string at the " " 
   *  and return an Array with x and y values, which then can be assigned to 
   *  this.x and this.y.
   *  
   *  return true when processed successful
     * @param geometry a String received from getGeometryAsText()
     * @return true when method was sucessfull, false for errors
   */
  public boolean setGeometryFromText(String geometry) {
  /* {author=Lukas, version=1.0}*/
    try {
        String[] coords = geometry.split(" ");
        this.x = Double.parseDouble(coords[0]);
        this.y = Double.parseDouble(coords[1]);
        return true;
    }
    catch (NumberFormatException e) {
        System.err.println("Error while parsing Geometry String to Point\n" + e);
        return false;
    }
  }
  
   /** 
   *  returns an Ellipse2D object which can be drawn 
     * @return Ellipse2D with X and Y coordinates and a radius of 5
   */
  public Ellipse2D getGeometry() {
  /* {author=Lukas, version=1.0}*/
    Ellipse2D P;
    P = new Ellipse2D.Double(this.x, this.y, 5, 5);
    return P;
  }

  /** 
   *  returns X coordinate (the attribute x)
   *  (if the attribute is not filled yet, tell the user to use createPoint)
     * @return X coordinate
   */
  public double getX() {
  /* {author=Lukas, version=1.0}*/

    return this.x;
  }

  /** 
   *  returns Y coordinate (the attribute y)
   *  (if the attribute is not filled yet, tell the user to use createPoint)
     * @return Y coordinate
   */
  public double getY() {
  /* {author=Lukas, version=1.0}*/

    return this.y;
  }

}