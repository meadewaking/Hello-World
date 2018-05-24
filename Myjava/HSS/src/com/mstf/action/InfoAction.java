package com.mstf.action;

import java.util.List;

import com.mstf.bean.Info;
import com.mstf.service.InfoService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@SuppressWarnings("serial")

public class InfoAction extends ActionSupport implements ModelDriven<Info>{

	//用于ModelDriven填充对象,入口为getModel方法
	private Info info;
	//用于spring自动注入，入口为setInfoService方法
	private InfoService infoService;

	//方法名对应info_*.action中的*通配符
	public String list() throws Exception{
		//调用service方法，返回从数据库取出的数据
		List<Info> infolist=infoService.getAll();
		//放入session
		ActionContext.getContext().put("infolist", infolist);
		//返回视图，list对应struts.xml对应的jsp或action
		return "list";		
	}

	public String addUI() throws Exception{
		return "addUI";
	}
	
	public String add() throws Exception{
		infoService.save(info);
		return "tolist";		
	}

	public String updateUI() throws Exception{	
		Info infos=infoService.getById(info.getId());
		ActionContext.getContext().getValueStack().push(infos);
		return "updateUI";		
	}
	
	public String update() throws Exception{	
		Info infos=infoService.getById(info.getId());
		infos.setName(info.getName());
		infos.setAge(info.getAge());
		infos.setAddress(info.getAddress());
		infoService.update(infos);
		return "tolist";		
	}
	
	public String delete() throws Exception{
		infoService.delete(info.getId());
		return "tolist";		
	}

	@Override
	public Info getModel() {
		info =new Info();
		return info;
	}

	public InfoService getInfoService() {
		return infoService;
	}

	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}
	
}
