package strategy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;

public class SaveLog implements Exporter {

	@Override
	public void exportData(Object objectsToExport, String path) {
		// TODO Auto-generated method stub
		try{
			String objects = (String) objectsToExport;
			FileOutputStream stream = new FileOutputStream(path);
			stream.write(objects.getBytes(Charset.forName("UTF-8")));
			stream.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
