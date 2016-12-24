package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.Frame;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;

public class StudentWorkShop extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private JButton toolbarFaculty;
	private JButton toolbarExit;
	
	//InternalFrame
	private FacultyFrame facultyFrame;
	private BranchFrame branchFrame;
	
	//Menu
	private JMenuItem mntmFaculty;
	private JMenuItem mntmBranch;
	private JMenuItem mntmStudent;
	private JButton toolbarBranch;

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
					StudentWorkShop frame = new StudentWorkShop();
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
	public StudentWorkShop() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(StudentWorkShop.class.getResource("/image32/vcard.png")));
		setTitle("Student WorkShop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("จัดการข้อมูลพื้นฐาน");
		mnNewMenu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(mnNewMenu);
		
		mntmFaculty = new JMenuItem("ข้อมูลคณะ");
		mntmFaculty.setIcon(new ImageIcon(StudentWorkShop.class.getResource("/image16/house.png")));
		mntmFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(facultyFrame == null || facultyFrame.isClosed()){
					facultyFrame = new FacultyFrame();
					facultyFrame.setVisible(true);
					
					desktopPane.add(facultyFrame);
					
					try {
						facultyFrame.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mntmFaculty.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnNewMenu.add(mntmFaculty);
		
		mntmBranch = new JMenuItem("ข้อมูลสาขา");
		mntmBranch.setIcon(new ImageIcon(StudentWorkShop.class.getResource("/image16/tree.png")));
		mntmBranch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnNewMenu.add(mntmBranch);
		
		mntmStudent = new JMenuItem("ข้อมูลนักศึกษา");
		mntmStudent.setIcon(new ImageIcon(StudentWorkShop.class.getResource("/image16/user_add.png")));
		mntmStudent.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnNewMenu.add(mntmStudent);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		toolbarFaculty = new JButton("Faculty");
		toolbarFaculty.setIcon(new ImageIcon(StudentWorkShop.class.getResource("/image32/house.png")));
		toolbarFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(facultyFrame == null || facultyFrame.isClosed()){
					facultyFrame = new FacultyFrame();
					facultyFrame.setVisible(true);
					
					desktopPane.add(facultyFrame);
					
					try {
						facultyFrame.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		toolBar.add(toolbarFaculty);
		
		toolbarExit = new JButton("Exit");
		toolbarExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		toolbarBranch = new JButton("Branch");
		toolbarBranch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(branchFrame == null || branchFrame.isClosed()){
					branchFrame = new BranchFrame();
					branchFrame.setVisible(true);
					
					desktopPane.add(branchFrame);
					
					try {
						branchFrame.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		toolbarBranch.setIcon(new ImageIcon(StudentWorkShop.class.getResource("/image32/tree.png")));
		toolBar.add(toolbarBranch);
		toolbarExit.setIcon(new ImageIcon(StudentWorkShop.class.getResource("/image32/cross.png")));
		toolBar.add(toolbarExit);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}

}
