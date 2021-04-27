package UILayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Panel;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	
	private TopMenuPanel topMenuPanel;
	private AnalyticsPanel analyticsPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setMinimumSize(new Dimension(1280, 960));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#1A1F20"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{230, 0};
		gbl_contentPane.rowHeights = new int[]{39, 47, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel logoLabel = new JLabel("");
		logoLabel.setBorder(null);
		logoLabel.setForeground(Color.WHITE);
		logoLabel.setBackground(Color.WHITE);
		logoLabel.setIcon(new ImageIcon("C:\\Users\\itskalu\\Desktop\\UCN\\2nd Semester\\2ndSemesterProject\\Bog-Og-Ide-Analytics\\assets\\Group 124.png"));
		GridBagConstraints gbc_logoLabel = new GridBagConstraints();
		gbc_logoLabel.insets = new Insets(0, 0, 5, 0);
		gbc_logoLabel.gridx = 0;
		gbc_logoLabel.gridy = 0;
		contentPane.add(logoLabel, gbc_logoLabel);
		
		
		CardLayout cardLayout = new CardLayout();
		JPanel panelCardLayout = new JPanel();
		panelCardLayout.setBorder(null);
		panelCardLayout.setLayout(cardLayout);
		panelCardLayout.setBackground(Color.decode("#1A1F20"));
		GridBagConstraints gbc_cardLayout = new GridBagConstraints();
		gbc_cardLayout.fill = GridBagConstraints.BOTH;
		gbc_cardLayout.gridx = 0;
		gbc_cardLayout.gridy = 2;
		contentPane.add(panelCardLayout, gbc_cardLayout);
		
		
		analyticsPanel = new AnalyticsPanel();
		panelCardLayout.add(analyticsPanel, "analytics");
		
		JPanel productsPanel = new JPanel();
		productsPanel.setBackground(Color.YELLOW);
		panelCardLayout.add(productsPanel, "products");
		
		JPanel employeesPanel = new JPanel();
		employeesPanel.setBackground(Color.DARK_GRAY);
		panelCardLayout.add(employeesPanel, "employees");
		
		JPanel salesPanel = new JPanel();
		panelCardLayout.add(salesPanel, "sales");
		
		GridBagConstraints gbc_topMenuPanel = new GridBagConstraints();
		gbc_topMenuPanel.fill = GridBagConstraints.VERTICAL;
		gbc_topMenuPanel.insets = new Insets(0, 0, 5, 0);
		gbc_topMenuPanel.gridx = 0;
		gbc_topMenuPanel.gridy = 1;
		topMenuPanel = new TopMenuPanel(panelCardLayout, cardLayout);
		contentPane.add(topMenuPanel, gbc_topMenuPanel);
		
	}

	
}
