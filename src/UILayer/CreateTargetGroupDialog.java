package UILayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTargetGroupDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the dialog.
	 */
	public CreateTargetGroupDialog() {
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 462);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.decode("#1A1F20"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel = new JLabel("CREATE TARGET CATEGORY");
			lblNewLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
			contentPanel.add(lblNewLabel, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(20, 0, 0, 0));
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{190, 213, 207, 0};
			gbl_panel.rowHeights = new int[]{20, 20, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setBackground(Color.decode("#1A1F20"));
			panel.setLayout(gbl_panel);
			{
				JLabel lblNewLabel_1 = new JLabel("AGE");
				lblNewLabel_1.setForeground(Color.WHITE);
				lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
				lblNewLabel_1.setBorder(new EmptyBorder(0, 0, 2, 20));
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 0;
				gbc_lblNewLabel_1.gridy = 0;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				textField_1 = new JTextField();
				textField_1.setBorder(new EmptyBorder(5, 5, 5, 5));
				GridBagConstraints gbc_textField_1 = new GridBagConstraints();
				gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_1.anchor = GridBagConstraints.NORTH;
				gbc_textField_1.insets = new Insets(0, 0, 5, 5);
				gbc_textField_1.gridx = 1;
				gbc_textField_1.gridy = 0;
				panel.add(textField_1, gbc_textField_1);
				textField_1.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("GENDER");
				lblNewLabel_2.setForeground(Color.WHITE);
				lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
				lblNewLabel_2.setBorder(new EmptyBorder(0, 0, 2, 20));
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_2.gridx = 0;
				gbc_lblNewLabel_2.gridy = 1;
				panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
			}
			{
				textField_2 = new JTextField();
				textField_2.setBorder(new EmptyBorder(5, 5, 5, 5));
				GridBagConstraints gbc_textField_2 = new GridBagConstraints();
				gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_2.anchor = GridBagConstraints.NORTH;
				gbc_textField_2.insets = new Insets(0, 0, 5, 5);
				gbc_textField_2.gridx = 1;
				gbc_textField_2.gridy = 1;
				panel.add(textField_2, gbc_textField_2);
				textField_2.setColumns(10);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("New label");
				lblNewLabel_3.setForeground(Color.WHITE);
				lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
				lblNewLabel_3.setBorder(new EmptyBorder(0, 0, 2, 20));
				lblNewLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
				gbc_lblNewLabel_3.gridx = 0;
				gbc_lblNewLabel_3.gridy = 2;
				panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
			}
			{
				textField = new JTextField();
				textField.setBorder(new EmptyBorder(5, 5, 5, 5));
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.insets = new Insets(0, 0, 0, 5);
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.anchor = GridBagConstraints.NORTH;
				gbc_textField.gridx = 1;
				gbc_textField.gridy = 2;
				panel.add(textField, gbc_textField);
				textField.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EmptyBorder(10, 10, 10, 10));
			buttonPane.setBackground(Color.decode("#1A1F20"));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JButton okButton = new CustomButton("ADD");
				okButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
				okButton.setBorder(new EmptyBorder(10, 20, 10, 20));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton, BorderLayout.EAST);
				getRootPane().setDefaultButton(okButton);
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton, BorderLayout.WEST);
			}
		}
	}
	protected void cancelClick() {	
		this.dispose();
	}

}
