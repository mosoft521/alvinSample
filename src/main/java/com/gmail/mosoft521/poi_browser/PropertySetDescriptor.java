package com.gmail.mosoft521.poi_browser;

import org.apache.poi.hpsf.MarkUnsupportedException;
import org.apache.poi.hpsf.NoPropertySetStreamException;
import org.apache.poi.hpsf.PropertySet;
import org.apache.poi.hpsf.PropertySetFactory;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.POIFSDocumentPath;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * <p>Describes the most important (whatever that is) features of a
 * stream containing a {@link PropertySet}.</p>
 */
public class PropertySetDescriptor extends DocumentDescriptor {

    protected PropertySet propertySet;

    /**
     * <p>Returns this {@link PropertySetDescriptor}'s {@link
     * PropertySet}.</p>
     */
    public PropertySet getPropertySet() {
        return propertySet;
    }


    /**
     * <p>Creates a {@link PropertySetDescriptor} by reading a {@link
     * PropertySet} from a {@link DocumentInputStream}.</p>
     *
     * @param name            The stream's name.
     * @param path            The stream's path in the POI filesystem hierarchy.
     * @param stream          The stream.
     * @param nrOfBytesToDump The maximum number of bytes to display in a
     *                        dump starting at the beginning of the stream.
     */
    public PropertySetDescriptor(final String name,
                                 final POIFSDocumentPath path,
                                 final DocumentInputStream stream,
                                 final int nrOfBytesToDump)
            throws NoPropertySetStreamException,
            MarkUnsupportedException, UnsupportedEncodingException,
            IOException {
        super(name, path, stream, nrOfBytesToDump);
        propertySet = PropertySetFactory.create(stream);
    }

}