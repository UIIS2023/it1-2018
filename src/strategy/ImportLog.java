package strategy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class ImportLog implements Importer{

	@Override
	public FileReader importData(String path) {
		// TODO Auto-generated method stub
		try{
			FileReader fileReader = new FileReader(path);
			return fileReader;
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}

	
}
