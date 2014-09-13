package api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String path = scanner.next();
		IPsoft_api api = new IPsoft_api(path);
		
		//Test for xml/csv file output
		api.output_by_csv_file(api.get_host_data(), "csv_output");
		api.output_by_xml_file(api.get_host_data(), "xml_output");
		
	/*
		//Test api filter functions
		Performance_Filter_and_Sort pfs = new Performance_Filter_and_Sort();
		ArrayList<Host> filtered_data = new ArrayList<Host>(pfs.filter_by_site_name("NY-01", api.get_host_data()));
		api.output_by_xml_file(filtered_data, "test_filter");
		
		//Test api sorting functions
		ArrayList<Host> sorted_data1 = new ArrayList<Host>(pfs.location_ASC(api.get_host_data()));
		ArrayList<Host> sorted_data2 = new ArrayList<Host>(pfs.location_DESC(api.get_host_data()));
		ArrayList<Host> sorted_data3 = new ArrayList<Host>(pfs.host_name_DESC(api.get_host_data()));
		ArrayList<Host> sorted_data4 = new ArrayList<Host>(pfs.operative_system_ASC(api.get_host_data()));
		api.output_by_csv_file(sorted_data1, "test_location_ASC");
		api.output_by_csv_file(sorted_data2, "test_location_DESC");
		api.output_by_csv_file(sorted_data3, "test_host_name_DESC");
		api.output_by_csv_file(sorted_data4, "test_os_ASC");
*/
		
		//Unit test OS_ASC, host_id_DESC
		unit_test_1(api);
		
		//Unit test filter, host_name_ASC
		unit_test_2(api);
		
		//Unit test load_avg_1min_ASC, host_id_DESC
		unit_test_3(api);
	}
	
	public static void unit_test_1 (IPsoft_api api){
		ArrayList<Host> hosts_data = api.get_host_data();
		Performance_Filter_and_Sort pfs = new Performance_Filter_and_Sort();
		for(int i=0; i<hosts_data.size()-1; i++) {
			for(int j=i+1; j<hosts_data.size(); j++) {
				if(hosts_data.get(i).get_operative_system()
						.compareTo(hosts_data.get(j).get_operative_system())> 0) {
					Collections.swap(hosts_data, i, j);
				} else if(hosts_data.get(i).get_operative_system()
						.compareTo(hosts_data.get(j).get_operative_system()) == 0
						&& hosts_data.get(i).get_host_name()
						.compareTo(hosts_data.get(j).get_host_name())< 0) {
					Collections.swap(hosts_data, i, j);
				}
			}
		}
		api.output_by_csv_file(hosts_data, "test_case_1");
	}
	
	public static void unit_test_2 (IPsoft_api api){
		ArrayList<Host> hosts_data = api.get_host_data();
		Performance_Filter_and_Sort pfs = new Performance_Filter_and_Sort();
		ArrayList<Host> filtered_hosts = pfs.filter_by_site_name("NY-01", hosts_data);
		for(int i=0; i<filtered_hosts.size()-1; i++) {
			for(int j=i+1; j<filtered_hosts.size(); j++) {
				if(filtered_hosts.get(i).get_host_name()
						.compareTo(filtered_hosts.get(j).get_host_name()) > 0) {
					Collections.swap(filtered_hosts, i, j);
				}
			}
		}
		api.output_by_csv_file(filtered_hosts, "test_case_2");
	}
	
	public static void unit_test_3 (IPsoft_api api){
		ArrayList<Host> hosts_data = api.get_host_data();
		Performance_Filter_and_Sort pfs = new Performance_Filter_and_Sort();
		for(int i=0; i<hosts_data.size()-1; i++) {
			for(int j=i+1; j<hosts_data.size(); j++) {
				if(hosts_data.get(i).get_load_avg_1min() > hosts_data.get(j).get_load_avg_1min()) {
					Collections.swap(hosts_data, i, j);
				} else if (hosts_data.get(i).get_load_avg_1min() == 
						hosts_data.get(j).get_load_avg_1min() &&
						hosts_data.get(i).get_host_id() < hosts_data.get(j).get_host_id()) {
					Collections.swap(hosts_data, i, j);	
				}
			}
		}
		api.output_by_xml_file(hosts_data, "test_case_3");
	}
}
