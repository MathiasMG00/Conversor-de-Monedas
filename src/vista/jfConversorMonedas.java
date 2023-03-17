package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import monedas.Monedas;
import monedas.enumMonedas;

import java.awt.Label;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class jfConversorMonedas extends JFrame {

	private JPanel contentPane;

	private String comprovante;
	private Monedas mony = new Monedas();

	/**
	 * Create the frame.
	 */
	public jfConversorMonedas() {

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		Label label = new Label("Conversor de Monedas");
		label.setBounds(164, 10, 135, 22);
		contentPane.add(label);

		Label label_1 = new Label("Tipo de Moneda");
		label_1.setBounds(52, 51, 90, 22);
		contentPane.add(label_1);

		Label label_2 = new Label("a");
		label_2.setAlignment(Label.CENTER);
		label_2.setBounds(220, 77, 20, 22);
		contentPane.add(label_2);

		Label label_3 = new Label("Ingrese la cantidad");
		label_3.setBounds(52, 144, 105, 22);
		contentPane.add(label_3);

		final Choice choice = new Choice();
		choice.setBounds(82, 79, 110, 20);
		contentPane.add(choice);

		final Choice choice_1 = new Choice();
		choice_1.setBounds(272, 79, 110, 20);
		contentPane.add(choice_1);

		int orden = 0;
		for (enumMonedas moneda : enumMonedas.values()) {
			choice.insert(moneda.toString(), orden);
			orden++;
		}
		for (enumMonedas moneda : enumMonedas.values()) {
			choice_1.insert(moneda.toString(), orden);
			orden++;
		}

		final TextField textField = new TextField();
		textField.setBounds(177, 144, 110, 22);
		contentPane.add(textField);

		final TextField textField_1 = new TextField();
		textField_1.setBounds(177, 219, 110, 22);
		contentPane.add(textField_1);
		textField_1.setEditable(false);

		Button button = new Button("Convertir");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String monedaActual = choice.getSelectedItem();
				String cantidadActual = textField.getText();
				String monedaCambio = choice_1.getSelectedItem();

				// If que evita el error si no hay algun valor en el campo de "cantidadActual"
				if (!cantidadActual.isEmpty()) {
					if (comprovante != monedaActual) {
						comprovante = monedaActual;
						mony = new Monedas(monedaActual, cantidadActual);
						mony.obtenerVC();
					}
					if (cantidadActual != mony.getCantidadActual())
						mony.setCantidadActual(cantidadActual);

					textField_1.setText(mony.Conversion(monedaCambio).toString());
					// System.out.println("total de 'Monedas': " + Monedas.getTotal());
				}
			}
		});
		button.setBounds(185, 174, 94, 35);
		contentPane.add(button);

		Button button_1 = new Button("VOLVER");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!jfPrincipal.getJfPrincipal().isVisible()) {
					jfPrincipal.getJfPrincipal().setVisible(true);
					jfPrincipal.getConMonedas().setVisible(false);
				}
			}
		});
		button_1.setBounds(384, 268, 70, 22);
		contentPane.add(button_1);

		// validador de solo numeros en en campo "cantidadActual"
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c < '0' || c > '9')
					e.consume();
			}
		});
	}
}
