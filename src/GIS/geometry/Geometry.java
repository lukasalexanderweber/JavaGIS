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
   *  Constructor sets the value of attributes id and type
   *  Type is specific from the created Geometry and is passed by super("Point")
     * @param type one of "Point", "Polyline" or "Polygon"
   */
  public Geometry(String type){
      // TO-DO: get existing ID's and create a unique one!!
      int testid = 1;
      this.id = testid;
      this.type = type;
  }
  
  /** 
   *  returns the value of the attribute id
     * @return ID of the object
   */
  public int getID() {
       return this.id;
  }

  /** 
   *  returns the value of the attribute type
     * @return Type of the object
   */
  public String getType() {
       return this.type;
  }
  
}