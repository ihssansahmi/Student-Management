package user;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controller.BaseD;
import dao.Etudiant;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Manage {

	BaseD baseD = new BaseD();
	Connection connection = baseD.connection();
	
	private JFrame frame;
	private JTable tbListView;
	private JTextField flNom;
	private JTextField flPrenom;
	private JTextField flFiliere;
	private JLabel lblPrenom;
	private JLabel lblNom;
	private JLabel lblFiliere;
	private JButton btnUpdate;
	private DefaultTableModel tbModel;
	private JLabel lblId;
	private JTextField flId;
	private JButton btnInsert;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manage window = new Manage();
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
	public Manage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initComponent();
		createEvent();
		selectData();
	}
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	//Components
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	public void initComponent() {
		frame = new JFrame();

		frame.setBounds(100, 100, 642, 376);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(254, 30, 362, 259);
		frame.getContentPane().add(scrollPane);
		
		tbListView = new JTable();

		tbListView.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nom", "Prenom", "Filiere"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(tbListView);
		
		lblNom = new JLabel("Nom");
		lblNom.setBounds(31, 97, 46, 14);
		frame.getContentPane().add(lblNom);
		
		lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(31, 139, 46, 14);
		frame.getContentPane().add(lblPrenom);
		
		lblFiliere = new JLabel("Filiere");
		lblFiliere.setBounds(31, 185, 46, 14);
		frame.getContentPane().add(lblFiliere);
		
		flNom = new JTextField();
		flNom.setBounds(125, 94, 86, 20);
		frame.getContentPane().add(flNom);
		flNom.setColumns(10);
		
		flPrenom = new JTextField();
		flPrenom.setBounds(125, 136, 86, 20);
		frame.getContentPane().add(flPrenom);
		flPrenom.setColumns(10);
		
		flFiliere = new JTextField();
		flFiliere.setBounds(125, 182, 86, 20);
		frame.getContentPane().add(flFiliere);
		flFiliere.setColumns(10);
		
		btnInsert = new JButton("Insert");

		btnInsert.setBounds(10, 264, 89, 23);
		frame.getContentPane().add(btnInsert);
		
		btnUpdate = new JButton("Update");

		btnUpdate.setBounds(143, 264, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		lblId = new JLabel("Id");
		lblId.setBounds(31, 61, 46, 14);
		frame.getContentPane().add(lblId);
		
		flId = new JTextField();
		flId.setEditable(false);
		flId.setBounds(125, 58, 86, 20);
		frame.getContentPane().add(flId);
		flId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Click on the panel to empty the inputs");
		lblNewLabel.setBounds(31, 11, 201, 14);
		frame.getContentPane().add(lblNewLabel);
	}
	
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	//Events
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	public void createEvent() {
		//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		//Insert
		//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flFiliere.getText().equals("") || flNom.getText().equals("") || flPrenom.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Can you fill the form");
				}else {
					String nomString = flNom.getText();
					String prenomString = flPrenom.getText();
					String filiereString = flFiliere.getText();
					
					Etudiant etudiant = new Etudiant(nomString,prenomString,filiereString);
					

					baseD.insert(connection,etudiant);
					

					JOptionPane.showMessageDialog(null, "the data inserted successfully");
					
					flId.setText("");
					flNom.setText("");
					flPrenom.setText("");
					flFiliere.setText("");
					
					tbModel.setRowCount(0);
					selectData();
				}
			}
		});
		//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		//Update
		//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flFiliere.getText().equals("") || flNom.getText().equals("") || flPrenom.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "You have to select a row");
				}else {
					String idString = flId.getText();
					
					String nomString = flNom.getText();
					String prenomString = flPrenom.getText();
					String filiereString = flFiliere.getText();
					Etudiant etudiant = new Etudiant(Integer.parseInt(idString), nomString,prenomString,filiereString);
					
					baseD.update(connection,etudiant);
					
					flId.setText("");
					flNom.setText("");
					flPrenom.setText("");
					flFiliere.setText("");
					
					JOptionPane.showMessageDialog(null, "the data updated successfully");
					
					tbModel.setRowCount(0);
					selectData();
				}
			}
		});
		//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		//Select in table
		//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		tbListView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tbListView.getSelectedRow();
				if(selectedRow >=  0) {
				String IdColumn = (String) tbListView.getValueAt(selectedRow, 0);
				String nomColumn = (String) tbListView.getValueAt(selectedRow, 1);
				String prenomColumn = (String) tbListView.getValueAt(selectedRow, 2);
				String filiereColumn = (String) tbListView.getValueAt(selectedRow, 3);
				
				flId.setText(IdColumn);
				flNom.setText(nomColumn);
				flPrenom.setText(prenomColumn);
				flFiliere.setText(filiereColumn);
			}
			}
		});
		//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		//Empty the fields
		//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		frame.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flId.setText("");
				flNom.setText("");
				flPrenom.setText("");
				flFiliere.setText("");
			}
		});
	}

	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	//fill the table
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	public void selectData() {
		tbModel = (DefaultTableModel)tbListView.getModel();
		ResultSet rs;
		rs=baseD.select(connection);
		try {
			while(rs.next()) {
					String idString = String.valueOf(rs.getInt("id"));
					String nomString = rs.getString("nom");
					String prenomString = rs.getString("prenom");
					String filierString = rs.getString("filiere");
					
					String data[] = {idString,nomString,prenomString,filierString};
					tbModel.addRow(data);
					
			};
		} catch (SQLException e) {
			System.out.println("Error on inserting");
			e.printStackTrace();
		}
	}
}
