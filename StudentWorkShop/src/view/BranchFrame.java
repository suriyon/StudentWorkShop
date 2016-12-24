package view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

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

public class BranchFrame extends JInternalFrame {
	private JTextField txtBranchId;
	private JTextField txtBranchName;
	private JComboBox cmbFaculty;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnClear;
	private JButton btnClose;

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
		setBounds(100, 100, 450, 350);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Branch Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 414, 134);
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
		panel_1.setBounds(10, 156, 414, 66);
		getContentPane().add(panel_1);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String branchId = txtBranchId.getText();
				String branchName = txtBranchName.getText();
				Object item = cmbFaculty.getSelectedItem();
				String facultyId = ((ComboBoxItem)item).getKey();
				//JOptionPane.showMessageDialog(null, facultyId);
				
				Branch branch = new Branch(branchId, branchName, facultyId);
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
		btnClose.setBounds(312, 22, 89, 33);
		panel_1.add(btnClose);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(114, 22, 89, 33);
		panel_1.add(btnUpdate);
		
		btnClear = new JButton("Clear");
		btnClear.setEnabled(false);
		btnClear.setBounds(213, 22, 89, 33);
		panel_1.add(btnClear);

		
		addFacultyToComboBox();
		getBranchId();
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
