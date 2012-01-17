package it.okkam.rdf2okkam.ens.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EnsConfig {
	
	private String username;
	private String password;
	private String pointer;
	private String dataFolder;
	private String proxyUrl;
	private String proxyPort;
	private String proxyUser;
	private String proxyPassword;
	
	
	private final static String location= "credentials/";
	private final static String file_name="credentials.conf";

	
	public EnsConfig(String path) throws IOException {
		//Properties prop = new Properties();
		//String credentialFilePath = path+location+file_name;
		//String credentialFilePath = "conf/credentials/credential.conf";
		//BufferedReader reader = new BufferedReader(new FileReader(new File(credentialFilePath)));
		//prop.load(reader);
		//System.out.println(prop.get("username"));
		//this.username =(String)prop.get("username");
		this.username = "okkamadmin";
		//System.out.println(prop.get("password"));
		//this.password =(String)prop.get("password");
		this.password = "password";
		//System.out.println(prop.get("services"));
		//this.pointer =(String)prop.get("services");
		this.pointer = "http://localhost:8080/okkam-core/WebServices";
		//this.proxyUrl = (String)prop.get("proxyUrl");
		//System.out.println(prop.get("proxyUrl"));
		//this.proxyPort = (String)prop.get("proxyPort");
		//System.out.println(prop.get("proxyPort"));
		//this.proxyUser = (String)prop.get("proxyUser");
		//System.out.println(prop.get("proxyUser"));
		//this.proxyPassword = (String)prop.get("proxyPassword");
		//System.out.println(prop.get("proxyPassword"));
		this.dataFolder = "conf/data";

	}
	
	public EnsConfig(String path, String file_name) throws IOException {
		Properties prop = new Properties();
		BufferedReader reader = new BufferedReader(new FileReader(new File(path+location+file_name)));
		prop.load(reader);
		//System.out.println(prop.get("username"));
		this.username =(String)prop.get("username");
		//System.out.println(prop.get("password"));
		this.password =(String)prop.get("password");
		//System.out.println(prop.get("services"));
		this.pointer =(String)prop.get("services");
		this.proxyUrl = (String)prop.get("proxyUrl");
		//System.out.println(prop.get("proxyUrl"));
		this.proxyPort = (String)prop.get("proxyPort");
		//System.out.println(prop.get("proxyPort"));
		this.proxyUser = (String)prop.get("proxyUser");
		//System.out.println(prop.get("proxyUser"));
		this.proxyPassword = (String)prop.get("proxyPassword");
		//System.out.println(prop.get("proxyPassword"));

	}
	
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPointer() {
		return pointer;
	}

	public String getProxyUrl() {
		return proxyUrl;
	}

	public String getProxyPort() {
		return proxyPort;
	}

	public String getProxyUser() {
		return proxyUser;
	}

	public String getProxyPassword() {
		return proxyPassword;
	}


}
