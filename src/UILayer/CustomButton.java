package UILayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class CustomButton extends JButton{

	public CustomButton(String name) {
	
		setBorder(new EmptyBorder(5, 20, 5, 20));
		setFocusable(false);
		setFocusTraversalKeysEnabled(false);
		setFocusPainted(false);
		setFont(new Font("Segoe UI", Font.BOLD, 14));
		setForeground(Color.WHITE);
		setBackground(Color.decode("#242A2B"));
		setText(name);
		
	}
	
	

}
