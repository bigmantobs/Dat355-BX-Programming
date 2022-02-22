package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

public class LogoAsymmLens extends JComponent {
	private static final long serialVersionUID = 1L;
	private Image asymm;
	public LogoAsymmLens(){
	    this.asymm = Toolkit.getDefaultToolkit().getImage("asymmLens.png");
	    if ( asymm != null ) repaint();
	}
 @Override
 	protected void paintComponent( Graphics g ){
	    if ( asymm != null )g.drawImage( asymm, 0, 0, this );
	}
}
