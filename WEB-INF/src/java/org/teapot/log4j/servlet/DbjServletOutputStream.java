package org.teapot.log4j.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * DbjServletOutputStream
 * @author DBJ(dubenju@126.com)
 *
 */
public class DbjServletOutputStream extends ServletOutputStream {

    /**
     * OutputStream
     */
    private OutputStream outputStream;
    /**
     * ByteArrayOutputStream
     */
    private ByteArrayOutputStream aryOutStream;

    /**
     * DbjServletOutputStream
     * @param outputStream
     */
    public DbjServletOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.aryOutStream = new ByteArrayOutputStream(1024);
    }

    /**
     * isReady
     */
    @Override
    public boolean isReady() {
        return false;
    }

    /**
     * setWriteListener
     */
    @Override
    public void setWriteListener(WriteListener paramWriteListener) {
    }

    /**
     * write
     */
    @Override
    public void write(int b) throws IOException {
        this.outputStream.write(b);
        this.aryOutStream.write(b);
    }

    /**
     * getBytes
     * @return byte[]
     */
    public byte[] getBytes() {
        return this.aryOutStream.toByteArray();
    }
}
