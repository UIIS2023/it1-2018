package strategy;

import java.util.ArrayList;

public class ImportManager implements Importer {

	private Importer importer;
	
	public ImportManager(Importer importer){
		this.importer=importer;
	}
	@Override
	public Object importData(String path) {
		
		return importer.importData(path);
		
	}

}
