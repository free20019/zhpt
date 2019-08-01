package mvc.entity;

import java.util.ArrayList;
import java.util.List;

public class RECORD {
	private String id;
	private String area_id;
	private String area_name;
	private String vehi_no;
	private String source_db_time;
	private String speed;
	private String state;
	private String longi;
	private String lati;
	private String record_time;
	private List<String> A=new ArrayList<String>();
	private List<String> B=new ArrayList<String>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArea_id() {
		return area_id;
	}
	public void setArea_id(String areaId) {
		area_id = areaId;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String areaName) {
		area_name = areaName;
	}
	public String getVehi_no() {
		return vehi_no;
	}
	public void setVehi_no(String vehiNo) {
		vehi_no = vehiNo;
	}
	public String getSource_db_time() {
		return source_db_time;
	}
	public void setSource_db_time(String sourceDbTime) {
		source_db_time = sourceDbTime;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLongi() {
		return longi;
	}
	public void setLongi(String longi) {
		this.longi = longi;
	}
	public String getLati() {
		return lati;
	}
	public void setLati(String lati) {
		this.lati = lati;
	}
	public String getRecord_time() {
		return record_time;
	}
	public void setRecord_time(String recordTime) {
		record_time = recordTime;
	}
	public List<String> getA() {
		return A;
	}
	public void setA(List<String> a) {
		A = a;
	}
	public List<String> getB() {
		return B;
	}
	public void setB(List<String> b) {
		B = b;
	}
}
