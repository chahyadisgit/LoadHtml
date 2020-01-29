/**
 * 
 */
package com.hctest.htmlload.objects;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.opencsv.CSVWriter;

/**
 * CreateCSVFile<br>
 * Object for generate CSV file.
 * 
 * @author SuryaChahyadi
 * @version 1.0.0
 * @since January 28th, 2019
 */
public class CreateCSVFile {
	/**
	 * writeDataIntoCsv<br>
	 * Write data into csv.
	 * 
	 * @param filePath {@link String}<br>
	 *                 target directory file.
	 * @param data     {@link List}<br>
	 *                 list of data for write into csv file.
	 * @throws Exception<br>
	 *                       Error handler.
	 */
	public static void writeDataIntoCsv(String filePath, List<String[]> data) throws Exception {
		// first create file object for file placed at location
		// specified by filePath
		File file = new File(filePath);
		// create FileWriter object with file as parameter
		FileWriter outputfile = new FileWriter(file);

		// create CSVWriter with ',' as separator
		CSVWriter writer = new CSVWriter(outputfile, ',', CSVWriter.DEFAULT_QUOTE_CHARACTER,
				CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

		// writing header into file
		writer.writeNext(new String[] { "Kode BPS", "Nama", "Ibu Kota", "Populasi", "Luas km", "pulau" });
		// create a List which contains String array
		writer.writeAll(data);

		// closing writer connection
		writer.close();
	}

}
