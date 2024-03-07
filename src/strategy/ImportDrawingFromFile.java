package strategy;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ImportDrawingFromFile implements Importer{

	@Override
	public ArrayList<Object> importData(String path) {
		// TODO Auto-generated method stub
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {

			return (ArrayList<Object>) ois.readObject();

		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		return null;
	
	}

}
