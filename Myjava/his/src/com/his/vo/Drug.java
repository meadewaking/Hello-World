package com.his.vo;

public class Drug {
	private String DID;		//药品编号
	private String drugURL;		//药品图片地址
	private double purchasing_price;		//进价
	private double selling_price;		//售价
	private String drugName;		//药品名称
	private int drugType;		//药品类型
	private String description;		//简单描述
	private String production_date;		//生产日期
	private String expiration_date;		//过期日期
	private int shelf_life;		//保质期
	private String detail;		//详细描述
	private String manufacturer;		//生产厂商
	private String directions;		//服用说明
	private int countpurchases;		//总的进货量
	private int inventory;		//剩余量
	private int drugflag;		//状态
	private String drugcomment;		//备注
	
	public String getDID() {
		return DID;
	}
	public void setDID(String dID) {
		DID = dID;
	}
	public String getDrugURL() {
		return drugURL;
	}
	public void setDrugURL(String drugURL) {
		this.drugURL = drugURL;
	}
	public Double getPurchasing_price() {
		return purchasing_price;
	}
	public void setPurchasing_price(Double purchasing_price) {
		this.purchasing_price = purchasing_price;
	}
	public Double getSelling_price() {
		return selling_price;
	}
	public void setSelling_price(Double selling_price) {
		this.selling_price = selling_price;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public int getDrugType() {
		return drugType;
	}
	public void setDrugType(int drugType) {
		this.drugType = drugType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProduction_date() {
		return production_date;
	}
	public void setProduction_date(String production_date) {
		this.production_date = production_date;
	}
	public String getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}
	public int getShelf_life() {
		return shelf_life;
	}
	public void setShelf_life(int shelf_life) {
		this.shelf_life = shelf_life;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getDirections() {
		return directions;
	}
	public void setDirections(String directions) {
		this.directions = directions;
	}
	public int getCountpurchases() {
		return countpurchases;
	}
	public void setCountpurchases(int countpurchases) {
		this.countpurchases = countpurchases;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public int getDrugflag() {
		return drugflag;
	}
	public void setDrugflag(int drugflag) {
		this.drugflag = drugflag;
	}
	public String getDrugcomment() {
		return drugcomment;
	}
	public void setDrugcomment(String drugcomment) {
		this.drugcomment = drugcomment;
	}
	
}
