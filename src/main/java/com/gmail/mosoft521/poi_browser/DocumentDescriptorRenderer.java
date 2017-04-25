package com.gmail.mosoft521.poi_browser;

import org.apache.poi.util.HexDump;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

/**
 * <p>{@link TreeCellRenderer} for a {@link DocumentDescriptor}. The
 * renderer is extremly rudimentary since displays only the document's
 * name, its size and its fist few bytes.</p>
 */
public class DocumentDescriptorRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(final JTree tree,
                                                  final Object value,
                                                  final boolean selectedCell,
                                                  final boolean expanded,
                                                  final boolean leaf,
                                                  final int row,
                                                  final boolean hasCellFocus) {
        final DocumentDescriptor d = (DocumentDescriptor)
                ((DefaultMutableTreeNode) value).getUserObject();
        final JPanel p = new JPanel();
        final JTextArea text = new JTextArea();
        text.append(renderAsString(d));
        text.setFont(new Font("Monospaced", Font.PLAIN, 10));
        p.add(text);
        if (selectedCell) {
            Util.invert(text);
        }
        return p;
    }


    /**
     * <p>Renders {@link DocumentDescriptor} as a string.</p>
     */
    protected String renderAsString(final DocumentDescriptor d) {
        final StringBuilder b = new StringBuilder();
        b.append("Name: ");
        b.append(d.name);
        b.append(" ");
        b.append(HexDump.toHex(d.name));
        b.append("\n");

        b.append("Size: ");
        b.append(d.size);
        b.append(" bytes\n");

        b.append("First bytes: ");
        b.append(HexDump.toHex(d.bytes));

        return b.toString();
    }

}