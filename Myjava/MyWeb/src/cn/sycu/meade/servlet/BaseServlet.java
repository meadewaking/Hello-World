package cn.sycu.meade.servlet;

import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class BaseServlet extends HttpServlet {

	public Cookie getCookie(String name, HttpServletRequest request) { // 获取cookie
		Cookie[] cookis = request.getCookies();
		for (int i = 0; cookis != null && i < cookis.length; i++) { // 在cookis列表中找到用户名并读取
			if (cookis[i].getName().compareTo("LoginName") == 0) {
				// request.setAttribute("LoginName", cookis[i].getValue());
				return cookis[i];
			}
		}
		return null;
	}
	
	public Timestamp getNowTimestamp(){			//获取timestamp格式的当前系统时间
		return new Timestamp(System.currentTimeMillis());
	}
	
	public Date getNowDate(){			//获取timestamp格式的当前系统时间
		return new Date(System.currentTimeMillis());
	}
	
	public String getStringParameter(String name,HttpServletRequest request){
		if(request.getParameter(name) != null)		//通过request获取值（值为string）
			return request.getParameter(name);
		return "";
	}
	
	public boolean getBooleanParameter(String name,HttpServletRequest request){
		if(request.getParameter(name) != null){
			try{
				return Boolean.parseBoolean(request.getParameter(name));
			}catch (Exception ex){
				return false;
			}
		}
		return false;
	}
	
	public int getIntegerParameter(String name,HttpServletRequest request){
		if(request.getParameter(name) != null){
			try{
				return Integer.parseInt(request.getParameter(name));
			}catch (Exception ex){
				return 0;
			}
		}
		return 0;
	}
	public float getFloatParameter(String name,HttpServletRequest request){
		if(request.getParameter(name) != null){
			try{
				return Float.parseFloat(request.getParameter(name));
			}catch (Exception ex){
				return 0;
			}
		}
		return 0;
	}
	public double getDoubleParameter(String name,HttpServletRequest request){
		if(request.getParameter(name) != null){
			try{
				return Double.parseDouble(request.getParameter(name));
			}catch (Exception ex){
				return 0;
			}
		}
		return 0;
	}
	public Timestamp getTimestampParameter(String name,HttpServletRequest request){
		if(request.getParameter(name) != null){
			try{
				return Timestamp.valueOf(request.getParameter(name));
			}catch (Exception ex){
				return getNowTimestamp();
			}
		}
		return getNowTimestamp();
	}
	public Date getDateParameter(String name,HttpServletRequest request){
		if(request.getParameter(name) != null){
			try{
				return Date.valueOf(request.getParameter(name));
			}catch (Exception ex){
				return getNowDate();
			}
		}
		return getNowDate();
	}
}
