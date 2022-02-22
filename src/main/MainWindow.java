package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import framework.AsymmetricLens;
import framework.SymmetricLens;
import lensImplementations.IntegerPair;
import lensImplementations.LensFactory;
import lensImplementations.StringPair;
import lensImplementations.standard.PersonDataSynchronizer;
import lensImplementations.standard.SummationRestorerPreserveFirst;
import lensImplementations.student.SummationRestorerPreserveSecond;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int fieldheight = 25;
	private LogoAsymmLens logoAsymmLens;
	private LogoSymmLens logoSymmLens;
	private JPanel contentPane;
	private JLabel lblAsymmLensHeader, lblAsymmLensSourceName1, lblAsymmLensSourceName2, lblAsymmLensViewName;
	private JLabel lblSymmetricLensHeader, lblSymmetricLensM1Name1, lblSymmetricLensM1Name2, lblSymmetricLensM2Name1, lblSymmetricLensM2Name2;
	private JTextField tFSummationFirstSummand;
	private JTextField tFSummationSecondSummand;
	private JTextField tFSummationSum;
	private JTextField tFModelSpace1Field1;
	private JTextField tFModelSpace1Field2;
	private JTextField tFModelSpace2Field1;
	private JTextField tFModelSpace2Field2;

	private AsymmetricLens<IntegerPair,Integer> sumRestorer; 
	private SymmetricLens<StringPair, StringPair> symmLensRestorer;
	private JTextField textFieldMessage;
	
	private LensFactory factory; 
	public MainWindow() {
		this.factory = new LensFactory();
		this.sumRestorer = factory.createAsymmetricLens();
		this.symmLensRestorer = factory.createSymmetricLens();
		this.initialize();
	}
// ============== Summation stuff ============================	
	private void onChangingSummand() {
		this.init();
		Optional<IntegerPair> summands = this.getSummands();
		if(summands.isEmpty()) 	this.tFSummationSum.setText("");
		else 					this.tFSummationSum.setText(this.sumRestorer.get(summands.get()).toString());				
	}
	private void onChangingSum() {
		this.init();
		Optional<Integer> sum = this.getSum();
		Optional<IntegerPair> summands = this.getSummands();
		if(sum.isPresent() && summands.isPresent()) {
			try{
				this.tFSummationFirstSummand.setText(this.sumRestorer.put(summands.get(), sum.get()).getFirst().toString());
				this.tFSummationSecondSummand.setText(this.sumRestorer.put(summands.get(), sum.get()).getSecond().toString());
			}catch(Exception e) {
				this.textFieldMessage.setText(e.getMessage());
			}
		}
	}
// ============== Summation stuff ============================
	
// ============== PersonData Stuff =============================
	private void onChangeInM1() {
		this.init();
		StringPair left = new StringPair(this.tFModelSpace1Field1.getText(), this.tFModelSpace1Field2.getText());
		StringPair right = new StringPair(this.tFModelSpace2Field1.getText(), tFModelSpace2Field2.getText());
		try{
			StringPair syncResult = this.symmLensRestorer.RForward(left, right);
			this.tFModelSpace2Field1.setText(syncResult.getFirst());
			this.tFModelSpace2Field2.setText(syncResult.getSecond());
		}catch(Exception e) {
			this.setColor(Color.RED);
			this.textFieldMessage.setText(e.getMessage());
		}
	}
	private void onChangeInM2() {
		this.init();
		StringPair left = new StringPair(this.tFModelSpace1Field1.getText(), this.tFModelSpace1Field2.getText());
		StringPair right = new StringPair(this.tFModelSpace2Field1.getText(), tFModelSpace2Field2.getText());
		try{StringPair syncResult = this.symmLensRestorer.RBackward(left, right);
			this.tFModelSpace1Field1.setText(syncResult.getFirst());
			this.tFModelSpace1Field2.setText(syncResult.getSecond());
		}catch(Exception e) {
			this.setColor(Color.RED);
			this.textFieldMessage.setText(e.getMessage());
		}
	}
// ============== PersonData Stuff End =========================
	private void init() {
		this.setColor(Color.WHITE);
		this.textFieldMessage.setText("");
	}
	private void setColor(Color c) {
		this.tFModelSpace1Field1.setBackground(c);
		this.tFModelSpace2Field1.setBackground(c);
		this.tFModelSpace2Field2.setBackground(c);
		this.tFModelSpace1Field2.setBackground(c);
	}
// ================ UI Managment ============================	
	private JTextField getTFSummationFirstSummand(){
		if(this.tFSummationFirstSummand==null) {
			tFSummationFirstSummand = new JTextField();
			this.setTextFieldFont(tFSummationFirstSummand);
			tFSummationFirstSummand.setBounds(83, 91, 96, 30);
			tFSummationFirstSummand.setColumns(10);
			tFSummationFirstSummand.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {}
				public void keyReleased(KeyEvent e) {
					onChangingSummand();
				}
				public void keyPressed(KeyEvent e) {}
			});
		}
		return this.tFSummationFirstSummand;
	}
	private JTextField getTFSummationSecondSummand(){
		if(this.tFSummationSecondSummand==null) {
			tFSummationSecondSummand = new JTextField();
			this.setTextFieldFont(tFSummationSecondSummand);
			tFSummationSecondSummand.setBounds(270, 91, 96, fieldheight);
			tFSummationSecondSummand.setColumns(10);
			tFSummationSecondSummand.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {}
				public void keyReleased(KeyEvent e) {
					onChangingSummand();
				}				
				public void keyPressed(KeyEvent e) {}
			});
		}
		return this.tFSummationSecondSummand;
	}
	private JTextField getTFSummationSum(){
		if(this.tFSummationSum==null) {
			tFSummationSum = new JTextField();
			this.setTextFieldFont(tFSummationSum);
			tFSummationSum.setBounds(482, 91, 96, fieldheight);
			tFSummationSum.setColumns(10);
			tFSummationSum.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {}
				public void keyReleased(KeyEvent e) {
					onChangingSum();
				}
				public void keyPressed(KeyEvent e) {}
			});
		}
		return this.tFSummationSum;
	}
	private JTextField getTFModelSpace1Field1(){
		if(this.tFModelSpace1Field1==null) {
			tFModelSpace1Field1 = new JTextField();
			tFModelSpace1Field1.setBounds(10, 429, 96, fieldheight);
			this.setTextFieldFont(tFModelSpace1Field1);
			tFModelSpace1Field1.setColumns(10);
			tFModelSpace1Field1.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {}
				public void keyReleased(KeyEvent e) {
					onChangeInM1();
				}
				public void keyPressed(KeyEvent e) {}
			});
		}
		return this.tFModelSpace1Field1;
	}

	private JTextField getTFModelSpace1Field2(){
		if(this.tFModelSpace1Field2==null) {
			tFModelSpace1Field2 = new JTextField();
			tFModelSpace1Field2.setBounds(138, 429, 96, fieldheight);
			this.setTextFieldFont(tFModelSpace1Field2);
			tFModelSpace1Field2.setColumns(10);
			tFModelSpace1Field2.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {}
				public void keyReleased(KeyEvent e) {
					onChangeInM1();
				}
				public void keyPressed(KeyEvent e) {}
			});
		}
		return this.tFModelSpace1Field2;
	}
	private JTextField getTFModelSpace2Field1(){
		if(this.tFModelSpace2Field1==null) {
			tFModelSpace2Field1 = new JTextField();
			tFModelSpace2Field1.setBounds(331, 429, 96, fieldheight);
			this.setTextFieldFont(tFModelSpace2Field1);
			tFModelSpace2Field1.setColumns(10);
			tFModelSpace2Field1.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {}
				public void keyReleased(KeyEvent e) {
					onChangeInM2();
				}
				public void keyPressed(KeyEvent e) {}
			});
		}
		return this.tFModelSpace2Field1;
	}
	private JTextField getTFModelSpace2Field2(){
		if(this.tFModelSpace2Field2==null) {
			tFModelSpace2Field2 = new JTextField();
			tFModelSpace2Field2.setBounds(458, 429, 96, fieldheight);
			this.setTextFieldFont(tFModelSpace2Field2);
			tFModelSpace2Field2.setColumns(10);
			tFModelSpace2Field2.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {}
				public void keyReleased(KeyEvent e) {
					onChangeInM2();
				}
				public void keyPressed(KeyEvent e) {}
			});
		}
		return this.tFModelSpace2Field2;
	}
	private void setTextFieldFont(JTextField tf) {
		tf.setFont(new Font("Arial", 1, 12));
	}
	private void setLabelFont(JLabel tf) {
		tf.setFont(new Font("Times", 0, 12));
	}

/**	
 * Reads the values of the two summands
 * @returns an empty Optional if no integer values found in text fields
 */
	private Optional<IntegerPair> getSummands() {
		try {
			return Optional.of(new IntegerPair(
					Integer.parseInt(this.tFSummationFirstSummand.getText()),
					Integer.parseInt(this.tFSummationSecondSummand.getText())
			));	
		}catch(NumberFormatException nfe) {
			return Optional.empty();
		}
	}	
	private Optional<Integer> getSum(){
		try {
			return Optional.of(Integer.parseInt(this.tFSummationSum.getText())); 
		}catch(NumberFormatException nfe) {
			return Optional.empty();
		}
	}

	private LogoAsymmLens getLogoAsymm() {
		if(this.logoAsymmLens == null) {
			this.logoAsymmLens = new LogoAsymmLens();
			this.logoAsymmLens.setBounds(30, 160, 250, 300);
		}
		return this.logoAsymmLens;
	}
	private LogoSymmLens getLogoSymm() {
		if(this.logoSymmLens == null) {
			this.logoSymmLens = new LogoSymmLens();
			this.logoSymmLens.setBounds(320, 160, 250, 300);
		}
		return this.logoSymmLens;
	}
	private void initialize() {
		setTitle("HVL Lens Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.add(this.getLogoAsymm());
		contentPane.add(this.getLogoSymm());
		contentPane.add(this.getTFSummationFirstSummand());
		contentPane.add(this.getTFSummationSecondSummand());
		contentPane.add(this.getTFSummationSum());
		contentPane.add(this.getTFModelSpace1Field1());
		contentPane.add(this.getTFModelSpace1Field2());
		contentPane.add(this.getTFModelSpace2Field1());
		contentPane.add(this.getTFModelSpace2Field2());
		contentPane.add(this.getLblAsymmLensHeader());
		contentPane.add(this.getLblAsymmLensSourceName1());
		contentPane.add(this.getLblAsymmLensSourceName2());
		contentPane.add(this.getLblAsymmLensViewName());
		contentPane.add(this.getLblSymmetricLensHeader());
		contentPane.add(this.getLblSymmetricLensM1Name1());
		contentPane.add(this.getLblSymmetricLensM1Name2());
		contentPane.add(this.getLblSymmetricLensM2Name1());
		contentPane.add(this.getLblSymmetricLensM2Name2());
		this.placeNonDynamicWidgets();
		this.fillFieldNames();
	}
	private void fillFieldNames() {
		this.fillFieldNamesAsymm();
		this.fillFieldNamesSymm();
	}
	private void fillFieldNamesSymm() {
		this.lblSymmetricLensHeader.setText(this.symmLensRestorer.getRepresentation());
		List<String> m1FieldNames = this.symmLensRestorer.getM1FieldNames();
		int length = m1FieldNames.size();
		if(length==0) return;
		this.lblSymmetricLensM1Name1.setText(m1FieldNames.get(0));
		if(length>1) this.lblSymmetricLensM1Name2.setText(m1FieldNames.get(1));
		
		List<String> m2FieldNames = this.symmLensRestorer.getM2FieldNames();
		length = m2FieldNames.size();
		if(length==0) return;
		this.lblSymmetricLensM2Name1.setText(m2FieldNames.get(0));
		if(length>1) this.lblSymmetricLensM2Name2.setText(m2FieldNames.get(1));
	}
	
	private void fillFieldNamesAsymm() {
		this.lblAsymmLensHeader.setText(this.sumRestorer.getRepresentation());
		List<String> sourceFieldNames = this.sumRestorer.getSourceFieldNames();
		int length = sourceFieldNames.size();
		if(length==0) return;
		this.lblAsymmLensSourceName1.setText(sourceFieldNames.get(0));
		if(length>1) this.lblAsymmLensSourceName2.setText(sourceFieldNames.get(1));
		
		List<String> viewFieldNames = this.sumRestorer.getViewFieldNames();
		if(viewFieldNames.size()==0) return;
		this.lblAsymmLensViewName.setText(viewFieldNames.get(0));
	}
	
	private void placeNonDynamicWidgets() {
		JLabel lblAsymm = new JLabel("Asymmetric Lens");
		lblAsymm.setBounds(10, 10, 300, fieldheight +10);
		contentPane.add(lblAsymm);
		lblAsymm.setFont(new Font("Times", 1, 20));
		
		JLabel lblSymm = new JLabel("Symmetric Lens");
		lblSymm.setBounds(10, 315, 300, fieldheight +10);
		contentPane.add(lblSymm);
		lblSymm.setFont(new Font("Times", 1, 20));
		
		JLabel lblSlash = new JLabel("/");
		lblSlash.setBounds(272, 427, 24, fieldheight);
		contentPane.add(lblSlash);
		this.setLabelFont(lblSlash);
		
		textFieldMessage = new JTextField();
		textFieldMessage.setEditable(false);
		textFieldMessage.setBounds(22, 530, 515, 20);
		contentPane.add(textFieldMessage);
		textFieldMessage.setColumns(10);
	}
	private JLabel getLblAsymmLensHeader() {
		if(this.lblAsymmLensHeader==null) {
			lblAsymmLensHeader = new JLabel("No text for asymmetric lens");
			lblAsymmLensHeader.setBounds(10, 52, 384, 30);
			contentPane.add(lblAsymmLensHeader);
			this.setLabelFont(lblAsymmLensHeader);
		}
		return this.lblAsymmLensHeader;
	}
	private JLabel getLblAsymmLensSourceName1() {
		if(this.lblAsymmLensSourceName1 == null) {
			lblAsymmLensSourceName1 = new JLabel("No text for source 1");
			lblAsymmLensSourceName1.setBounds(10, 94, 60, fieldheight);
			contentPane.add(lblAsymmLensSourceName1);
			this.setLabelFont(lblAsymmLensSourceName1);		
		}
		return this.lblAsymmLensSourceName1;
	}
	private JLabel getLblAsymmLensSourceName2() {
		if(this.lblAsymmLensSourceName2 == null) {
			lblAsymmLensSourceName2 = new JLabel("No text for source 2");
			lblAsymmLensSourceName2.setBounds(188, 94, 60, fieldheight);
			contentPane.add(lblAsymmLensSourceName2);
			this.setLabelFont(lblAsymmLensSourceName2);		
		}
		return this.lblAsymmLensSourceName2;
	}
	private JLabel getLblAsymmLensViewName() {
		if(this.lblAsymmLensViewName == null) {
			lblAsymmLensViewName = new JLabel("No text for view");
			lblAsymmLensViewName.setBounds(413, 94, 60, fieldheight);
			contentPane.add(lblAsymmLensViewName);
			this.setLabelFont(lblAsymmLensViewName);		
		}
		return this.lblAsymmLensViewName;
	}
	private JLabel getLblSymmetricLensHeader() {
		if(this.lblSymmetricLensHeader == null) {
			lblSymmetricLensHeader = new JLabel("No text for symmetric lens header");
			lblSymmetricLensHeader.setBounds(10, 363, 304, fieldheight);
			contentPane.add(lblSymmetricLensHeader);
			this.setLabelFont(lblSymmetricLensHeader);		
		}
		return this.lblSymmetricLensHeader;
	}
	
	private JLabel getLblSymmetricLensM1Name1() {
		if(this.lblSymmetricLensM1Name1 == null) {
			lblSymmetricLensM1Name1 = new JLabel("No text for M1 name 1");
			lblSymmetricLensM1Name1.setBounds(10, 388, 100, fieldheight);
			this.setLabelFont(lblSymmetricLensM1Name1);		
		}
		return this.lblSymmetricLensM1Name1;
	}
	private JLabel getLblSymmetricLensM1Name2() {
		if(this.lblSymmetricLensM1Name2 == null) {
			lblSymmetricLensM1Name2 = new JLabel("No text for M1 name 2");
			lblSymmetricLensM1Name2.setBounds(138, 388, 100, fieldheight);
			this.setLabelFont(lblSymmetricLensM1Name2);		
		}
		return this.lblSymmetricLensM1Name2;
	}
	private JLabel getLblSymmetricLensM2Name1() {
		if(this.lblSymmetricLensM2Name1 == null) {
			lblSymmetricLensM2Name1 = new JLabel("No text for M2 name 1");
			lblSymmetricLensM2Name1.setBounds(331, 388, 100, fieldheight);
			this.setLabelFont(lblSymmetricLensM2Name1);		
		}
		return this.lblSymmetricLensM2Name1;
	}
	private JLabel getLblSymmetricLensM2Name2() {
		if(this.lblSymmetricLensM2Name2 == null) {
			lblSymmetricLensM2Name2 = new JLabel("No text for M2 name 2");
			lblSymmetricLensM2Name2.setBounds(458, 388, 100, fieldheight);
			this.setLabelFont(lblSymmetricLensM2Name2);		
		}
		return this.lblSymmetricLensM2Name2;
	}

/**
 * Launch the application.
 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
