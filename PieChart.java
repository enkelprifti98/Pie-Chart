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
import java.util.Random;
import javafx.scene.shape.ArcType;

public class PieChart {

		private double x,y;
		private int n;
		private String[] events;
		private double[] probabilities;
		private double startAngle = 0;
		private double arcAngle = 0;
		private String[] labels;


	//Constructor
	public PieChart(double x, double y, int n, String[] events, double[] probabilities){
				this.x = x;
				this.y = y;
				this.n = n;
        this.events = events;
        this.probabilities = probabilities;
				this.labels = new String[n];
				//Creating label for each piece of the pie
				for(int i = 0;i<n;i++){
					this.labels[i] = events[i] + ", " + Double.toString(probabilities[i]);
				}
	}


	//Draw Function
	public void draw(GraphicsContext gc){

		Random randomNumbers = new Random();

		//Loop that draws each piece of the pie chart with angles related to the probabilities of letters
		for (int i = this.n-1; i >= 0; i--) {
				this.arcAngle = (this.probabilities[i] * 360);

				gc.setFill(Color.rgb(randomNumbers.nextInt(256), randomNumbers.nextInt(256), randomNumbers.nextInt(256)));

				gc.strokeArc(this.x-200, this.y-200, 400, 400, this.startAngle+90, this.arcAngle, ArcType.ROUND);
				gc.fillArc(this.x-200, this.y-200, 400, 400, this.startAngle+90, this.arcAngle, ArcType.ROUND);
				this.startAngle += this.arcAngle;
			}

			//Draw Labels
	 for (int i = 0; i < this.n; i++) {
			 this.arcAngle = (this.probabilities[i] * 360);

			 double xpoint = (this.x-30) + 270*(Math.sin((this.startAngle+(this.arcAngle/2))*Math.PI/180));
			 double ypoint = (this.y) - 270*(Math.cos((this.startAngle+(this.arcAngle/2))*Math.PI/180));

			 gc.strokeText(this.labels[i], xpoint, ypoint);
			 this.startAngle += this.arcAngle;

	 }
	}


}
