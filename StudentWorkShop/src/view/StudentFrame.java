package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.BranchDAO;
import dao.FacultyDAO;
import dao.StudentDAO;
import model.Branch;
import model.Faculty;
import model.Student;
import util.ComboBoxItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class StudentFrame extends JInternalFrame {
	private JTextField txtStudentId;
	private JTextField txtStudentName;
	private JComboBox cmbBranch;
	private JComboBox cmbFaculty;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnClear;
	private JButton btnClose;
	private JTextField txtSearch;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	
	private JTable table;
	private DefaultTableModel model;

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
					StudentFrame frame = new StudentFrame();
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
	public StudentFrame() {
		setFrameIcon(new ImageIcon(StudentFrame.class.getResource("/image32/user_add.png")));
		setTitle("Student Frame");
		setClosable(true);
		setBounds(100, 100, 545, 555);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Student Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 450, 140);
		getContentPane().add(panel);
		
		JLabel lblStudentId = new JLabel("Student Id");
		lblStudentId.setBounds(21, 27, 57, 14);
		panel.add(lblStudentId);
		
		JLabel lblStudentName = new JLabel("Student Name");
		lblStudentName.setBounds(21, 76, 85, 14);
		panel.add(lblStudentName);
		
		txtStudentId = new JTextField();
		txtStudentId.setColumns(10);
		txtStudentId.setBounds(21, 45, 174, 20);
		panel.add(txtStudentId);
		
		txtStudentName = new JTextField();
		txtStudentName.setColumns(10);
		txtStudentName.setBounds(21, 101, 174, 20);
		panel.add(txtStudentName);
		
		JLabel label_2 = new JLabel("Faculty");
		label_2.setBounds(205, 27, 46, 14);
		panel.add(label_2);
		
		cmbFaculty = new JComboBox();
		cmbFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object item = cmbFaculty.getSelectedItem();
				String facultyId = ((ComboBoxItem)item).getKey();
				
				//JOptionPane.showMessageDialog(null, facultyId);
				addBranchToComboBox(facultyId);
			}
		});
		cmbFaculty.setBounds(205, 45, 222, 20);
		panel.add(cmbFaculty);
		
		JLabel lblBranch = new JLabel("Branch");
		lblBranch.setBounds(205, 83, 46, 14);
		panel.add(lblBranch);
		
		cmbBranch = new JComboBox();
		cmbBranch.setBounds(205, 101, 222, 20);
		panel.add(cmbBranch);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Command", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 150, 450, 66);
		getContentPane().add(panel_1);
		
		btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon(StudentFrame.class.getResource("/image16/add.png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String studentId = txtStudentId.getText();
				String studentName = txtStudentName.getText();
				
				Object item = cmbBranch.getSelectedItem();
				String branchId = ((ComboBoxItem)item).getKey();
				
				Student student = new Student(studentId, studentName, branchId);
				
				StudentDAO dao = new StudentDAO();
				
				boolean result = dao.insert(student);
				if(result){
					JOptionPane.showMessageDialog(null, "Insert Student Successfully.");
					addStudentToTable();
				}else{
					JOptionPane.showMessageDialog(null, "Insert Student Fail.");
				}
				
				txtStudentId.setText("");
				txtStudentName.setText("");
			}
		});
		btnAdd.setBounds(18, 22, 89, 33);
		panel_1.add(btnAdd);
		
		btnClose = new JButton("Close");
		btnClose.setIcon(new ImageIcon(StudentFrame.class.getResource("/image16/cross.png")));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(339, 22, 89, 33);
		panel_1.add(btnClose);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon(StudentFrame.class.getResource("/image16/update.png")));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String studentId = txtStudentId.getText();
				String studentName = txtStudentName.getText();
				
				Student student = new Student();
				student.setStudentId(studentId);
				student.setStudentName(studentName);
				
				StudentDAO dao = new StudentDAO();
				boolean result = dao.update(student);
				if(result){
					JOptionPane.showMessageDialog(null, "Update Student Successfully.");
					addStudentToTable();
				}else{
					JOptionPane.showMessageDialog(null, "Update Student Fail.");
				}
				btnAdd.setEnabled(true);
				btnUpdate.setEnabled(false);
				btnClear.setEnabled(false);
				txtStudentId.setText("");
				txtStudentName.setText("");
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(125, 22, 89, 33);
		panel_1.add(btnUpdate);
		
		btnClear = new JButton("Clear");
		btnClear.setIcon(new ImageIcon(StudentFrame.class.getResource("/image16/arrow_refresh.png")));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdd.setEnabled(true);
				btnUpdate.setEnabled(false);
				btnClear.setEnabled(false);
				txtStudentId.setText("");
				txtStudentName.setText("");
				cmbFaculty.setSelectedIndex(0);
				
			}
		});
		btnClear.setEnabled(false);
		btnClear.setBounds(232, 22, 89, 33);
		panel_1.add(btnClear);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Search Student", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 216, 450, 66);
		getContentPane().add(panel_2);
		
		JLabel label = new JLabel("กรอกชื่อคณะ");
		label.setBounds(21, 29, 86, 14);
		panel_2.add(label);
		
		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setBounds(98, 26, 231, 20);
		panel_2.add(txtSearch);
		
		btnSearch = new JButton("Search");
		btnSearch.setIcon(new ImageIcon(StudentFrame.class.getResource("/image16/magnifier.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String studentName = txtSearch.getText();
				addStudentToTable(studentName);
			}
		});
		btnSearch.setBounds(339, 20, 89, 33);
		panel_2.add(btnSearch);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Student Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(10, 281, 450, 234);
		getContentPane().add(panel_3);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 430, 201);
		panel_3.add(scrollPane);
		
		
		addFacultyToComboBox();
		prepareStudentTable();
		addStudentToTable();
	}
	protected void addStudentToTable(String studentName) {
		removeStudentFromTable();
		
		StudentDAO dao = new StudentDAO();
		Vector students = dao.selectByName(studentName);
		
		int row = students.size();
		for(int i=0; i<row; i++){
			model.addRow((Vector) students.get(i));
		}
	}

	private void addStudentToTable() {
		removeStudentFromTable();
		
		StudentDAO dao = new StudentDAO();
		Vector students = dao.selectAll();
		
		int row = students.size();
		for(int i=0; i<row; i++){
			model.addRow((Vector) students.get(i));
		}
	}

	private void removeStudentFromTable() {
		int row = model.getRowCount();
		for(int i=0; i<row; i++){
			model.removeRow(0);
		}
	}

	private void prepareStudentTable() {
		model = new DefaultTableModel();
		model.addColumn("Student ID");
		model.addColumn("Student Name");
		model.addColumn("Branch Name");
		
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
				int row = table.getSelectedRow();
				txtStudentId.setText(table.getValueAt(row, 0).toString());
				txtStudentName.setText(table.getValueAt(row, 1).toString());
				
				String branchName = table.getValueAt(row, 2).toString();
				
				FacultyDAO dao = new FacultyDAO();
				String facultyName = dao.getFacultyName(branchName);
				
				int count = cmbFaculty.getItemCount();
				int index = 0;
				for(int i=0; i<count; i++){
					Object item = cmbFaculty.getItemAt(i);
					if(((ComboBoxItem)item).getValue().equals(facultyName)){
						index = i;
						break;
					}
				}
				cmbFaculty.setSelectedIndex(index);
				
								
				int count1 = cmbBranch.getItemCount();
				int index1 = 0;
				for(int i=0; i<count1; i++){
					Object item = cmbBranch.getItemAt(i);
					if(((ComboBoxItem)item).getValue().equals(branchName)){
						index1 = i;
						break;
					}
				}
				
				cmbBranch.setSelectedIndex(index1);
				
				btnUpdate.setEnabled(true); 
				btnClear.setEnabled(true);
				btnAdd.setEnabled(false);
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		
		table.setFillsViewportHeight(true);
		scrollPane.add(table);
		scrollPane.setViewportView(table);
	}

	private void addFacultyToComboBox() {
		if(cmbFaculty.getItemCount() > 0){
			cmbFaculty.removeAllItems();
		}
		
		FacultyDAO dao = new FacultyDAO();
		List<Faculty> faculties = dao.select();
		
		for (Faculty faculty : faculties) {
			cmbFaculty.addItem(new ComboBoxItem(faculty.getFacultyId(),
					faculty.getFacultyName()));
		}
	}
	private void addBranchToComboBox(String facultyId) {
		if(cmbBranch.getItemCount() > 0){
			cmbBranch.removeAllItems();
		}
		
		BranchDAO dao = new BranchDAO();
		List<Branch> branches = dao.select(facultyId);
		
		for (Branch branch : branches) {
			cmbBranch.addItem(new ComboBoxItem(branch.getBranchId(),
					branch.getBranchName()));
		}
	}
}
