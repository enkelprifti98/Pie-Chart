import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import javafx.scene.shape.ArcType;
import javafx.scene.control.*;



public class DrawChartController {

    @FXML
    private Canvas canvas;
    public Canvas getCanvas(){
      return this.canvas;
    }


    @FXML
    private TextField frequentletters;

    @FXML
    void drawPieChartButtonPressed(ActionEvent event) {

      GraphicsContext gc = canvas.getGraphicsContext2D();
      gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

      //Get user input for n letters
      String size = frequentletters.getText();
      int n = Integer.parseInt(size);

      String filepath = "text.txt";

      //Create HistogramLetters object with n and filepath parameters
      HistogramLetters test = new HistogramLetters(n,filepath);

      //Create PieChart object with parameters supplied by the HistogramLetters object
      PieChart chart = new PieChart(canvas.getWidth()/2, canvas.getHeight()/2, test.getSize(), test.getEvents(), test.getFrequencies());

      //Draw the newly created pie chart
      chart.draw(gc);

    }


}
