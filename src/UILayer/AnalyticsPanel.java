package UILayer;

import javax.swing.JPanel;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import modelLayer.Product;
import modelLayer.TargetedCategory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Insets;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

import controlLayer.SaleCtrl;
import controlLayer.TargetedCategoryCtrl;
import databaseLayer.SaleDB;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;

public class AnalyticsPanel extends JPanel {
	private JComboBox<TargetedCategory> comboBoxBooks;
	private JComboBox<TargetedCategory> comboBoxGames;
	private DefaultComboBoxModel<TargetedCategory> comboBoxModel;
	private SaleCtrl saleCtrl;
	private JFXChart jfxChart;
	private TargetedCategoryCtrl targetedCategoryCtrl;
	
	
	private ArrayList<Product> products = new ArrayList<>();
	private JFXPanel booksFxPanel;
	private JFXPanel gamesFxPanel;
	
	public AnalyticsPanel() throws SQLException {
		targetedCategoryCtrl = new TargetedCategoryCtrl();
		saleCtrl = new SaleCtrl();
		jfxChart = new JFXChart();
		initAndShowGUI();		
	}
	
	
	private void initAndShowGUI() {

		comboBoxModel = new DefaultComboBoxModel<TargetedCategory>();
		try {
			ArrayList<TargetedCategory> targetedCategories = targetedCategoryCtrl.getAllTargetedCategories();
			comboBoxModel.addAll(targetedCategories);
		}catch(SQLException e) {
			
		}
		
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
		booksButtonPanel.setBorder(new EmptyBorder(10, 5, 10, 5));
		booksButtonPanel.setBackground(new Color(36, 42, 43));
		Books.add(booksButtonPanel, BorderLayout.NORTH);
		
		CustomButton slowSellingBooksBtn = new CustomButton("SLOW SELLING BOOKS", "#1A1F20",18);
		slowSellingBooksBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				SwingWorker<Boolean, Void> slowSellingBooksWorker = new SwingWorker<Boolean, Void>() {
					@Override
					protected Boolean doInBackground() {
						getSlowestSellingBooks(booksFxPanel);
						return true;
					}
					@Override
					protected void done() {
					}		
				};
				slowSellingBooksWorker.execute();
			}
		});
		
		CustomButton fastSellingBooksBtn = new CustomButton("FAST SELLING BOOKS", "#1A1F20",18);
		fastSellingBooksBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				SwingWorker<Boolean, Void> fastSellingBooksWorker = new SwingWorker<Boolean, Void>() {
					@Override
					protected Boolean doInBackground() {
						getFastestSellingBooks(booksFxPanel);
						return true;
					}
					@Override
					protected void done() {
					}		
				};
				fastSellingBooksWorker.execute();
			}
		});
		booksButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		
		CustomButton mostProfitBooksBtn = new CustomButton("MOST PROFIT BOOKS", "#1A1F20",18);
		mostProfitBooksBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				SwingWorker<Boolean, Void> mostProfitBooksWorker = new SwingWorker<Boolean, Void>() {
					@Override
					protected Boolean doInBackground() {
						getMostProfitBooks(booksFxPanel);
						return true;
					}
					@Override
					protected void done() {
					}		
				};
				mostProfitBooksWorker.execute();
			}
		});
		
		booksButtonPanel.add(fastSellingBooksBtn);
		booksButtonPanel.add(slowSellingBooksBtn);
		booksButtonPanel.add(mostProfitBooksBtn);
		
		CustomButton manageTargetCategoriesButtonBooks = new CustomButton("MANAGE TARGET CATEGORIES", "#1A1F20", 18);
		manageTargetCategoriesButtonBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				SwingWorker<Boolean, Void> manageTargetedCategoryDialogWorker = new SwingWorker<Boolean, Void>() {
					@Override
					protected Boolean doInBackground() {
						manageTargetCategoryDialog();
						return true;
					}
					@Override
					protected void done() {
					}		
				};
				manageTargetedCategoryDialogWorker.execute();
			}
		});
		manageTargetCategoriesButtonBooks.setHorizontalAlignment(SwingConstants.LEFT);
		booksButtonPanel.add(manageTargetCategoriesButtonBooks);
		
		comboBoxBooks = new JComboBox<TargetedCategory>(comboBoxModel);
		comboBoxBooks.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	TargetedCategory selectedTargetedCategory = comboBoxGames.getModel().getElementAt(comboBoxGames.getSelectedIndex());
		    	int selectedCategoryID = selectedTargetedCategory.getID();
		    	SwingWorker<Boolean, Void> getTargetedCategoryAnalyticsBooksWorker = new SwingWorker<Boolean, Void>() {
					@Override
					protected Boolean doInBackground() {
				    	
				    	getTargetedCategoryAnalytics(booksFxPanel, selectedCategoryID);
						return true;
					}
					@Override
					protected void done() {
					}		
				};
				getTargetedCategoryAnalyticsBooksWorker.execute();
		    	
		    }
		});
		comboBoxBooks.setBorder(new EmptyBorder(5, 5, 5, 5));
		comboBoxBooks.setOpaque(false);
		comboBoxBooks.setForeground(Color.WHITE);
		comboBoxBooks.setFont(new Font("Segoe UI", Font.BOLD, 18));
		comboBoxBooks.setFocusable(false);
		comboBoxBooks.setFocusTraversalKeysEnabled(false);
		comboBoxBooks.setBackground(new Color(26, 31, 32));
		booksButtonPanel.add(comboBoxBooks);
		
		
		tabbedPane.setForegroundAt(0, Color.WHITE);
		tabbedPane.setBackgroundAt(0, Color.decode("#1A1F20"));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#242A2B"));
		Books.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{511, 225, 0};
		gbl_panel.rowHeights = new int[]{130, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
				
		booksFxPanel = new JFXPanel();
		GridBagConstraints gbc_booksFxPanel = new GridBagConstraints();
		gbc_booksFxPanel.fill = GridBagConstraints.VERTICAL;
		gbc_booksFxPanel.anchor = GridBagConstraints.WEST;
		gbc_booksFxPanel.insets = new Insets(0, 0, 0, 5);
		gbc_booksFxPanel.gridx = 0;
		gbc_booksFxPanel.gridy = 0;
		panel.add(booksFxPanel, gbc_booksFxPanel);
		
		
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
		fastSellingGamesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				SwingWorker<Boolean, Void> fastSellingGamesWorker = new SwingWorker<Boolean, Void>() {
					@Override
					protected Boolean doInBackground() {
						getFastestSellingGames(gamesFxPanel);
						return true;
					}
					@Override
					protected void done() {
					}		
				};
				fastSellingGamesWorker.execute();
			}
		});
		
		CustomButton slowSellingGamesBtn = new CustomButton("SLOW SELLING GAMES", "#1A1F20",18);
		slowSellingGamesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				SwingWorker<Boolean, Void> slowSellingGamesWorker = new SwingWorker<Boolean, Void>() {
					@Override
					protected Boolean doInBackground() {
						getSlowestSellingGames(gamesFxPanel);
						return true;
					}
					@Override
					protected void done() {
					}		
				};
				slowSellingGamesWorker.execute();
			}
		});
		
		CustomButton mostProfitGamesBtn = new CustomButton("MOST PROFIT GAMES", "#1A1F20",18);
		mostProfitGamesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				SwingWorker<Boolean, Void> mostProfitGamesBtn = new SwingWorker<Boolean, Void>() {
					@Override
					protected Boolean doInBackground() {
						getMostProfitGames(gamesFxPanel);
						return true;
					}
					@Override
					protected void done() {
					}		
				};
				mostProfitGamesBtn.execute();
			}
		});
		gamesButtonPanel.add(fastSellingGamesBtn);
		gamesButtonPanel.add(slowSellingGamesBtn);
		gamesButtonPanel.add(mostProfitGamesBtn);
		
		CustomButton manageTargetCategoriesButtonGames = new CustomButton("MANAGE TARGET CATEGORIES", "#1A1F20", 18);
		manageTargetCategoriesButtonGames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				SwingWorker<Boolean, Void> manageTargetedCategoryDialogWorker = new SwingWorker<Boolean, Void>() {
					@Override
					protected Boolean doInBackground() {
						manageTargetCategoryDialog();
						return true;
					}
					@Override
					protected void done() {
					}		
				};
				manageTargetedCategoryDialogWorker.execute();
			}
		});
		manageTargetCategoriesButtonGames.setHorizontalAlignment(SwingConstants.LEFT);
		gamesButtonPanel.add(manageTargetCategoriesButtonGames);
		
		comboBoxGames = new JComboBox<TargetedCategory>(comboBoxModel);
		comboBoxGames.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	TargetedCategory selectedTargetedCategory = comboBoxGames.getModel().getElementAt(comboBoxGames.getSelectedIndex());
		    	int selectedCategoryID = selectedTargetedCategory.getID();
		    	SwingWorker<Boolean, Void> getTargetedCategoryAnalyticsGamesWorker = new SwingWorker<Boolean, Void>() {
					@Override
					protected Boolean doInBackground() {
				    	
				    	getTargetedCategoryAnalytics(gamesFxPanel, selectedCategoryID);
						return true;
					}
					@Override
					protected void done() {
					}		
				};
				getTargetedCategoryAnalyticsGamesWorker.execute();
		    	
		    }
		});
		comboBoxGames.setOpaque(false);
		comboBoxGames.setForeground(Color.WHITE);
		comboBoxGames.setFont(new Font("Segoe UI", Font.BOLD, 18));
		comboBoxGames.setFocusable(false);
		comboBoxGames.setFocusTraversalKeysEnabled(false);
		comboBoxGames.setBackground(new Color(26, 31, 32));
		gamesButtonPanel.add(comboBoxGames);
		tabbedPane.setForegroundAt(1, Color.WHITE);
		tabbedPane.setBackgroundAt(1, Color.decode("#1A1F20"));
		
		gamesFxPanel = new JFXPanel();
		Games.add(gamesFxPanel,BorderLayout.CENTER);
		
		

       Platform.runLater(new Runnable() {
            @Override
            public void run() {
               
				initFX(booksFxPanel);
				initFX(gamesFxPanel);
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
		comboBoxBooks.setModel(comboBoxModel);
		comboBoxGames.setModel(comboBoxModel);

    }
    
    protected void getFastestSellingBooks(JFXPanel fxPanel) {
    		
    	try {
			List<Product> res = saleCtrl.getProductsAnalytics("Fast", "Book", 0, 0, 0, 0);
			
			Platform.runLater(new Runnable() {
	            @Override
	            public void run() {          
	            	Scene scene = jfxChart.updateChart("FASTEST SELLING BOOKS", res);
	            	fxPanel.setScene(scene);
	            }
	        });
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    }
    
    protected void getSlowestSellingBooks(JFXPanel fxPanel) {
		
    	try {
			List<Product> res = saleCtrl.getProductsAnalytics("Slow", "Book", 0, 0, 0, 0);
			
			Platform.runLater(new Runnable() {
	            @Override
	            public void run() {          
	            	Scene scene = jfxChart.updateChart("SLOWEST SELLING BOOKS", res);
	            	fxPanel.setScene(scene);
	            }
	        });
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    }
    
    protected void getFastestSellingGames(JFXPanel fxPanel) {
		
    	try {
			List<Product> res = saleCtrl.getProductsAnalytics("Fast", "Game", 0, 0, 0, 0);
			
			Platform.runLater(new Runnable() {
	            @Override
	            public void run() {          
	            	Scene scene = jfxChart.updateChart("FASTEST SELLING GAMES", res);
	            	fxPanel.setScene(scene);
	            }
	        });
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    }
    
    protected void getSlowestSellingGames(JFXPanel fxPanel) {
		
    	try {
			List<Product> res = saleCtrl.getProductsAnalytics("Slow", "Game", 0, 0, 0, 0);
			
			Platform.runLater(new Runnable() {
	            @Override
	            public void run() {          
	            	Scene scene = jfxChart.updateChart("SLOWEST SELLING GAMES", res);
	            	fxPanel.setScene(scene);
	            }
	        });
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    }
    
    protected void getMostProfitBooks(JFXPanel fxPanel) {
    	try {
			List<Product> res = saleCtrl.getProductsAnalytics("Most profit", "Books", 0, 0, 0, -1);
			
			Platform.runLater(new Runnable() {
	            @Override
	            public void run() {          
	            	Scene scene = jfxChart.updateChartMostProfit("MOST PROFIT BOOKS", res);
	            	fxPanel.setScene(scene);
	            }
	        });
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    protected void getMostProfitGames(JFXPanel fxPanel) {
    
			List<Product> res;
			try {
				res = saleCtrl.getProductsAnalytics("Most profit", "Games", 0, 0, 0, -1);
				
				Platform.runLater(new Runnable() {
		            @Override
		            public void run() {          
		            	Scene scene = jfxChart.updateChartMostProfit("MOST PROFIT GAMES", res);
		            	fxPanel.setScene(scene);	            
		            }
		        });
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    }
    
    
    protected void getTargetedCategoryAnalytics(JFXPanel fxPanel, int selectedTargetedCategory) {
        
		List<Product> res;
		try {
			res = saleCtrl.getProductsAnalytics("Most profit", "Book", 0, 0, 0, selectedTargetedCategory);
			
			Platform.runLater(new Runnable() {
	            @Override
	            public void run() {          
	            	Scene scene = jfxChart.updateChartMostProfit("SELECT TARGETED CATEGORY - MOST PROFIT", res);
	            	fxPanel.setScene(scene);	            
	            }
	        });
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
}
