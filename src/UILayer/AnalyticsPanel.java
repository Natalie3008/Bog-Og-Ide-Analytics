package UILayer;

import javax.swing.JPanel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.paint.Color;

import java.awt.BorderLayout;

public class AnalyticsPanel extends JPanel {

	public AnalyticsPanel() {
		initAndShowGUI();
	}
	
	private void initAndShowGUI() {

        
        final JFXPanel fxPanel = new JFXPanel();
        fxPanel.setLayout(new BorderLayout());
        setVisible(true);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
        });
    }

	 private static Scene createScene() {
	        Group  root  =  new  Group();
	        Scene  scene  =  new  Scene(root,400,400, Color.ALICEBLUE);

	        ObservableList<PieChart.Data> pieChartData =
	                FXCollections.observableArrayList(
	                new PieChart.Data("Grapefruit", 13),
	                new PieChart.Data("Oranges", 25),
	                new PieChart.Data("Plums", 10),
	                new PieChart.Data("Pears", 22),
	                new PieChart.Data("Apples", 30));
	        final PieChart chart = new PieChart(pieChartData);
	        chart.setTitle("Imported Fruits");
	        
	        root.getChildren().add(chart);
	        
	        return (scene);
	    }
	 
    private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

  

}
