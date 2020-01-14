//Name: Anushree Badarinath Kasal
//Student ID: 1001624311
//Net ID: abk4311

//This file has the code of Client GUI.
//I have used Swing components to implement the GUI of Client Window. which has the text box to take the username of client and another text box
//to take the destination client name while sending 1 to 1 message.
//It includes start and stop buttons to start and stop client.
//It has the 1 to 1 and 1 to N buttons to choose whether the message has to be delivered to single client of broadcasted to all the active clients.
//The links used in ServerWindow.java has been referred here as well to create the components.

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class ClientWindow {

	private JFrame frame;
	private JTextField textField;//username or client name
	private static JTextArea textField_1;//message field
	Client client;
	JTextArea textArea ; //client side logs
	private JTextField textField_2;//destination client name

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientWindow window = new ClientWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientWindow() {
		initialize();
	}

	public static void Log(String msg) {
		if (SwingUtilities.isEventDispatchThread()) {
			textField_1.setText(textField_1.getText() + "\n" + msg);
		} else {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					textField_1.setText(textField_1.getText() + "\n" + msg);
				}
			});
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBounds(10, 10, 40, 20);
		frame.getContentPane().setLayout(null);

		JLabel lblEnterClientName = new JLabel("Enter Client Name:");
		lblEnterClientName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterClientName.setBounds(103, 13, 169, 26);
		frame.getContentPane().add(lblEnterClientName);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(287, 17, 299, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		//to handle 1 to 1 request
		JButton btnTo = new JButton("1 to 1");
		btnTo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String destinationClient = textField_2.getText();
				client.SendToOne(destinationClient+ ":"+ textArea.getText());
			}
		});
		
		btnTo.setBounds(54, 110, 92, 25);
		frame.getContentPane().add(btnTo);

		//to handle 1 to N request
		JButton btnToN = new JButton("1 to N");
		btnToN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnToN.setBounds(185, 110, 92, 25);
		frame.getContentPane().add(btnToN);
		btnToN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				client.SendToAll(textArea.getText());
			}
		});

		JLabel lblEnterMessageHere = new JLabel("Enter Message Here:");
		lblEnterMessageHere.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEnterMessageHere.setBounds(353, 110, 258, 20);
		frame.getContentPane().add(lblEnterMessageHere);

		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnStart.setBounds(294, 715, 97, 25);
		frame.getContentPane().add(btnStart);
		//when a client is started the below action is performed
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread clThread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub

						try {
							client = new Client("localhost", 8818, textField.getText());
							client.startClient();
							// System.out.println("Client Started");
						} catch (IOException | InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				clThread.start();
			}
		});

		JButton btnStop = new JButton("Stop");
		btnStop.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnStop.setBounds(431, 715, 97, 25);
		frame.getContentPane().add(btnStop);
		//when a stop button is pressed client exits and the message is printed on the server window.
		btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		

		/*JButton btnNewButton = new JButton("Get Active Clients");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(54, 166, 223, 25);
		frame.getContentPane().add(btnNewButton);*/
		
		//To enter the clients name
		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setBounds(353, 169, 417, 194);
		frame.getContentPane().add(textArea);

		/*JList list = new JList();
		list.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new LineBorder(new Color(0, 0, 0))));
		list.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list.setBounds(54, 207, 223, 156);
		frame.getContentPane().add(list);*/

		JLabel lblLogs = new JLabel("Logs:");
		lblLogs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLogs.setBounds(52, 376, 56, 42);
		frame.getContentPane().add(lblLogs);
		
		//To display logs
		textField_1 = new JTextArea();
		textField_1.setBounds(54, 414, 716, 288);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		//To enter destination client name
		JLabel lblDestinationClientName = new JLabel("Destination Client Name:");
		lblDestinationClientName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDestinationClientName.setBounds(52, 52, 231, 26);
		frame.getContentPane().add(lblDestinationClientName);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(287, 57, 299, 22);
		frame.getContentPane().add(textField_2);
		frame.setBounds(800, 100, 842, 799);
		frame.setTitle("Client Window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
