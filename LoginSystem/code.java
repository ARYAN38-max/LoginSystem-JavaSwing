import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.IOException;
import java.io.*;

import java.lang.Thread;

public class code extends JFrame implements ActionListener
{	final int frameBackground = 0x24221e;
	JPanel mainContainer;

	final int formBackground = 0x3d3e47;	
	JPanel form;

	JPanel rightPanel;

	JTextField userName;

	JTextField userEmail;

	JPasswordField  userPassword;
	

	final int textColor = 0x666fb9;

	JButton submit;
	JButton exit;
	
	String uName;
	String uEmail;
	String uPassword;
	code()
		
	{
		Frame();
		Background();
		Form();
		formComponents();
		RightPanel();

		Visible();	
	}
	public void Frame()
	{
		this.setSize(750,750);
		this.setTitle("Login Form");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
	}
	public void Visible()
	{
		this.setVisible(true);
	}
	public void Background()
	{
		mainContainer = new JPanel();
		mainContainer.setBounds(0,0,750,750);
		mainContainer.setLayout(null);
		mainContainer.setBackground(new Color(frameBackground));
		this.add(mainContainer);
			
	}
	public void Form()
	{
		form = new JPanel();
		form.setBounds(0,50,600,600);
		form.setBackground(new Color(formBackground));
		form.setLayout(null);
		mainContainer.add(form);
	}
	public void formComponents()
	{
		UserName();
		UserEmail();
		UserPassword();	
		Submit();
		Exit();

	}
	public void UserName()
	{
		JLabel name = new JLabel("Name");
		name.setForeground(new Color(textColor));
		name.setBounds(100,50,200,50);
		form.add(name);

		userName = new JTextField();
		userName.setBounds(100,100,300,50);
		userName.setFont(new Font("Monospaced",Font.BOLD,20));
		form.add(userName);
		
	}
	public void UserEmail()
	{
		JLabel email = new JLabel();
		email.setForeground(new Color(textColor));
		email.setText("Email");
		email.setBounds(100,200,200,50);
		form.add(email);

		userEmail = new JTextField();
		userEmail.setBounds(100,250,300,50);
		userEmail.setFont(new Font("Monospaced",Font.BOLD,20));
		form.add(userEmail);
	}
	public void UserPassword()
	{
		JLabel password = new JLabel();
		password.setForeground(new Color(textColor));
		password.setText("Password");
		password.setBounds(100,350,200,50);
		form.add(password);

		userPassword = new JPasswordField();
		userPassword.setBounds(100,400,300,50);
		userPassword.setFont(new Font("Monospaced",Font.BOLD,20));
		form.add(userPassword);	
	}
	public void RightPanel()
	{
		rightPanel = new JPanel();
		rightPanel.setBounds(600,50,150,600);
		rightPanel.setBackground(Color.pink);
		mainContainer.add(rightPanel);
	}
	public void Submit()
	{
		submit = new JButton("Submit");
		submit.setForeground(Color.black);
		submit.setBackground(Color.white);
		submit.setBounds(100,500,100,30);
		submit.setFocusable(false);
		submit.addActionListener(this);
		form.add(submit);
	}
	public void Exit()
	{
		exit = new JButton("Exit");
		exit.setBounds(300,500,100,30);
		exit.setForeground(Color.black);
		exit.setBackground(Color.white);
		exit.setFocusable(false);
		exit.addActionListener(this);
		form.add(exit);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==submit)
			
		{
			//Checking if any user text field is empty
		if(userName.getText().equals("") || userEmail.getText().equals("") || userPassword.getText().equals(""))

			{
				JOptionPane.showMessageDialog(null,
						"Please Fill All the Text Field In Order To Proceed || Restarting the App",
						"UserInformation",
						JOptionPane.INFORMATION_MESSAGE);
				submit.setEnabled(false);
				Sleep();
				
				//Restarting the Application
				this.setVisible(false);
				Sleep();

				//App Restarted
				this.setVisible(true);
				submit.setEnabled(true);
				userName.setText("");
				userPassword.setText("");
				userEmail.setText("");
			}
		else		
		{
			submit.setEnabled(true);
				try
				{	
					//Storing userInformation in array
					String UserCredentials[] = {
						userName.getText().toString(),
						userEmail.getText().toString(),
						userPassword.getText().toString()
				};

				FileWriter userCredentials = new FileWriter("UserCredentials.txt");
				
				//Fetching userInformation from above array
				userCredentials.write("UserName: " + UserCredentials[0]);
				userCredentials.append("\n" + "UserEmail:  " + UserCredentials[1]);
				userCredentials.append("\n"+ "UserPassword: " + UserCredentials[2]);

				userCredentials.close();
			}
			catch(IOException a)
			{
				a.printStackTrace();
			}
			//Resetting Values of all text field after submittion of userInformation
			userName.setText("");
			userEmail.setText("");
			userPassword.setText("");
			Sleep();
			JOptionPane.showMessageDialog(null,
					"Login Done",
					"Login",
					JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		}
	}
		else if(e.getSource() == exit)
		{
			this.dispose();
		}
	}
	public void Sleep()
	{
		try
		{
			Thread.sleep(1000);
		}
		catch(Exception b)
		{
			b.printStackTrace();
		}
	}
}
