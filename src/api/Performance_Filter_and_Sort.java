package api;

import java.util.ArrayList;
import java.util.Collections;

public class Performance_Filter_and_Sort {
	
	public Performance_Filter_and_Sort() {}
	
	public ArrayList<Host> filter_by_site_name(String name, ArrayList<Host> hosts) {
		ArrayList<Host> filtered_hosts = new ArrayList<Host>();
		for(Host h : hosts) {
			if(h.get_site_name().contains(name)) {
				filtered_hosts.add(h);
			}
		}
		return filtered_hosts;
	}
	
	public ArrayList<Host> filter_by_ip_address(String ip_address, ArrayList<Host> hosts) {
		ArrayList<Host> filtered_hosts = new ArrayList<Host>();
		for(Host h : hosts) {
			if(h.get_ip_address().contains(ip_address)) {
				filtered_hosts.add(h);
			}
		}
		return filtered_hosts;
	}
	
	//Sort by location_desc
	public ArrayList<Host> location_DESC(ArrayList<Host> hosts_data) {
		for(int i=0; i<hosts_data.size()-1; i++) {
			for(int j=i+1; j<hosts_data.size(); j++) {
				if(hosts_data.get(i).get_site_location()
						.compareTo(hosts_data.get(j).get_site_location())< 0) {
					Collections.swap(hosts_data, i, j);
				}
			}
		}
		return hosts_data;
	}
	
	//Sort by location_asc
	public ArrayList<Host> location_ASC(ArrayList<Host> hosts_data) {
		for(int i=0; i<hosts_data.size()-1; i++) {
			for(int j=i+1; j<hosts_data.size(); j++) {
				if(hosts_data.get(i).get_site_location()
						.compareTo(hosts_data.get(j).get_site_location())> 0) {
					Collections.swap(hosts_data, i, j);
				}
			}
		}
		return hosts_data;
	}
	
	//Sort by host_name_desc
	public ArrayList<Host> host_name_DESC(ArrayList<Host> hosts_data) {
		for(int i=0; i<hosts_data.size()-1; i++) {
			for(int j=i+1; j<hosts_data.size(); j++) {
				if(hosts_data.get(i).get_host_name()
						.compareTo(hosts_data.get(j).get_host_name())< 0) {
					Collections.swap(hosts_data, i, j);
				}
			}
		}
		return hosts_data;
	}
	
	//Sort by operative_system_asc
	public ArrayList<Host> operative_system_ASC(ArrayList<Host> hosts_data) {
		for(int i=0; i<hosts_data.size()-1; i++) {
			for(int j=i+1; j<hosts_data.size(); j++) {
				if(hosts_data.get(i).get_operative_system()
						.compareTo(hosts_data.get(j).get_operative_system())> 0) {
					Collections.swap(hosts_data, i, j);
				}
			}
		}
		return hosts_data;
	}
	
}
