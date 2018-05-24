package cn.sycu.meade.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CommonListener implements ServletContextListener,
		HttpSessionListener,ServletRequestListener {
    //服务器停止前运行
	public void contextDestroyed(ServletContextEvent arg0) {
	    System.out.println("contextDestroyed");

	}
    //服务器启动时运行
	public void contextInitialized(ServletContextEvent arg0) {
		int accessCount = 100;
		int onlineCount = 0;
		int loginCount = 0;
		
		ServletContext application = arg0.getServletContext();
		application.setAttribute("AccessCount",accessCount);
		application.setAttribute("OnlineCount",onlineCount);
		application.setAttribute("LoginCount",loginCount);
	}
    //创建session时运行
	public void sessionCreated(HttpSessionEvent arg0) {
		int accessCount = 100;
		int onlineCount = 0;
		int loginCount = 0;
		
		ServletContext application = arg0.getSession().getServletContext();
		if (application.getAttribute("AccessCount") != null)
			accessCount = (Integer)application.getAttribute("AccessCount");
		if(application.getAttribute("OnlineCount") != null)
			onlineCount = (Integer)application.getAttribute("OnlineCount");
		if(application.getAttribute("LoginCount") != null)
			onlineCount = (Integer)application.getAttribute("LoginCount");
		
		accessCount++ ;//访问量+1
		onlineCount++ ;//在线人数+1
		loginCount++;
		
		application.setAttribute("AccessCount",accessCount);
		application.setAttribute("OnlineCount",onlineCount);
		application.setAttribute("LoginCount",loginCount);
	}
    //session销毁时运行
	public void sessionDestroyed(HttpSessionEvent arg0) {
		int onlineCount = 0;
		
		ServletContext application = arg0.getSession().getServletContext();
		if(application.getAttribute("OnlineCount") != null)
			onlineCount = (Integer)application.getAttribute("OnlineCount");
		
		onlineCount--;//在线人数-1
		
		application.setAttribute("OnlineCount",onlineCount);

	}
	//请求结束时运行
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("requestDestroyed");
		
	}
	//创建请求对象时运行
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("requestInitialized");
		
	}
	

}
