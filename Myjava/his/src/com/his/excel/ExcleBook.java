package com.his.excel;

import java.io.File;
import java.util.List;

import com.his.vo.Dep;
import com.his.vo.Doctor;
import com.his.vo.Drug;
import com.his.vo.Hospital;
import com.his.vo.Register;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcleBook {
	/**
	 * 将挂号表导出到Excle中
	 * @param regList
	 * @param docList
	 * @param depList
	 * @param fileName
	 * @return
	 */
	public int excleOutReg(List<Register> regList,List<Doctor> docList,List<Dep> depList,String fileName){
		int fg=0;
		WritableWorkbook book = null;//Excle对象
		try {
			//创建excle对象
			book = Workbook.createWorkbook(new File(fileName));
			//通过excle对象创建一个选项卡对象
			WritableSheet sheet = book.createSheet("sheet1", 0);
			Label la01 = new Label(0,0,"病历号");
			Label la02 = new Label(1,0,"主治医生");
			Label la03 = new Label(2,0,"挂号时间");
			Label la04 = new Label(3,0,"挂号科室");
			Label la05 = new Label(4,0,"状态");
			sheet.addCell(la01);
			sheet.addCell(la02);
			sheet.addCell(la03);
			sheet.addCell(la04);
			sheet.addCell(la05);
			for(int i = 0;i<regList.size();i++){
				Register reg = regList.get(i);
				//创建一个单元格对象  列   行  值
				Label la1 = new Label(0,i+1,reg.getMedicalNo());
				int docid=reg.getDocid();
				Label la2=null;
				for (Doctor doc : docList) {
					if (docid==doc.getId()) {
						la2= new Label(1,i+1,doc.getName());
					}
				}
				Label la3 = new Label(2,i+1,reg.getRegtime());
				int depid=reg.getDepid();
				Label la4=null;
				for (Dep dep : depList) {
					if (depid==dep.getId()) {
						la4= new Label(3,i+1,dep.getDepName());
					}
				}
				Label la5=null;
				int flag=reg.getFlag();
				for (int j = 0; j < 6; j++) {
					switch (flag) {
					case 1:
						la5 = new Label(4,i+1,"已挂号");
						break;
					case 2:
						la5 = new Label(4,i+1,"已询医");
						break;
					case 3:
						la5 = new Label(4,i+1,"已出院");
						break;
					case 4:
						la5 = new Label(4,i+1,"已退号");
						break;
					case 5:
						la5 = new Label(4,i+1,"已住院");
						break;
					case 6:
						la5 = new Label(4,i+1,"已退院");
						break;
					case 7:
						la5 = new Label(4,i+1,"已结算");
						break;
					default:
						la5 = new Label(4,i+1,"未知状态");
						break;
					}
				}
				//将创建好的单元格对象放入 选项卡中
				sheet.addCell(la1);
				sheet.addCell(la2);
				sheet.addCell(la3);
				sheet.addCell(la4);
				sheet.addCell(la5);
			}
			//写入目标路径
			book.write();
			fg=1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				book.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return fg;
	}
	/**
	 * 将挂号表导出到Excle中
	 * @param docList
	 * @param depList
	 * @param fileName
	 * @return
	 */
	public int excleOutDoc(List<Doctor> docList,List<Dep> depList,String fileName){
		int fg=0;
		WritableWorkbook book = null;//Excle对象
		try {
			//创建excle对象
			book = Workbook.createWorkbook(new File(fileName));
			//通过excle对象创建一个选项卡对象
			WritableSheet sheet = book.createSheet("sheet1", 0);
			Label la01 = new Label(0,0,"医生编号");
			Label la02 = new Label(1,0,"医生姓名");
			Label la03 = new Label(2,0,"入院时间");
			Label la04 = new Label(3,0,"所属科室");
			sheet.addCell(la01);
			sheet.addCell(la02);
			sheet.addCell(la03);
			sheet.addCell(la04);
			for (int i = 0; i < docList.size(); i++) {
				Doctor doc=docList.get(i);
				Label la1 = new Label(0,i+1,String.valueOf(doc.getId()));
				Label la2 = new Label(1,i+1,doc.getName());
				Label la3 = new Label(2,i+1,doc.getJoinTime());
				Label la4 = null;
				int depid=doc.getDepid();
				for (Dep dep : depList) {
					if (dep.getId()==depid) {
						la4=new Label(3,i+1,dep.getDepName());
					}
				}
				//将创建好的单元格对象放入 选项卡中
				sheet.addCell(la1);
				sheet.addCell(la2);
				sheet.addCell(la3);
				sheet.addCell(la4);
			}
			//写入目标路径
			book.write();
			fg=1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				book.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return fg;
	}
	/**
	 * 将药品表导出到Excle中
	 */
	public int excleOutDrug(List<Drug> drugList,String fileName){
		int fg=0;
		WritableWorkbook book = null;//Excle对象
		try {
			//创建excle对象
			book = Workbook.createWorkbook(new File(fileName));
			//通过excle对象创建一个选项卡对象
			WritableSheet sheet = book.createSheet("sheet1", 0);
			Label la01 = new Label(0,0,"药品编号");
			Label la02 = new Label(1,0,"药品名称");
			Label la03 = new Label(2,0,"药品类型");
			Label la04 = new Label(3,0,"简单描述");
			Label la05 = new Label(4,0,"状态");
			Label la06 = new Label(5,0,"剩余量");
			sheet.addCell(la01);
			sheet.addCell(la02);
			sheet.addCell(la03);
			sheet.addCell(la04);
			sheet.addCell(la05);
			sheet.addCell(la06);
			for (int i = 0; i < drugList.size(); i++) {
				Drug drug=drugList.get(i);
				Label la1 = new Label(0,i+1,drug.getDID());
				Label la2 = new Label(1,i+1,drug.getDrugName());
				int type=drug.getDrugType();
				Label la3 = null;
				if (type==1) {
					la3 = new Label(2,i+1,"中药");
				}else if (type==2) {
					la3 = new Label(2,i+1,"西药");
				}else {
					la3 = new Label(2,i+1,"处方药");
				}
				Label la4 = new Label(3,i+1,drug.getDescription());
				int flag=drug.getDrugflag();
				Label la5 =null;
				if (flag==1) {
					la5 =new Label(4,i+1,"销售中");
				}else {
					la5 =new Label(4,i+1,"停售");
				}
				Label la6 = new Label(5,i+1,String.valueOf(drug.getInventory()));
				//将创建好的单元格对象放入 选项卡中
				sheet.addCell(la1);
				sheet.addCell(la2);
				sheet.addCell(la3);
				sheet.addCell(la4);
				sheet.addCell(la5);
				sheet.addCell(la6);
			}
			//写入目标路径
			book.write();
			fg=1;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				book.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return fg;
	}
	/**
	 * 将住院信息导出到Excle中
	 */
	public int excleOutHos(List<Hospital> hosList,List<Register> regList,List<Doctor> docList,List<Dep> depList,String fileName){
		int fg=0;
		WritableWorkbook book = null;//Excle对象
		try {
			//创建excle对象
			book = Workbook.createWorkbook(new File(fileName));
			//通过excle对象创建一个选项卡对象
			WritableSheet sheet = book.createSheet("sheet1", 0);
			Label la01 = new Label(0,0,"病历号");
			Label la02 = new Label(1,0,"姓名");
			Label la03 = new Label(2,0,"床位号");
			Label la04 = new Label(3,0,"联系电话");
			Label la05 = new Label(4,0,"押金");
			Label la06 = new Label(5,0,"主治医生");
			Label la07 = new Label(6,0,"入院时间");
			Label la08 = new Label(7,0,"科室");
			Label la09 = new Label(8,0,"状态");
			sheet.addCell(la01);
			sheet.addCell(la02);
			sheet.addCell(la03);
			sheet.addCell(la04);
			sheet.addCell(la05);
			sheet.addCell(la06);
			sheet.addCell(la07);
			sheet.addCell(la08);
			sheet.addCell(la09);
			for (int i = 0; i < hosList.size(); i++) {
				Hospital hos=hosList.get(i);
				Label la1 = new Label(0,i+1,hos.getMedicalNo());
				String mNo=hos.getMedicalNo();
				Label la2 =null;
				Label la4 =null;
				Label la6 =null;
				Label la8 =null;
				Label la9 =null;
				for (Register reg : regList) {
					if (mNo.equals(reg.getMedicalNo())) {
						la2=new Label(1,i+1,reg.getName());
						la4=new Label(3,i+1,reg.getPhoneNumber());
						int docid=reg.getDocid();
						for (Doctor doc : docList) {
							if (docid==doc.getId()) {
								la6=new Label(5,i+1,doc.getName());
							}
						}
						int depid=reg.getDepid();
						for (Dep dep : depList) {
							if (depid==dep.getId()) {
								la8=new Label(7,i+1,dep.getDepName());
							}
						}
						int flag=reg.getFlag();
						if (flag==1) {
							la9=new Label(8,i+1,"已挂号");
						}else if (flag==2) {
							la9=new Label(8,i+1,"已询医");
						}else if (flag==3) {
							la9=new Label(8,i+1,"已出院");
						}else if (flag==4) {
							la9=new Label(8,i+1,"已退号");
						}else if (flag==5) {
							la9=new Label(8,i+1,"已住院");
						}else if (flag==6) {
							la9=new Label(8,i+1,"已退院");
						}else if (flag==7) {
							la9=new Label(8,i+1,"已结算");
						}else {
							la9=new Label(8,i+1,"未知状态");
						}
					}
				}
				Label la3 = new Label(2,i+1,hos.getBedNo());
				Label la5 = new Label(4,i+1,String.valueOf(hos.getPayCase()));
				Label la7 = new Label(6,i+1,hos.getHosTime());
				//将创建好的单元格对象放入 选项卡中
				sheet.addCell(la1);
				sheet.addCell(la2);
				sheet.addCell(la3);
				sheet.addCell(la4);
				sheet.addCell(la5);
				sheet.addCell(la6);
				sheet.addCell(la7);
				sheet.addCell(la8);
				sheet.addCell(la9);
			}
			//写入目标路径
			book.write();
			fg=1;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				book.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return fg;
	}	
			
}
