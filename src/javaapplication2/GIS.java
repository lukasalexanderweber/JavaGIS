/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import static GIS.GIS.DBconnect;
import GIS.drawing.DrawingPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Fuad Amin
 */
public class GIS extends JFrame implements ActionListener {
    DrawingPanel p = new DrawingPanel();

    @Override
    public void actionPerformed(ActionEvent e) {
        
       if(e.getSource() == zoom) {
            factor += 0.1;
        }
        // full extent
        else if(e.getSource() == fullext) {
            factor = 1;
            horizontal = 0;
            vertical = 0;
        }
        //pan button 
        else if (e.getSource() == pan){
            p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pan.setBackground(Color.GRAY);
            createPolygon.setBackground(new JButton().getBackground()); //default color
            createPolyline.setBackground(new JButton().getBackground()); //default color
            createPoint.setBackground(new JButton().getBackground()); //default color
            ActualState = "Pan";
        }
        // zoom out (coodinated will be multiplied with the factor)
        else if(e.getSource() == zoomout) {
            factor -= 0.1;
        }

       
        else if(e.getSource()==addshp){
            JFileChooser Jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            Jfc.setDialogTitle("Open CSV or DBMS file");
            Jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int Jfcreturn = Jfc.showOpenDialog(null);
            if (Jfcreturn == JFileChooser.APPROVE_OPTION){
                File[] file = Jfc.getSelectedFiles();   
            }
        }
        else if(e.getSource() == addlayer) {
            DBconnect.setVisible(true);
        }
        else if(e.getSource() == createPoint) {
            if (!ActualState.equals("drawPoint")){
                p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                createPoint.setBackground(Color.GRAY);
                createPolyline.setBackground(new JButton().getBackground()); //default color
                createPolygon.setBackground(new JButton().getBackground()); //default color
                pan.setBackground(new JButton().getBackground()); //default color
                ActualState = "drawPoint";
            }
            else{
                p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                createPoint.setBackground(new JButton().getBackground()); //default color
                ActualState = "Pan";
            }
        }
        else if(e.getSource() == createPolyline) {
            if (!ActualState.equals("drawPolyline")){
                p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                createPolyline.setBackground(Color.GRAY);
                createPoint.setBackground(new JButton().getBackground()); //default color
                createPolygon.setBackground(new JButton().getBackground()); //default color
                pan.setBackground(new JButton().getBackground()); //default color
                ActualState = "drawPolyline";
            }
            else{
                p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                createPolyline.setBackground(new JButton().getBackground()); //default color
                ActualState = "Pan";
            }
        }
        else if(e.getSource() == createPolygon) {
            if (!ActualState.equals("drawPolygon")){
                p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                createPolygon.setBackground(Color.GRAY);
                createPolyline.setBackground(new JButton().getBackground()); //default color
                createPoint.setBackground(new JButton().getBackground()); //default color
                pan.setBackground(new JButton().getBackground()); //default color
                ActualState = "drawPolygon";
            }
            else{
                p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                createPolygon.setBackground(new JButton().getBackground()); //default color
                ActualState = "pan";
            }
        }

        // maximum zoom out: 3, maximum zoom in: 0.1
        // bottom will be disabled when max reached 
        // (works just for the bottons, not for scrolling)
        zoomout.setEnabled(factor > 0.1);
        zoom.setEnabled(factor < 3);
        p.setPan(factor, horizontal, vertical);
        p.repaint();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
    String ActualState = "Pan";
    boolean drawPolygonStarted = false;
    boolean drawPolylineStarted = false;
    int numberOfPoints = 0;
    Ellipse2D Point2D;
    Path2D Polygon2D;
    Path2D Polyline2D;
    
    // --------------------------------------------------
    // Constructor which initiates GIS functionality
    // --------------------------------------------------
    GIS(){
        super("GIS"); //heading

        // create new Drawing Panel
        p = new DrawingPanel();
      
        // set the zoom and x,y offset (default 1,0,0)
        p.setPan(factor, horizontal, vertical);

        // when starting the programm Pan mode is active -> HAND_CURSOR
        p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       
        // !!!! get and transform coordinates!!!
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
                    Logger.getLogger(javaapplication2.GIS.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // display x,y in the JLabels x,y
                x.setText(String.valueOf(Math.round(mouseX)));
                y.setText(String.valueOf(Math.round(mouseY)));
            }
        });

        // !!! Geometry drawing !!!
            // MouseAdapter in the DrawingPanel to draw Point, Line or Polygon
        p.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (ActualState) {
                    
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
                    
                    // do nothing if the state is none of those 3
                    default:
                        break;
                }
            }     
        });
            
        // !!!! PAN !!!!
        // MouseInput to pan
        p.addMouseListener(new MouseInputAdapter() { 
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("pressed");
                oldMouseX = e.getX();
                oldMouseY = e.getY();
                // -> will be processed in mouseReleased listener
            }
 
            @Override
            public void mouseReleased(MouseEvent e) {
                if (ActualState.equals("Pan")){
                    System.out.println("released");
                    newMouseX = e.getX();
                    newMouseY = e.getY();

                    // Pan to the position where mouse is released
                    horizontal = horizontal + newMouseX - oldMouseX;
                    vertical = vertical + newMouseY - oldMouseY;
                    p.setPan(factor, horizontal, vertical);
                    p.repaint();
                }
            }
        });
        
        // !!!! Zoom !!!!
        // MouseWheel to zoom in and out
        p.addMouseWheelListener((MouseWheelEvent e) -> {
            if ("Pan".equals(ActualState)) {
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        zoom = new javax.swing.JToggleButton();
        zoomout = new javax.swing.JToggleButton();
        pan = new javax.swing.JToggleButton();
        fullext = new javax.swing.JToggleButton();
        jToolBar2 = new javax.swing.JToolBar();
        createPolyline = new javax.swing.JToggleButton();
        createPoint = new javax.swing.JToggleButton();
        createPolygon = new javax.swing.JToggleButton();
        sltfeature = new javax.swing.JToggleButton();
        jToolBar3 = new javax.swing.JToolBar();
        addshp = new javax.swing.JToggleButton();
        Saveall = new javax.swing.JToggleButton();
        savechange = new javax.swing.JToggleButton();
        delete = new javax.swing.JToggleButton();
        jToolBar4 = new javax.swing.JToolBar();
        db = new javax.swing.JToggleButton();
        expCSV = new javax.swing.JToggleButton();
        x = new javax.swing.JTextField();
        y = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        menubar = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        save = new javax.swing.JMenuItem();
        print = new javax.swing.JMenuItem();
        property = new javax.swing.JMenuItem();
        exit = new javax.swing.JMenuItem();
        edit = new javax.swing.JMenu();
        editpoint = new javax.swing.JMenuItem();
        editline = new javax.swing.JMenuItem();
        editpolygon = new javax.swing.JMenuItem();
        layer = new javax.swing.JMenu();
        addlayer = new javax.swing.JMenuItem();
        removelayer = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setRollover(true);

        zoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/zoom-128.png"))); // NOI18N
        zoom.setText("Zoom");
        zoom.setFocusable(false);
        zoom.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        zoom.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(zoom);

        zoomout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/Zoom_out_font_awesome.svg.png"))); // NOI18N
        zoomout.setText("Zoom Out");
        zoomout.setFocusable(false);
        zoomout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        zoomout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(zoomout);

        pan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/775293_arrows_512x512.png"))); // NOI18N
        pan.setText("Pan");
        pan.setFocusable(false);
        pan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(pan);

        fullext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/thin-1523_target_audience_country_location-128.png"))); // NOI18N
        fullext.setText("Full Extent");
        fullext.setFocusable(false);
        fullext.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fullext.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(fullext);

        jToolBar2.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar2.setRollover(true);

        createPolyline.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/penline.png"))); // NOI18N
        createPolyline.setText("Line Edit");
        createPolyline.setFocusable(false);
        createPolyline.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createPolyline.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(createPolyline);

        createPoint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/penpoint.png"))); // NOI18N
        createPoint.setText("Point Edit");
        createPoint.setFocusable(false);
        createPoint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createPoint.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(createPoint);

        createPolygon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/penpoly.png"))); // NOI18N
        createPolygon.setText("Polygon Edit");
        createPolygon.setFocusable(false);
        createPolygon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createPolygon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(createPolygon);

        sltfeature.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/Slice-select-tool-icon.png"))); // NOI18N
        sltfeature.setText("Select Feature");
        sltfeature.setFocusable(false);
        sltfeature.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sltfeature.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(sltfeature);

        jToolBar3.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar3.setRollover(true);

        addshp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/add.png"))); // NOI18N
        addshp.setText("Add Layer");
        addshp.setFocusable(false);
        addshp.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addshp.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addshp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addshpMouseClicked(evt);
            }
        });
        jToolBar3.add(addshp);

        Saveall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/save-icon.png"))); // NOI18N
        Saveall.setText("Save ");
        Saveall.setFocusable(false);
        Saveall.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Saveall.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar3.add(Saveall);

        savechange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/Household-Toolbox-icon.png"))); // NOI18N
        savechange.setText("Save changes");
        savechange.setFocusable(false);
        savechange.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        savechange.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar3.add(savechange);

        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/Trash-Delete-icon.png"))); // NOI18N
        delete.setText("delete feature");
        delete.setFocusable(false);
        delete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        delete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar3.add(delete);

        jToolBar4.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar4.setRollover(true);

        db.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/database.png"))); // NOI18N
        db.setText("connect database");
        db.setFocusable(false);
        db.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        db.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar4.add(db);

        expCSV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/export.png"))); // NOI18N
        expCSV.setText("Export to CSV");
        expCSV.setFocusable(false);
        expCSV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        expCSV.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar4.add(expCSV);

        x.setText(" X:  ");
        x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xActionPerformed(evt);
            }
        });

        y.setText(" Y:");

        jTextField3.setBackground(new java.awt.Color(204, 204, 204));
        jTextField3.setText("   COORDINATE");
        jTextField3.setToolTipText("");

        file.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, 0));
        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/Opened-Folder-icon.png"))); // NOI18N
        jMenuItem1.setText("Open file");
        file.add(jMenuItem1);

        save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/save-icon.png"))); // NOI18N
        save.setText("Save ");
        file.add(save);

        print.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        print.setText("Print");
        file.add(print);

        property.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK));
        property.setText("Property");
        file.add(property);

        exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exit.setText("Exit");
        file.add(exit);

        menubar.add(file);

        edit.setText("Edit");

        editpoint.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, 0));
        editpoint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/penpoint.png"))); // NOI18N
        editpoint.setText("Edit Point");
        edit.add(editpoint);

        editline.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, 0));
        editline.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/penline.png"))); // NOI18N
        editline.setText("Edit Line");
        edit.add(editline);

        editpolygon.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, 0));
        editpolygon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seicon/penpoly.png"))); // NOI18N
        editpolygon.setText("Edit Polygon");
        edit.add(editpolygon);

        menubar.add(edit);

        layer.setText("Layer");

        addlayer.setText("Add Layer");
        layer.add(addlayer);

        removelayer.setText("Layer Remove");
        layer.add(removelayer);

        menubar.add(layer);

        jMenu1.setText("Help");
        menubar.add(jMenu1);

        setJMenuBar(menubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(x, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(y, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jToolBar3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(498, 498, 498)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addshpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addshpMouseClicked


    }//GEN-LAST:event_addshpMouseClicked

    private void xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GIS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GIS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GIS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GIS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GIS().setVisible(true);
                System.out.println("start");
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton Saveall;
    private javax.swing.JMenuItem addlayer;
    private javax.swing.JToggleButton addshp;
    private javax.swing.JToggleButton createPoint;
    private javax.swing.JToggleButton createPolygon;
    private javax.swing.JToggleButton createPolyline;
    private javax.swing.JToggleButton db;
    private javax.swing.JToggleButton delete;
    private javax.swing.JMenu edit;
    private javax.swing.JMenuItem editline;
    private javax.swing.JMenuItem editpoint;
    private javax.swing.JMenuItem editpolygon;
    private javax.swing.JMenuItem exit;
    private javax.swing.JToggleButton expCSV;
    private javax.swing.JMenu file;
    private javax.swing.JToggleButton fullext;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JMenu layer;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JToggleButton pan;
    private javax.swing.JMenuItem print;
    private javax.swing.JMenuItem property;
    private javax.swing.JMenuItem removelayer;
    private javax.swing.JMenuItem save;
    private javax.swing.JToggleButton savechange;
    private javax.swing.JToggleButton sltfeature;
    private javax.swing.JTextField x;
    private javax.swing.JTextField y;
    private javax.swing.JToggleButton zoom;
    private javax.swing.JToggleButton zoomout;
    // End of variables declaration//GEN-END:variables

   

 
    
}
