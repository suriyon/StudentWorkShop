package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import dao.FacultyDAO;
import model.Faculty;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FacultyFrame extends JInternalFrame {
	private JTextField txtFacultyId;
	private JTextField txtFacultyName;
	private JButton btnClose;
	private JButton btnAdd;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	
	private JTable table;
	private DefaultTableModel model;
	private JButton btnUpdate;
	private JButton btnClear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacultyFrame frame = new FacultyFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FacultyFrame() {
		setTitle("Faculty Frame");
		setClosable(true);
		setBounds(100, 100, 470, 448);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Faculty Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 434, 104);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Faculty Id");
		lblNewLabel.setBounds(37, 30, 71, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Faculty Name");
		lblNewLabel_1.setBounds(37, 67, 71, 14);
		panel.add(lblNewLabel_1);
		
		txtFacultyId = new JTextField();
		txtFacultyId.setEnabled(false);
		txtFacultyId.setBounds(118, 27, 86, 20);
		panel.add(txtFacultyId);
		txtFacultyId.setColumns(10);
		
		txtFacultyName = new JTextField();
		txtFacultyName.setBounds(118, 64, 268, 20);
		panel.add(txtFacultyName);
		txtFacultyName.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Command", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 126, 434, 66);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon(FacultyFrame.class.getResource("/image16/add.png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String facultyId = txtFacultyId.getText();
				String facultyName = txtFacultyName.getText();
				
				Faculty faculty = new Faculty(facultyId, facultyName);
				
				FacultyDAO dao = new FacultyDAO();
				boolean result = dao.insert(faculty);
				
				if(result){
					JOptionPane.showMessageDialog(null, "Insert Successfull.");
					getFacultyId();
					addDataToTable();
				}else{
					JOptionPane.showMessageDialog(null, "Insert Fail.");
				}
				txtFacultyName.setText("");
			}
		});
		btnAdd.setBounds(15, 22, 89, 33);
		panel_1.add(btnAdd);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(FacultyFrame.class.getResource("/image16/cancel.png")));
		btnClose.setBounds(327, 22, 89, 33);
		panel_1.add(btnClose);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String facultyId = txtFacultyId.getText();
				String facultyName = txtFacultyName.getText();
				
				Faculty faculty = new Faculty(facultyId, facultyName);
				
				FacultyDAO dao = new FacultyDAO();
				boolean result = dao.update(faculty);
				
				if(result){
					JOptionPane.showMessageDialog(null, "Update Successfull.");
					getFacultyId();
					addDataToTable();					
				}else{
					JOptionPane.showMessageDialog(null, "Update Fail.");
				}
				txtFacultyName.setText("");
				btnAdd.setEnabled(true);
				btnUpdate.setEnabled(false);
				btnClear.setEnabled(false);
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(119, 22, 89, 33);
		panel_1.add(btnUpdate);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFacultyId();
				txtFacultyName.setText("");
				btnAdd.setEnabled(true);
				btnClear.setEnabled(false);
				btnUpdate.setEnabled(false);
			}
		});
		btnClear.setEnabled(false);
		btnClear.setBounds(223, 22, 89, 33);
		panel_1.add(btnClear);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Faculty Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 218, 434, 190);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 414, 157);
		panel_2.add(scrollPane);

		model = new DefaultTableModel(null, new Object[] {
			"Faculty Id", "Faculty Name"	
		});
		table = new JTable(model){

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int rowSelected = table.getSelectedRow();
				txtFacultyId.setText(table.getValueAt(rowSelected, 0).toString());
				txtFacultyName.setText(table.getValueAt(rowSelected, 1).toString());
				
				btnUpdate.setEnabled(true);
				btnClear.setEnabled(true);
				btnAdd.setEnabled(false);
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);		
		
		table.getTableHeader().setReorderingAllowed(false);//fix column header
		
		scrollPane.add(table);
		scrollPane.setViewportView(table);
		
		getFacultyId();
		addDataToTable();
	}

	private void addDataToTable() {
		removeDataFromTable();
				
		FacultyDAO dao = new FacultyDAO();
		Vector faculties = dao.selectAll();
		int row = faculties.size();
		for(int i=0; i<row; i++){
			model.addRow((Vector) faculties.get(i));
		}
	}

	private void removeDataFromTable() {
		int row = model.getRowCount();
		if(row > 0){
			for(int i=0; i<row; i++){
				model.removeRow(0);
			}
		}
	}

	private void getFacultyId() {
		FacultyDAO dao = new FacultyDAO();
		txtFacultyId.setText(dao.getFacultyId());
	}
}
