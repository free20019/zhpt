package mvc.entity;

import com.sun.corba.se.spi.orb.StringPair;


public class HalfMonth {
	private String[] areavhicmax;//车辆数量
	private String[] areavhicmin;
	private String[] areaonlinemax;//上线率
	private String[] areaonlinemin;
	private String[] arealoadmax;//重车率
	private String[] arealoadmin;
	private String[] yingyunmax;//在线营运率
	private String[] yingyunmin;
	private String[] actualmax;//实载率
	private String[] actualmin;
	private String stime;
	private String etime;
	private String area_id;
	private String area_name;
	private String[] allonlinemax;//全部区域上线率
	private String[] allonlinemin;
	private String[] allloadmax;//全部区域重车率
	private String[] allloadmin;
	private String[] profitmax;//营运收益
	private String[] profitmin;
	private String[] actualloadmax;//实载里程
	private String[] actualloadmin;
	private String[] noloadmax;//空驶里程
	private String[] noloadmin;
	private String[] totalloadmax;//总里程
	private String[] totalloadmin;
	private String[] timesmax;//营运次数
	private String[] timesmin;
	private String[] timemax;//载客时间
	private String[] timemin;
	private String[] waittingtimemax;//载客等候时间
	private String[] waittingtimemin;
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
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
	public String[] getAreavhicmax() {
		return areavhicmax;
	}
	public void setAreavhicmax(String[] areavhicmax) {
		this.areavhicmax = areavhicmax;
	}
	public String[] getAreavhicmin() {
		return areavhicmin;
	}
	public void setAreavhicmin(String[] areavhicmin) {
		this.areavhicmin = areavhicmin;
	}
	public String[] getAreaonlinemax() {
		return areaonlinemax;
	}
	public void setAreaonlinemax(String[] areaonlinemax) {
		this.areaonlinemax = areaonlinemax;
	}
	public String[] getAreaonlinemin() {
		return areaonlinemin;
	}
	public void setAreaonlinemin(String[] areaonlinemin) {
		this.areaonlinemin = areaonlinemin;
	}
	public String[] getArealoadmax() {
		return arealoadmax;
	}
	public void setArealoadmax(String[] arealoadmax) {
		this.arealoadmax = arealoadmax;
	}
	public String[] getArealoadmin() {
		return arealoadmin;
	}
	public void setArealoadmin(String[] arealoadmin) {
		this.arealoadmin = arealoadmin;
	}
	public String[] getYingyunmax() {
		return yingyunmax;
	}
	public void setYingyunmax(String[] yingyunmax) {
		this.yingyunmax = yingyunmax;
	}
	public String[] getYingyunmin() {
		return yingyunmin;
	}
	public void setYingyunmin(String[] yingyunmin) {
		this.yingyunmin = yingyunmin;
	}
	public String[] getActualmax() {
		return actualmax;
	}
	public void setActualmax(String[] actualmax) {
		this.actualmax = actualmax;
	}
	public String[] getActualmin() {
		return actualmin;
	}
	public void setActualmin(String[] actualmin) {
		this.actualmin = actualmin;
	}
	public String[] getAllonlinemax() {
		return allonlinemax;
	}
	public void setAllonlinemax(String[] allonlinemax) {
		this.allonlinemax = allonlinemax;
	}
	public String[] getAllonlinemin() {
		return allonlinemin;
	}
	public void setAllonlinemin(String[] allonlinemin) {
		this.allonlinemin = allonlinemin;
	}
	public String[] getAllloadmax() {
		return allloadmax;
	}
	public void setAllloadmax(String[] allloadmax) {
		this.allloadmax = allloadmax;
	}
	public String[] getAllloadmin() {
		return allloadmin;
	}
	public void setAllloadmin(String[] allloadmin) {
		this.allloadmin = allloadmin;
	}
	public String[] getProfitmax() {
		return profitmax;
	}
	public void setProfitmax(String[] profitmax) {
		this.profitmax = profitmax;
	}
	public String[] getProfitmin() {
		return profitmin;
	}
	public void setProfitmin(String[] profitmin) {
		this.profitmin = profitmin;
	}
	public String[] getActualloadmax() {
		return actualloadmax;
	}
	public void setActualloadmax(String[] actualloadmax) {
		this.actualloadmax = actualloadmax;
	}
	public String[] getActualloadmin() {
		return actualloadmin;
	}
	public void setActualloadmin(String[] actualloadmin) {
		this.actualloadmin = actualloadmin;
	}
	public String[] getNoloadmax() {
		return noloadmax;
	}
	public void setNoloadmax(String[] noloadmax) {
		this.noloadmax = noloadmax;
	}
	public String[] getNoloadmin() {
		return noloadmin;
	}
	public void setNoloadmin(String[] noloadmin) {
		this.noloadmin = noloadmin;
	}
	public String[] getTotalloadmax() {
		return totalloadmax;
	}
	public void setTotalloadmax(String[] totalloadmax) {
		this.totalloadmax = totalloadmax;
	}
	public String[] getTotalloadmin() {
		return totalloadmin;
	}
	public void setTotalloadmin(String[] totalloadmin) {
		this.totalloadmin = totalloadmin;
	}
	public String[] getTimesmax() {
		return timesmax;
	}
	public void setTimesmax(String[] timesmax) {
		this.timesmax = timesmax;
	}
	public String[] getTimesmin() {
		return timesmin;
	}
	public void setTimesmin(String[] timesmin) {
		this.timesmin = timesmin;
	}
	public String[] getTimemax() {
		return timemax;
	}
	public void setTimemax(String[] timemax) {
		this.timemax = timemax;
	}
	public String[] getTimemin() {
		return timemin;
	}
	public void setTimemin(String[] timemin) {
		this.timemin = timemin;
	}
	public String[] getWaittingtimemax() {
		return waittingtimemax;
	}
	public void setWaittingtimemax(String[] waittingtimemax) {
		this.waittingtimemax = waittingtimemax;
	}
	public String[] getWaittingtimemin() {
		return waittingtimemin;
	}
	public void setWaittingtimemin(String[] waittingtimemin) {
		this.waittingtimemin = waittingtimemin;
	}
	
}
