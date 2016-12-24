package view;

import java.awt.EventQueue;
import java.util.List;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.BranchDAO;
import dao.FacultyDAO;
import model.Branch;
import model.Faculty;
import util.ComboBoxItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BranchFrame extends JInternalFrame {
	private JTextField txtBranchId;
	private JTextField txtBranchName;
	private JComboBox cmbFaculty;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnClear;
	private JButton btnClose;
	private JTextField txtSearch;
	private JScrollPane scrollPane;
	
	private DefaultTableModel model;
	private JTable table;
	private JButton btnSearch;

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
					BranchFrame frame = new BranchFrame();
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
	public BranchFrame() {
		setFrameIcon(new ImageIcon(BranchFrame.class.getResource("/image32/tree.png")));
		setTitle("Branch Frame");
		setClosable(true);
		setBounds(100, 100, 450, 490);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Branch Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 8, 414, 134);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblBranchId = new JLabel("Branch Id");
		lblBranchId.setBounds(21, 27, 57, 14);
		panel.add(lblBranchId);
		
		JLabel lblBranchname = new JLabel("Branch Name");
		lblBranchname.setBounds(21, 76, 85, 14);
		panel.add(lblBranchname);
		
		txtBranchId = new JTextField();
		txtBranchId.setEnabled(false);
		txtBranchId.setBounds(21, 45, 86, 20);
		panel.add(txtBranchId);
		txtBranchId.setColumns(10);
		
		txtBranchName = new JTextField();
		txtBranchName.setBounds(21, 101, 372, 20);
		panel.add(txtBranchName);
		txtBranchName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Faculty");
		lblNewLabel.setBounds(151, 27, 46, 14);
		panel.add(lblNewLabel);
		
		cmbFaculty = new JComboBox();
		cmbFaculty.setBounds(151, 45, 242, 20);
		panel.add(cmbFaculty);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Command", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 145, 414, 66);
		getContentPane().add(panel_1);
		
		btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon(BranchFrame.class.getResource("/image16/add.png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String branchId = txtBranchId.getText();
				String branchName = txtBranchName.getText();
				
				Object item = cmbFaculty.getSelectedItem();
				String facultyId = ((ComboBoxItem)item).getKey();
				//JOptionPane.showMessageDialog(null, facultyId);
				
				Branch branch = new Branch(branchId, branchName, facultyId);
				BranchDAO dao = new BranchDAO();
				
				boolean result = dao.insert(branch);
				if(result){
					JOptionPane.showMessageDialog(null, "Insert Branch Successfully.");
					getBranchId();
					addDataToTable();
					
				}else{
					JOptionPane.showMessageDialog(null, "Insert Branch Fails.");
				}
				txtBranchName.setText("");
				cmbFaculty.setSelectedIndex(0);
			}
		});
		btnAdd.setBounds(15, 22, 89, 33);
		panel_1.add(btnAdd);
		
		btnClose = new JButton("Close");
		btnClose.setIcon(new ImageIcon(BranchFrame.class.getResource("/image16/cross.png")));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(312, 22, 89, 33);
		panel_1.add(btnClose);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String branchId = txtBranchId.getText();
				String branchName = txtBranchName.getText();
				
				Object item = cmbFaculty.getSelectedItem();
				String facultyId = ((ComboBoxItem)item).getKey();
				//JOptionPane.showMessageDialog(null, facultyId);
				
				Branch branch = new Branch(branchId, branchName, facultyId);
				BranchDAO dao = new BranchDAO();
				
				boolean result = dao.update(branch);
				if(result){
					JOptionPane.showMessageDialog(null, "Update Branch Successfully.");
					getBranchId();
					addDataToTable();
					
				}else{
					JOptionPane.showMessageDialog(null, "Update Branch Fails.");
				}
				txtBranchName.setText("");
				cmbFaculty.setSelectedIndex(0);
				
				btnAdd.setEnabled(true);
				btnUpdate.setEnabled(false);
				btnClear.setEnabled(false);
			}
		});
		btnUpdate.setIcon(new ImageIcon(BranchFrame.class.getResource("/image16/update.png")));
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(114, 22, 89, 33);
		panel_1.add(btnUpdate);
		
		btnClear = new JButton("Clear");
		btnClear.setEnabled(false);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getBranchId();
				txtBranchName.setText("");
				cmbFaculty.setSelectedIndex(0);
				btnAdd.setEnabled(true);
				btnUpdate.setEnabled(false);
				btnClear.setEnabled(false);
			}
		});
		btnClear.setIcon(new ImageIcon(BranchFrame.class.getResource("/image16/arrow_refresh.png")));
		btnClear.setBounds(213, 22, 89, 33);
		panel_1.add(btnClear);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Search Faculty", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 222, 414, 66);
		getContentPane().add(panel_2);
		
		JLabel label = new JLabel("กรอกชื่อสาขา");
		label.setBounds(21, 29, 86, 14);
		panel_2.add(label);
		
		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setBounds(98, 26, 196, 20);
		panel_2.add(txtSearch);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String branchName = txtSearch.getText();				
				addDataToTable(branchName);
				
				txtSearch.setText("");
			}
		});
		btnSearch.setIcon(new ImageIcon(BranchFrame.class.getResource("/image16/magnifier.png")));
		btnSearch.setBounds(304, 20, 89, 33);
		panel_2.add(btnSearch);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Branch Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 299, 414, 151);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 394, 117);
		panel_3.add(scrollPane);
		
		model = new DefaultTableModel(null, new Object [] {
				"Branch Id", "Branch Name", "Faculty Name"
		});
		table = new JTable(){

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int row = table.getSelectedRow();
				String branchId = model.getValueAt(row, 0).toString();
				String branchName = model.getValueAt(row, 1).toString();
				String facultyName = model.getValueAt(row, 2).toString();
								
				int count = cmbFaculty.getItemCount();
				int index = 0;
				//JOptionPane.showMessageDialog(null, cmbFaculty.getSelectedItem().toString());
				for(int i=0; i<count; i++){
					if(cmbFaculty.getItemAt(i).toString().equals(facultyName)){
						index = i;
						break;
					}
				}
				cmbFaculty.setSelectedIndex(index);
				
				txtBranchId.setText(branchId);
				txtBranchName.setText(branchName);
				
				btnAdd.setEnabled(false);
				btnUpdate.setEnabled(true);
				btnClear.setEnabled(true);
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(220);
		table.getColumnModel().getColumn(2).setPreferredWidth(220);
		
		table.getTableHeader().setReorderingAllowed(false);
		
		scrollPane.setViewportView(table);

		
		addFacultyToComboBox();		
		getBranchId();
		addDataToTable();
	}

	protected void addDataToTable(String branchName) {
		removeDataFromTable();
		
		BranchDAO dao = new BranchDAO();
		Vector branches = dao.selectByName(branchName);
		
		int row = branches.size();
		for(int i=0; i<row; i++){
			model.addRow((Vector) branches.get(i));
		}
	}

	protected void addDataToTable() {
		removeDataFromTable();
		
		BranchDAO dao = new BranchDAO();
		Vector branches = dao.selectAll();
		
		int row = branches.size();
		for(int i=0; i<row; i++){
			model.addRow((Vector) branches.get(i));
		}
	}

	private void removeDataFromTable() {
		int row = table.getRowCount();
		if(row > 0){
			for(int i=0; i<row; i++){
				model.removeRow(0);
			}
		}
	}

	private void getBranchId() {
		BranchDAO dao = new BranchDAO();
		txtBranchId.setText(dao.getBranchId());
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
}
