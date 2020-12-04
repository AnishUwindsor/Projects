
// Java program to read and download 
// webpage in html file 
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.net.MalformedURLException;

public class WebCrawler implements Constants {

	public static void createDir(String path) {
		System.out.println("creating directory");
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	public static void DownloadWebPagesInLocalDir(String[] urls) {
		try {

			// Create URL object
			for (String webpageURL : urls) {
				URL url = new URL(webpageURL);
				BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

				// generate random file name
				String fileName = System.currentTimeMillis() + htmlPageExtention;

				// Enter filename in which you want to download
				BufferedWriter writer = new BufferedWriter(new FileWriter(absoluteHTMLDirectoryPath + fileName));

				// read each line from stream till end
				String line;
				while ((line = reader.readLine()) != null) {
					writer.write(line);
				}
				reader.close();
				writer.close();
			}
		}

		// Exceptions
		catch (MalformedURLException mue) {
			System.out.println("Malformed URL Exception raised");
		} catch (IOException ie) {
			System.out.println("IOException raised" + ie.getMessage());
		}
	}

	public void web_Crawler() throws IOException {
		System.out.println("WebCrawler execution initialized");

		// first, create webpages directory if not exists
		createDir(HTML_PAGE_DIR);

		String url = "https://www.uwindsor.ca/";
		System.out.println("Scrapping URL" + url);

		URLScrapper urlScrapper = new URLScrapper();
		urlScrapper.getPageLinks(url, 0);

		String data = new String(Files.readAllBytes(Paths.get(scrappedURLFile)));
		String[] urls = data.split("\n");

		System.out.println("Scrapping URL - completed");

        System.out.println("Downloading HTML pages from URLs");

		DownloadWebPagesInLocalDir(urls);

        System.out.println("Downloading HTML pages from URLs - completed");

		System.out.println("WebCrawler execution completed");
	}
}