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
import java.awt.event.ActionEvent;

public class FacultyFrame extends JInternalFrame {
	private JTextField txtFacultyId;
	private JTextField txtFacultyName;
	private JButton btnClose;
	private JButton btnAdd;

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
		setBounds(100, 100, 470, 300);
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
				}else{
					JOptionPane.showMessageDialog(null, "Insert Fail.");
				}
				txtFacultyName.setText("");
			}
		});
		btnAdd.setBounds(31, 32, 89, 23);
		panel_1.add(btnAdd);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(FacultyFrame.class.getResource("/image16/cancel.png")));
		btnClose.setBounds(322, 32, 89, 23);
		panel_1.add(btnClose);

		getFacultyId();
	}

	private void getFacultyId() {
		FacultyDAO dao = new FacultyDAO();
		txtFacultyId.setText(dao.getFacultyId());
	}
}
