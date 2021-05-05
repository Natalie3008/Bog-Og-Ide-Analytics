package UILayer;

import javax.swing.JPanel;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import modelLayer.Employee;
import modelLayer.TargetedCategory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Insets;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

import controlLayer.SaleCtrl;
import controlLayer.TargetedCategoryCtrl;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Dimension;

public class AnalyticsPanel extends JPanel {

	private JComboBox<TargetedCategory> comboBox;
	private DefaultComboBoxModel<TargetedCategory> comboBoxModel;
	
	private TargetedCategoryCtrl targetedCategoryCtrl;
	
	public AnalyticsPanel() {
		targetedCategoryCtrl = new TargetedCategoryCtrl();
		
		initAndShowGUI();
		
	}
	
	private void initAndShowGUI() {
		
		JPanel analyticsMenu = new JPanel();
		analyticsMenu.setBorder(new EmptyBorder(10, 10, 10, 10));
		analyticsMenu.setBackground(Color.decode("#242A2B"));
		setLayout(new BorderLayout(0, 0));
		add(analyticsMenu, BorderLayout.NORTH);
		GridBagLayout gbl_analyticsMenu = new GridBagLayout();
		gbl_analyticsMenu.columnWidths = new int[]{290, 310, 0};
		gbl_analyticsMenu.rowHeights = new int[]{45, 0};
		gbl_analyticsMenu.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_analyticsMenu.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		analyticsMenu.setLayout(gbl_analyticsMenu);
		
		JButton manageTargetCategoriesButton = new JButton("MANAGE TARGET CATEGORIES");
		manageTargetCategoriesButton.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_manageTargetCategoriesButton = new GridBagConstraints();
		gbc_manageTargetCategoriesButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_manageTargetCategoriesButton.insets = new Insets(0, 0, 0, 5);
		gbc_manageTargetCategoriesButton.gridx = 0;
		gbc_manageTargetCategoriesButton.gridy = 0;
		analyticsMenu.add(manageTargetCategoriesButton, gbc_manageTargetCategoriesButton);
		manageTargetCategoriesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageTargetCategoryDialog();
				
			}
		});
		manageTargetCategoriesButton.setBackground(Color.BLUE);
		manageTargetCategoriesButton.setForeground(Color.WHITE);
		manageTargetCategoriesButton.setBorder(new EmptyBorder(10, 10, 10, 10));
		manageTargetCategoriesButton.setBorderPainted(false);
		manageTargetCategoriesButton.setFocusable(false);
		manageTargetCategoriesButton.setOpaque(false);
		manageTargetCategoriesButton.setFocusPainted(false);
		manageTargetCategoriesButton.setFocusTraversalKeysEnabled(false);
		manageTargetCategoriesButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		
		comboBoxModel = new DefaultComboBoxModel<TargetedCategory>();
		try {
			ArrayList<TargetedCategory> targetedCategories = targetedCategoryCtrl.getAllTargetedCategories();
			comboBoxModel.addAll(targetedCategories);
		}catch(SQLException e) {
			
		}
		
		comboBox = new JComboBox<TargetedCategory>(comboBoxModel);
				
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		analyticsMenu.add(comboBox, gbc_comboBox);
		comboBox.setBackground(Color.decode("#242A2B"));
		comboBox.setForeground(Color.WHITE);
		comboBox.setFont(new Font("Segoe UI", Font.BOLD, 18));
		comboBox.setName("");
		comboBox.setToolTipText("");
		comboBox.setOpaque(false);
		comboBox.setFocusable(false);
		comboBox.setFocusTraversalKeysEnabled(false);
		comboBox.setBorder(new EmptyBorder(5, 5, 5, 0));
		
		final JFXPanel fxPanel = new JFXPanel();
        fxPanel.setLayout(new BorderLayout());
        fxPanel.setVisible(true);
		
		JPanel analyticsPanel = new JPanel();
		analyticsPanel.setBackground(Color.decode("#242A2B"));
		add(analyticsPanel, BorderLayout.CENTER);
		analyticsPanel.setLayout(new BorderLayout(0, 0));
		analyticsPanel.add(fxPanel);
		
		JSeparator separator = new JSeparator();
		separator.setBorder(new EmptyBorder(0, 30, 0, 30));
		separator.setPreferredSize(new Dimension(0, 3));
		analyticsPanel.add(separator, BorderLayout.NORTH);
        
        

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
        });
    }

	 private Scene createScene() {
	        Group  root  =  new  Group();
	        Scene  scene  =  new  Scene(root,100,100, javafx.scene.paint.Color.TRANSPARENT);

	        final NumberAxis xAxis = new NumberAxis();
	        final NumberAxis yAxis = new NumberAxis();
	        xAxis.setLabel("Number of Month");
	        //creating the chart
	        final LineChart<Number,Number> lineChart = 
	                new LineChart<Number,Number>(xAxis,yAxis);
	                
	        lineChart.setTitle("Stock Monitoring, 2010");
	        //defining a series
	        XYChart.Series series = new XYChart.Series();
	        series.setName("My portfolio");
	        //populating the series with data
	        series.getData().add(new XYChart.Data(1, 23));
	        series.getData().add(new XYChart.Data(2, 14));
	        series.getData().add(new XYChart.Data(3, 15));
	        series.getData().add(new XYChart.Data(4, 24));
	        series.getData().add(new XYChart.Data(5, 34));
	        series.getData().add(new XYChart.Data(6, 36));
	        series.getData().add(new XYChart.Data(7, 22));
	        series.getData().add(new XYChart.Data(8, 45));
	        series.getData().add(new XYChart.Data(9, 43));
	        series.getData().add(new XYChart.Data(10, 17));
	        series.getData().add(new XYChart.Data(11, 29));
	        series.getData().add(new XYChart.Data(12, 25));
	        
	        lineChart.getData().add(series);

	        root.getChildren().add(lineChart);
	        
	        return (scene);
	    }
	 
    private void initFX(JFXPanel fxPanel) {
        // This method is invoked on JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    
    protected void manageTargetCategoryDialog()
    {
    	ManageTargetCategoryDialog dialog = new ManageTargetCategoryDialog();
    	
    	dialog.addWindowListener(new WindowAdapter() {
    		@Override
    	    public void windowClosed(WindowEvent e) {
    	        refreshComboBoxList();
    	    }
    	});
    	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
    	
    }
  
    
    protected void refreshComboBoxList() {
    	comboBoxModel = new DefaultComboBoxModel<TargetedCategory>();
    	try {
			ArrayList<TargetedCategory> targetedCategories = targetedCategoryCtrl.getAllTargetedCategories();
			comboBoxModel.addAll(targetedCategories);
		}catch(SQLException e) {
			
		}
		comboBox.setModel(comboBoxModel);

    }

}
