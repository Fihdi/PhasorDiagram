import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VectorFrame extends JFrame implements ActionListener{

	JTextField tfAmp1, tfAmp2, tfAmp3, tfOmega1, tfOmega2, tfOmega3, tfPhase1, tfPhase2, tfPhase3;
	JLabel lbAmplitudes, lbAmp1, lbAmp2, lbAmp3, lbOmega, lbOmega1, lbOmega2, lbOmega3, lbPhase, lbPhase1, lbPhase2, lbPhase3;

	JRadioButton add, sep, res;
	JButton btApply;

	JCheckBox cbRainbow;
	private boolean rainbow = false;
	private int option = 0;
	PointAndPlotPanel bp;

	VectorFrame() {
		super("PhasorDiagram");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.BLACK);

		setLocationRelativeTo(null);

		add = new JRadioButton("added");
		add.setBounds(140,460,70,16);
		add.addActionListener(this);
		add.setForeground(Color.BLUE);
		add.setBackground(Color.BLACK);
		add.setSelected(true);

		sep = new JRadioButton("seperate");
		sep.addActionListener(this);
		sep.setForeground(Color.BLUE);
		sep.setBackground(Color.BLACK);
		sep.setBounds(140,480,100,16);

		res = new JRadioButton("resulting");
		res.addActionListener(this);
		res.setForeground(Color.BLUE);
		res.setBackground(Color.BLACK);
		res.setBounds(140,500,80,16);

		ButtonGroup group = new ButtonGroup();

		group.add(add);
		group.add(sep);
		group.add(res);

		this.add(add);
		this.add(sep);
		this.add(res);

		lbAmplitudes = new JLabel("Amplitude:");
		lbAmplitudes.setBounds(110,300,170,20);
		lbAmplitudes.setForeground(Color.WHITE);
		this.add(lbAmplitudes);

		lbOmega = new JLabel("Angular Frequency:");
		lbOmega.setBounds(300,300,170,20);
		lbOmega.setForeground(Color.WHITE);
		this.add(lbOmega);

		lbPhase = new JLabel("Phase(in degrees):");
		lbPhase.setBounds(570,300,170,20);
		lbPhase.setForeground(Color.WHITE);
		this.add(lbPhase);

		lbAmp1 = new JLabel("Vector1:");
		lbAmp1.setBounds(60, 340, 170, 20);
		lbAmp1.setFont(new Font("Arial", Font.BOLD, 12));
		lbAmp1.setForeground(Color.GREEN);
		this.add(lbAmp1);

		tfAmp1 = new JTextField("60");
		tfAmp1.setBounds(120, 340, 140, 20);
		this.add(tfAmp1);

		lbAmp2 = new JLabel("Vector2:");
		lbAmp2.setBounds(60, 370, 170, 20);
		lbAmp2.setFont(new Font("Arial", Font.BOLD, 12));
		lbAmp2.setForeground(Color.YELLOW);
		this.add(lbAmp2);

		tfAmp2 = new JTextField("20");
		tfAmp2.setBounds(120, 370, 140, 20);
		this.add(tfAmp2);

		lbAmp3 = new JLabel("Vector3:");
		lbAmp3.setBounds(60, 400, 170, 20);
		lbAmp3.setFont(new Font("Arial", Font.BOLD, 12));
		lbAmp3.setForeground(Color.RED);
		this.add(lbAmp3);

		tfAmp3 = new JTextField("12");
		tfAmp3.setBounds(120, 400, 140, 20);
		this.add(tfAmp3);

		lbOmega1 = new JLabel("Vector1:");
		lbOmega1.setBounds(280, 340, 170, 20);
		lbOmega1.setFont(new Font("Arial", Font.BOLD, 12));
		lbOmega1.setForeground(Color.GREEN);
		this.add(lbOmega1);

		tfOmega1 = new JTextField("40");
		tfOmega1.setBounds(350, 340, 140, 20);
		this.add(tfOmega1);

		lbOmega2 = new JLabel("Vector2:");
		lbOmega2.setBounds(280, 370, 170, 20);
		lbOmega2.setFont(new Font("Arial", Font.BOLD, 12));
		lbOmega2.setForeground(Color.YELLOW);
		this.add(lbOmega2);

		tfOmega2 = new JTextField("120");
		tfOmega2.setBounds(350, 370, 140, 20);
		this.add(tfOmega2);

		lbOmega3 = new JLabel("Vector3:");
		lbOmega3.setBounds(280, 400, 170, 20);
		lbOmega3.setFont(new Font("Arial", Font.BOLD, 12));
		lbOmega3.setForeground(Color.RED);
		this.add(lbOmega3);

		tfOmega3 = new JTextField("200");
		tfOmega3.setBounds(350, 400, 140, 20);
		this.add(tfOmega3);

		lbPhase2 = new JLabel("Vector2:");
		lbPhase2.setBounds(510, 370, 170, 20);
		lbPhase2.setFont(new Font("Arial", Font.BOLD, 12));
		lbPhase2.setForeground(Color.YELLOW);
		this.add(lbPhase2);

		tfPhase2 = new JTextField("0");
		tfPhase2.setBounds(570, 370, 140, 20);
		this.add(tfPhase2);

		lbPhase3 = new JLabel("Vector3:");
		lbPhase3.setBounds(510, 400, 170, 20);
		lbPhase3.setFont(new Font("Arial", Font.BOLD, 12));
		lbPhase3.setForeground(Color.RED);
		this.add(lbPhase3);

		tfPhase3 = new JTextField("0");
		tfPhase3.setBounds(570, 400, 140, 20);
		this.add(tfPhase3);

		lbPhase1 = new JLabel("Vector1:");
		lbPhase1.setBounds(510, 340, 170, 20);
		lbPhase1.setFont(new Font("Arial", Font.BOLD, 12));
		lbPhase1.setForeground(Color.GREEN);
		this.add(lbPhase1);

		tfPhase1 = new JTextField("0");
		tfPhase1.setBounds(570, 340, 140, 20);
		this.add(tfPhase1);

		cbRainbow = new JCheckBox();
		cbRainbow.setText("Rainbow");
		cbRainbow.setBounds(140, 520, 80, 30);
		cbRainbow.setBackground(Color.BLACK);
		cbRainbow.setForeground(Color.BLUE);
		cbRainbow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (cbRainbow.isSelected()) {
					rainbow = true;

				} else {
					rainbow = false;

				}
			}

		});
		this.add(cbRainbow);

		btApply = new JButton("Apply");
		btApply.setBounds(360, 450, 80, 40);
		btApply.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double amp1 = Double.parseDouble(tfAmp1.getText());
				double amp2 = Double.parseDouble(tfAmp2.getText());
				double amp3 = Double.parseDouble(tfAmp3.getText());

				double speed1 = Double.parseDouble(tfOmega1.getText());
				double speed2 = Double.parseDouble(tfOmega2.getText());
				double speed3 = Double.parseDouble(tfOmega3.getText());

				double phase1 = Double.parseDouble(tfPhase1.getText());
				double phase2 = Double.parseDouble(tfPhase2.getText());
				double phase3 = Double.parseDouble(tfPhase3.getText());

				bp.setParameters(amp1, amp2, amp3, speed1, speed2, speed3, phase1, phase2,phase3,
						rainbow, option);

				bp.resetPlot();
				bp.repaint();

			}
		});
		this.add(btApply);
		bp = new PointAndPlotPanel();

		this.add(bp);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JRadioButton) e.getSource()).getActionCommand().equals("added")){
			this.option = 0;
		}
		if(((JRadioButton) e.getSource()).getActionCommand().equals("seperate")){
			this.option = 1;
		}
		if(((JRadioButton) e.getSource()).getActionCommand().equals("resulting")){
			this.option = 2;
		}
	}
}
