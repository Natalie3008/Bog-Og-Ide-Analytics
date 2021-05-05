package UILayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import modelLayer.*;

public class TargetCategoryListCellRenderer extends JLabel implements ListCellRenderer<TargetedCategory> {

	public TargetCategoryListCellRenderer(){
		setOpaque(true);
	}
	
	@Override
    public Component getListCellRendererComponent(JList<? extends TargetedCategory> list, TargetedCategory value, int index,
        boolean isSelected, boolean cellHasFocus) {
          
        String targetedCategoryName = String.format("TITLE: "+value.getTitle()+", AGE: "+value.getMinimumAge()+"-"+value.getMaximumAge()+", GENDER: "+value.getGender());
        
        setText(targetedCategoryName);
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 18));
        
         
        if (isSelected) {
            setBackground(Color.decode("#323A3A"));
        } else {
            setBackground(Color.decode("#242A2B"));
        }
        
        return this;
    }

}
