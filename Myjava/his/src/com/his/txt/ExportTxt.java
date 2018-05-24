package com.his.txt;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.his.vo.Drug;

public class ExportTxt {
	public int toTxtDoc(List<Drug> drugList,String fileName){
		int fg=0;
		FileWriter fw=null;
		try {
			fw = new FileWriter(fileName);
			fw.write("ҩƷ���\t\tҩƷ����\t\tҩƷ����\t\t������\t\t\t״̬\t\tʣ����\r\n");
			for(int i=0;i<drugList.size();i++){
				Drug drug=drugList.get(i);
				fw.write(drug.getDID()+"\t\t");
				fw.write(drug.getDrugName()+"\t\t");
				int drugType=drug.getDrugType();
				if (drugType==1) {
					fw.write("��ҩ\t\t");
				}else if (drugType==2) {
					fw.write("��ҩ\t\t");
				}else{
					fw.write("����ҩ\t\t");
				}
				fw.write(drug.getDescription()+"\t\t\t");
				int flag=drug.getDrugflag();
				if (flag==1) {
					fw.write("������\t\t");
				}else{
					fw.write("ͣ��\t\t");
				}
				fw.write(drug.getInventory()+"\r\n");
			}
			fw.close();
			fg=1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fg;
	}
}
