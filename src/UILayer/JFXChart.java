package UILayer;

import java.sql.SQLException;
import java.util.List;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import modelLayer.Product;

public class JFXChart{


	public JFXChart() {
	}
	
	public JFXChart(JFXPanel fxPanel, String chartName, List<Product> products) {
		updateChart(fxPanel, chartName, products);
	}
	
	public Scene initChart(JFXPanel fxPanel) {
        Group  root  =  new  Group();
        Scene  scene  =  new  Scene(root,100,100, javafx.scene.paint.Color.TRANSPARENT);
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        final BarChart<String,Number> barChart = new BarChart<String,Number>(xAxis,yAxis);
                
        XYChart.Series series = new XYChart.Series();
        
        series.getData().add(new XYChart.Data("Placeholder 1", 100));
        series.getData().add(new XYChart.Data("Placeholder 2", 200));
        series.getData().add(new XYChart.Data("Placeholder 3", 300));
        
        barChart.getData().add(series);

        root.getChildren().add(barChart);  
        
        return (scene);

    }
	
	public Scene updateChart(JFXPanel fxPanel, String chartName, List<Product> products) {
        Group  root  =  new  Group();
        Scene  scene  =  new  Scene(root,100,100, javafx.scene.paint.Color.TRANSPARENT);
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        final BarChart<String,Number> barChart = new BarChart<String,Number>(xAxis,yAxis);        
        
        barChart.setTitle(chartName);
        
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Placeholder 3", 300));
       /* try {
	        List<Product> res = products;
	        if(!res.isEmpty()) {
	        	int index = 0;
	        	for(Product p : res) {
	        		if(series.getData().size()<10) {
	        			String title = p.getTitle();
	        			int dateDiff = 1;
	        			series.getData().add(new XYChart.Data(title, dateDiff));	
	        		}
	        	}
	        }
        } catch (Exception e) {

        }*/
        
        barChart.getData().add(series);

        root.getChildren().add(barChart);  
        
        return (scene);

    }

}



