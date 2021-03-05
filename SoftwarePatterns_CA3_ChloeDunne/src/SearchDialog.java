import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class SearchDialog extends JDialog implements ActionListener {
	EmployeeDetails parent;
	JButton search, cancel;
	JTextField searchField;
	String searchDialog;
	
	// constructor
	public SearchDialog(EmployeeDetails parent, String searchDialog) {
		this.searchDialog = searchDialog;
		setTitle("Search by " + searchDialog);
		setModal(true);
		this.parent = parent;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane(searchPane());
		setContentPane(scrollPane);

		getRootPane().setDefaultButton(search);
		
		setSize(500, 190);
		setLocation(350, 250);
		setVisible(true);
	}// end SearchByIdDialog
	
	// initialize search container
	public Container searchPane() {
		JPanel searchPanel = new JPanel(new GridLayout(3, 1));
		JPanel textPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel searchLabel;

		searchPanel.add(new JLabel("Search by " + searchDialog));

		textPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		textPanel.add(searchLabel = new JLabel("Enter ID:"));
		searchLabel.setFont(this.parent.font1);
		textPanel.add(searchField = new JTextField(20));
		searchField.setFont(this.parent.font1);
		searchField.setDocument(new JTextFieldLimit(20));
		
		buttonPanel.add(search = new JButton("Search"));
		search.addActionListener(this);
		search.requestFocus();
		
		buttonPanel.add(cancel = new JButton("Cancel"));
		cancel.addActionListener(this);

		searchPanel.add(textPanel);
		searchPanel.add(buttonPanel);

		return searchPanel;
	}// end searchPane

	// action listener for save and cancel button
	public void actionPerformed(ActionEvent e) {
		// if option search, search for Employee
		if (e.getSource() == search) {
			// try get correct valus from text field
			if(searchDialog.equalsIgnoreCase("ID"))
				searchByID();
			else if(searchDialog.equalsIgnoreCase("Surname"))
				searchBySurname();
		
		}// end if
		// else dispose dialog
		else if (e.getSource() == cancel)
			dispose();
	}// end actionPerformed
	
	public void searchByID() {
		
		try {
			Double.parseDouble(searchField.getText());
			this.parent.searchByIdField.setText(searchField.getText());
			// search Employee by ID
			this.parent.searchEmployeeById();
			dispose();// dispose dialog
		}// end try
		catch (NumberFormatException num) {
			// display message and set colour to text field if entry is wrong
			searchField.setBackground(new Color(255, 150, 150));
			JOptionPane.showMessageDialog(null, "Wrong ID format!");
		}// end catch
		
	}
	
	public void searchBySurname() {
		this.parent.searchBySurnameField.setText(searchField.getText());
		// search Employee by surname
		this.parent.searchEmployeeBySurname();
		dispose();// dispose dialog
	
	}
}



