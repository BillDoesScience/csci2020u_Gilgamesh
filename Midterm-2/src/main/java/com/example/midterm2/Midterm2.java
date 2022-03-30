package com.example.midterm2;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.io.IOException;

/**
 * <h1>Primary Graphics Driver</h1>
 * The Primary Graphics Driver, for this Java program
 * Generates a Menu that Opens Sub Windows which contain pre-determined
 * set of events such as an Animation, 2D Graphics of a Author's Initials and
 * About Page summary info about author and program desc.
 * <p>
 *
 * @author  Kishore Muralitharan
 * @version 1.0
 * @since   2022-03-15
 */

public class Midterm2 extends Application {

    /**
     * A List Containing an All the Root Panes for the Classes
     */
    private final Pane[] Roots = new Pane[4];

    /** Generates the Return to Main Menu Button that sets the Root of the scene back to the main menu setup.
     * @param btn - Button Object Passed to Create Back to Main Button that is used to return to the Main Root
     * @param stage - The primary Scene that is used to set the Root back to the main Menu
     */
    public void Return(Button btn, Scene stage) {
        btn.setText("Back to Main");
        btn.setPrefWidth(250);
        btn.setLayoutX(125);
        btn.setLayoutY(400);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setRoot(Roots[0]);
            }
        });
    }


    /** Creates the scene and objects for the Animation Scene and
     * Sets up the Transition Animation
     * @param primary_stage - Primary Scene passed to the Return Menu Generator
     */
    public void AnimationBtn(Scene primary_stage) {
        try {
            Pane root1 = new Pane();

            // Generate Circle Object
            Circle circle = new Circle(50,250,50);

            //Transition Setup
            TranslateTransition sllllide = new TranslateTransition();
            sllllide.setDuration(Duration.seconds(2));
            sllllide.setNode(circle);
            sllllide.setByX(400);
            sllllide.setCycleCount(50);
            sllllide.setAutoReverse(true);
            sllllide.play();

            //Return to Main Menu Button
            Button btn4 = new Button();
            Return(btn4, primary_stage);

            root1.getChildren().addAll(circle,btn4);
            Roots[1] = root1;
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    /** Generates the line objects that create the K.M Initials Graphic also
     * Generates the Initials as a Label in the Top Left Corner for
     * Grader Reference then creates the scene object
     * @param primary_stage - Primary Scene passed to the Return Menu Generator
     */
    public void Graphics(Scene primary_stage) {
        try {
            Pane root1 = new Pane();

            // Generate Lines for the First Letter
            Line line = new Line(25,25,25,125);
            Line line2 = new Line(25,75,75,25);
            Line line3 = new Line(25,75,75,125);

            // Period for Between Initials
            Circle circle = new Circle(88,125,2);

            //Generate Lines for the Second Letter
            Line line4 = new Line(100,125,100,25);
            Line line5 = new Line(100,25,125,75);
            Line line6 = new Line(125,75,150,25);
            Line line7 = new Line(150,25,150,125);

            // Label for Reference
            Label b = new Label("K.M");

            //Return to Main Menu Button
            Button btn4 = new Button();
            Return(btn4, primary_stage);

            root1.getChildren().addAll(line,line2,line3,line4,line5,line6,line7,circle,b,btn4);
            Roots[2] = root1;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /** Loads the XML File using the DOM Method and then parses the relevant information into
     * nodes and elements. Then Generates text objects and fills said objects with the information parsed from the
     * xml file located in resources. Then Creates the Stage for the About Object
     * @param primary_stage - Primary Scene passed to the Return Menu Generator
     */
    public void About(Scene primary_stage) {
        try {
            Pane root1 = new Pane();

            // Document Builder
            File file = new File("src/main/resources/com/example/midterm2/UserInfo.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();

            // Student Info
            NodeList StudentInfo = document.getElementsByTagName("student");
            Node nNode = StudentInfo.item(0);
            Element eElement = (Element) nNode;
            Text ID = new Text(25,25,"Student ID: " + eElement.getAttribute("id"));
            Text Name = new Text(25,50,"Student Name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
            Text Email = new Text(25,75, "Student Email: " + eElement.getElementsByTagName("email").item(0).getTextContent());

            //Program Desc Info
            NodeList Desc = document.getElementsByTagName("software-description");
            String Anode = Desc.item(0).getTextContent();
            Text SoftDesc = new Text(25,100,"Software Desc:\n" + Anode);

            //Return to Main Menu Button
            Button btn4 = new Button();
            Return(btn4, primary_stage);

            root1.getChildren().addAll(ID,Name,Email,SoftDesc,btn4);
            Roots[3] = root1;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates all the Roots for the 3 Sub Menu's ahead of time
     * @param primary_stage - Passes the Primary Scene to the Generator Class to be passed to the Return Menu Button
     */
    public void PreGenerate(Scene primary_stage) {
        AnimationBtn(primary_stage);
        Graphics(primary_stage);
        About(primary_stage);
    }

    /** Primary Stage that generates the main menu and creates the 3 main action buttons
     * @param primary_stage - The Main Scene that is passed and used to generate the main action menu
     * @throws IOException - IO Errors such as missing files, file errors or unsupported file formats
     */
    @Override
    public void start(Stage primary_stage) throws IOException {
        //Generate Three Main Menu Buttons
        String[] ButtonTitles = {"Animation","2D Graphics","About"};
        Button[] btns = new Button[3];
        for (int i = 0; i < ButtonTitles.length; i++) {
            btns[i] = new Button();
            btns[i].setText(ButtonTitles[i]);
            btns[i].setPrefWidth(250);
            btns[i].setLayoutX(125);
            btns[i].setLayoutY(100 + (50 * i));

        }

        Pane root = new Pane();
        root.getChildren().addAll(btns);
        Roots[0] = root;

        Scene scene = new Scene(root, 500,500);

        PreGenerate(scene);

        // Set First Button Action aka Animation Button
        btns[0].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                scene.setRoot(Roots[1]);
            }
        });
        // Set Second Button Action aka 2D Graphics Button
        btns[1].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                scene.setRoot(Roots[2]);
            }
        });
        // Set Third Button Action aka About Button
        btns[2].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                scene.setRoot(Roots[3]);
            }
        });

        primary_stage.setTitle("Midterm 2");
        primary_stage.setScene(scene);
        primary_stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}