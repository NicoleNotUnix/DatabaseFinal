import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.List;

import javax.swing.BoxLayout;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class DBSearchGUI extends JFrame {

	private JPanel contentPane;
	private JTextField searchKeywordField;
	Chant selectedChant;
	ArrayList<Chant> results;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBSearchGUI frame = new DBSearchGUI();
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
	public DBSearchGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel top = new JPanel();
		contentPane.add(top, BorderLayout.NORTH);
		top.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel bottom = new JPanel();
		contentPane.add(bottom, BorderLayout.SOUTH);
		
		String resultLabelString = "Results : ";
		JLabel resultNumberLabel = new JLabel(resultLabelString + 0);
		bottom.add(resultNumberLabel);
		
		JButton expandViewButton = new JButton("Expanded View");
		expandViewButton.setEnabled(false);
		bottom.add(expandViewButton);
		
		JPanel left = new JPanel();
		contentPane.add(left, BorderLayout.WEST);
		
		JPanel right = new JPanel();
		contentPane.add(right, BorderLayout.EAST);
		
		JScrollPane center = new JScrollPane();
		contentPane.add(center, BorderLayout.CENTER);
		
		JList resultList = new JList();
		resultList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				expandViewButton.setEnabled(true);
				int selectionIndex = resultList.getSelectedIndex();
				selectedChant = results.get(selectionIndex);
			}
		});
		center.setViewportView(resultList);
		
		JLabel lblSearchKeywords = new JLabel("Search Keywords");
		top.add(lblSearchKeywords);
		
		searchKeywordField = new JTextField();
		top.add(searchKeywordField);
		searchKeywordField.setColumns(10);
		
		JButton btnRecord = new JButton("Record");
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO add logic for record being pressed here
			}
		});
		top.add(btnRecord);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchKeyword = searchKeywordField.getText();
				results = IncipitSearch.searchForFullText(searchKeyword);
				resultList.setListData(results.toArray());
				resultNumberLabel.setText(resultLabelString + results.size());
			}
		});
		top.add(btnSearch);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchKeywordField.setText("");
				resultList.clearSelection();
				String[] emptyList = {};
				resultList.setListData(emptyList);
				resultNumberLabel.setText(resultLabelString + 0);
			}
		});
		top.add(btnClear);
	}

}
