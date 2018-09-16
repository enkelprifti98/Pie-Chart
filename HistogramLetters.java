import java.util.*;
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
import java.io.*;

public class HistogramLetters {

	private String text;
	private int size;
	private String filepath;

	private String[] events;
	private double[] probabilities;

	//Constructor
	public HistogramLetters(int n, String filepath){
		this.size = n+1;
		this.filepath = filepath;
		ReadFromFile();
		CreateData();

	}

	//Get all the letters
	public String[] getEvents(){
		return this.events;
	}

	//Get all the frequencies of each letter
	public double[] getFrequencies(){
		return this.probabilities;
	}

	//Get the amount of letters
	public int getSize(){
		return this.size;
	}

	//Function that takes a text file and reads it into a string
	public void ReadFromFile(){

			try{
				File file = new File(this.filepath);

  			BufferedReader br = new BufferedReader(new FileReader(file));

				this.text = "";
				String line = br.readLine();
  			while (line != null){
    			this.text += " " + line;
					line = br.readLine();
				}

			}
			catch (Exception e) {
				System.out.println("you cant do that");
			}
  }


	//
	public void CreateData(){

		//Convert the text characters to lower case
		String lctext = this.text.toLowerCase();


		//Make a map of all the characters you want to track.
		String indexes = "abcdefghijklmnopqrstuvwxyz";

		//Initialize an array to the size of the possible matches.
		int[] count = new int[indexes.length()];

		//Loop through the sentence looking for matches.
		for (int i = 0; i < lctext.length(); i++) {
    	//This will get the index in the array, if it's a character we are tracking
    	int index = indexes.indexOf(lctext.charAt(i));

    	//If it's not a character we are tracking, indexOf returns -1, so skip those.
    	if (index < 0)
        	continue;

    	count[index]++;
		}


		//Creating arraylists for all the letters and their respective frequencies
		ArrayList<Character> letters = new ArrayList<Character>();
		ArrayList<Integer> amount = new ArrayList<Integer>();

		double totalfrequencies = 0;

		for (int i = 0; i < count.length; i++) {
    	if (count[i] < 1)
        	continue;

			totalfrequencies += count[i];
			letters.add(indexes.charAt(i));
			amount.add(count[i]);

		}


		//Sort the data in decreasing order
		ArrayList<Character> sortedletters = new ArrayList<Character>();
		ArrayList<Integer> sortedamount = new ArrayList<Integer>();
		for(int j = amount.size();j > 0;j--){
			double maxamount = 0;
			int maxindex = 0;
			for(int i = 0;i<amount.size();i++){
				if(maxamount < amount.get(i)){
					maxamount = amount.get(i);
					maxindex = i;
				}
			}
			sortedletters.add(letters.get(maxindex));
			sortedamount.add(amount.get(maxindex));
			letters.remove(maxindex);
			amount.remove(maxindex);
		}


		//Create n most frequent events and probabilities to be passed to the DrawChart class.
		double sum = 0;
		this.events = new String[this.size];
		this.probabilities = new double[this.size];
		for(int i = 0; i < this.size-1;i++){
			this.events[i] = Character.toString(sortedletters.get(i));  //Character.toString(c);
			this.probabilities[i] = (double)Math.round((sortedamount.get(i)/totalfrequencies) * 10000d) / 10000d;
			sum += this.probabilities[i];
		}
		this.events[this.size-1] = "Others";
		this.probabilities[this.size-1] = (double)Math.round((1-sum) * 10000d) / 10000d;


	}


}
