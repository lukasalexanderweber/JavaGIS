/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

/**
 *
 * @author Fuad Amin
 */


import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;


public class GeotoolsFxTest extends Application {
    Group root = new Group();
    Group map1 = new Group();
    Group map = new Group();
    Group texts = new Group();
    Scene scene;

    private double dragBaseX, dragBaseY;
    private double dragBase2X, dragBase2Y;

    public static void main(String[] args) {
        Application.launch(GeotoolsFxTest.class, args);
    }
    
    private void zoom(double d) {
        map.scaleXProperty().set(map.scaleXProperty().get() * d);
        map.scaleYProperty().set(map.scaleYProperty().get() * d);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Geotools FX Test");
        scene = new Scene(root, 300, 250, Color.LIGHTBLUE);
        Color[] colors = new Color[] { Color.YELLOW, Color.RED, Color.ORANGE, Color.VIOLET, Color.CHOCOLATE, Color.YELLOW, Color.AZURE };
        int currentColor=0;
        
        
        }
        
        
       // primaryStage.setVisible(true);
    
}