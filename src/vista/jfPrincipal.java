package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Font;

public class jfPrincipal extends JFrame {

	private JPanel contentPane;
	private static jfPrincipal jfPrincipal = new jfPrincipal();;
	private static jfConversorMonedas conMonedas;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jfPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static jfConversorMonedas getConMonedas() {
		return conMonedas;
	}

	public static jfPrincipal getJfPrincipal() {
		return jfPrincipal;
	}

	/**
	 * Create the frame.
	 */
	public jfPrincipal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 110);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		final Choice choice = new Choice();
		choice.setFont(new Font("Dialog", Font.PLAIN, 14));
		choice.setBounds(28, 22, 200, 25);
		contentPane.add(choice);
		choice.insert("Seleccionar una opcion", 0);
		choice.insert("Conversor de monedas", 1);
		choice.insert("Conversor", 2);

		Button button = new Button("ABRIR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer oredenSeleccion = choice.getSelectedIndex();

				if (oredenSeleccion == 1) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								conMonedas = new jfConversorMonedas();
								conMonedas.setVisible(true);
								jfPrincipal.setVisible(false);
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						}
					});
				}
			}
		});
		button.setBounds(267, 25, 70, 22);
		contentPane.add(button);
	}
}
