package view;

import java.awt.EventQueue;

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
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class StudentReportFrame extends JInternalFrame {
	private JButton btnPrint;
	private JComboBox cmbCondition;
	private JComboBox cmbPrint;

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
					StudentReportFrame frame = new StudentReportFrame();
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
	public StudentReportFrame() {
		setTitle("Student Report");
		setFrameIcon(new ImageIcon(StudentReportFrame.class.getResource("/image32/printer_add.png")));
		setClosable(true);
		setBounds(100, 100, 513, 170);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Student Report", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 477, 106);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("เลือกเงื่อนไข");
		lblNewLabel.setBounds(10, 46, 71, 14);
		panel.add(lblNewLabel);
		
		cmbCondition = new JComboBox();
		cmbCondition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String conditionPrint = cmbCondition.getSelectedItem().toString();
				//JOptionPane.showMessageDialog(null, conditionPrint);
				if(conditionPrint.equals("สาขาที่สังกัด")){
					addBranchToComboBox();
				}else if(conditionPrint.equals("คณะที่สังกัด")){
					addFacultyToComboBox();
				}
			}
		});
		cmbCondition.setModel(new DefaultComboBoxModel(new String[] {"----เลือก---", "สาขาที่สังกัด", "คณะที่สังกัด"}));
		cmbCondition.setBounds(76, 43, 95, 20);
		panel.add(cmbCondition);
		
		btnPrint = new JButton("Print");
		btnPrint.setIcon(new ImageIcon(StudentReportFrame.class.getResource("/image16/printer_add.png")));
		btnPrint.setBounds(378, 35, 89, 36);
		panel.add(btnPrint);
		
		cmbPrint = new JComboBox();
		cmbPrint.setBounds(181, 43, 187, 20);
		panel.add(cmbPrint);

	}

	protected void addFacultyToComboBox() {
		if(cmbPrint.getItemCount() > 0){
			cmbPrint.removeAllItems();
		}
		
		FacultyDAO dao = new FacultyDAO();
		List<Faculty> faculties = dao.select();
		
		for (Faculty faculty : faculties) {
			cmbPrint.addItem(new ComboBoxItem(faculty.getFacultyId(),
					faculty.getFacultyName()));
		}
	}

	protected void addBranchToComboBox() {
		if(cmbPrint.getItemCount() > 0){
			cmbPrint.removeAllItems();
		}
		
		BranchDAO dao = new BranchDAO();
		List<Branch> branches = dao.select();
		
		for (Branch branch : branches) {
			cmbPrint.addItem(new ComboBoxItem(branch.getBranchId(),
					branch.getBranchName()));
		}
	}

}
