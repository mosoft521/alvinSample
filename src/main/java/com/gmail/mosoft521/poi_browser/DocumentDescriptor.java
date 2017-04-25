package com.gmail.mosoft521.poi_browser;

import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.POIFSDocumentPath;

import java.io.IOException;

/**
 * <p>Describes the most important (whatever that is) features of a
 * {@link POIFSDocument}.</p>
 */
public class DocumentDescriptor {
    String name;
    POIFSDocumentPath path;
    DocumentInputStream stream;

    int size;
    byte[] bytes;


    /**
     * <p>Creates a {@link DocumentDescriptor}.</p>
     *
     * @param name      The stream's name.
     * @param path      The stream's path in the POI filesystem hierarchy.
     * @param stream    The stream.
     * @param nrOfBytes The maximum number of bytes to display in a
     *                  dump starting at the beginning of the stream.
     */
    public DocumentDescriptor(final String name,
                              final POIFSDocumentPath path,
                              final DocumentInputStream stream,
                              final int nrOfBytes) {
        this.name = name;
        this.path = path;
        this.stream = stream;
        try {
            size = stream.available();
            if (stream.markSupported()) {
                stream.mark(nrOfBytes);
                final byte[] b = new byte[nrOfBytes];
                final int read = stream.read(b, 0, Math.min(size, b.length));
                bytes = new byte[read];
                System.arraycopy(b, 0, bytes, 0, read);
                stream.reset();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}