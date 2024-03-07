package strategy;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializeShapesToFile implements Exporter {
	
	
	@Override
	public void exportData(Object objectsToExport, String path) {
		// TODO Auto-generated method stub
		if (path == null)
			return;
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
			
			oos.writeObject(objectsToExport);
		}
		catch (Exception ex) {
		
		ex.printStackTrace();
		
	}
		
	}

}
