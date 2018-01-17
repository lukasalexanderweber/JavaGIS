/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GIS.drawing;

import GIS.geometry.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;


// here the JPanel is created

/**
 * A class which can draw the GIS objects stored in a Content object.
 * @author Lukas
 */
public class DrawingPanel extends JPanel {
            
    double factor;
    double horizontal;
    double vertical;
    
    // geometry arrays, for example DB import
    private ArrayList<GISPoint> points = new ArrayList<>();
    private ArrayList<GISPolyline> polylines = new ArrayList<>();
    private ArrayList<GISPolygon> polygons = new ArrayList<>();
    
    private ArrayList<GISPoint> selectedPoints = new ArrayList<>();
    private ArrayList<GISPolyline> selectedPolylines = new ArrayList<>();
    private ArrayList<GISPolygon> selectedPolygons = new ArrayList<>();
    
    // selection Rectangle
    private Rectangle2D selectR = null;

    /**
     * Forward the zoomfactor and horizontal/vertical offset defined by the users zooming and panning 
     * @param f zoomfactor
     * @param h horizontal offset
     * @param v vertical offset
     */
    public void setPan(double f, double h, double v) { 
        this.factor = f; 
        this.horizontal = h;
        this.vertical = v;
    }
    
    /**
     * Returns the transformation defined by zoom and pan.
     * @return AffineTransform object
     */
    public AffineTransform getCurrentTransform() {
        AffineTransform tx = new AffineTransform(); 
        tx.scale(factor, factor);
        tx.translate(horizontal, vertical);
        return tx;
    }
    
    /**
     * Overwrite the private (selected) point/polyline/polygon lists with the corresponding elements of an Content object. 
     * @param c the new content
     */
    public void updateContent(Content c) {
        points = c.pointlist; 
        polylines = c.polylinelist;         
        polygons = c.polygonlist; 
        selectedPoints = c.SelectedPointlist;
        selectedPolylines = c.SelectedPolylinelist;
        selectedPolygons = c.SelectedPolygonlist;
    }
    
    /**
     * Set the private selection rectangle to the user defined rectangle
     * @param rec Rectangle2D object drawn in the DrawingPanel
     */
    public void setSelectionRectangle(Rectangle2D rec) {
        selectR = rec; 
    }
            
    
    // paintComponent for painting elements
    @Override
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.clearRect(0, 0, getWidth(), getHeight());
           
        AffineTransform tx = getCurrentTransform();
        g2.setTransform(tx);
                
        //color for the Geometries:
        Color color = new Color(0, 0, 0, 0.75f); //black 
        g2.setPaint(color);
        
        //draw all points:
        points.forEach((GISPoint point) -> {
            Ellipse2D geom = point.getGeometry();
            g2.fill(geom);
        });            

        //draw all polylines:
        polylines.forEach((GISPolyline polyline) -> {
            Path2D geom = polyline.getGeometry();
            g2.draw(geom);
        });

        //draw all polygons:
        polygons.forEach((GISPolygon polygon) -> {
            Path2D geom = polygon.getGeometry();
            if (polygon.NoPoints > 2){
                g2.fill(geom);                
            }
            else{
                g2.draw(geom);    
            }
        });
        
        // DRAW SELECTION RECTANGLE AND SELECTED GEOMETRIES
        Color color2 = new Color(1, 0, 0, 0.75f); //Red
        g2.setPaint(color2);
        
        //draw selection rectangle:        
        if (selectR != null){
            g2.draw(selectR);
        }
        
        //draw all SELECTED points:
        selectedPoints.forEach((GISPoint point) -> {
            Ellipse2D geom = point.getGeometry();
            g2.fill(geom);
        });            

        //draw all SELECTED polylines:
        selectedPolylines.forEach((GISPolyline polyline) -> {
            Path2D geom = polyline.getGeometry();
            g2.draw(geom);
        });

        //draw all SELECTED polygons:
        selectedPolygons.forEach((GISPolygon polygon) -> {
            Path2D geom = polygon.getGeometry();
            if (polygon.NoPoints > 2){
                g2.fill(geom);                
            }
            else{
                g2.draw(geom);    
            }
        });
    }
}

