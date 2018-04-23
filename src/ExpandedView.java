import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class ExpandedView extends JFrame {

	private JPanel contentPane;
	private JTextField msSiglumField;
	private JTextField libSiglumField;
	private JTextField chantIDField;
	private JTextField sectionIDField;
	private int selectedTrans;

	/**
	 * Launch the application.
	 */
	public static void run(Chant chant) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExpandedView frame = new ExpandedView(chant);
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
	public ExpandedView(Chant chant) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel label_1 = new JLabel("");
		
		JLabel label_2 = new JLabel("");
		
		JLabel lblNewLabel_1 = new JLabel("libSiglum");
		
		libSiglumField = new JTextField(chant.libSiglum);
		libSiglumField.setEditable(false);
		libSiglumField.setColumns(10);
		
		JLabel label = new JLabel("");
		
		JLabel label_3 = new JLabel("");
		
		JLabel lblNewLabel_3 = new JLabel("sectionID");
		
		JLabel label_5 = new JLabel("");
		
		sectionIDField = new JTextField(chant.sectionID);
		sectionIDField.setEditable(false);
		sectionIDField.setColumns(10);
		
		chantIDField = new JTextField(chant.chantID);
		chantIDField.setEditable(false);
		chantIDField.setColumns(10);
		
		JLabel label_4 = new JLabel("");
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(label_1);
		panel.add(label_2);
		panel.add(lblNewLabel_1);
		panel.add(libSiglumField);
		panel.add(label);
		panel.add(label_3);
		
		JLabel lblNewLabel = new JLabel("msSiglum");
		panel.add(lblNewLabel);
		
		msSiglumField = new JTextField(chant.msSiglum);
		msSiglumField.setEditable(false);
		msSiglumField.setColumns(10);
		panel.add(msSiglumField);
		panel.add(lblNewLabel_3);
		panel.add(label_5);
		panel.add(sectionIDField);
		
		JLabel lblNewLabel_2 = new JLabel("chantID");
		panel.add(lblNewLabel_2);
		panel.add(chantIDField);
		panel.add(label_4);
		
		String longText = chant.msFullText;
		
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JTextPane fullTextPane = new JTextPane();
		fullTextPane.setBounds(6, 34, 278, 379);
		panel_1.add(fullTextPane);
		fullTextPane.setEditable(false);
		
		fullTextPane.setText(longText);
		
		JTextPane transTextPane = new JTextPane();
		transTextPane.setEditable(false);
		transTextPane.setBounds(306, 34, 278, 379);
		panel_1.add(transTextPane);
		transTextPane.setText("");
		
		JLabel lblNewLabel_4 = new JLabel("Full Text :");
		lblNewLabel_4.setBounds(6, 6, 278, 16);
		panel_1.add(lblNewLabel_4);
		
		selectedTrans = 0;
		
		JComboBox transComboBox = new JComboBox();
		transComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTrans = transComboBox.getSelectedIndex();
				if (selectedTrans == 0) {
					transTextPane.setText(chant.getEnglishTranslation());
				} else {
					String longTransText = "";
					if (chant.country == null)
					{
						longTransText += "Unable to Translate (No country code).";
					} else {
						longTransText += chant.getCountryTranslation();
					}
					transTextPane.setText(longTransText);
				}
				transTextPane.repaint();
			}
		});
		transComboBox.setBounds(306, 2, 278, 27);
		panel_1.add(transComboBox);
		
		transComboBox.addItem("English Text");
		transComboBox.addItem("Country Text");
	
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton readOriginalButton = new JButton("Read Original");
		panel_2.add(readOriginalButton);
		
		JButton readTranslationButton = new JButton("Read Translation");
		readTranslationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedTrans == 0) Vocals.synthesizeText(chant.getEnglishTranslation());
				if (selectedTrans == 1) Vocals.synthesizeText(chant.getCountryTranslation());
			}
		});
		panel_2.add(readTranslationButton);
		readOriginalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vocals.synthesizeText(chant.msFullText);
			}
		});
	}
}
