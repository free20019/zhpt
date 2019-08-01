package mvc.entity;

public class station {
	private String station_name;
	private String station_juri;
	private String station_admin;
	private String id;
	public String getStation_name() {
		return station_name;
	}
	public void setStation_name(String stationName) {
		station_name = stationName;
	}
	public String getStation_juri() {
		return station_juri;
	}
	public void setStation_juri(String stationJuri) {
		station_juri = stationJuri;
	}
	public String getStation_admin() {
		return station_admin;
	}
	public void setStation_admin(String stationAdmin) {
		station_admin = stationAdmin;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
