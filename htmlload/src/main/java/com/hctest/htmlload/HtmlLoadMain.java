/**
 * 
 */
package com.hctest.htmlload;

import java.util.List;

import com.hctest.htmlload.objects.CreateCSVFile;
import com.hctest.htmlload.objects.LoadHTMLSource;

/**
 * @author SuryaChahyadi
 *
 */
public class HtmlLoadMain {

	/**
	 * main<br>
	 * main method.
	 * 
	 * @param args {@link String}<br>
	 *             location csv file will be save, default location
	 *             D:\Indonesia_Demography_by_Province.csv.
	 */
	public static void main(String[] args) {
		System.out.println("============= Starting Process ==============");
		// Declare variable
		// target url
		String url = "https://id.wikipedia.org/wiki/Demografi_Indonesia";
		// target directory for save file
		String filePath = args.length > 0 ? args[0] : "D:\\Indonesia_Demography_by_Province.csv";
		// attribute object variable
		String attrObject = ".wikitable";

		try {
			System.out.println(">>>>>> Starting loading website");
			// load table on pages
			List<String[]> resultList = LoadHTMLSource.readHtml(url, attrObject);
			System.out.println(">>>>>> Finish loading website");

			System.out.println(">>>>>> Staring write csv file");
			// write csv to file, target file is args[0] or filePath (default)
			CreateCSVFile.writeDataIntoCsv(filePath, resultList);
			System.out.println(">>>>>> End of writing csv file into " + filePath);

		} catch (Exception e) {
			System.out.println(">> Error: " + e.getMessage());
			// print error
			e.printStackTrace();
		}
		System.out.println("============= End of Process ==============");
	}

}
