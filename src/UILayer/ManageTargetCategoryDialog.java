package UILayer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

import modelLayer.*;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dialog.ModalExclusionType;

public class ManageTargetCategoryDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList<Employee> list;
	
	
	// MAIN DARK COLOR: #1A1F20
	// SECONDARY DARK GREEN COLOR: #242A2B 
	// SELECTED COLOR: #323A3A
	
	
	/**
	 * Create the dialog.
	 */
	public ManageTargetCategoryDialog() {
		setResizable(false);
		setBounds(100, 100, 526, 490);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.decode("#1A1F20"));
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		Employee e1 = new Employee(5, "Bob", "There", 123, "email", "position");
		Employee e2 = new Employee(10, "Jane", "There", 123, "email", "position");
		Employee e3 = new Employee(10, "Joe", "There", 123, "email", "position");
		
		DefaultListModel<Employee> listModel = new DefaultListModel<>();
		listModel.addElement(e1);
		listModel.addElement(e2);
		listModel.addElement(e3);
		list = new JList<>(listModel);
		list.setBorder(new EmptyBorder(30, 0, 10, 0));
		list.setBackground(Color.decode("#1A1F20"));
		list.setCellRenderer(new TargetCategoryListCellRenderer());
		contentPanel.add(list, BorderLayout.CENTER);
	
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.decode("#1A1F20"));
		buttonsPanel.setBorder(null);
		contentPanel.add(buttonsPanel, BorderLayout.NORTH);
		GridBagLayout gbl_buttonsPanel = new GridBagLayout();
		gbl_buttonsPanel.columnWidths = new int[]{72, 53, 75, 0};
		gbl_buttonsPanel.rowHeights = new int[]{45, 0};
		gbl_buttonsPanel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_buttonsPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		buttonsPanel.setLayout(gbl_buttonsPanel);
					
			JButton createButton = new CustomButton("CREATE");
			createButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createTargetGroup();
				}
			});
			GridBagConstraints gbc_createButton = new GridBagConstraints();
			gbc_createButton.fill = GridBagConstraints.BOTH;
			gbc_createButton.insets = new Insets(0, 0, 0, 5);
			gbc_createButton.gridx = 0;
			gbc_createButton.gridy = 0;
			buttonsPanel.add(createButton, gbc_createButton);
			createButton.setIcon(new ImageIcon(getClass().getResource("/Assets/plus-icon.png")));
			createButton.setBorder(new EmptyBorder(10, 0, 10, 0));
				
			CustomButton editButton = new CustomButton("EDIT");
			editButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			editButton.setBorder(new EmptyBorder(10, 0, 10, 0));
			editButton.setIcon(new ImageIcon(getClass().getResource("/Assets/edit-icon.png")));
			GridBagConstraints gbc_editButton = new GridBagConstraints();
			gbc_editButton.fill = GridBagConstraints.BOTH;
			gbc_editButton.insets = new Insets(0, 0, 0, 5);
			gbc_editButton.gridx = 1;
			gbc_editButton.gridy = 0;
			buttonsPanel.add(editButton, gbc_editButton);
		
		
			CustomButton deleteButton = new CustomButton("DELETE");
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteTargetGroup();
				}
			});
			GridBagConstraints gbc_deleteButton = new GridBagConstraints();
			gbc_deleteButton.fill = GridBagConstraints.BOTH;
			gbc_deleteButton.gridx = 2;
			gbc_deleteButton.gridy = 0;
			buttonsPanel.add(deleteButton, gbc_deleteButton);
			deleteButton.setBorder(new EmptyBorder(10, 0, 10, 0));
			deleteButton.setIcon(new ImageIcon(getClass().getResource("/Assets/delete-icon.png")));

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPane.setBackground(Color.decode("#161B1C"));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new BorderLayout(0, 0));
		
			JButton doneButton = new CustomButton("DONE");
			doneButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancelClick();
				}
			});
			doneButton.setBorder(new EmptyBorder(5, 20, 5, 20));
			doneButton.setFocusable(false);
			doneButton.setFocusTraversalKeysEnabled(false);
			doneButton.setFocusPainted(false);
			doneButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
			doneButton.setForeground(Color.WHITE);
			doneButton.setBackground(Color.decode("#242A2B"));
			doneButton.setActionCommand("OK");
			buttonPane.add(doneButton, BorderLayout.EAST);
			getRootPane().setDefaultButton(doneButton);
	}
	
	protected void deleteTargetGroup() {
		
		DefaultListModel<Employee> model = (DefaultListModel<Employee>) list.getModel();
		int index = list.getSelectedIndex();
		if(index!=-1) {
			model.remove(index);
		}else {
			JOptionPane.showMessageDialog(null, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
	protected void createTargetGroup() {
		
		CreateTargetGroupDialog dialog = new CreateTargetGroupDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		
	}
	
	protected void cancelClick() {	
		this.dispose();
	}
	

}
