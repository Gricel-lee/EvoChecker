//==============================================================================
//	
//	Copyright (c) 2002-
//	Authors:
//	* Andrew Hinton <ug60axh@cs.bham.ac.uk> (University of Birmingham)
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

import java.awt.Component;

import javax.swing.JTable;

public interface SettingEditor
{
    public static final String NOT_CHANGED_VALUE = "$%^&*NOTCHANGED*&^%$";
    
    public Component getTableCellEditorComponent(JTable table, Setting owner, Object value, boolean isSelected, int row, int column);
    
    public void stopEditing();
    
    //throw the exception if the value that has been entered into the editor is invalid
    public Object getEditorValue() throws SettingException;
    
}
