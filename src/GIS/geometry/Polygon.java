package GIS.geometry;

/*
 */
/** 
 *  A Polygon is just a closed Polyline. When finished drawing, the first point from the Polyline will be added as last point to close the Polygon (method: closePolygon)
 *  Refer to the object Polyline for the inherited methods to fill the pointlist and write to or create from DB/CSV friendly formats.
 *  Additional attributes for Polylines are: circumference and area.
 */
public class Polygon extends Polyline {

  /** 
   *  the circumference of the Polygon
   */
  public float circumference;

  /** 
   *  the area of the Polygon
   */
  public float area;

  /** 
   *  when finished drawing, the first point of the pointlist will be added as the last point to close the polyline.
   */
  public void closePolygon() {
  /* {author=Name, version=1.0}*/

  }

  /** 
   *  calculate the circumference of a Polygon, stored in Attribute circumference.
   */
  public void calculateCircumference() {
  /* {author=Name, version=1.0}*/

  }

  /** 
   *  returns the value of attribute circumference of a Polyline
   *  (if the attribute is not filled yet, tell the user to use calculateCircumference)
   */
  public float getCircumference() {
  /* {author=Name, version=1.0}*/

  return (float) 0.0;
  }

  /** 
   *  calculate the area of a Polygon, stored in Attribute area.
   */
  public void calculateArea() {
  /* {author=Name, version=1.0}*/

  }

  /** 
   *  returns the value of attribute area of a Polyline
   *  (if the attribute is not filled yet, tell the user to use calculateArea)
   */
  public float getArea() {
  /* {author=Name, version=1.0}*/

  return (float) 0.0;
  }

}