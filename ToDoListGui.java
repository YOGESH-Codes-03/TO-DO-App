package com.yogi.todo;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
public class ToDoListGui extends JFrame implements ActionListener{
	
	private JPanel taskPanel, taskComponentPanel;
	
	public ToDoListGui() {
		super("To Do List Application");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(CommonConstants.GUI_SIZE);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		addGuiComponents();
		
	}
	
	private void addGuiComponents() {
		JLabel bannerLabel = new JLabel("To Do List");
		bannerLabel.setFont(createFont("LEMONMILK-Light.otf", 20f));
		bannerLabel.setBounds((CommonConstants.GUI_SIZE.width - bannerLabel.getPreferredSize().width)/2,
				15,
				CommonConstants.BANNER_SIZE.width,
				CommonConstants.BANNER_SIZE.height
				);
		
		// taskpanel
		taskPanel = new JPanel();
		taskComponentPanel = new JPanel();
		taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
		taskPanel.add(taskComponentPanel);
		
		//add srcolling to the task panel
		JScrollPane scrollPane = new JScrollPane(taskPanel);
		scrollPane.setBounds(8, 70, CommonConstants.TASKPANEL_SIZE.width, CommonConstants.TASKPANEL_SIZE.height);
		scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
		scrollPane.setMaximumSize(CommonConstants.TASKPANEL_SIZE);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//changing the speed of the scroll bar
		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
		verticalScrollBar.setUnitIncrement(20);
		
		//add task button
		JButton addTaskButton = new JButton("Add Task");
		addTaskButton.setFont(createFont("LEMONMILK-Light.otf", 10f));
		addTaskButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addTaskButton.setBounds(-5, CommonConstants.GUI_SIZE.height - 88,
				CommonConstants.ADDTASK_BUTTON_SIZE.width, CommonConstants.ADDTASK_BUTTON_SIZE.height);
		addTaskButton.addActionListener(this);
		
		
		
		
		this.getContentPane().add(bannerLabel);
		this.getContentPane().add(scrollPane);
		this.getContentPane().add(addTaskButton);
		
	}
	
	private Font createFont(String resource, float size) {
		String filePath = getClass().getClassLoader().getResource(resource).getPath();	
		
		if(filePath.contains("%20")) {
			filePath = getClass().getClassLoader().getResource(resource).getPath().replaceAll("%20", " ");	
			
		}
		try {
			File customFontFile = new File(filePath);
			Font customFont = Font.createFont(Font.TRUETYPE_FONT,customFontFile).deriveFont(size);
			return customFont;
		}catch(Exception e) {
			System.out.println("Error:" + e);
		}
		return null;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equalsIgnoreCase("Add Task")) {
			// create a task component 
			TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
			taskComponentPanel.add(taskComponent);
			
			//make the previous task appear disabled
			if(taskComponentPanel.getComponentCount() > 1) {
				TaskComponent previousTask = (TaskComponent)taskComponentPanel.getComponent(
						      taskComponentPanel.getComponentCount() - 2);
				previousTask.getTaskFeild().setBackground(null);
 			}
			
			
			//make the task field request focus after creation 
			taskComponent.getTaskFeild().requestFocus();
			repaint();
			revalidate();
			
			
		}
		
	}
	

}
