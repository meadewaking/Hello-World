package cn.sycu.meade.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class XXXFilter implements Filter {
    //���ٷ���
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		arg2.doFilter(arg0, arg1);//�������������ã�������һ������
	}
    //��ʼ������
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
