package mvc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public abstract  class MvcService {
	protected  Map<String, String> genTypeMap(String exp) {
		Map<String,String> typeMap = new HashMap();
		if(exp.trim().isEmpty())return typeMap;
		String patt [] = exp.split("\\|");
		for(int i = 0;i<patt.length;i++){
			String dataType = patt[i].substring(0,1);
			String temp2 [] = patt[i].substring(2).split(",");
			for(String s:temp2){
				typeMap.put(s.trim(), dataType);
			}
		}
		return typeMap;
	}

	protected  Object[] genOcs(Map<String, Object> map2, String cs,
			String exp) {
		Map<String,String> map = genTypeMap(exp);
		List list =new ArrayList();
		String css[] = cs.split(",");
		for(int i=0;i<css.length;i++){
			Object o = null;
			o = map2.get(css[i]);
			try {
				if(map.containsKey(css[i])){
					String dateType = map.get(css[i]);
					if(dateType.toUpperCase().equals("D")){
						Date date = new Date();
						date.setTime((Long)o);
						o = date;
					}
					if(dateType.toUpperCase().equals("F")){
						o = Float.parseFloat((String) o);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				o=null;
			}
			
			list.add(o);
		}
		return list.toArray();
	}
	
	protected String getPlaceholder(String columns) {
		String placeholder [] = new String [columns.split(",").length];
		Arrays.fill(placeholder,"?");
		String placeholderString = Arrays.toString(placeholder).replaceAll("[\\[\\]]", "");
		return placeholderString;
	}
}
