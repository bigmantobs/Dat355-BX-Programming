package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

public class LogoSymmLens extends JComponent {
	private static final long serialVersionUID = 1L;
	private Image symm;
	public LogoSymmLens(){
	    this.symm = Toolkit.getDefaultToolkit().getImage("symmLens.png");
	    if ( symm != null ) repaint();
	}
 @Override
 	protected void paintComponent( Graphics g ){
	    if ( symm != null )g.drawImage( symm, 0, 0, this );
	}
}
