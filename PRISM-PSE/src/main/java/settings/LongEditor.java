//==============================================================================
//	
//	Copyright (c) 2002-
//	Authors:
//	* Dave Parker <d.a.parker@cs.bham.ac.uk> (University of Birmingham/Oxford)
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

package settings;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class LongEditor implements SettingEditor, CaretListener, FocusListener
{
    private JTextField field;
    private Font font = new Font("monospaced", Font.PLAIN, 12);
    private Font font2 = new Font("monospaced", Font.PLAIN, 12);
    
    private JTable lastTable = null;
    private int tableCol = -1;
    private int tableRow = -1;
    
    private boolean valueGot = true;
    
    /** Creates a new instance of LongEditor */
    public LongEditor()
    {
        field = new JTextField();
        field.addCaretListener(this);
        field.addFocusListener(this);
    }
    
    public Object getEditorValue() throws SettingException
    {
        if(field.getText().equals("")) return NOT_CHANGED_VALUE;
        valueGot = true;
        try
        {
            return new Long(field.getText());
        }
        catch(NumberFormatException e)
        {
            throw new SettingException("The value entered is not a valid number.");
        }
    }
    
    public Component getTableCellEditorComponent(JTable table, Setting owner, Object value, boolean isSelected, int row, int column)
    {
        field.setMargin(new Insets(0, 2, 4, 2));
        if (isSelected)
        {
            field.setForeground(table.getSelectionForeground());
            field.setBackground(table.getSelectionBackground());
        }
        else
        {
            field.setForeground(table.getForeground());
            field.setBackground(table.getBackground());
        }
        
        
        field.setBorder( UIManager.getBorder("Table.focusCellHighlightBorder") );
        
        
        if(value instanceof Long)
        {
            Long str = (Long)value;
            
            field.setText(str.toString());
            
            
            field.setCaretColor(Color.black);
            
            field.setFont(font);
        }
        else if(value instanceof ArrayList)
        {
            ArrayList values = (ArrayList)value;
            if(values.size() > 0)
            {
                //if we have multiple properties selected.
                Long last = null;
                boolean allSame = true;
                for(int i = 0; i < values.size(); i++)
                {
                    if(values.get(i) instanceof Long)
                    {
                        Long str = (Long)values.get(i);
                        if(last != null)
                        {
                            if(!str.equals(last))
                            {
                                allSame = false; break;
                            }
                            last = str;
                        }
                        else
                        {
                            last = str;
                        }
                    }
                }
                if(allSame)
                {
                    field.setText(""+last.longValue());
                    
                    field.setFont(font);
                }
                else
                {
                    field.setText("");
                    
                    field.setFont(font2);
                }
                
            }
        }
        
        lastTable = table;
        tableRow = row;
        tableCol = column;
        valueGot = false;
        
        return field;
    }
    
    public void stopEditing()
    {
    }
    
    public void caretUpdate(CaretEvent e)
    {
        field.getCaret().setVisible(true);
    }
    
    public void focusGained(FocusEvent e)
    {
    }
    
    public void focusLost(FocusEvent e)
    {
        if(!valueGot && lastTable != null)
        {
            valueGot = true;
            Object value;
            if(field.getText().equals(""))
                value = NOT_CHANGED_VALUE;
            else
            {
                try
                {
                    value = new Long(field.getText());
                }
                catch(NumberFormatException ex)
                {
                    value = new SettingException("The value entered is not a valid number.");
                }
            }
            lastTable.setValueAt(value, tableRow, tableCol);
            if(lastTable.getCellEditor() != null) lastTable.removeEditor();
        }
    }
    
}
