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

	
	public Scene initChart(JFXPanel fxPanel) {
        Group  root  =  new  Group();
        Scene  scene  =  new  Scene(root,700,600, javafx.scene.paint.Color.TRANSPARENT);
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        final BarChart<String,Number> barChart = new BarChart<String,Number>(xAxis,yAxis);
                
        barChart.setMinSize(700,600);

        
        XYChart.Series series = new XYChart.Series();
        
        series.getData().add(new XYChart.Data("Placeholder 1", 100));
        series.getData().add(new XYChart.Data("Placeholder 2", 200));
        series.getData().add(new XYChart.Data("Placeholder 3", 300));
        
        barChart.getData().add(series);
        
        xAxis.setLabel("Book Title");
        yAxis.setLabel("Days in Stock");

        String cssPath = this.getClass().getResource("chartStyle.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
        root.getChildren().add(barChart);  
        
        return (scene);

    }
	
	public Scene updateChart(String chartName, List<Product> products) {
		Group  root  =  new  Group();
        Scene  scene  =  new  Scene(root,700,600, javafx.scene.paint.Color.TRANSPARENT);
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        final BarChart<String,Number> barChart = new BarChart<String,Number>(xAxis,yAxis);
        
        barChart.setTitle(chartName);
        barChart.setMinSize(700,600);
        
        xAxis.setLabel("Book Title");
        yAxis.setLabel("Days in Stock");
        
        
        XYChart.Series series = new XYChart.Series();
        try {
	        List<Product> res = products;
	        if(!res.isEmpty()) {
	        	int index = 0;
	        	for(Product p : res) {
	        		if(series.getData().size()<10) {
	        			String title = p.getTitle();
	        			long dateDiff = p.getCopies().get(index).getDaysInStock();
	        			series.getData().add(new XYChart.Data(title, dateDiff));	
	        		}
	        	}
	        }
        } catch (Exception e) {

  
        }
        
        barChart.getData().add(series);
        String cssPath = this.getClass().getResource("chartStyle.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
        root.getChildren().add(barChart);  
        
        return (scene);

    }
	
	public Scene updateChartMostProfit(String chartName, List<Product> products) {
        
		Group  root  =  new  Group();
        Scene  scene  =  new  Scene(root,700,600, javafx.scene.paint.Color.TRANSPARENT);
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        final BarChart<String,Number> barChart = new BarChart<String,Number>(xAxis,yAxis);
        
        barChart.setTitle(chartName);
        barChart.setMinSize(700,600);
        
        xAxis.setLabel("Book Title");
        yAxis.setLabel("Total profit");
        
        
        XYChart.Series series = new XYChart.Series();
        try {
	        List<Product> res = products;
	        if(!res.isEmpty()) {
	        	int index = 0;
	        	for(Product p : res) {
	        		if(series.getData().size()<10) {
	        			String title = p.getTitle();
	        			double profit = (p.getRecommendedRetailPrice()-p.getCostPrice())*p.getAmountInStock();
	        			series.getData().add(new XYChart.Data(title, profit));	
	        		}
	        	}
	        }
        } catch (Exception e) {

  
        }
        
        barChart.getData().add(series);
        String cssPath = this.getClass().getResource("chartStyle.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
        root.getChildren().add(barChart);  
        
        return (scene);

    }

}



