package GIS.drawing;

import GIS.geometry.GISPolygon;
import GIS.geometry.GISPolyline;
import GIS.geometry.GISPoint;
import java.util.ArrayList;

/*
 */
public class Content {

  public ArrayList<GISPoint> pointlist = new ArrayList();

  public ArrayList<GISPolyline> polylinelist = new ArrayList();

  public ArrayList<GISPolygon> polygonlist = new ArrayList();


  public void addPoint(GISPoint point) {
  /* {author=Name, version=1.0}*/
    pointlist.add(point);
  }

  public void addPolyline(GISPolyline polyline) {
  /* {author=Name, version=1.0}*/
    polylinelist.add(polyline);
  }
  /**
  * refreshes the last element of the arraylist polyline
     * @param polyline the updated polyline
  */
  public void refreshPolyline(GISPolyline polyline) {
  /* {author=Name, version=1.0}*/
    polylinelist.set(polylinelist.size() - 1, polyline);
    System.out.println(polyline.NoPoints);
  }

  public void addPolygon(GISPolygon polygon) {
  /* {author=Name, version=1.0}*/
    polygonlist.add(polygon);
  }
  
  public void refreshPolygon(GISPolygon polygon) {
  /* {author=Name, version=1.0}*/
    polygonlist.set(polygonlist.size() - 1, polygon);
  }

  public boolean removePoint(int id) {
  /* {author=Name, version=1.0}*/

  return false;
  }

  public boolean removePolyline(int id) {
  /* {author=Name, version=1.0}*/

  return false;
  }

  public boolean removePolygon(int id) {
  /* {author=Name, version=1.0}*/

  return false;
  }

}