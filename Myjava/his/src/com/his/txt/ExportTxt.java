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
			fw.write("药品编号\t\t药品名称\t\t药品类型\t\t简单描述\t\t\t状态\t\t剩余量\r\n");
			for(int i=0;i<drugList.size();i++){
				Drug drug=drugList.get(i);
				fw.write(drug.getDID()+"\t\t");
				fw.write(drug.getDrugName()+"\t\t");
				int drugType=drug.getDrugType();
				if (drugType==1) {
					fw.write("中药\t\t");
				}else if (drugType==2) {
					fw.write("西药\t\t");
				}else{
					fw.write("处方药\t\t");
				}
				fw.write(drug.getDescription()+"\t\t\t");
				int flag=drug.getDrugflag();
				if (flag==1) {
					fw.write("销售中\t\t");
				}else{
					fw.write("停售\t\t");
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
