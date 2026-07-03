package logica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.*;
import Visitor.CalculadorPoder;
import dominio.*;

public class VentanaPrincipal extends JFrame {
	public VentanaPrincipal() {
        setTitle("Pokémon TCG - Colección");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600); // Tamaño sugerido para visualización amplia

        JTabbedPane pestañas = new JTabbedPane();
        pestañas.addTab("Administración", crearPanelAdmin());
        pestañas.addTab("Ver Colección", crearPanelColeccion());

        add(pestañas);
        setVisible(true);
    }
	private Component crearPanelColeccion() {
		JPanel panel = new JPanel();
		actualizarVistaColeccion(panel);
		return panel;
	}
	private JPanel crearPanelAdmin() {
	    JPanel panel = new JPanel();
	    JButton btnAgregar = new JButton("Agregar Carta");
	    JButton btnEliminar = new JButton("Eliminar Carta");
	    JButton btnModificar = new JButton("Modificar Carta");
	    
	    
	    btnAgregar.addActionListener(e -> {
	    	try {
	    	String linea = "";
	        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la carta:");
	        int rareza = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la rareza de la carta:"));
	        String tipo = JOptionPane.showInputDialog("Ingrese el tipo de la carta:");
	        switch(tipo) {
	        case("Pokemon"):
	        	int dmg = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el daño de la carta:"));
	        	int cantEnergias = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de energias de la carta:"));
	        	linea += nombre+";"+rareza+";"+tipo+";"+dmg+";"+cantEnergias;
	        	break;
	        case("Item"):
	        	int buff = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el buff de la carta:"));
        		linea += nombre+";"+rareza+";"+tipo+";"+buff;
	        	break;
	        case("Supporter"):
	        	int efectos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese los efectos por turno de la carta:"));
    			linea += nombre+";"+rareza+";"+tipo+";"+efectos;
	        	break;
	        case("Energy"):
	        	String elemento = JOptionPane.showInputDialog("Ingrese el elemento de la carta:");
	        	linea += nombre+";"+rareza+";"+tipo+";"+elemento;
	        	break;
	        }
	        if (linea != null && !linea.isEmpty()) {
	            SistemaImpl.getInstance().crearCarta(linea);
	            JOptionPane.showMessageDialog(this, "Carta agregada exitosamente.");
	        }
	    	} catch (Exception ex) {
	    		JOptionPane.showMessageDialog(this, "Entrada invalida, intente otra vez.");
	    	}
	    });

	    panel.add(btnAgregar);
	    panel.add(btnEliminar);
	    panel.add(btnModificar);
	    
	    return panel;
	}
	private void actualizarVistaColeccion(JPanel panelCartas) {
	    panelCartas.removeAll(); // Paso 1: Limpiar [8]
	    
	    // Obtener la colección (necesitarás un getter en tu SistemaImpl)
	    for (Carta c : SistemaImpl.getInstance().getColeccion()) {
	        panelCartas.add(crearMiniaturaCarta(c));
	    }

	    panelCartas.revalidate(); // Paso 2: Recalcular layout
	    panelCartas.repaint();    // Paso 3: Redibujar
	}
	private JPanel crearMiniaturaCarta(Carta carta) {
	    JPanel mini = new JPanel();
	    mini.add(new JLabel(carta.getNombre()));

	    mini.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            
	            JDialog detalle = new JDialog();
	            detalle.setTitle("Detalle: " + carta.getNombre());
	            detalle.setSize(300, 400);
	            
	            
	            int poder = carta.aceptarVisita(new CalculadorPoder());
	            detalle.add(new JLabel("Poder Calculado: " + poder), BorderLayout.SOUTH);
	            
	            detalle.setVisible(true);
	        }
	    });
	    return mini;
	}
	private ImageIcon obtenerImagen(String nombreCarta) {
	    // Intenta buscar la imagen específica
	    String rutaCarta = "imagenes/" + nombreCarta + ".png";
	    File file = new File(rutaCarta);

	    if (file.exists()) {
	        return new ImageIcon(rutaCarta);
	    } else {
	        // Usa la que vimos en tu captura de pantalla
	        return new ImageIcon("/Taller4BP/imagenes/default_card.png");
	    }
	}
}


