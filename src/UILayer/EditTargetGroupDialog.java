package UILayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlLayer.TargetedCategoryCtrl;
import modelLayer.TargetedCategory;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;

public class EditTargetGroupDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JLabel IDLabel;
	private JLabel titleLabel;
	private JLabel minimumAgeLabel;
	private JLabel maximumAgeLabel;
	private JLabel genderLabel;
	private JLabel otherLabel;
	
	private JTextField maximumAgeTextField;
	private JTextField idTextField;
	private JTextField minimumAgeTextField;
	private JTextField genderTextField;
	private JTextField otherTextField;
	private JTextField titleTextField;
	
	private TargetedCategoryCtrl targetedCategoryCtrl;

	/**
	 * Create the dialog.
	 */
	public EditTargetGroupDialog(TargetedCategory targetedCategory) {
		
		targetedCategoryCtrl = new TargetedCategoryCtrl();
		
		
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 462);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.decode("#1A1F20"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel createTargetCategoryLabel = new JLabel("EDIT TARGETED CATEGORY");
			createTargetCategoryLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
			createTargetCategoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
			createTargetCategoryLabel.setForeground(Color.WHITE);
			createTargetCategoryLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
			contentPanel.add(createTargetCategoryLabel, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(20, 0, 0, 0));
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{190, 342, 174, 0};
			gbl_panel.rowHeights = new int[]{20, 20, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setBackground(Color.decode("#1A1F20"));
			panel.setLayout(gbl_panel);
			{
				IDLabel = new JLabel("ID");
				IDLabel.setForeground(Color.WHITE);
				IDLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
				IDLabel.setBorder(new EmptyBorder(0, 0, 2, 20));
				IDLabel.setHorizontalAlignment(SwingConstants.TRAILING);
				GridBagConstraints gbc_IDLabel = new GridBagConstraints();
				gbc_IDLabel.anchor = GridBagConstraints.EAST;
				gbc_IDLabel.insets = new Insets(0, 0, 5, 5);
				gbc_IDLabel.gridx = 0;
				gbc_IDLabel.gridy = 0;
				panel.add(IDLabel, gbc_IDLabel);
			}
			{
				idTextField = new JTextField();
				idTextField.setBorder(new EmptyBorder(5, 5, 5, 5));
				GridBagConstraints gbc_idTextField = new GridBagConstraints();
				gbc_idTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_idTextField.anchor = GridBagConstraints.NORTH;
				gbc_idTextField.insets = new Insets(0, 0, 5, 5);
				gbc_idTextField.gridx = 1;
				gbc_idTextField.gridy = 0;
				panel.add(idTextField, gbc_idTextField);
				idTextField.setColumns(10);
			}
			{
				titleLabel = new JLabel("TITLE");
				titleLabel.setHorizontalAlignment(SwingConstants.TRAILING);
				titleLabel.setForeground(Color.WHITE);
				titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
				titleLabel.setBorder(new EmptyBorder(0, 0, 2, 20));
				GridBagConstraints gbc_titleLabel = new GridBagConstraints();
				gbc_titleLabel.anchor = GridBagConstraints.EAST;
				gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
				gbc_titleLabel.gridx = 0;
				gbc_titleLabel.gridy = 1;
				panel.add(titleLabel, gbc_titleLabel);
			}
			{
				titleTextField = new JTextField();
				titleTextField.setColumns(10);
				titleTextField.setBorder(new EmptyBorder(5, 5, 5, 5));
				GridBagConstraints gbc_titleTextField = new GridBagConstraints();
				gbc_titleTextField.insets = new Insets(0, 0, 5, 5);
				gbc_titleTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_titleTextField.gridx = 1;
				gbc_titleTextField.gridy = 1;
				panel.add(titleTextField, gbc_titleTextField);
			}
			{
				minimumAgeLabel = new JLabel("MINUMUM AGE");
				minimumAgeLabel.setForeground(Color.WHITE);
				minimumAgeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
				minimumAgeLabel.setBorder(new EmptyBorder(0, 0, 2, 20));
				minimumAgeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
				GridBagConstraints gbc_minumumAgeLabel = new GridBagConstraints();
				gbc_minumumAgeLabel.anchor = GridBagConstraints.EAST;
				gbc_minumumAgeLabel.insets = new Insets(0, 0, 5, 5);
				gbc_minumumAgeLabel.gridx = 0;
				gbc_minumumAgeLabel.gridy = 2;
				panel.add(minimumAgeLabel, gbc_minumumAgeLabel);
			}
			{
				minimumAgeTextField = new JTextField();
				minimumAgeTextField.setBorder(new EmptyBorder(5, 5, 5, 5));
				GridBagConstraints gbc_minumumAgeTextField = new GridBagConstraints();
				gbc_minumumAgeTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_minumumAgeTextField.anchor = GridBagConstraints.NORTH;
				gbc_minumumAgeTextField.insets = new Insets(0, 0, 5, 5);
				gbc_minumumAgeTextField.gridx = 1;
				gbc_minumumAgeTextField.gridy = 2;
				panel.add(minimumAgeTextField, gbc_minumumAgeTextField);
				minimumAgeTextField.setColumns(10);
			}
			{
				maximumAgeLabel = new JLabel("MAXIMUM AGE");
				maximumAgeLabel.setForeground(Color.WHITE);
				maximumAgeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
				maximumAgeLabel.setBorder(new EmptyBorder(0, 0, 2, 20));
				maximumAgeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
				GridBagConstraints gbc_maximumAgeLabel = new GridBagConstraints();
				gbc_maximumAgeLabel.anchor = GridBagConstraints.EAST;
				gbc_maximumAgeLabel.insets = new Insets(0, 0, 5, 5);
				gbc_maximumAgeLabel.gridx = 0;
				gbc_maximumAgeLabel.gridy = 3;
				panel.add(maximumAgeLabel, gbc_maximumAgeLabel);
			}
			{
				maximumAgeTextField = new JTextField();
				maximumAgeTextField.setBorder(new EmptyBorder(5, 5, 5, 5));
				GridBagConstraints gbc_maximumAgeTextField = new GridBagConstraints();
				gbc_maximumAgeTextField.insets = new Insets(0, 0, 5, 5);
				gbc_maximumAgeTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_maximumAgeTextField.anchor = GridBagConstraints.NORTH;
				gbc_maximumAgeTextField.gridx = 1;
				gbc_maximumAgeTextField.gridy = 3;
				panel.add(maximumAgeTextField, gbc_maximumAgeTextField);
				maximumAgeTextField.setColumns(10);
			}
			{
				genderLabel = new JLabel("GENDER");
				genderLabel.setHorizontalAlignment(SwingConstants.TRAILING);
				genderLabel.setForeground(Color.WHITE);
				genderLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
				genderLabel.setBorder(new EmptyBorder(0, 0, 2, 20));
				GridBagConstraints gbc_genderLabel = new GridBagConstraints();
				gbc_genderLabel.anchor = GridBagConstraints.EAST;
				gbc_genderLabel.insets = new Insets(0, 0, 5, 5);
				gbc_genderLabel.gridx = 0;
				gbc_genderLabel.gridy = 4;
				panel.add(genderLabel, gbc_genderLabel);
			}
			{
				genderTextField = new JTextField();
				genderTextField.setColumns(10);
				genderTextField.setBorder(new EmptyBorder(5, 5, 5, 5));
				GridBagConstraints gbc_genderTextField = new GridBagConstraints();
				gbc_genderTextField.insets = new Insets(0, 0, 5, 5);
				gbc_genderTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_genderTextField.gridx = 1;
				gbc_genderTextField.gridy = 4;
				panel.add(genderTextField, gbc_genderTextField);
			}
			{
				otherLabel = new JLabel("OTHER");
				otherLabel.setHorizontalAlignment(SwingConstants.TRAILING);
				otherLabel.setForeground(Color.WHITE);
				otherLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
				otherLabel.setBorder(new EmptyBorder(0, 0, 2, 20));
				GridBagConstraints gbc_otherLabel = new GridBagConstraints();
				gbc_otherLabel.anchor = GridBagConstraints.EAST;
				gbc_otherLabel.insets = new Insets(0, 0, 0, 5);
				gbc_otherLabel.gridx = 0;
				gbc_otherLabel.gridy = 5;
				panel.add(otherLabel, gbc_otherLabel);
			}
			{
				otherTextField = new JTextField();
				otherTextField.setColumns(10);
				otherTextField.setBorder(new EmptyBorder(5, 5, 5, 5));
				GridBagConstraints gbc_otherTextField = new GridBagConstraints();
				gbc_otherTextField.insets = new Insets(0, 0, 0, 5);
				gbc_otherTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_otherTextField.gridx = 1;
				gbc_otherTextField.gridy = 5;
				panel.add(otherTextField, gbc_otherTextField);
			}
			
			idTextField.setText(String.valueOf(targetedCategory.getID()));
			titleTextField.setText(targetedCategory.getTitle());
			minimumAgeTextField.setText(String.valueOf(targetedCategory.getMinimumAge()));
			maximumAgeTextField.setText(String.valueOf(targetedCategory.getMaximumAge()));
			genderTextField.setText(targetedCategory.getGender());
			otherTextField.setText(targetedCategory.getOther());
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EmptyBorder(10, 10, 10, 10));
			buttonPane.setBackground(Color.decode("#1A1F20"));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JButton addButton = new CustomButton("SAVE");
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveClick();
					}
				});
				
				addButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
				addButton.setBorder(new EmptyBorder(10, 20, 10, 20));
				buttonPane.add(addButton, BorderLayout.EAST);
				getRootPane().setDefaultButton(addButton);
			}
			{
				JButton cancelButton = new CustomButton("CANCEL");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelClick();
					}
				});
				cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
				cancelButton.setBorder(new EmptyBorder(10, 10, 10, 10));
				buttonPane.add(cancelButton, BorderLayout.WEST);
			}
		}
	}
	protected void cancelClick() {	
		this.dispose();
	}
	
	protected void saveClick() {	
		int ID = Integer.parseInt(idTextField.getText());
		String title = titleTextField.getText();
		int minimumAge = Integer.parseInt(minimumAgeTextField.getText());
		int maximumAge = Integer.parseInt(maximumAgeTextField.getText());
		String gender = genderTextField.getText();
		String other = otherTextField.getText();
		
		TargetedCategory newTargetedCategory = new TargetedCategory(ID, title, minimumAge, maximumAge, gender, other);
		
		try {
			targetedCategoryCtrl.updateTargetedCategory(newTargetedCategory);
			
		}catch(SQLException e) {
		}
		
		this.dispose();
	}

}
