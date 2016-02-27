package org.teapot.log4j.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

// TODO:Services
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 所有的Servlet过滤器都必须实现javax.servlet.Filter接口
 * @author DBJ(dubenju@126.com)
 *
 */
public abstract class AbstractFilter implements Filter {

	// TODO:Services
    Logger log = LoggerFactory.getLogger(AbstractFilter.class);

    /**
     * Servlet过滤器的初始化方法，Servlet容器创建Servlet过滤器实例后将调用该方法。
     * 该方法将读取web.xml文件中Servlet过滤器的初始化参数。
     * Filter的init方法中提供了一个FilterConfig对象，提供相关的操作：
     * 如获取Filter中配置的初始化参数：
     * <filter>
     *     <filter-name>LoginFilter</filter-name>
     *     <filter-class>com.itzhai.login.LoginFilter</filter-class>
     *     <init-param>
     *         <param-name>username</param-name>
     *        <param-value>arthinking</param-value>
     *     </init-param>
     * </filter>
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(this.getClass().getName() + ".init");
        //获取Filter初始化参数
        String username = filterConfig.getInitParameter("username");
        ServletContext context = filterConfig.getServletContext();
    }

    /**
     * 该方法完成实际的过滤操作，当客户端请求方法与过滤器设置匹配的URL时，Servlet容器将先调用过滤器的doFilter方法。
     * FilterChain用户访问后续过滤器。
     * 这里的ServletRequest和ServletResponse一般需要转换成具体的Servlet实现对于的对象，如：HttpServletRequest和HttpServletResponse。
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    }

    /**
     * Servlet容器在销毁过滤器实例前调用该方法，在该方法中释放Servlet过滤器占用的资源。
     */
    @Override
    public void destroy() {
        log.info(this.getClass().getName() + ".destroy");
    }
}
