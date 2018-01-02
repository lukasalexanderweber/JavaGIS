/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GIS.drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.geom.AffineTransform;


// here the JPanel is created
public class DrawingPanel extends JPanel {
            
    public double factor;
    public double horizontal;
    public double vertical;

    /**
     * ghd
     * @param f fgd
     * @param h
     * @param v 
     */
    public void setPan(double f, double h, double v) { 
        this.factor = f; 
        this.horizontal = h;
        this.vertical = v;
    }
    
    public AffineTransform getCurrentTransform() {
        AffineTransform tx = new AffineTransform(); 
        tx.scale(factor, factor);
        tx.translate(horizontal, vertical);
        return tx;
    }
    
    // geometry arrays, for example DB import
    public ArrayList<Ellipse2D> points = new ArrayList<>();
    public ArrayList<Path2D> polylines = new ArrayList<>();
    public ArrayList<Path2D> polygons = new ArrayList<>();
    
    // actual drawed geometry
    public Ellipse2D drawPoint = null;
    public Path2D drawPolyline = null;
    public Path2D drawPolygon = null;
    
    // needed for knowing if Polygon is actualy a line or real polygon (P>2)
    public int NoOfPoints = 0;
    
    
    // paintComponent for painting elements
    @Override
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.clearRect(0, 0, getWidth(), getHeight());
           
        AffineTransform tx = getCurrentTransform();
        g2.setTransform(tx);
        
        //paint imported/allready painted Geometries:
        Color color = new Color(1, 0, 0, 0.75f); //Red 
        g2.setPaint(color);
        
        points.forEach((point) -> {
            g2.fill(point);
        });
        polylines.forEach((polyline) -> {
            g2.draw(polyline);
        });
        polygons.forEach((polygon) -> {
            g2.fill(polygon);
        });
                
        //paint actual drawed Geometry:
        color = new Color(0, 0, 1, 0.75f); //Blue 
        g2.setPaint(color);
        
        // Point
        if (drawPoint != null){
            g2.fill(drawPoint);
        }        
        // Polyline
        if (drawPolyline != null){
            g2.draw(drawPolyline);
        }
        // Polygon
        if (drawPolygon != null){
            if (NoOfPoints > 2){
                g2.fill(drawPolygon);
            }
            else{
                g2.draw(drawPolygon);
            }
        }
    }
    
    public void drawPoint(Ellipse2D point, boolean comp) { 
        this.drawPoint = point;   
        if (comp == true){
            points.add(point);
            drawPoint = null;
        }
    }
    public void drawPolyline(Path2D polyline, boolean comp) { 
        this.drawPolyline = polyline;
        if (comp == true){
            polylines.add(polyline);
            drawPolyline = null;
        }
    }
    public void drawPolygon(Path2D polygon, boolean comp, int noPoints) { 
        this.drawPolygon = polygon;
        this.NoOfPoints = noPoints;  
        if (comp == true){
            polygons.add(polygon);
            drawPolygon = null;
            NoOfPoints = 0;
        }
    }
}
