package strategy;

import java.util.ArrayList;

public class ExportManager implements Exporter{
	private Exporter exporter;

	public ExportManager(Exporter exporter) {
		this.exporter = exporter;
	}

	@Override
	public void exportData(Object objectsToExport, String path) {
		// TODO Auto-generated method stub
		exporter.exportData(objectsToExport, path);
	}

}
