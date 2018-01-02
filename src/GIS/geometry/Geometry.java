package GIS.geometry;

/** 
 *  Parent class for the three geometry types.
 */
public class Geometry {
  /* {author=Lukas, version=1.0}*/

  // ATTRIBUTES
  /** 
   *  unique ID of the Geometrie
   *  (we have to think about how to assign them)
   */
  public int id;

  /** 
   *  type: One of Point, Polygon or Polyline
   *  will be assigned in the constructor of the subclasses
   */
  public String type;
  
  // METHODS

  /** 
   *  returns the value of the attribute id
   */
  public int getID() {
  return this.id;
  }

  /** 
   *  returns the value of the attribute type
   */
  public String getType() {
  return this.type;
  }

}