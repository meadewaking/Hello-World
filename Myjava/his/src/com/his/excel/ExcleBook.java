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
	 * ���Һű�����Excle��
	 * @param regList
	 * @param docList
	 * @param depList
	 * @param fileName
	 * @return
	 */
	public int excleOutReg(List<Register> regList,List<Doctor> docList,List<Dep> depList,String fileName){
		int fg=0;
		WritableWorkbook book = null;//Excle����
		try {
			//����excle����
			book = Workbook.createWorkbook(new File(fileName));
			//ͨ��excle���󴴽�һ��ѡ�����
			WritableSheet sheet = book.createSheet("sheet1", 0);
			Label la01 = new Label(0,0,"������");
			Label la02 = new Label(1,0,"����ҽ��");
			Label la03 = new Label(2,0,"�Һ�ʱ��");
			Label la04 = new Label(3,0,"�Һſ���");
			Label la05 = new Label(4,0,"״̬");
			sheet.addCell(la01);
			sheet.addCell(la02);
			sheet.addCell(la03);
			sheet.addCell(la04);
			sheet.addCell(la05);
			for(int i = 0;i<regList.size();i++){
				Register reg = regList.get(i);
				//����һ����Ԫ�����  ��   ��  ֵ
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
						la5 = new Label(4,i+1,"�ѹҺ�");
						break;
					case 2:
						la5 = new Label(4,i+1,"��ѯҽ");
						break;
					case 3:
						la5 = new Label(4,i+1,"�ѳ�Ժ");
						break;
					case 4:
						la5 = new Label(4,i+1,"���˺�");
						break;
					case 5:
						la5 = new Label(4,i+1,"��סԺ");
						break;
					case 6:
						la5 = new Label(4,i+1,"����Ժ");
						break;
					case 7:
						la5 = new Label(4,i+1,"�ѽ���");
						break;
					default:
						la5 = new Label(4,i+1,"δ֪״̬");
						break;
					}
				}
				//�������õĵ�Ԫ�������� ѡ���
				sheet.addCell(la1);
				sheet.addCell(la2);
				sheet.addCell(la3);
				sheet.addCell(la4);
				sheet.addCell(la5);
			}
			//д��Ŀ��·��
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
	 * ���Һű�����Excle��
	 * @param docList
	 * @param depList
	 * @param fileName
	 * @return
	 */
	public int excleOutDoc(List<Doctor> docList,List<Dep> depList,String fileName){
		int fg=0;
		WritableWorkbook book = null;//Excle����
		try {
			//����excle����
			book = Workbook.createWorkbook(new File(fileName));
			//ͨ��excle���󴴽�һ��ѡ�����
			WritableSheet sheet = book.createSheet("sheet1", 0);
			Label la01 = new Label(0,0,"ҽ�����");
			Label la02 = new Label(1,0,"ҽ������");
			Label la03 = new Label(2,0,"��Ժʱ��");
			Label la04 = new Label(3,0,"��������");
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
				//�������õĵ�Ԫ�������� ѡ���
				sheet.addCell(la1);
				sheet.addCell(la2);
				sheet.addCell(la3);
				sheet.addCell(la4);
			}
			//д��Ŀ��·��
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
	 * ��ҩƷ������Excle��
	 */
	public int excleOutDrug(List<Drug> drugList,String fileName){
		int fg=0;
		WritableWorkbook book = null;//Excle����
		try {
			//����excle����
			book = Workbook.createWorkbook(new File(fileName));
			//ͨ��excle���󴴽�һ��ѡ�����
			WritableSheet sheet = book.createSheet("sheet1", 0);
			Label la01 = new Label(0,0,"ҩƷ���");
			Label la02 = new Label(1,0,"ҩƷ����");
			Label la03 = new Label(2,0,"ҩƷ����");
			Label la04 = new Label(3,0,"������");
			Label la05 = new Label(4,0,"״̬");
			Label la06 = new Label(5,0,"ʣ����");
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
					la3 = new Label(2,i+1,"��ҩ");
				}else if (type==2) {
					la3 = new Label(2,i+1,"��ҩ");
				}else {
					la3 = new Label(2,i+1,"����ҩ");
				}
				Label la4 = new Label(3,i+1,drug.getDescription());
				int flag=drug.getDrugflag();
				Label la5 =null;
				if (flag==1) {
					la5 =new Label(4,i+1,"������");
				}else {
					la5 =new Label(4,i+1,"ͣ��");
				}
				Label la6 = new Label(5,i+1,String.valueOf(drug.getInventory()));
				//�������õĵ�Ԫ�������� ѡ���
				sheet.addCell(la1);
				sheet.addCell(la2);
				sheet.addCell(la3);
				sheet.addCell(la4);
				sheet.addCell(la5);
				sheet.addCell(la6);
			}
			//д��Ŀ��·��
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
	 * ��סԺ��Ϣ������Excle��
	 */
	public int excleOutHos(List<Hospital> hosList,List<Register> regList,List<Doctor> docList,List<Dep> depList,String fileName){
		int fg=0;
		WritableWorkbook book = null;//Excle����
		try {
			//����excle����
			book = Workbook.createWorkbook(new File(fileName));
			//ͨ��excle���󴴽�һ��ѡ�����
			WritableSheet sheet = book.createSheet("sheet1", 0);
			Label la01 = new Label(0,0,"������");
			Label la02 = new Label(1,0,"����");
			Label la03 = new Label(2,0,"��λ��");
			Label la04 = new Label(3,0,"��ϵ�绰");
			Label la05 = new Label(4,0,"Ѻ��");
			Label la06 = new Label(5,0,"����ҽ��");
			Label la07 = new Label(6,0,"��Ժʱ��");
			Label la08 = new Label(7,0,"����");
			Label la09 = new Label(8,0,"״̬");
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
							la9=new Label(8,i+1,"�ѹҺ�");
						}else if (flag==2) {
							la9=new Label(8,i+1,"��ѯҽ");
						}else if (flag==3) {
							la9=new Label(8,i+1,"�ѳ�Ժ");
						}else if (flag==4) {
							la9=new Label(8,i+1,"���˺�");
						}else if (flag==5) {
							la9=new Label(8,i+1,"��סԺ");
						}else if (flag==6) {
							la9=new Label(8,i+1,"����Ժ");
						}else if (flag==7) {
							la9=new Label(8,i+1,"�ѽ���");
						}else {
							la9=new Label(8,i+1,"δ֪״̬");
						}
					}
				}
				Label la3 = new Label(2,i+1,hos.getBedNo());
				Label la5 = new Label(4,i+1,String.valueOf(hos.getPayCase()));
				Label la7 = new Label(6,i+1,hos.getHosTime());
				//�������õĵ�Ԫ�������� ѡ���
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
			//д��Ŀ��·��
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
