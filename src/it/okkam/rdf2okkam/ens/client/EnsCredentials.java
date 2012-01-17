package it.okkam.rdf2okkam.ens.client;

import org.okkam.client.wsclient.IOkkamCredential;

public class EnsCredentials implements IOkkamCredential {
	
	private String username;
	private String password;
	private String dataFolder;
	private String okkamPointer;

	@Override
	public String getDataFolder() {
		// TODO Auto-generated method stub
		return dataFolder;
	}

	@Override
	public String getOkkamEndPoint() {
		// TODO Auto-generated method stub
		return okkamPointer;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public void init(String username, String password, String dataFolder) {
		this.username = username;
		this.password = password;
		this.dataFolder = dataFolder;

	}

	@Override
	public void init(String username, String password, String dataFolder, String okkamPointer) {
		this.username = username;
		this.password = password;
		this.dataFolder = dataFolder;
		this.okkamPointer = okkamPointer;

	}

}
