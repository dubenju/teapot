package org.teapot.log4j.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



//TODO:Services
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.teapot.log4j.servlet.DbjResponseWrapper;

/**
 * HTTP报文
 * @author DBJ(dubenju@126.com)
 *
 */
public class TraceHttpFilter extends AbstractFilter {
	// TODO:Services
    Logger log = LoggerFactory.getLogger(TraceHttpFilter.class);

    /**
     * doFilter
     * @see dbj.filter.AbstractFilter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("---------------------------------");
        log.info(this.getClass().getName() + ".doFilter + request=" + request );
        log.info(this.getClass().getName() + ".doFilter + response=" + response );
        log.info(this.getClass().getName() + ".doFilter + chain=" + chain );

        // HTTP请求报文由请求行（request line）、请求头部（header）、空行和请求数据4部分组成
        HttpServletRequest req = (HttpServletRequest) request;
        log.info("HttpServletRequest---------------------------------");

        // 请求行 请求行由请求方法字段、URL字段和HTTP协议版本字段3个字段组成，它们用空格分隔。
        // 请求方法
        System.out.print(req.getMethod());
        System.out.print(" ");
        // URL
        System.out.print(req.getRequestURL());
        System.out.println(" ");

        // HTTP协议版本
        log.info(req.getProtocol());

        //请求头部由关键字/值对组成，每行一对，关键字和值用英文冒号“:”分隔。请求头部通知服务器有关于客户端请求的信息。
        // System.out.print("Accept:" + req.getHeader("Accept"));
        log.info("HTTP Header:");
        Enumeration<String> qhns = req.getHeaderNames();
        while (qhns.hasMoreElements()) {
            String headerName = qhns.nextElement();
            log.info("  " + headerName + ":" + req.getHeader(headerName));
        }

        // 空行，发送回车符和换行符，通知服务器以下不再有请求头。
        log.info(" ");

        // 请求数据不在GET方法中使用，而是在POST方法中使用。POST方法适用于需要客户填写表单的场合。
        // 与请求数据相关的最常使用的请求头是Content-Type和Content-Length。
        String contentType = req.getContentType();
        String splitRegex = "";
        log.info("ContentType:" + contentType); // text/plain
        if (contentType == null) {
        } else if ("text/plain".equalsIgnoreCase(contentType)) {
            splitRegex = "\r\n";
        } else if ("application/x-www-form-urlencoded".equalsIgnoreCase(contentType)) {
            splitRegex = "&";
        } else if (contentType.toLowerCase().startsWith("multipart/form-data")) {
            // multipart/form-data; boundary=---------------------------7e1234101d0726
            splitRegex = contentType.substring(contentType.indexOf("; boundary=") + "; boundary=".length());
        }
        long contentLength = req.getContentLengthLong();
        log.info("ContentLength:" + contentLength);
        if (contentLength > 0) {
            int len = Integer.MAX_VALUE;
            if (contentLength < (len - 1)) {
                len = (int) (contentLength + 1);
            }
            BufferedReader bfr = req.getReader();
            if (bfr.markSupported() == true) {
                bfr.mark(len);
            } else {
                log.info("mark/reset not Supported");
            }
            char[] cbuf = new char[len];
            bfr.read(cbuf);
            String line = new String(cbuf);
            log.info(line);
            if("application/x-www-form-urlencoded".equalsIgnoreCase(contentType)) {
                line = URLDecoder.decode(line, "UTF-8");
                log.info(line);
            }
            bfr.reset();
            String[] sary = line.split(splitRegex); // ContentType
            for (int i = 0; i < sary.length; i ++) {
                String s = sary[i];
                String[] kv = s.split("=");
                if (kv.length ==2) {
                    log.info(kv[0] + ":" + kv[1]);
                    req.setAttribute(kv[0], kv[1]);
                }
            }
        }

        log.info("Request Sent Server :" + req.getServerName() + ":" + req.getServerPort());
        log.info("Request Remote Client :"+ req.getRemoteUser() + "@" + req.getRemoteHost() + "(" + req.getRemoteAddr() + ")");
        log.info("Local:" + req.getLocalName() + "(" + req.getLocalAddr() + "):" + req.getLocalPort());

        log.info("AuthType :" + req.getAuthType());
        log.info("CharacterEncoding :" + req.getCharacterEncoding());
        log.info("Scheme :" + req.getScheme());
        log.info("Parameter :");
        Enumeration<String> qpns = req.getParameterNames();
        while (qpns.hasMoreElements()) {
            String headerName = qpns.nextElement();
            log.info("  " + headerName + ":" + req.getParameter(headerName));
            req.setAttribute(headerName, req.getParameter(headerName));
        }
        log.info("Attribute :");
        Enumeration<String> qans = req.getAttributeNames();
        while (qans.hasMoreElements()) {
            String headerName = qans.nextElement();
            log.info("  " + headerName + ":" + req.getAttribute(headerName));
        }
        String sessionId = null;
        HttpSession session = req.getSession();
        if (session != null) {
            sessionId = session.getId();
        } else {
            sessionId = req.getRequestedSessionId();
        }
        log.info("SessionId :" + sessionId);
        Cookie cookie = null;
        Cookie[] cookies = null;
        // 获取与该域相关的 Cookie 的数组
        cookies = ((HttpServletRequest) request).getCookies();
        if( cookies != null ) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                log.info("Cookie name:" + cookie.getName());
//                if((cookie.getName()).compareTo("name") == 0 ) {
                     //cookie.setMaxAge(0);
                     //((HttpServletResponse) response).addCookie(cookie);
//                }
            }
        }
        log.info("---------------------------------HttpServletRequest");

        ServletContext context = req.getSession().getServletContext();

        if (response.getCharacterEncoding() == null) {
            response.setCharacterEncoding("UTF-8"); // Or whatever default. UTF-8 is good for World Domination.
        }
        HttpServletResponse res = (HttpServletResponse) response;
        DbjResponseWrapper resw = new DbjResponseWrapper(res);


        chain.doFilter(request, resw);
        resw.flushBuffer();

        log.info("HttpServletResponse---------------------------------");
        // HTTP响应也由4部分组成，分别是：状态行(status-line)、消息报头(headers)、空行(blank line)和响应正文(response-body)。
        // 状态行  HTTP-Version表示服务器HTTP协议的版本；
        // Status-Code表示服务器发回的响应状态代码；
        // Reason-Phrase表示状态代码的文本描述。状态代码由三位数字组成，第一个数字定义了响应的类别，且有五种可能取值。
        log.info("Status=" + res.getStatus());

        //消息报头
        log.info("HTTP Header:");
        Collection<String> shns = res.getHeaderNames();
        for (String headerName : shns) {
            log.info("  " + headerName + ":" + res.getHeader(headerName));
        }

        // 空行，发送回车符和换行符，通知浏览器以下不再有请求头。
        log.info("\n");

        // 响应正文
        log.info("ContentType:" + res.getContentType());
        log.info("BufferSize:" + res.getBufferSize());
        System.out.print("CharacterEncoding:" + res.getCharacterEncoding());
        byte[] copy = resw.getOutput();
        log.info(new String(copy, response.getCharacterEncoding()));
        log.info("---------------------------------HttpServletResponse");
    }
}
