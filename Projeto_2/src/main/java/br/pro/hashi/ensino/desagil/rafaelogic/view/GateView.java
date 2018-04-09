package br.pro.hashi.ensino.desagil.rafaelogic.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.pro.hashi.ensino.desagil.rafaelogic.model.Gate;
import br.pro.hashi.ensino.desagil.rafaelogic.model.Source;

public class GateView extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Gate gate;
	
	private	JCheckBox campoEntrada1;
	private	JCheckBox campoEntrada2;
	private	JCheckBox campoSaida;
	

	public GateView(Gate gate) {
		this.gate = gate;
		if (gate.getSize()==1){
			campoEntrada1 = new JCheckBox("Pino", false);
			campoSaida = new JCheckBox();
			
			JLabel escritoEntrada = new JLabel("Entrada");
			JLabel escritoSaida = new JLabel("Saida");
			
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
			add(escritoEntrada);
			add(campoEntrada1);
			add(escritoSaida);
			add(campoSaida);
			
			campoEntrada1.addActionListener(this);
		}
		else{
			campoEntrada1 = new JCheckBox("Pino 1", false);
			campoEntrada2 = new JCheckBox("Pino 2", false);
			campoSaida = new JCheckBox();
			
			JLabel escritoEntrada = new JLabel("Entrada");
			JLabel escritoSaida = new JLabel("Saida");
			
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
			add(escritoEntrada);
			add(campoEntrada1);
			add(campoEntrada2);
			add(escritoSaida);
			add(campoSaida);
			
			campoEntrada1.addActionListener(this);
			campoEntrada2.addActionListener(this);
		}
				
		campoSaida.setEnabled(false);
	
		update();
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
		campoSaida.setSelected(result);
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		update();
	}
	
}
