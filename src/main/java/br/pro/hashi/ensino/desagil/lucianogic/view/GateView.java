package br.pro.hashi.ensino.desagil.lucianogic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;


public class GateView extends FixedPanel implements ActionListener {

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;

	private Image image;
	

	private JButton[] inBoxes;
	private JCheckBox outBox;

	private Switch[] switches;
	private Gate gate;
	private int size;
	private boolean[] on;
	

	

	public GateView(Gate gate) {
		super(255, 180);

		this.gate = gate;
		image = loadImage(gate.toString());
		size = gate.getSize();
		inBoxes = new JButton[size];
		on = new boolean[size];
		switches = new Switch[size];

		for(int i = 0; i < size; i++) {
			inBoxes[i] = new JButton();

			inBoxes[i].addActionListener(this);
			inBoxes[i].setActionCommand(Integer.toString(i));
			
			inBoxes[i].setOpaque(false);
			inBoxes[i].setContentAreaFilled(false);
			inBoxes[i].setBorderPainted(false);

			switches[i] = new Switch();
			
			on[i] = true;

			gate.connect(switches[i], i);
		}

		outBox = new JCheckBox();

		outBox.setEnabled(false);

		if(size == 1) {
			add(inBoxes[0], 10, 30, 40, 40);			
		}
		else {
			for(int i = 0; i < size; i++) {
				add(inBoxes[i], 10, (i + 1) * 40 - 10, 40, 40);			
			}			
		}

		add(outBox, 224, 60, 20, 20);

		outBox.setSelected(gate.read());
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		int i;
		for(i = 0; i < inBoxes.length; i++) {
			if(inBoxes[i] == e.getSource()) {
				break;
			}
		}

		switches[i].setOn(on[i]);
		
		for (int k = 0; k < size;k ++){
			if(Integer.toString(k).equals(e.getActionCommand())){
				on[k] = !on[k];
			}
		}
		

		outBox.setSelected(gate.read());
		
		repaint();
	}


	// Necessario para carregar os arquivos de imagem.
	private Image loadImage(String filename) {
		URL url = getClass().getResource("/img/" + filename + ".png");
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}


	@Override
	public void paintComponent(Graphics g) {
		// Evita bugs visuais em alguns sistemas operacionais.
		super.paintComponent(g);

		g.drawImage(image, 50, 20, 184, 140, null);
		for (int i = 0; i < size;i ++){
			g.drawLine(27, 30 + i * 40, on[i] ? 10:10, + i * 35 );
			g.drawLine(27, 30 + i * 40, on[i] ? 10:10, + i * 35 );
			g.fillArc(35, 15, 15, 15,15,15);
			
			g.fillArc(35,35,35,35,35,35);

		}
		// Evita bugs visuais em alguns sistemas operacionais.
		getToolkit().sync();
    }
	
}