
package userInterfaceTestbed;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface testbed to develop and test UI ideas.</p>
 * 
 * <p> Copyright: Lynn Robert Carter Â© 2018 </p>
 * 
 * @author Shivam Singhal
 * 
 * @version 1.00		2018-02-20 The JavaFX-based GUI for the implementation of a testbed
 * 
 */

public class UserInterface {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager for
	   this application. Rather we manually control the location of each graphical element for exact
	   control of the look and feel. */
	private final double BUTTON_WIDTH = 60;

	// These are the application values required by the user interface
	private Label label_ApplicationTitle = new Label("UI Testbed");
	private Label label_Operand1 = new Label("First operand");
	private TextField text_Operand1MeasuredValue = new TextField();
	private TextField text_Operand1ErrorTerm = new TextField();
	private Button button_Go= new Button("Go");
	private Label label_errOperand1MeasuredValue = new Label("");	
	private Label label_errOperand1ErrorTerm = new Label("");	
	private TextFlow errMeasuredValue;
    private Text errMVPart1 = new Text();
    private Text errMVPart2 = new Text();
	private TextFlow errErrorTerm;
    private Text errETPart1 = new Text();
    private Text errETPart2 = new Text();

	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */
	public UserInterface(Pane theRoot) {
		
		// Label theScene with the name of the calculator, centered at the top of the pane
		setupLabelUI(label_ApplicationTitle, "Arial", 24, UserInterfaceTestbed.WINDOW_WIDTH, Pos.CENTER, 0, 10);
		
		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 18, UserInterfaceTestbed.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 40);
		
		// Establish the first text input operand field and when anything changes in operand 1 measured value,
		// measured value process all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1MeasuredValue, "Arial", 18, UserInterfaceTestbed.WINDOW_WIDTH/2 
				+ 30, Pos.BASELINE_LEFT, 10, 70, true);
		text_Operand1MeasuredValue.textProperty().addListener((observable, oldValue, newValue) 
				-> {setOperand1MeasuredValue(); });
		
		// Establish the Error Term textfield for the first operand.  If anything changes, process
		// all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1ErrorTerm, "Arial", 18, 150, Pos.BASELINE_LEFT, 
				UserInterfaceTestbed.WINDOW_WIDTH/2 + 50, 70, true);
		text_Operand1ErrorTerm.textProperty().addListener((observable, oldValue, newValue) 
				-> {setOperand1ErrorTerm(); });
		
		// Establish an error message for the first operand Measured Value, left aligned
		label_errOperand1MeasuredValue.setTextFill(Color.RED);
		label_errOperand1MeasuredValue.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand1MeasuredValue, "Arial", 14,  
						UserInterfaceTestbed.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 22, 126);
		
		// Establish an error message for the first operand Error Term, left aligned
		label_errOperand1ErrorTerm.setTextFill(Color.RED);
		label_errOperand1ErrorTerm.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand1ErrorTerm, "Arial", 14, 
						UserInterfaceTestbed.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 
						UserInterfaceTestbed.WINDOW_WIDTH/2 - 150, 126);
		
		
		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Go, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 
						UserInterfaceTestbed.WINDOW_WIDTH/2-BUTTON_WIDTH/2, 300);
		button_Go.setOnAction((event) -> { performGo(); });
		
		// Error Message for the Measured Value for operand 1
		errMVPart1.setFill(Color.BLACK);
	    errMVPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errMVPart2.setFill(Color.RED);
	    errMVPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errMeasuredValue = new TextFlow(errMVPart1, errMVPart2);
		errMeasuredValue.setMinWidth(UserInterfaceTestbed.WINDOW_WIDTH-10); 
		errMeasuredValue.setLayoutX(22);  
		errMeasuredValue.setLayoutY(100);
		
		// Error Message for the Error Term for operand 1
	    errETPart1.setFill(Color.BLACK);
	    errETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errETPart2.setFill(Color.RED);
	    errETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errErrorTerm = new TextFlow(errETPart1, errETPart2);
		// Establish an error message for the second operand just above it with, left aligned
		errErrorTerm.setMinWidth(UserInterfaceTestbed.WINDOW_WIDTH-10); 
		errErrorTerm.setLayoutX(UserInterfaceTestbed.WINDOW_WIDTH/2+63);  
		errErrorTerm.setLayoutY(100);

		
		
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_ApplicationTitle, label_Operand1, text_Operand1MeasuredValue, 
				text_Operand1ErrorTerm, label_errOperand1MeasuredValue, label_errOperand1ErrorTerm, 
				button_Go, errMeasuredValue, errErrorTerm);

	}
	
	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}
	
	
	/**********************************************************************************************

	User Interface Actions
	
	**********************************************************************************************/

	private void setOperand1MeasuredValue() {
		label_errOperand1MeasuredValue.setText("");
		errMVPart1.setText("");
		errMVPart2.setText("");
		performGo();
	}
	
	private void setOperand1ErrorTerm() {
		label_errOperand1ErrorTerm.setText("");
		errETPart1.setText("");
		errETPart2.setText("");
		performGo();
		}
	
	private void performGo() {
		String errMessage = MeasuredValueRecognizer.checkMeasureValue(text_Operand1MeasuredValue.getText());
		if (errMessage != "") {
			System.out.println(errMessage);
			label_errOperand1MeasuredValue.setText(MeasuredValueRecognizer.measuredValueErrorMessage);
			if (MeasuredValueRecognizer.measuredValueIndexofError <= -1) return;
			String input = MeasuredValueRecognizer.measuredValueInput;
			errMVPart1.setText(input.substring(0, MeasuredValueRecognizer.measuredValueIndexofError));
			errMVPart2.setText("\u21EB");
		}
		else {
			/* This is the stub for when implementing ErrorTerm recognition
			 */
			errMessage = ErrorTermRecognizer.checkErrorTerm(text_Operand1ErrorTerm.getText());
			if (errMessage != "") {
				System.out.println(errMessage);
				label_errOperand1ErrorTerm.setText(ErrorTermRecognizer.errorTermErrorMessage);
				String input = ErrorTermRecognizer.errorTermInput;
				if (ErrorTermRecognizer.errorTermIndexofError <= -1) return;
				errETPart1.setText(input.substring(0, ErrorTermRecognizer.errorTermIndexofError));
				errETPart2.setText("\u21EB");
			}
			
		}
	}
}
