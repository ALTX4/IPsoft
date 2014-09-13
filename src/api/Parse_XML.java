package api;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Parse_XML {
	public ArrayList<Host> parse(String path) {
		ArrayList<Host> host_data = new ArrayList<Host>();
		
		try {
			File xml_file = new File(path);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xml_file);
			
			//Optional, but recommended
			doc.getDocumentElement().normalize();
			
			System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
			
			NodeList site_list = doc.getElementsByTagName("Site");
			System.out.println("-----------------------------");
			
			for(int i=0; i<site_list.getLength(); i++) {
				Element site = (Element) site_list.item(i);
				int site_id = Integer.parseInt(site.getAttribute("id"));
				String site_name = site.getAttribute("name");
				String site_location = site.getAttribute("location");
				
				NodeList host_list = site.getElementsByTagName("Host");
				for(int j=0; j<host_list.getLength(); j++) {
					Element host = (Element) host_list.item(j);
					
					int host_id = Integer.parseInt(host.getAttribute("id"));
					String host_name = host.getElementsByTagName("Host_Name").item(0).getTextContent();
					String ip_address = host.getElementsByTagName("IP_address").item(0).getTextContent();
					String operative_system = host.getElementsByTagName("OS").item(0).getTextContent();
					double load_avg_1min = Double.parseDouble(host.getElementsByTagName("Load_avg_1min").item(0).getTextContent());
					double load_avg_5min = Double.parseDouble(host.getElementsByTagName("Load_avg_5min").item(0).getTextContent());
					double load_avg_15min = Double.parseDouble(host.getElementsByTagName("Load_avg_15min").item(0).getTextContent());
				
					//Single host information parsed and stored
					Host h = new Host();
					h.set_site_id(site_id);
					h.set_site_name(site_name);
					h.set_site_location(site_location);
					h.set_host_id(host_id);
					h.set_host_name(host_name);
					h.set_ip_address(ip_address);
					h.set_operative_system(operative_system);
					h.set_load_avg_1min(load_avg_1min);
					h.set_load_avg_5min(load_avg_5min);
					h.set_load_avg_15min(load_avg_15min);
//					h.display_host_info();
				
					host_data.add(h);
				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return host_data;
	}
}
