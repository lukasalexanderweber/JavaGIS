package GIS.geometry;

/*
 */
/** 
 *  Creates an empty Point Object.
 *  Coordinates can be added with the Method "createPoint()", that will add values to x and y.
 *  To write Feature in DB or CSV the coordinates can be transformed to the right format using "writeAsText()". To read it from a DB or CSV use "textToPoint()"
 */
public class Point extends Geometry {
  /* {author=Name, version=1.0}*/

  // ATTRIBUTES
  /** 
   *  The X coordinate of a point object
   */
  public float x;

  /** 
   *  The Y coordinate of a point object
   */
  public float y;

  
  // METHODS
  
  /** 
   *  write the point so that they can be stored as a string in a DB/CSV and be reconstructed to a Point when loaded.
   *  should be something like that:
   *  x = 1.234
   *  y = 5.678
   *  writeAsText()
   *  "1.234|5.678"
     * @return
   */
  public String writeGeometryAsText() {
  /* {author=Name, version=1.0}*/

  return null;
  }

  /** 
   *  assign x and y values derived from the plotting area to the objects attributes:
   *  this.x = x
   *  this.y = y
   *  
   *  return true when processed sucessfull
     * @param x
     * @param y
     * @return 
   */
  public boolean createPoint(float x, float y) {
  /* {author=Name, version=1.0}*/
    this.x = x;
    this.y = y;
    return true;
  }

  /** 
   *  when receiving a point from a DB/CSV it will be stored in the string:
   *  "1.234 5.678"
   *  textToPoint("1.234 5.678") will split the string at the " " and return an Array with x and y values, which then can be assigned to this.x and this.y.
   *  
   *  return true when processed sucessfull
   */
  public boolean geometryAsTextToPoint(String geometry) {
  /* {author=Name, version=1.0}*/

  return false;
  }

  /** 
   *  returns X coordinate (the attribute x)
   *  (if the attribute is not filled yet, tell the user to use createPoint)
   */
  public float getX() {
  /* {author=Name, version=1.0}*/

  return this.x;
  }

  /** 
   *  returns Y coordinate (the attribute y)
   *  (if the attribute is not filled yet, tell the user to use createPoint)
   */
  public float getY() {
  /* {author=Name, version=1.0}*/

  return this.y;
  }

}