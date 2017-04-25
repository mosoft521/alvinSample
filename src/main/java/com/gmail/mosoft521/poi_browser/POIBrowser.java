package com.gmail.mosoft521.poi_browser;

/**
 * Created by root on 2017/4/25 0025.
 */

import org.apache.poi.poifs.eventfilesystem.POIFSReader;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p>The main class of the POI Browser. It shows the structure of POI
 * filesystems (Microsoft Office documents) in a {@link
 * JTree}. Specify their filenames on the command line!</p>
 *
 * @see POIFSReader
 */
@SuppressWarnings("serial")
public class POIBrowser extends JFrame {

    /**
     * <p>The tree's root node must be visible to all methods.</p>
     */
    protected MutableTreeNode rootNode;


    /**
     * <p>Takes a bunch of file names as command line parameters,
     * opens each of them as a POI filesystem and displays their
     * internal structures in a {@link JTree}.</p>
     */
    public static void main(String[] args) {
        new POIBrowser().run(args);
    }


    protected void run(String[] args) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        /* Create the tree model with a root node. The latter is
         * invisible but it must be present because a tree model
         * always needs a root. */
        rootNode = new DefaultMutableTreeNode("POI Filesystems");
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);

        /* Create the tree UI element. */
        final JTree treeUI = new JTree(treeModel);
        getContentPane().add(new JScrollPane(treeUI));

        /* Add the POI filesystems to the tree. */
        int displayedFiles = 0;
        for (int i = 0; i < args.length; i++) {
            final String filename = args[i];
            try {
                FileInputStream fis = new FileInputStream(filename);
                POIFSReader r = new POIFSReader();
                r.registerListener(new TreeReaderListener(filename, rootNode));
                r.read(fis);
                fis.close();
                displayedFiles++;
            } catch (IOException ex) {
                System.err.println(filename + ": " + ex);
            } catch (Exception t) {
                System.err.println("Unexpected exception while reading \"" +
                        filename + "\":");
                t.printStackTrace(System.err);
            }
        }

        /* Exit if there is no file to display (none specified or only
         * files with problems). */
        if (displayedFiles == 0) {
            System.out.println("No POI filesystem(s) to display.");
            System.exit(0);
        }

        /* Make the tree UI element visible. */
        treeUI.setRootVisible(true);
        treeUI.setShowsRootHandles(true);
        ExtendableTreeCellRenderer etcr = new ExtendableTreeCellRenderer();
        etcr.register(DocumentDescriptor.class,
                new DocumentDescriptorRenderer());
        etcr.register(PropertySetDescriptor.class,
                new PropertySetDescriptorRenderer());
        treeUI.setCellRenderer(etcr);
        setSize(600, 450);
        setTitle("POI Browser 0.09");
        setVisible(true);
    }

}