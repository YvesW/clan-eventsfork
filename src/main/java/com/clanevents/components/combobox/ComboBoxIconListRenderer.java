/*
 * Copyright (c) 2022, cmsu224 <https://github.com/cmsu224>
 * Copyright (c) 2022, Brianmm94 <https://github.com/Brianmm94>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.clanevents.components.combobox;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import lombok.Setter;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.components.ComboBoxListRenderer;
import net.runelite.client.util.Text;

/**
 * Based off the {@link ComboBoxListRenderer} but supports icons and default text value
 */
public final class ComboBoxIconListRenderer extends JLabel implements ListCellRenderer<Object>
{
    @Setter
    private String defaultText = "Select an option...";

    @Override
    public Component getListCellRendererComponent(JList list, Object o, int index, boolean isSelected, boolean cellHasFocus)
    {
        if (isSelected)
        {
            setBackground(ColorScheme.DARK_GRAY_COLOR);
            setForeground(Color.WHITE);
        }
        else
        {
            setBackground(list.getBackground());
            setForeground(ColorScheme.LIGHT_GRAY_COLOR);
        }

        setBorder(new EmptyBorder(5, 5, 5, 0));

        String text;
        setIcon(null);
        // If using setSelectedItem(null) or setSelectedIndex(-1) show default text until a selection is made
        if (index == -1 && o == null)
        {
            text = defaultText;
        }
        else if (o instanceof Enum)
        {
            text = Text.titleCase((Enum<?>) o);
        }
        else if (o instanceof ComboBoxIconEntry)
        {
            final ComboBoxIconEntry e = (ComboBoxIconEntry) o;
            text = e.getText();
            setIcon(e.getIcon());
        }
        else
        {
            text = o.toString();
        }

        setText(text);

        return this;
    }
}