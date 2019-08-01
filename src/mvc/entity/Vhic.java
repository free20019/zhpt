package mvc.entity;

import java.io.Serializable;

public class Vhic implements Serializable{
	private String vehi_no;
	private String vehi_sim;
	private String comp_id;
	private String own_name;
	private String own_tel;
	private String time;
	private String comp_name;
	private String ba_id;
	private String vehi_id;
	private String ba_name;
	private String owner_id;
	private String taxi_chuche_rate;
	private String busy_taxi_chuche_rate;
	private String average_run_times;
	private String run_times;
	private String groupid;
	private String groupname;
	private int a;
	public String getVehi_no() {
		return vehi_no;
	}
	public void setVehi_no(String vehiNo) {
		vehi_no = vehiNo;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getVehi_sim() {
		return vehi_sim;
	}
	public void setVehi_sim(String vehiSim) {
		vehi_sim = vehiSim;
	}
	public String getComp_id() {
		return comp_id;
	}
	public void setComp_id(String compId) {
		comp_id = compId;
	}
	public String getOwn_name() {
		return own_name;
	}
	public void setOwn_name(String ownName) {
		own_name = ownName;
	}
	public String getOwn_tel() {
		return own_tel;
	}
	public void setOwn_tel(String ownTel) {
		own_tel = ownTel;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getComp_name() {
		return comp_name;
	}
	public void setComp_name(String compName) {
		comp_name = compName;
	}
	public String getBa_id() {
		return ba_id;
	}
	public void setBa_id(String baId) {
		ba_id = baId;
	}
	public String getVehi_id() {
		return vehi_id;
	}
	public void setVehi_id(String vehiId) {
		vehi_id = vehiId;
	}
	public String getBa_name() {
		return ba_name;
	}
	public void setBa_name(String baName) {
		ba_name = baName;
	}
	public String getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(String ownerId) {
		owner_id = ownerId;
	}
	public String getTaxi_chuche_rate() {
		return taxi_chuche_rate;
	}
	public void setTaxi_chuche_rate(String taxiChucheRate) {
		taxi_chuche_rate = taxiChucheRate;
	}
	public String getBusy_taxi_chuche_rate() {
		return busy_taxi_chuche_rate;
	}
	public void setBusy_taxi_chuche_rate(String busyTaxiChucheRate) {
		busy_taxi_chuche_rate = busyTaxiChucheRate;
	}
	public String getAverage_run_times() {
		return average_run_times;
	}
	public void setAverage_run_times(String averageRunTimes) {
		average_run_times = averageRunTimes;
	}
	public String getRun_times() {
		return run_times;
	}
	public void setRun_times(String runTimes) {
		run_times = runTimes;
	}
}
