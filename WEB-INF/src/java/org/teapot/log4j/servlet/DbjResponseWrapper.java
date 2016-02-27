package org.teapot.log4j.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * DbjResponseWrapper
 * @author DBJ(dubenju@126.com)
 *
 */
public class DbjResponseWrapper extends HttpServletResponseWrapper {

    /**
     * ServletOutputStream
     */
    private ServletOutputStream outStream;
    /**
     * PrintWriter
     */
    private PrintWriter writer;
    /**
     * DbjServletOutputStream
     */
    private DbjServletOutputStream traceOutStream;

    /**
     * DbjResponseWrapper
     * @param response
     */
    public DbjResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    /**
     * flushBuffer
     * @see javax.servlet.ServletResponseWrapper#flushBuffer()
     */
    @Override
    public void flushBuffer() throws IOException {
        if (this.writer != null) {
            this.writer.flush();
        } else if (this.outStream != null) {
            this.traceOutStream.flush();
        }
    }

    /**
     * getOutputStream
     * @see javax.servlet.ServletResponseWrapper#getOutputStream()
     */
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (this.writer != null) {
            throw new IllegalStateException("getWriter() has already been called on this response.");
        }

        if (this.outStream == null) {
            this.outStream = this.getResponse().getOutputStream();
            this.traceOutStream = new DbjServletOutputStream(this.outStream);
        }
        return this.traceOutStream;
    }

    /**
     * getWriter
     * @see javax.servlet.ServletResponseWrapper#getWriter()
     */
    @Override
    public PrintWriter getWriter() throws IOException {
        if (this.outStream != null) {
            throw new IllegalStateException("getOutputStream() has already been called on this response.");
        }

        if (this.writer == null) {
            this.traceOutStream = new DbjServletOutputStream(this.getResponse().getOutputStream());
            this.writer = new PrintWriter(new OutputStreamWriter(this.traceOutStream, this.getResponse().getCharacterEncoding()));
        }
        return this.writer;
    }

    /**
     * getOutput
     * @return
     */
    public byte[] getOutput() {
        if (this.traceOutStream != null) {
            return this.traceOutStream.getBytes();
        } else {
            return new byte[0];
        }
    }
}
