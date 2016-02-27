/**
 *
 */
package org.teapot.servlet;


import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.descriptor.JspConfigDescriptor;

/**
 * @author DBJ
 *
 */
public class TeapotServletContext implements ServletContext {

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getContextPath()
     */
    @Override
    public String getContextPath() {
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getContext(java.lang.String)
     */
    @Override
    public ServletContext getContext(String paramString) {
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getMajorVersion()
     */
    @Override
    public int getMajorVersion() {
        return 0;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getMinorVersion()
     */
    @Override
    public int getMinorVersion() {
        return 0;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getEffectiveMajorVersion()
     */
    @Override
    public int getEffectiveMajorVersion() {
        return 0;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getEffectiveMinorVersion()
     */
    @Override
    public int getEffectiveMinorVersion() {
        return 0;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getMimeType(java.lang.String)
     */
    @Override
    public String getMimeType(String paramString) {
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getResourcePaths(java.lang.String)
     */
    @Override
    public Set<String> getResourcePaths(String paramString) {
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getResource(java.lang.String)
     */
    @Override
    public URL getResource(String paramString) throws MalformedURLException {
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getResourceAsStream(java.lang.String)
     */
    @Override
    public InputStream getResourceAsStream(String paramString) {
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getRequestDispatcher(java.lang.String)
     */
    @Override
    public RequestDispatcher getRequestDispatcher(String paramString) {
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getNamedDispatcher(java.lang.String)
     */
    @Override
    public RequestDispatcher getNamedDispatcher(String paramString) {
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getServlet(java.lang.String)
     */
    @Override
    public Servlet getServlet(String paramString) throws ServletException {
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getServlets()
     */
    @Override
    public Enumeration<Servlet> getServlets() {
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getServletNames()
     */
    @Override
    public Enumeration<String> getServletNames() {
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#log(java.lang.String)
     */
    @Override
    public void log(String paramString) {
        System.out.println(paramString);
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#log(java.lang.Exception, java.lang.String)
     */
    @Override
    public void log(Exception paramException, String paramString) {
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#log(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void log(String paramString, Throwable paramThrowable) {
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getRealPath(java.lang.String)
     */
    @Override
    public String getRealPath(String paramString) {
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getServerInfo()
     */
    @Override
    public String getServerInfo() {
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getInitParameter(java.lang.String)
     */
    @Override
    public String getInitParameter(String paramString) {
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getInitParameterNames()
     */
    @Override
    public Enumeration<String> getInitParameterNames() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#setInitParameter(java.lang.String, java.lang.String)
     */
    @Override
    public boolean setInitParameter(String paramString1, String paramString2) {
        // TODO 自動生成されたメソッド・スタブ
        return false;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getAttribute(java.lang.String)
     */
    @Override
    public Object getAttribute(String paramString) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getAttributeNames()
     */
    @Override
    public Enumeration<String> getAttributeNames() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#setAttribute(java.lang.String, java.lang.Object)
     */
    @Override
    public void setAttribute(String paramString, Object paramObject) {
        // TODO 自動生成されたメソッド・スタブ

    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#removeAttribute(java.lang.String)
     */
    @Override
    public void removeAttribute(String paramString) {
        // TODO 自動生成されたメソッド・スタブ

    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getServletContextName()
     */
    @Override
    public String getServletContextName() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#addServlet(java.lang.String, java.lang.String)
     */
    @Override
    public Dynamic addServlet(String paramString1, String paramString2) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#addServlet(java.lang.String, javax.servlet.Servlet)
     */
    @Override
    public Dynamic addServlet(String paramString, Servlet paramServlet) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#addServlet(java.lang.String, java.lang.Class)
     */
    @Override
    public Dynamic addServlet(String paramString,
            Class<? extends Servlet> paramClass) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#createServlet(java.lang.Class)
     */
    @Override
    public <T extends Servlet> T createServlet(Class<T> paramClass)
            throws ServletException {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getServletRegistration(java.lang.String)
     */
    @Override
    public ServletRegistration getServletRegistration(String paramString) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getServletRegistrations()
     */
    @Override
    public Map<String, ? extends ServletRegistration> getServletRegistrations() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#addFilter(java.lang.String, java.lang.String)
     */
    @Override
    public javax.servlet.FilterRegistration.Dynamic addFilter(
            String paramString1, String paramString2) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#addFilter(java.lang.String, javax.servlet.Filter)
     */
    @Override
    public javax.servlet.FilterRegistration.Dynamic addFilter(
            String paramString, Filter paramFilter) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#addFilter(java.lang.String, java.lang.Class)
     */
    @Override
    public javax.servlet.FilterRegistration.Dynamic addFilter(
            String paramString, Class<? extends Filter> paramClass) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#createFilter(java.lang.Class)
     */
    @Override
    public <T extends Filter> T createFilter(Class<T> paramClass)
            throws ServletException {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getFilterRegistration(java.lang.String)
     */
    @Override
    public FilterRegistration getFilterRegistration(String paramString) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getFilterRegistrations()
     */
    @Override
    public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getSessionCookieConfig()
     */
    @Override
    public SessionCookieConfig getSessionCookieConfig() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#setSessionTrackingModes(java.util.Set)
     */
    @Override
    public void setSessionTrackingModes(Set<SessionTrackingMode> paramSet) {
        // TODO 自動生成されたメソッド・スタブ

    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getDefaultSessionTrackingModes()
     */
    @Override
    public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getEffectiveSessionTrackingModes()
     */
    @Override
    public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#addListener(java.lang.String)
     */
    @Override
    public void addListener(String paramString) {
        // TODO 自動生成されたメソッド・スタブ

    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#addListener(java.util.EventListener)
     */
    @Override
    public <T extends EventListener> void addListener(T paramT) {
        // TODO 自動生成されたメソッド・スタブ

    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#addListener(java.lang.Class)
     */
    @Override
    public void addListener(Class<? extends EventListener> paramClass) {
        // TODO 自動生成されたメソッド・スタブ

    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#createListener(java.lang.Class)
     */
    @Override
    public <T extends EventListener> T createListener(Class<T> paramClass)
            throws ServletException {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getJspConfigDescriptor()
     */
    @Override
    public JspConfigDescriptor getJspConfigDescriptor() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getClassLoader()
     */
    @Override
    public ClassLoader getClassLoader() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#declareRoles(java.lang.String[])
     */
    @Override
    public void declareRoles(String... paramVarArgs) {
        // TODO 自動生成されたメソッド・スタブ

    }

    /* (非 Javadoc)
     * @see javax.servlet.ServletContext#getVirtualServerName()
     */
    @Override
    public String getVirtualServerName() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

}
