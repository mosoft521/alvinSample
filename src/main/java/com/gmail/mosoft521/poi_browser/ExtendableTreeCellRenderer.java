package com.gmail.mosoft521.poi_browser;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>This is a {@link TreeCellRenderer} implementation which is able
 * to render arbitrary objects. The {@link ExtendableTreeCellRenderer}
 * does not do the rendering itself but instead dispatches to
 * class-specific renderers. A class/renderer pair must be registered
 * using the {@link #register} method. If a class has no registered
 * renderer, the renderer of its closest superclass is used. Since the
 * {@link ExtendableTreeCellRenderer} always has a default renderer
 * for the {@link Object} class, rendering is always possible. The
 * default {@link Object} renderer can be replaced by another renderer
 * but it cannot be unregistered.</p>
 */
public class ExtendableTreeCellRenderer implements TreeCellRenderer {

    /**
     * <p>Maps classes to renderers.</p>
     */
    protected Map<Class<?>, TreeCellRenderer> renderers;


    public ExtendableTreeCellRenderer() {
        renderers = new HashMap<Class<?>, TreeCellRenderer>();
        register(Object.class, new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent
                    (JTree tree, Object value, boolean selectedCell,
                     boolean expanded, boolean leaf, int row, boolean hasCellFocus) {
                final String s = value.toString();
                final JLabel l = new JLabel(s + "  ");
                if (selected) {
                    Util.invert(l);
                    l.setOpaque(true);
                }
                return l;
            }
        });
    }


    /**
     * <p>Registers a renderer for a class.</p>
     **/
    public void register(final Class<?> c, final TreeCellRenderer renderer) {
        renderers.put(c, renderer);
    }


    /**
     * <p>Unregisters a renderer for a class. The renderer for the
     * {@link Object} class cannot be unregistered.</p>
     */
    public void unregister(final Class<?> c) {
        if (c == Object.class)
            throw new IllegalArgumentException
                    ("Renderer for Object cannot be unregistered.");
        renderers.put(c, null);
    }


    /**
     * <p>Renders an object in a tree cell depending of the object's
     * class.</p>
     *
     * @see TreeCellRenderer#getTreeCellRendererComponent
     */
    @Override
    public Component getTreeCellRendererComponent
    (final JTree tree, final Object value, final boolean selected,
     final boolean expanded, final boolean leaf, final int row,
     final boolean hasFocus) {
        final String NULL = "null";
        TreeCellRenderer r;
        Object userObject;
        if (value == null)
            userObject = NULL;
        else {
            userObject = ((DefaultMutableTreeNode) value).getUserObject();
            if (userObject == null)
                userObject = NULL;
        }
        r = findRenderer(userObject.getClass());
        return r.getTreeCellRendererComponent
                (tree, value, selected, expanded, leaf, row,
                        hasFocus);
    }


    /**
     * <p>Find the renderer for the specified class.</p>
     */
    protected TreeCellRenderer findRenderer(final Class<?> c) {
        final TreeCellRenderer r = renderers.get(c);
        if (r != null)
            /* The class has a renderer. */
            return r;

        /* The class has no renderer, try the superclass, if any. */
        final Class<?> superclass = c.getSuperclass();
        if (superclass != null) {
            return findRenderer(superclass);
        }
        return null;
    }

}