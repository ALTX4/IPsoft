package api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parse_CSV {
	public ArrayList<Host> parse(String path) {
		ArrayList<Host> host_data = new ArrayList<Host>();
		String csv_file = path;
		BufferedReader br = null;
		String line = "";
		String cvs_split_by = ", ";
		
		try {
			br = new BufferedReader(new FileReader(csv_file));
			String header_line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] host_info = line.split(cvs_split_by);
				Host host = new Host();
				host.set_site_id(Integer.parseInt(host_info[0]));
				host.set_site_name(host_info[1]);
				host.set_site_location(host_info[2]);
				host.set_host_id(Integer.parseInt(host_info[3]));
				host.set_host_name(host_info[4]);
				host.set_ip_address(host_info[5]);
				host.set_operative_system(host_info[6]);
				host.set_load_avg_1min(Double.parseDouble(host_info[7]));
				host.set_load_avg_5min(Double.parseDouble(host_info[8]));
				host.set_load_avg_15min(Double.parseDouble(host_info[9]));
//				host.display_host_info();
				
				host_data.add(host);
			} 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return host_data;
	}
}
