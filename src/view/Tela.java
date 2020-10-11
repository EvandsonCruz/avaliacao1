package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerEscola;
import controller.ControllerJurado;
import controller.ControllerNota;
import controller.ControllerQuesito;
import model.Nota;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Tela extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNota;
	int contadorEscola = 0;
	int contadorJurado = 0;
	int contadorQuesito = 1;
	Boolean primeiraInsercao = false;
	String saida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Tela() throws ClassNotFoundException, SQLException {
		setTitle("SISTEMA DE NOTAS - CARNAVAL 2013");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 281);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Escola");
		lblNewLabel.setBounds(47, 56, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Jurado");
		lblNewLabel_1.setBounds(47, 96, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Quesito");
		lblNewLabel_2.setBounds(47, 140, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nota");
		lblNewLabel_3.setBounds(47, 184, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		txtNota = new JTextField();
		txtNota.setEnabled(false);
		txtNota.setBounds(103, 181, 86, 20);
		contentPane.add(txtNota);
		txtNota.setColumns(10);
		
		JComboBox<String> cbEscola = new JComboBox<String>();
		cbEscola.setEnabled(false);
		cbEscola.setBounds(103, 52, 176, 22);
		contentPane.add(cbEscola);
		
		JComboBox<String> cbJurado = new JComboBox<String>();
		cbJurado.setEnabled(false);
		cbJurado.setBounds(103, 92, 176, 22);
		contentPane.add(cbJurado);
		
		ControllerEscola ce = new ControllerEscola();
		String nomeEscolas[] = 	ce.listarNomeEscola();
		ControllerJurado cj = new ControllerJurado();
		String nomeJurados[] = 	cj.listarNomeJurados();
		ControllerQuesito cq = new ControllerQuesito();
		String nomeQuesitos[] = cq.listarNomeQuesitos();
						
		JComboBox<String> cbQuesito = new JComboBox<String>();
		cbQuesito.setBounds(103, 136, 176, 22);
		contentPane.add(cbQuesito);
		cbQuesito.addItem("");
		cbQuesito.addItem(nomeQuesitos[0]);	
		
		cbQuesito.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbQuesito.setEnabled(false);
                for(int i=0; i<=13;i++) {
                	cbEscola.addItem((nomeEscolas[i]));
                }
                for(int i=0; i<=4;i++) {
                	cbJurado.addItem(nomeJurados[i]);
                }
                txtNota.setEnabled(true);
            }
        });
		
		JLabel lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setBounds(103, 205, 203, 14);
		contentPane.add(lblAviso);
		lblAviso.setVisible(false);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setEnabled(false);
		
						
		txtNota.addKeyListener(new KeyAdapter() {
			    public void keyReleased(KeyEvent e) {
				if(txtNota.getText().equals("")) {
					btnInserir.setEnabled(false);
				}else {					
					try {
						if(Double.parseDouble(txtNota.getText()) < 5.0 || Double.parseDouble(txtNota.getText()) > 10.0) {
							lblAviso.setText("A nota deve ser entre 5.0 e 10.0");
							lblAviso.setVisible(true);
							btnInserir.setEnabled(false);
							
						}else {
							lblAviso.setVisible(false);
							btnInserir.setEnabled(true);
							btnInserir.grabFocus();
						}
					}catch(NumberFormatException f) {
						btnInserir.setEnabled(false);
						lblAviso.setText("Digite uma nota válida");
						lblAviso.setVisible(true);
					}
				}
			}
		});
		
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(Double.parseDouble(txtNota.getText()) < 5.0 || Double.parseDouble(txtNota.getText()) > 10.0) {
					lblAviso.setText("A nota deve ser entre 5.0 e 10.0");
					lblAviso.setVisible(true);
					
				}else {					
					try {
						Nota nota = new Nota();
						nota.setIdEscola(contadorEscola+1);
						nota.setIdJurado(contadorJurado+1);
						nota.setIdQuesito(contadorQuesito);
						nota.setNota(txtNota.getText());
						ControllerNota cn = new ControllerNota();
						saida = cn.insereNota(nota);
						System.out.println(saida);
						if(primeiraInsercao == false) {
							for(int i=1; i<=8;i++) {
			        			cbQuesito.addItem(nomeQuesitos[i]);
			        		}
							primeiraInsercao = true;
						}
						contadorEscola++;
						cbEscola.setSelectedIndex(contadorEscola);
						lblAviso.setVisible(false);
						txtNota.setText("");
						txtNota.grabFocus();
						btnInserir.setEnabled(false);
						if(contadorEscola >= 14) {
							contadorEscola = 0;
							contadorJurado++;
							cbEscola.setSelectedIndex(contadorEscola);
							cbJurado.setSelectedIndex(contadorJurado);
						}
						if(contadorJurado >= 5) {
							contadorJurado = 0;
							contadorQuesito++;
							cbEscola.setSelectedIndex(contadorEscola);
							cbJurado.setSelectedIndex(contadorJurado);
							if(contadorQuesito >= 10) {
								JOptionPane.showMessageDialog(null, "Já foram inseridas todas as notas de todos os jurados em todos os quesitos");
								txtNota.setEnabled(false);
								btnInserir.setEnabled(false);
							}else {
								cbQuesito.setSelectedIndex(contadorQuesito);
							}
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			}
		});
		btnInserir.setBounds(190, 180, 89, 23);
		contentPane.add(btnInserir);
		
		JButton btnQuesito = new JButton("Ver Quesito");
		btnQuesito.setBounds(328, 136, 114, 23);
		contentPane.add(btnQuesito);
		
		JButton btnTotal = new JButton("Ver Total");
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerEscola ce = new ControllerEscola();
				try {
					ce.listarEscola();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTotal.setBounds(328, 180, 114, 23);
		contentPane.add(btnTotal);
		
		
	}
}
