// Bastián Felipe Perines Flores
// 22.386.978-5
// ICCI
package logica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import Visitor.CalculadorPoder;
import dominio.*;

public class VentanaPrincipal extends JFrame {
	private JPanel panelCartasVisual; 
	public VentanaPrincipal() {
        setTitle("Pokémon TCG - Colección");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600); 

        JTabbedPane pestañas = new JTabbedPane();
        pestañas.addTab("Administración", crearPanelAdmin());
        pestañas.addTab("Ver Colección", crearPanelColeccion());

        add(pestañas);
        setVisible(true);
    }
	private Component crearPanelColeccion() {
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		this.panelCartasVisual = new JPanel(new GridLayout(0, 3, 10, 10)); 
		
		JPanel barraOrden = new JPanel();
	    JButton btnNombre = new JButton("Nombre");
	    JButton btnRareza = new JButton("Rareza");
	    JButton btnPoder = new JButton("Poder");
	    
	    btnNombre.addActionListener(e -> {
	        SistemaImpl.getInstance().ordenarNombre(); 
	        actualizarVistaColeccion(panelCartasVisual);    
	    });

	    btnRareza.addActionListener(e -> {
	        SistemaImpl.getInstance().ordenarRareza(); 
	        actualizarVistaColeccion(panelCartasVisual);
	    });

	    btnPoder.addActionListener(e -> {
	        SistemaImpl.getInstance().ordenarPoder();  
	        actualizarVistaColeccion(panelCartasVisual);
	    });
	    
	    barraOrden.add(new JLabel("Ordenar por:"));
	    barraOrden.add(btnNombre);
	    barraOrden.add(btnRareza);
	    barraOrden.add(btnPoder);

	    actualizarVistaColeccion(panelCartasVisual);

	    JScrollPane scroll = new JScrollPane(panelCartasVisual);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    
	    panelPrincipal.add(barraOrden, BorderLayout.NORTH);
	    panelPrincipal.add(scroll, BorderLayout.CENTER);
	    
	    return panelPrincipal; 
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
	            actualizarVistaColeccion(panelCartasVisual);
	            JOptionPane.showMessageDialog(this, "Carta agregada exitosamente.");
	            ActualizadorArchivos.getInstance().sobreEscribirSobres(SistemaImpl.getInstance().getColeccion());
	        }
	    	} catch (Exception ex) {
	    		JOptionPane.showMessageDialog(this, "Entrada invalida, intente otra vez.");
	    	}
	    });
	    
	    btnEliminar.addActionListener(e -> {
	        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la carta a eliminar:");
	        if (nombre != null) {
	            boolean eliminado = SistemaImpl.getInstance().eliminarCarta(nombre);
	            if (eliminado) {
	                JOptionPane.showMessageDialog(this, "Carta eliminada.");
	                ActualizadorArchivos.getInstance().sobreEscribirSobres(SistemaImpl.getInstance().getColeccion());
	                actualizarVistaColeccion(panelCartasVisual);
	            } else {
	                JOptionPane.showMessageDialog(this, "Carta no encontrada.");
	            }
	        }
	    });
	    
	    btnModificar.addActionListener(e -> {
	        String nombre = JOptionPane.showInputDialog("Nombre de la carta a modificar:");
	        Carta c = SistemaImpl.getInstance().buscarCarta(nombre);
	        
	        if (c != null) {
	        	try {
	            if (c instanceof Pokemon) {
	                int nuevoDmg = Integer.parseInt(JOptionPane.showInputDialog("Nuevo daño:"));
	                int nuevaCantEnergias = Integer.parseInt(JOptionPane.showInputDialog("Nueva cantidad de energia:"));
	                ((Pokemon) c).setDmg(nuevoDmg);
	                ((Pokemon) c).setEnergias(nuevaCantEnergias);
	                actualizarVistaColeccion(panelCartasVisual);
		            JOptionPane.showMessageDialog(this, "Atributos modificados.");
		            ActualizadorArchivos.getInstance().sobreEscribirSobres(SistemaImpl.getInstance().getColeccion());
	            } else if (c instanceof Item) {
	                int nuevoBuff = Integer.parseInt(JOptionPane.showInputDialog("Nueva bonificación:"));
	                ((Item) c).setBuff(nuevoBuff);
	                actualizarVistaColeccion(panelCartasVisual);
		            JOptionPane.showMessageDialog(this, "Atributos modificados.");
		            ActualizadorArchivos.getInstance().sobreEscribirSobres(SistemaImpl.getInstance().getColeccion());
	            } else if (c instanceof Supporter) {
	            	int nuevoEfectosPorTurno = Integer.parseInt(JOptionPane.showInputDialog("Nuevo efectos por turno:"));
	                ((Supporter) c).setEfectoPorTurno(nuevoEfectosPorTurno);
	                actualizarVistaColeccion(panelCartasVisual);
		            JOptionPane.showMessageDialog(this, "Atributos modificados.");
		            ActualizadorArchivos.getInstance().sobreEscribirSobres(SistemaImpl.getInstance().getColeccion());
	            } else if (c instanceof Energy) {
	            	String elemento = JOptionPane.showInputDialog("Nuevo elemento:");
	            	((Energy) c).setElemento(elemento);
	            	actualizarVistaColeccion(panelCartasVisual);
		            JOptionPane.showMessageDialog(this, "Atributos modificados.");
		            ActualizadorArchivos.getInstance().sobreEscribirSobres(SistemaImpl.getInstance().getColeccion());
	            }
	        	} catch (Exception ex) {
	        		JOptionPane.showMessageDialog(this, "entrada invalida, intente de nuevo.");
	        	}
	        } else {
	        	JOptionPane.showMessageDialog(this, "Carta no encontrada.");
	        }
	    });
	    
	    panel.add(btnAgregar);
	    panel.add(btnEliminar);
	    panel.add(btnModificar);
	    return panel;
	}
	private void actualizarVistaColeccion(JPanel panelCartas) {
	    panelCartas.removeAll(); 
	    
	    SistemaImpl.getInstance();
		
	    for (Carta c : SistemaImpl.getColeccion()) {
	        panelCartas.add(crearMiniaturaCarta(c));
	    }
	    panelCartas.revalidate(); 
	    panelCartas.repaint();    
	}
	
	private JPanel crearMiniaturaCarta(Carta carta) {
		JPanel mini = new JPanel();
	    mini.setLayout(new BoxLayout(mini, BoxLayout.Y_AXIS)); 

	    ImageIcon iconoOriginal = obtenerImagen(carta.getNombre());
	    
	    
	    Image imgEscalada = iconoOriginal.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
	    ImageIcon iconoFinal = new ImageIcon(imgEscalada);
 
	    JLabel labelImagen = new JLabel(iconoFinal);
	    mini.add(new JLabel(carta.getNombre()));
	    mini.add(labelImagen);
	    
	    mini.addMouseListener(new MouseAdapter() {
	     
	        public void mouseClicked(MouseEvent e) {
	        	JDialog detalle = new JDialog();
	            detalle.setTitle("Detalle: " + carta.getNombre());
	            detalle.setSize(500, 600); 
	            detalle.setLayout(new BorderLayout());

	            ImageIcon iconoOriginal = obtenerImagen(carta.getNombre());
	   
	            Image imgEscalada = iconoOriginal.getImage().getScaledInstance(400, 475, Image.SCALE_SMOOTH);
	            ImageIcon iconoDetalle = new ImageIcon(imgEscalada);
	            
	            JLabel imagenGrande = new JLabel(iconoDetalle);
	            detalle.add(imagenGrande, BorderLayout.CENTER);
	            int poder = carta.aceptarVisita(new CalculadorPoder());
	            if (carta instanceof Pokemon) {
	            	Pokemon p = (Pokemon) SistemaImpl.getInstance().buscarCarta(carta.getNombre());
	            	detalle.add(new JLabel("<html>Nombre: "+ p.getNombre() + "<br>" + "tipo: "+ p.getTipo() + "<br>" + "rareza: "+ p.getRareza() + "<br>" + "daño: "+ p.getDmg() + "<br>"+ "cantidad de energias: " + p.getEnergias() + "<br>" + "Poder Calculado: " + poder+"</html>"), BorderLayout.SOUTH);
	            	 detalle.setVisible(true);
	            } else if (carta instanceof Item) {
	            	Item i = (Item) SistemaImpl.getInstance().buscarCarta(carta.getNombre());
	            	detalle.add(new JLabel("<html>Nombre: "+ i.getNombre() + "<br>" + "tipo: "+ i.getTipo() + "<br>" + "rareza: "+ i.getRareza() + "<br>" + "buff: "+ i.getBuff() + "<br>" + "Poder Calculado: " + poder+"</html>"), BorderLayout.SOUTH);
	            	 detalle.setVisible(true);
	            } else if (carta instanceof Supporter) {
	            	Supporter s = (Supporter) SistemaImpl.getInstance().buscarCarta(carta.getNombre());
	            	detalle.add(new JLabel("<html>Nombre: "+ s.getNombre() + "<br>" + "tipo: "+ s.getTipo() + "<br>" + "rareza: "+ s.getRareza() + "<br>" + "efectos por turno: "+ s.getEfectoPorTurno() + "<br>" + "Poder Calculado: " + poder+"</html>"), BorderLayout.SOUTH);
	            	detalle.setVisible(true);
	            } else if (carta instanceof Energy) {
	            	Energy en = (Energy) SistemaImpl.getInstance().buscarCarta(carta.getNombre());
	            	detalle.add(new JLabel("<html>Nombre: "+ en.getNombre() + "<br>" + "tipo: "+ en.getTipo() + "<br>" + "rareza: "+ en.getRareza() + "<br>" + "elemento: "+ en.getElemento() + "<br>" + "Poder Calculado: " + poder+"</html>"), BorderLayout.SOUTH);
	            	detalle.setVisible(true);
	            }
	        }

	    });
	    return mini;
	}
	private ImageIcon obtenerImagen(String nombreCarta) {
	    String rutaCarta = "imagenes/" + nombreCarta + ".png";
	    File file = new File(rutaCarta);

	    if (file.exists()) {
	        return new ImageIcon(rutaCarta);
	    } else {
	    	return new ImageIcon("imagenes/default_card.png");
	    }
	}
}


