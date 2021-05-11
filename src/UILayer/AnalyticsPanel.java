package UILayer;

import javax.swing.JPanel;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import modelLayer.Employee;
import modelLayer.OrderLine;
import modelLayer.Product;
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
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JToggleButton;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class AnalyticsPanel extends JPanel {

	private JComboBox<TargetedCategory> comboBox;
	private DefaultComboBoxModel<TargetedCategory> comboBoxModel;
	private SaleCtrl saleCtrl;
	private JFXChart jfxChart;
	private TargetedCategoryCtrl targetedCategoryCtrl;
	
	
	private ArrayList<Product> products = new ArrayList<>();
	private JFXPanel booksFxPanel;
	private JFXPanel gamesFxPanel;
	private JFXPanel customerFxPanel;
	
	public AnalyticsPanel() throws SQLException {
		targetedCategoryCtrl = new TargetedCategoryCtrl();
		saleCtrl = new SaleCtrl();
		jfxChart = new JFXChart();
		initAndShowGUI();	
	}
	
	private void initAndShowGUI() {
		setLayout(new BorderLayout(0, 0));
	
		
		JPanel analyticsPanel = new JPanel();
		analyticsPanel.setBackground(Color.decode("#242A2B"));
		add(analyticsPanel, BorderLayout.CENTER);
		analyticsPanel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
		tabbedPane.setFocusTraversalKeysEnabled(false);
		tabbedPane.setFocusable(false);
		tabbedPane.setBorder(null);
		tabbedPane.setBackground(Color.decode("#242A2B"));
		tabbedPane.setForeground(Color.decode("#242A2B"));
		analyticsPanel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel Books = new JPanel();
		Books.setBorder(null);
		Books.setBackground(Color.decode("#242A2B"));
		tabbedPane.addTab("BOOKS", new ImageIcon(AnalyticsPanel.class.getResource("/Assets/books-white-icon.png")), Books, null);
		Books.setLayout(new BorderLayout(0, 0));
		
		JPanel booksButtonPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) booksButtonPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		booksButtonPanel.setBorder(new EmptyBorder(10, 5, 10, 5));
		booksButtonPanel.setBackground(new Color(36, 42, 43));
		Books.add(booksButtonPanel, BorderLayout.NORTH);
		
		CustomButton fastSellingBooksBtn = new CustomButton("FAST SELLING BOOKS", "#1A1F20",18);
		fastSellingBooksBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				getFastestSellingBooks(booksFxPanel);
			}
		});
		booksButtonPanel.add(fastSellingBooksBtn);
		
		CustomButton slowSellingBooksBtn = new CustomButton("SLOW SELLING BOOKS", "#1A1F20",18);
		booksButtonPanel.add(slowSellingBooksBtn);
		tabbedPane.setForegroundAt(0, Color.WHITE);
		tabbedPane.setBackgroundAt(0, Color.decode("#1A1F20"));
		
		booksFxPanel = new JFXPanel();
		Books.add(booksFxPanel,BorderLayout.CENTER);
		
		
		JPanel Games = new JPanel();
		Games.setBackground(Color.decode("#242A2B"));
		tabbedPane.addTab("GAMES", new ImageIcon(AnalyticsPanel.class.getResource("/Assets/gamepad-white-icon.png")), Games, null);
		Games.setLayout(new BorderLayout(0, 0));
		
		JPanel gamesButtonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) gamesButtonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		gamesButtonPanel.setBorder(new EmptyBorder(10, 5, 10, 5));
		gamesButtonPanel.setBackground(new Color(36, 42, 43));
		Games.add(gamesButtonPanel, BorderLayout.NORTH);
		
		CustomButton fastSellingGamesBtn = new CustomButton("FAST SELLING GAMES", "#1A1F20",18);
		gamesButtonPanel.add(fastSellingGamesBtn);
		
		CustomButton slowSellingGamesBtn = new CustomButton("SLOW SELLING GAMES", "#1A1F20",18);
		gamesButtonPanel.add(slowSellingGamesBtn);
		tabbedPane.setForegroundAt(1, Color.WHITE);
		tabbedPane.setBackgroundAt(1, Color.decode("#1A1F20"));
		
		gamesFxPanel = new JFXPanel();
		Games.add(gamesFxPanel,BorderLayout.CENTER);
		
		
		
		JPanel Customers = new JPanel();
		Customers.setBackground(Color.decode("#242A2B"));
		Customers.setFont(new Font("Segoe UI", Font.BOLD, 14));
		Customers.setFocusable(false);
		Customers.setFocusTraversalKeysEnabled(false);
		tabbedPane.addTab("CUSTOMERS", new ImageIcon(AnalyticsPanel.class.getResource("/Assets/people-white-icon.png")), Customers, null);
		Customers.setLayout(new BorderLayout(0, 0));
		

		JPanel analyticsMenu = new JPanel();
		Customers.add(analyticsMenu,BorderLayout.NORTH);
		analyticsMenu.setBorder(new EmptyBorder(10, 10, 10, 10));
		analyticsMenu.setBackground(Color.decode("#242A2B"));
		analyticsMenu.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton manageTargetCategoriesButton = new CustomButton("MANAGE TARGET CATEGORIES","#1A1F20",18);
		analyticsMenu.add(manageTargetCategoriesButton);
		manageTargetCategoriesButton.setHorizontalAlignment(SwingConstants.LEFT);
		manageTargetCategoriesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageTargetCategoryDialog();
				
			}
		});
		
		comboBoxModel = new DefaultComboBoxModel<TargetedCategory>();
		try {
			ArrayList<TargetedCategory> targetedCategories = targetedCategoryCtrl.getAllTargetedCategories();
			comboBoxModel.addAll(targetedCategories);
		}catch(SQLException e) {
			
		}
		
		comboBox = new JComboBox<TargetedCategory>(comboBoxModel);
		analyticsMenu.add(comboBox);
		comboBox.setBackground(Color.decode("#1A1F20"));
		comboBox.setForeground(Color.WHITE);
		comboBox.setFont(new Font("Segoe UI", Font.BOLD, 18));
		comboBox.setOpaque(false);
		comboBox.setFocusable(false);
		comboBox.setFocusTraversalKeysEnabled(false);
		
		customerFxPanel = new JFXPanel();
		Customers.add(customerFxPanel,BorderLayout.CENTER);
		
		tabbedPane.setForegroundAt(2, Color.WHITE);
		tabbedPane.setBackgroundAt(2, Color.decode("#1A1F20"));
		

       Platform.runLater(new Runnable() {
            @Override
            public void run() {
               
				initFX(booksFxPanel);
				initFX(gamesFxPanel);
				initFX(customerFxPanel);

            }
        });
    }
	 
	private void initFX(JFXPanel fxPanel) {
        // This method is invoked on JavaFX thread
        Scene scene = jfxChart.initChart(fxPanel);
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
    
    protected void getFastestSellingBooks(JFXPanel fxPanel) {
    		
    	/*try {
			//List<Product> res = saleCtrl.getProductsAnalytics("Fast", "Book", 0, 0, 0);
			initChart(fxPanel,"FASTEST SELLING BOOKS", res);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
    	List<Product> res = new ArrayList<Product>();
    	
    	Platform.runLater(new Runnable() {
            @Override
            public void run() {          
            	Scene scene = jfxChart.updateChart(fxPanel,"FASTEST SELLING BOOKS", res);
            	fxPanel.setScene(scene);
            }
        });
    	
    }

}
