package api;

public class Host {
	private int site_id;
	private String site_name;
	private String site_location;
	private int host_id;
	private String host_name;
	private String ip_address;
	private String operative_system;
	private double load_avg_1min;
	private double load_avg_5min;
	private double load_avg_15min;
	
	//Empty Constructor
	public Host() {}
	
	
	public void set_site_id(int id) {
		this.site_id = id;
	}
	
	public int get_site_id() {
		return this.site_id;
	}
	
	public void set_site_name(String name) {
		this.site_name = name;
	}
	
	public String get_site_name() {
		return this.site_name;
	}
	
	public void set_site_location(String location) {
		this.site_location = location;
	}
	
	public String get_site_location() {
		return this.site_location;
	}
	
	public void set_host_id(int id) {
		this.host_id = id;
	}
	
	public int get_host_id() {
		return this.host_id;
	}
	
	public void set_host_name(String name) {
		this.host_name = name;
	}
	
	public String get_host_name() {
		return this.host_name;
	}
	
	public void set_ip_address(String ip) {
		this.ip_address = ip;
	}
	
	public String get_ip_address() {
		return this.ip_address;
	}
	
	public void set_operative_system(String os) {
		this.operative_system = os;
	}
	
	public String get_operative_system() {
		return this.operative_system;
	}
	
	public void set_load_avg_1min(double time) {
		this.load_avg_1min = time;
	}
	
	public double get_load_avg_1min() {
		return this.load_avg_1min;
	}
	
	public void set_load_avg_5min(double time) {
		this.load_avg_5min = time;
	}
	
	public double get_load_avg_5min() {
		return this.load_avg_5min;
	}
	
	public void set_load_avg_15min(double time) {
		this.load_avg_15min = time;
	}
	
	public double get_load_avg_15min() {
		return this.load_avg_15min;
	}
	
	public void display_host_info() {
		System.out.println("site_id: " + site_id);
		System.out.println("site_name: " + site_name);
		System.out.println("site_location: " + site_location);
		System.out.println("host_id: " + host_id);
		System.out.println("host_name: " + host_name);
		System.out.println("ip_address: " + ip_address);
		System.out.println("operative_system: " + operative_system);
		System.out.println("load_avg_1min: " + load_avg_1min);
		System.out.println("load_avg_5min: " + load_avg_5min);
		System.out.println("load_avg_15min: " + load_avg_15min + "\n");
	}
}
