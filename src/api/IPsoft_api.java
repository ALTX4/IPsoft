package api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class IPsoft_api {
	private String file_type;
	private ArrayList<Host> hosts;
	
	public IPsoft_api(String path) {
		file_type = FilenameUtils.getExtension(path);
		if(file_type.contains("csv") || file_type.contains("txt")) {
			Parse_CSV parser = new Parse_CSV();
			hosts = parser.parse(path);
			System.out.println("CSV file...Done");
		} else if (file_type.contains("xml")) {
			Parse_XML parser =new Parse_XML();
			hosts = parser.parse(path);
			System.out.println("XML file...Done");
		} else {
			System.out.println("Unexpeceted file type:" + file_type);
		}
	}	
	
	public ArrayList<Host> get_host_data() {
		return this.hosts;
	}
	
	public void output_by_csv_file(ArrayList<Host> host_data, String output_file_name) {
		try {
			FileWriter writer = new FileWriter(output_file_name + ".csv");
			
			//First column for attributes
			writer.append("site_id, site_name, site_location, host_id, host_name, " +
					"ip_address, operative_system, load_avg_1min, " +
					"load_avg_5min, load_avg_15min\n");
			
			//Sorted data stored in the api
			for(Host host: host_data) {
				String record = host.get_site_id() + ","
						+ host.get_site_name() + ","
						+ host.get_site_location() + ","
						+ host.get_host_id() + ","
						+ host.get_host_name() + ","
						+ host.get_ip_address() + ","
						+ host.get_operative_system() + ","
						+ host.get_load_avg_1min() + ","
						+ host.get_load_avg_5min() + ","
						+ host.get_load_avg_15min() + "\n";
				writer.append(record);
			}
			writer.flush();
			writer.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("csv file....saved!");
	}
	
	public void output_by_xml_file(ArrayList<Host> host_data, String output_file_name) {
		try { 
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//Sites - root elements
			Document doc = db.newDocument();
			Element sites_element = doc.createElement("Sites");
			doc.appendChild(sites_element);
		
			int site_id_pointer = 0;
			Element hosts_pointer = null;
			for(Host host : host_data) {
				if(host.get_site_id() != site_id_pointer) {
					//Site - child elements
					Element site_element = doc.createElement("Site");
					sites_element.appendChild(site_element);
					site_element.setAttribute("id", Integer.toString(host.get_site_id()));
					site_element.setAttribute("name", host.get_site_name());
					site_element.setAttribute("location", host.get_site_location());
					site_id_pointer = host.get_site_id();
					
					//Hosts - child elements of Site with a tmp pointer
					Element hosts_element = doc.createElement("Hosts");
					site_element.appendChild(hosts_element);
					hosts_pointer = hosts_element;
					
					//Host - child elements of Hosts
					Element host_element = doc.createElement("Host");
					host_element.setAttribute("id", Integer.toString(host.get_host_id()));
					hosts_element.appendChild(host_element);
					
					//Host name elements
					Element host_name = doc.createElement("Host_Name");
					host_name.appendChild(doc.createTextNode(host.get_host_name()));
					host_element.appendChild(host_name);
					
					//IP address elements
					Element ip_address = doc.createElement("IP_address");
					ip_address.appendChild(doc.createTextNode(host.get_ip_address()));
					host_element.appendChild(ip_address);
					
					//Operative System elements
					Element os = doc.createElement("OS");
					os.appendChild(doc.createTextNode(host.get_operative_system()));
					host_element.appendChild(os);
					
					//load_avg_1min elements
					Element load_avg_1min = doc.createElement("Load_avg_1min");
					load_avg_1min.appendChild(doc.createTextNode(Double.toString(host.get_load_avg_1min())));
					host_element.appendChild(load_avg_1min);
					
					//load_avg_5min elements
					Element load_avg_5min = doc.createElement("Load_avg_5min");
					load_avg_5min.appendChild(doc.createTextNode(Double.toString(host.get_load_avg_5min())));
					host_element.appendChild(load_avg_5min);
					
					//load_avg_15min elements
					Element load_avg_15min = doc.createElement("Load_avg_15min");
					load_avg_15min.appendChild(doc.createTextNode(Double.toString(host.get_load_avg_15min())));
					host_element.appendChild(load_avg_15min);
					
				} else {
					
					//Host - child elements of Hosts append to the former Hosts element
					Element host_element = doc.createElement("Host");
					host_element.setAttribute("id", Integer.toString(host.get_host_id()));
					hosts_pointer.appendChild(host_element);
					
					//Host name elements
					Element host_name = doc.createElement("Host_Name");
					host_name.appendChild(doc.createTextNode(host.get_host_name()));
					host_element.appendChild(host_name);
					
					//IP address elements
					Element ip_address = doc.createElement("IP_address");
					ip_address.appendChild(doc.createTextNode(host.get_ip_address()));
					host_element.appendChild(ip_address);
					
					//Operative System elements
					Element os = doc.createElement("OS");
					os.appendChild(doc.createTextNode(host.get_operative_system()));
					host_element.appendChild(os);
					
					//load_avg_1min elements
					Element load_avg_1min = doc.createElement("Load_avg_1min");
					load_avg_1min.appendChild(doc.createTextNode(Double.toString(host.get_load_avg_1min())));
					host_element.appendChild(load_avg_1min);
					
					//load_avg_5min elements
					Element load_avg_5min = doc.createElement("Load_avg_5min");
					load_avg_5min.appendChild(doc.createTextNode(Double.toString(host.get_load_avg_5min())));
					host_element.appendChild(load_avg_5min);
					
					//load_avg_15min elements
					Element load_avg_15min = doc.createElement("Load_avg_15min");
					load_avg_15min.appendChild(doc.createTextNode(Double.toString(host.get_load_avg_15min())));
					host_element.appendChild(load_avg_15min);
				}	
			}
			
			//write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
	
			StreamResult result = new StreamResult(new File(output_file_name + ".xml"));
			transformer.transform(source, result);
			
//			Console file debug
//			StreamResult result =  new StreamResult(System.out);
//			transformer.transform(source, result);
			
			System.out.println("xml file....saved!");
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}
