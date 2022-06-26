package com.example.task.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "sessionFilter", urlPatterns = {"/*"})
public class ApiFilter implements Filter {
    //标示符：表示当前用户未登录(可根据自己项目需要改为json样式)
    String NO_LOGIN = "您还未登录";
    //不需要登录就可以访问的路径(比如:注册登录等)
    String[] includeUrls = new String[]{"/user/login", "/user/register"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("-----过滤器开始启动-----");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(3000000);//以秒为单位  设置session失效时间
        String uri = req.getRequestURI();
        log.info("#####filter url:" + uri);
        //是否需要过滤
        boolean needFilter = isNeedFilter(uri);
        if (!needFilter) { //不需要过滤直接传给下一个过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        } else { //需要过滤器
            // session中包含user对象,则是登录状态
            if (session != null && session.getAttribute("user") != null) {
                filterChain.doFilter(req, resp);
            } else {
                String requestType = req.getHeader("X-Requested-With");
                //判断是否是ajax请求
                if (requestType != null && "XMLHttpRequest".equals(requestType)) {
                    resp.getWriter().write(this.NO_LOGIN);
                } else {
                    resp.setStatus(10001);
                    //重定向到登录页(需要在static文件夹下建立此html文件)
                    //重定向到nginx首页
                  /*  resp.setHeader("Access-Control-Allow-Origin", "*");
                    resp.setHeader("Cache-Control","no-cache");
                    resp.sendRedirect("https://www.baidu.com/");*/
                   /* resp.setContentType("text/html;charset=utf-8");
                    //设置状态码

                 /*   String originHeader = req.getHeader("Origin");
                    resp.setHeader("Access-Control-Allow-Origin", originHeader);*/
                    /*String  url =  req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath();*/
                }
                return;
            }
        }
    }

    @Override
    public void destroy() {
        log.info("-----过滤器开始摧毁-----");
    }

    /**
     * @Description: 是否需要过滤
     */
    public boolean isNeedFilter(String uri) {
        for (String includeUrl : includeUrls) {
            if (includeUrl.equals(uri)) {
                return false;
            }
        }
        return true;
    }

}
