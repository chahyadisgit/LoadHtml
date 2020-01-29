/**
 * 
 */
package com.hctest.htmlload.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * LoadHTMLSource<br>
 * Load data from website.
 * 
 * @author SuryaChahyadi
 * @version 1.0.0
 * @since January 28th, 2019
 */
public class LoadHTMLSource {

	/**
	 * readHtml<br>
	 * read HTML Element.
	 * 
	 * @param url        {@link String}<br>
	 *                   link website as a target.
	 * @param attrObject {@link String}<br>
	 *                   attribute object, like id or class
	 * @return {@link List}<br>
	 *         string array into list
	 * @throws Exception<br>
	 *                       Error handler.
	 */
	public static List<String[]> readHtml(String url, String attrObject) throws Exception {
		List<String[]> resultList = new ArrayList<String[]>();
		// create connection to website
		Document doc = Jsoup.connect(url).get();

		// get all tags with attribute class name .wikitable
		Elements tableElements = doc.select(attrObject);

		// filter html tags
		resultList = ExtractTags(tableElements, 0, "tr", "td");

		// return result value as list
		return resultList;
	}

	/**
	 * ExtractTags<br>
	 * Extract Elements by parameter.
	 * 
	 * @param tableElements {@link Elements}<br>
	 *                      table Elements value.
	 * @param pos           {@link Integer}<br>
	 *                      index of elements.
	 * @param tags          {@link String}<br>
	 *                      element filter.
	 * @param targetTags    {@link String}<br>
	 *                      detail element filter.
	 */
	private static List<String[]> ExtractTags(Elements tableElements, int pos, String tags, String targetTags) {
		// select elements base on tags value
		Elements tblEle = tableElements.get(pos).select(tags);

		// Declare variable
		List<String[]> lst = new ArrayList<String[]>();
		StringJoiner joiner;
		String val;

		// loop elements value
		for (int i = 0; i < tblEle.size(); i++) {
			// initiate variable joiner
			joiner = new StringJoiner("|");

			// select elements base on targetTags value
			Elements ele = tblEle.get(i).select(targetTags);

			// loop element tagetTags
			for (int j = 0; j < ele.size(); j++) {
				// exclude rows Lambang (1), Kode ISO (3) and Status Khusus (7)
				if (j != 1 && j != 3 && j != 7) {
					val = new String(ele.get(j).text());
					// change format number for row populasi (5) and luas km (6)
					if (5 == j || 6 == j) {
						val = val.replace(".", "").replace(",", ".");
					}

					// insert value to joiner variable
					joiner.add(val);
				}
			}

			// if joiner variable is not empty
			if (joiner.toString().length() > 0) {
				// insert value joiner array into list
				lst.add(joiner.toString().split("\\|"));
			}
		}

		// return value as a list
		return lst;
	}
}
