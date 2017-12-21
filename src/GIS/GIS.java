package GIS;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.NoninvertibleTransformException;
import java.io.File;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileSystemView;

public class GIS extends JFrame implements ActionListener  {
    
    // variable decleration:
    DrawingPanel p;

    // Layout
    JButton buttonPlus, buttonMinus, AddFile, FE,PanB, fileOp;
    JButton buttonRight;
    JButton buttonLeft;
    JButton buttonTop;
    JButton buttonBottom;
    JButton createPoint;
    JButton createPolyline;
    JButton createPolygon;
    JLabel x;
    JLabel y;
    JToolBar toolB;
    JToolBar justatooltoseeifsomethingchanges;

    // variables for zooming and paning
    // (need to be available for all classes)
    public double factor = 1;
    public double horizontal = 0;
    public double vertical = 0;

    // mouse coordinates
    double mouseX;
    double mouseY;

    // old mouse coordinates (for paning)
    double oldMouseX;
    double oldMouseY;
    double newMouseX;
    double newMouseY;

    // drawing
    String FeatureType = "Pan";
    boolean drawPolygonStarted = false;
    boolean drawPolylineStarted = false;
    int numberOfPoints = 0;
    Ellipse2D Point2D;
    Path2D Polygon2D;
    Path2D Polyline2D;

    // --------------------------------------------------
    // Constructor which implements drawing functionality
    // --------------------------------------------------
    GIS(){
        super("GIS"); //heading

        // create new Drawing Panel
        p = new DrawingPanel();
      
        // set the zoom and x,y offset (default 1,0,0)
        p.setPan(factor, horizontal, vertical);

        // when starting the programm Pan mode is active -> HAND_CURSOR
        p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       
        // MouseMotion to get the actual x,y-coordinates of mouse
        p.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me){
                mouseX = (me.getX());
                mouseY = (me.getY());
		// create a 2D Point object from the coordinates
                Point2D.Double point = new Point2D.Double(mouseX, mouseY); 
		// get the current Transformation of the DrawingPanel
                AffineTransform tx = p.getCurrentTransform();			   
                try {
                    // backtransform them to display the correct X and Y
                    mouseX = tx.inverseTransform(point, null).getX();	   
                    mouseY = tx.inverseTransform(point, null).getY();
                } catch (NoninvertibleTransformException ex) {
                    Logger.getLogger(GIS.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // display x,y in the JLabels x,y
                x.setText(String.valueOf(Math.round(mouseX)));
                y.setText(String.valueOf(Math.round(mouseY)));
            }
        });

        // MouseListener in the DrawingPanel to draw Point, Line or Polygon
        p.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(FeatureType);
                switch (FeatureType) {
                    
                    // if drawPolyline mode in active
                    case "drawPoint":
                        // create new Ellipse2D with a radius of 5px
                        Point2D = new Ellipse2D.Double(mouseX, mouseY, 5, 5);
                        p.drawPoint(Point2D, true);
                        p.repaint();
                        System.out.println(String.valueOf(Math.round(mouseX))+" "+
                                String.valueOf(Math.round(mouseX)));
                        break; 

                    // if drawPolyline mode in active
                    case "drawPolyline":
                        // if no Polyline draw has started yet
                        if (drawPolylineStarted == false){
                            // create new Path2D and move startpoint to actual x,y
                            Polyline2D = new Path2D.Double();
                            Polyline2D.moveTo(mouseX, mouseY);
                            // pass the polyline to the DrawingPanel
                            p.drawPolyline(Polyline2D, false);
                            p.repaint();
                            System.out.println(String.valueOf(Math.round(mouseX))+" "+
                                    String.valueOf(Math.round(mouseX)));
                            // indicate that new Polygon draw started
                            drawPolylineStarted = true;
                        }
                        // finish plot when doubleclick (ClickCount()==2)
                        if (e.getClickCount() == 2 && drawPolylineStarted == true) {
                            System.out.println("Doubleclick");
                            p.drawPolyline(Polyline2D, true);
                            p.repaint();
                            // indicate that Polygon draw is finished
                            drawPolylineStarted = false;
                        }
                        // adding new points to Polygon
                        else{
                            Polyline2D.lineTo(mouseX, mouseY);
                            p.drawPolyline(Polyline2D, false);
                            p.repaint();
                            System.out.println(String.valueOf(Math.round(mouseX))+" "+
                                    String.valueOf(Math.round(mouseX)));
                        }                        
                        break; 
                        
                    case "drawPolygon":
                        // if no Polygon draw has started yet
                        if (drawPolygonStarted == false){
                            // create new Path2D and move startpoint to actual x,y
                            Polygon2D = new Path2D.Double();
                            Polygon2D.moveTo(mouseX, mouseY);
                            p.drawPolygon(Polygon2D, false, numberOfPoints);
                            p.repaint();
                            System.out.println(String.valueOf(Math.round(mouseX))+" "+
                                    String.valueOf(Math.round(mouseX)));
                            // indicate that new Polygon draw started
                            drawPolygonStarted = true;
                            numberOfPoints += 1;
                        }
                        // finish plot when doubleclick (ClickCount()==2)
                        if (e.getClickCount() == 2 && drawPolygonStarted == true) {
                            System.out.println("Doubleclick");
                            Polygon2D.closePath();
                            p.drawPolygon(Polygon2D, true, numberOfPoints);
                            p.repaint();
                            // indicate that Polygon draw is finished
                            drawPolygonStarted = false;
                            numberOfPoints = 0;
                        }
                        // adding new points to Polygon
                        else{
                            Polygon2D.lineTo(mouseX, mouseY);
                            p.drawPolygon(Polygon2D, false, numberOfPoints);
                            p.repaint();
                            numberOfPoints += 1;
                            System.out.println(String.valueOf(Math.round(mouseX))+" "+
                                    String.valueOf(Math.round(mouseX)));
                        }                        
                        break; 

                    // if Pan mode is active
                    case "Pan":
                        System.out.println("pressed");
                        oldMouseX = e.getX();
                        System.out.println(oldMouseX);
                        oldMouseY = e.getY();
                        // -> will be processed in mouseReleased listener
                        break;

                    // do nothing if the state is none of those four
                    default:
                        break;
                }
            }           

            // if Pan mode is active -> move to place where mouse is released
            @Override
            public void mouseReleased(MouseEvent e) {
                if ("Pan".equals(FeatureType)){
                    System.out.println("released");
                    newMouseX = e.getX();
                    System.out.println(newMouseX);
                    newMouseY = e.getY();

                    // Pan to the position where mouse is released
                    horizontal = horizontal + newMouseX - oldMouseX;
                    vertical = vertical + newMouseY - oldMouseY;
                    p.setPan(factor, horizontal, vertical);
                    p.repaint();
                }
            }
        });

        // MouseWheel to zoom in and out
        p.addMouseWheelListener((MouseWheelEvent e) -> {
            if ("Pan".equals(FeatureType)) {
                if (e.getWheelRotation() < 0) {
                    System.out.println("mouse wheel Up");
                    // zoom in when wheel up
                    factor += 0.01;
                    p.setPan(factor, horizontal, vertical);
                    p.repaint();
                    
                } else {
                    System.out.println("mouse wheel Down");
                    // zoom out when wheel down
                    factor -= 0.01;
                    p.setPan(factor, horizontal, vertical);
                    p.repaint();
                }
            }
        });
    }   
    // -------------------
    // End of Constructor 
    // -------------------    
    
    public void setLayout(){   
        // Layout declaration
        buttonMinus = new JButton("Zoom Out");
        buttonPlus = new JButton("Zoom In");
        buttonRight = new JButton("Right");
        buttonLeft = new JButton("Left");
        buttonTop = new JButton("Top");
        buttonBottom = new JButton("Bottom");
        createPoint = new JButton("Create Point");
        createPolyline = new JButton("Create Polyline");
        createPolygon = new JButton("Create Polygon");
        FE = new JButton("FE");
        PanB = new JButton("Pan");
        fileOp = new JButton("Open File");
        x = new JLabel();
        y = new JLabel();
        toolB = new JToolBar();

        // adding ActionListener
        buttonMinus.addActionListener(this);
        buttonPlus.addActionListener(this);
        buttonRight.addActionListener(this);
        buttonLeft.addActionListener(this);
        buttonTop.addActionListener(this);
        buttonBottom.addActionListener(this);
        createPoint.addActionListener(this);
        createPolyline.addActionListener(this);
        createPolygon.addActionListener(this);
        FE.addActionListener(this);
        PanB.addActionListener(this);
        fileOp.addActionListener(this);

        // define Layout

        toolB.add(createPoint);
        toolB.add(createPolyline);
        toolB.add(createPolygon);
        toolB.add(buttonPlus);
        toolB.add(buttonMinus); 
        toolB.add(FE);
        toolB.add(PanB);
        toolB.add(fileOp);
        toolB.setFloatable(true);

        //box in four side
        Box box = Box.createHorizontalBox();
        Box boxT = Box.createHorizontalBox();
        Box boxE = Box.createVerticalBox();
        Box boxW = Box.createVerticalBox();
        //box contain button and others
        boxE.add(Box.createVerticalStrut(250));
        boxE.add(buttonRight);
        boxW.add(Box.createVerticalStrut(250));
        boxW.add(buttonLeft);
        boxT.add(Box.createHorizontalStrut(500));
        boxT.add(buttonTop);
        boxT.add(toolB);
        box.add(Box.createHorizontalStrut(500));
        box.add(buttonBottom);
        box.add(Box.createHorizontalStrut(290));
        box.add(x);
        box.add(Box.createHorizontalStrut(10));
        box.add(y);
        add(box, BorderLayout.SOUTH);
        add(boxT, BorderLayout.NORTH);
        add(boxE, BorderLayout.EAST);
        add(boxW, BorderLayout.WEST);
        add(p, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1180,630);
        setVisible(true);
    }
    
        // Action handling for all buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        // zoom in (coodinated will be multiplied with the factor)
        if(e.getSource() == buttonPlus) {
            factor += 0.1;
        }
        // full extent
        else if(e.getSource() == FE) {
            factor = 1;
            horizontal = 0;
            vertical = 0;
        }
        //pan button 
        else if (e.getSource() == PanB){
            p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                FeatureType = "Pan";
        }
        // zoom out (coodinated will be multiplied with the factor)
        else if(e.getSource() == buttonMinus) {
            factor -= 0.1;
        }
        // move rigth (x coordinates will be added with the factor)
        else if(e.getSource() == buttonRight) {
            horizontal += 10;
        }
        // move left (x coordinates will be added with the factor)
        else if(e.getSource() == buttonLeft) {
            horizontal -= 10;
        }
        // move up (y coordinates will be added with the factor)
        else if(e.getSource() == buttonTop) {
            vertical -= 10;
        }
        // move down (y coordinates will be added with the factor)
        else if(e.getSource() == buttonBottom) {
            vertical += 10;
        }
        else if(e.getSource()==fileOp){
            JFileChooser Jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            Jfc.setDialogTitle("Open CSV or DBMS file");
            Jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int Jfcreturn = Jfc.showOpenDialog(null);
            if (Jfcreturn == JFileChooser.APPROVE_OPTION){
                File[] file = Jfc.getSelectedFiles();   
            }

        }
        else if(e.getSource() == createPoint) {
            if (!FeatureType.equals("drawPoint")){
                p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                createPoint.setBackground(Color.GRAY);
                FeatureType = "drawPoint";
            }
            else{
                p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                createPoint.setBackground(new JButton().getBackground()); //default color
                FeatureType = "Pan";
            }
        }
        else if(e.getSource() == createPolyline) {
            if (!FeatureType.equals("drawPolyline")){
                p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                createPolyline.setBackground(Color.GRAY);
                FeatureType = "drawPolyline";
            }
            else{
                p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                createPolyline.setBackground(new JButton().getBackground()); //default color
                FeatureType = "Pan";
            }
        }
        else if(e.getSource() == createPolygon) {
            if (!FeatureType.equals("drawPolygon")){
                p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                createPolygon.setBackground(Color.GRAY);
                FeatureType = "drawPolygon";
            }
            else{
                p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                createPolygon.setBackground(new JButton().getBackground()); //default color
                FeatureType = "Pan";
            }
        }

        // maximum zoom out: 3, maximum zoom in: 0.1
        // bottom will be disabled when max reached 
        // (works just for the bottons, not for scrolling)
        buttonMinus.setEnabled(factor > 0.1);
        buttonPlus.setEnabled(factor < 3);
        p.setPan(factor, horizontal, vertical);
        p.repaint();
    }

    public static void main(String[] args){
        GIS gis = new GIS(); 
        gis.setLayout();
        System.out.println("start");
    }
}