import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import org.jsoup.Jsoup;

public class HTMLParser implements Constants {


	public void html_Parser() throws Exception {

		System.out.println("HTML files parsing initialized");
		// first, create webpages directory if not exists
		//Helpers.createDir(PARSED_HTML_PAGE_DIR);
		createDir(PARSED_HTML_PAGE_DIR);
		// read all HTML files from
		File directoryPath = new File(absoluteHTMLDirectoryPath);

		StringBuilder sb = new StringBuilder();
		String line;
		// List of all files and directories
		File filesList[] = directoryPath.listFiles();

		for (File file : filesList) {
			String fileName = file.getName();
			BufferedReader br = new BufferedReader(new FileReader(absoluteHTMLDirectoryPath + fileName));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			String htmlContent = sb.toString().replaceAll("\\<.*?>", "");

			// fetch all words using JSOUP (still need to IMPROVE)
			String htmlWords = Jsoup.parse(htmlContent, "ISO-8859-1").select("body").text();

			System.out.println("parsing -" + fileName);
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
			BufferedWriter writer = new BufferedWriter(
					new FileWriter(absoluteParsedHTMLContentDirectoryPath + fileName + txtPageExtention));
			writer.write(htmlWords);
			writer.close();
		}
		System.out.println("HTML files parsing completed");
	}
	public static void createDir(String path) {
		System.out.println("creating directory if not exists");
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
}
