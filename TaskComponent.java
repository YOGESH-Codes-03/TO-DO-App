package com.yogi.todo;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

public class TaskComponent extends JPanel implements ActionListener {
	private JCheckBox checkBox;
	private JTextPane taskFeild;
	private JButton deleteButton;
	
	public JTextPane getTaskFeild() {
		return taskFeild;
	}
	
	private JPanel parentPanel;
	
	public TaskComponent(JPanel parentPanel) {
		this.parentPanel = parentPanel;
		
		taskFeild = new JTextPane();
		taskFeild.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		taskFeild.setPreferredSize(CommonConstants.TASKFIELD_SIZE);
		taskFeild.setContentType("text/html");
		taskFeild.addFocusListener(new FocusListener() {
			//indicate which task feild is currently being edited
			@Override
			public void focusGained(FocusEvent e) {
				taskFeild.setBackground(Color.WHITE);
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				taskFeild.setBackground(null);
				
			}
			
		});
		
		//checkbox
		checkBox = new JCheckBox();
		checkBox.setPreferredSize(CommonConstants.CHECKBOX_SIZE);
		checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		checkBox.addActionListener(this);
		
		//delete button 
		deleteButton = new JButton("X");
		deleteButton.setPreferredSize(CommonConstants.DELETE_BUTTON_SIZE);
		deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deleteButton.addActionListener(this);
		
		// add to this taskComponent
		add(checkBox);
		add(taskFeild);
		add(deleteButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(checkBox.isSelected()) {
			String taskText = taskFeild.getText().replaceAll("<[^>]*>","");
			
			
			// add strikethrough text
			taskFeild.setText("<html><s>" + taskText + "</s></html>");
			
		}else if(!checkBox.isSelected()) {
			String taskText = taskFeild.getText().replaceAll("<[^>]*>","");
			
			taskFeild.setText(taskText);
			
		}
		
		if(e.getActionCommand().equalsIgnoreCase("X")) {
			// delete this component from the parent panel
			parentPanel.remove(this);
			parentPanel.repaint();
			parentPanel.revalidate();
		}
		
	}
	
	

}
