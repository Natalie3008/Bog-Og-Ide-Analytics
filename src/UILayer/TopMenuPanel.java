package UILayer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;

public class TopMenuPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public TopMenuPanel(JPanel panelCardLayout, CardLayout cardLayout) {
		setMinimumSize(new Dimension(1920, 10));
		setBackground(Color.decode("#1A1F20"));
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{221, 148, 145, 120, 0};
		gridBagLayout.rowHeights = new int[]{34, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton analyticsButton = new JButton("VIEW ANALYTICS");
		analyticsButton.setFocusable(false);
		analyticsButton.setFocusTraversalKeysEnabled(false);
		analyticsButton.setFocusPainted(false);
		analyticsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelCardLayout, "analytics");
			}
		});
		analyticsButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		analyticsButton.setBackground(Color.WHITE);
		analyticsButton.setOpaque(false);
		analyticsButton.setForeground(Color.WHITE);
		analyticsButton.setBorder(null);
		GridBagConstraints gbc_analyticsButton = new GridBagConstraints();
		gbc_analyticsButton.fill = GridBagConstraints.BOTH;
		gbc_analyticsButton.insets = new Insets(0, 0, 0, 5);
		gbc_analyticsButton.gridx = 0;
		gbc_analyticsButton.gridy = 0;
		add(analyticsButton, gbc_analyticsButton);
		
		JButton productsButton = new JButton("PRODUCTS");
		productsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelCardLayout, "products");
			}
		});
		productsButton.setFocusable(false);
		productsButton.setFocusTraversalKeysEnabled(false);
		productsButton.setFocusPainted(false);
		productsButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		productsButton.setBackground(Color.WHITE);
		productsButton.setOpaque(false);
		productsButton.setForeground(Color.WHITE);
		productsButton.setBorder(null);
		GridBagConstraints gbc_productsButton = new GridBagConstraints();
		gbc_productsButton.fill = GridBagConstraints.BOTH;
		gbc_productsButton.insets = new Insets(0, 0, 0, 5);
		gbc_productsButton.gridx = 1;
		gbc_productsButton.gridy = 0;
		add(productsButton, gbc_productsButton);
		
		JButton employeesButton = new JButton("EMPLOYEES");
		employeesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelCardLayout, "employees");
			}
		});
		employeesButton.setFocusable(false);
		employeesButton.setFocusTraversalKeysEnabled(false);
		employeesButton.setFocusPainted(false);
		employeesButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		employeesButton.setBackground(Color.WHITE);
		employeesButton.setOpaque(false);
		employeesButton.setForeground(Color.WHITE);
		employeesButton.setBorder(null);
		GridBagConstraints gbc_employeesButton = new GridBagConstraints();
		gbc_employeesButton.fill = GridBagConstraints.BOTH;
		gbc_employeesButton.insets = new Insets(0, 0, 0, 5);
		gbc_employeesButton.gridx = 2;
		gbc_employeesButton.gridy = 0;
		add(employeesButton, gbc_employeesButton);
		
		JButton salesButton = new JButton("SALES");
		salesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelCardLayout, "sales");
				
			}
		});
		salesButton.setFocusable(false);
		salesButton.setFocusTraversalKeysEnabled(false);
		salesButton.setFocusPainted(false);
		salesButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		salesButton.setOpaque(false);
		salesButton.setForeground(Color.WHITE);
		salesButton.setBorder(null);
		salesButton.setBackground(Color.WHITE);
		GridBagConstraints gbc_salesButton = new GridBagConstraints();
		gbc_salesButton.fill = GridBagConstraints.BOTH;
		gbc_salesButton.gridx = 3;
		gbc_salesButton.gridy = 0;
		add(salesButton, gbc_salesButton);
	}

}
