//==============================================================================
//	
//	Copyright (c) 2002-
//	Authors:
//	* Andrew Hinton <ug60axh@cs.bham.ac.uk> (University of Birmingham)
//	* Dave Parker <david.parker@comlab.ox.ac.uk> (University of Oxford, formerly University of Birmingham)
//	
//------------------------------------------------------------------------------
//	
//	This file is part of PRISM.
//	
//	PRISM is free software; you can redistribute it and/or modify
//	it under the terms of the GNU General Public License as published by
//	the Free Software Foundation; either version 2 of the License, or
//	(at your option) any later version.
//	
//	PRISM is distributed in the hope that it will be useful,
//	but WITHOUT ANY WARRANTY; without even the implied warranty of
//	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//	GNU General Public License for more details.
//	
//	You should have received a copy of the GNU General Public License
//	along with PRISM; if not, write to the Free Software Foundation,
//	Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
//	
//==============================================================================

package userinterface;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import userinterface.util.GUIException;
import userinterface.util.PresentationMetalTheme;

public class GUIGeneralOptions extends OptionsPanel
{
    private GUIPrism gui;
    private int last;
    private int adjust;
    /** Creates new form GUIGeneralOptions */
    public GUIGeneralOptions(GUIPrism gui)
    {
        super("General");
        initComponents();
        this.gui = gui;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents()//GEN-BEGIN:initComponents
    {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        adjustSlider = new javax.swing.JSlider();

        setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(jPanel3, gridBagConstraints);

        jLabel1.setLabelFor(adjustSlider);
        jLabel1.setText("User interface size:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jLabel1, gridBagConstraints);

        adjustSlider.setMajorTickSpacing(5);
        adjustSlider.setMaximum(5);
        adjustSlider.setMinimum(-5);
        adjustSlider.setMinorTickSpacing(1);
        adjustSlider.setPaintLabels(true);
        adjustSlider.setPaintTicks(true);
        adjustSlider.setSnapToTicks(true);
        adjustSlider.setValue(0);
        add(adjustSlider, new java.awt.GridBagConstraints());

    }//GEN-END:initComponents
    
    public void apply()
    {
    
        int adjust = 0;
        
        adjust = adjustSlider.getValue();
        if(adjust != last)
        {
        //System.out.println("adjustment = "+adjust);
        try
        {
            MetalTheme theme = new PresentationMetalTheme(adjust);
            MetalLookAndFeel.setCurrentTheme(theme);
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        }
        catch(Exception e)
        {
            //System.out.println("Exception");
            return;
        }
        
        SwingUtilities.updateComponentTreeUI(gui);
        SwingUtilities.updateComponentTreeUI(gui.getOptions());
        
        gui.pack();
        gui.getOptions().pack();
        gui.addNotify();
        last = adjust;
        }
    }
    
    public void synchronizeGUI()
    {
        //not needed
        adjust = adjustSlider.getValue();
        last = adjust;
    }
    
    public void defaultGUI()
    {
        adjustSlider.setValue(0);
        
    }
    
    public boolean valid() throws GUIException
    {
        return true;
    }

	public Element saveXMLElement(Document doc) throws DOMException
	{
		return doc.createElement("dummy");
	}
    
	public void loadXMLElement(Element element)
	{
	}
    
        public void loadProperties(java.util.Properties properties)
        {
        }        
    
        public void loadXMLElement(Document doc, Element element)
        {
        }
        
        public void saveProperties(java.util.Properties properties)
        {
        }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JSlider adjustSlider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
    
    
}
