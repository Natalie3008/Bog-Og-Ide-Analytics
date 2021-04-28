package UILayer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Point;

public class ManageTargetCategoryDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	

	/**
	 * Create the dialog.
	 */
	public ManageTargetCategoryDialog() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.decode("#161B1C"));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EmptyBorder(10, 10, 10, 10));
			buttonPane.setBackground(Color.decode("#161B1C"));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JButton doneButton = new JButton("DONE");
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
		}
	}

}
