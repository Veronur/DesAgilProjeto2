package br.pro.hashi.ensino.desagil.rafaelogic.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;


import br.pro.hashi.ensino.desagil.rafaelogic.model.Gate;
import br.pro.hashi.ensino.desagil.rafaelogic.model.Source;

public class GateView extends SimplePanel implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;

	private Gate gate;
	
	private	JCheckBox campoEntrada1;
	private	JCheckBox campoEntrada2;
	//private	JCheckBox campoSaida;
	
	private Color color;
	private Image image;
	private Color colortr=Color.RED;
	

	public GateView(Gate gate) {
		super(245, 300);
		this.gate = gate;
		if (gate.getSize()==1){
			campoEntrada1 = new JCheckBox("Pino", false);
			//campoSaida = new JCheckBox();
			
			JLabel escritoEntrada = new JLabel("Entrada");
			//JLabel escritoSaida = new JLabel("Saida");
			
		
			add(escritoEntrada, 10, 10, 75, 25);
			add(campoEntrada1, 5, 160, 25, 25);
			//add(escritoSaida, 10, 265, 75, 25);
			//add(campoSaida, 85, 265, 150, 25);
			
			campoEntrada1.addActionListener(this);
		}
		else{
			campoEntrada1 = new JCheckBox("Pino 1", false);
			campoEntrada2 = new JCheckBox("Pino 2", false);
			//campoSaida = new JCheckBox();
			
			//JLabel escritoEntrada = new JLabel("Entrada");
			//JLabel escritoSaida = new JLabel("Saida");
			
		
			//add(escritoEntrada, 10, 10, 75, 25);
			add(campoEntrada1, 5, 110, 25, 25);
			add(campoEntrada2, 5, 160, 25, 25);
			//add(escritoSaida, 10, 265, 75, 25);
			//add(campoSaida, 85, 265, 150, 25);
			
			campoEntrada1.addActionListener(this);
			campoEntrada2.addActionListener(this);
		}
				
		//campoSaida.setEnabled(false);
		
		update();
		//color = Color.BLACK;
		
		String path = "/" + gate.toString() + ".png";
		URL url = getClass().getResource(path);
		image = new ImageIcon(url).getImage();
		addMouseListener(this);

		
	}
	
	private void update() {
		Source source1= new Source();
		Source source2= new Source();

		try {	
			source1.turn(campoEntrada1.isSelected());
			gate.connect(0, source1);
			if (gate.getSize()>1){
				source2.turn(campoEntrada2.isSelected());
				gate.connect(1, source2);
			
			}
		}
		catch(NumberFormatException exception) {
			return;
		}
		boolean result = gate.read();
		
		if (result){
			color=colortr;
			
		}
		else{
			color=Color.BLACK;
		}
		repaint();
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		update();
		
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		boolean result = gate.read();	
		double modulo =Math.pow(Math.pow(x,2)*Math.pow(y,2),0.5);
		if (result){
			if(x >= 210-(30*(210-x)/modulo) && x < 240-(30*(240-x)/modulo) && y >= 150-(30*(150-y)/modulo) && y < 180-(30*(180-y)/modulo)) {
				colortr = JColorChooser.showDialog(this, null, colortr);
				color=colortr;
		}	
			repaint();
		}

	}
	

	@Override
	public void mouseEntered(MouseEvent event) {

	}

	@Override
	public void mouseExited(MouseEvent event) {
		
	}

	@Override
	public void mousePressed(MouseEvent event) {

	}

	@Override
	public void mouseReleased(MouseEvent event) {

	}
	
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(image, 30, 80, 175, 150, null);
		

		g.setColor(color);
			

		g.fillOval(210, 150, 30, 30);

		getToolkit().sync();
    }

}
