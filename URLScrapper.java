import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashSet;

public class URLScrapper implements Constants {
	private static final int MAX_DEPTH = 2;
	private HashSet<String> links;

	public URLScrapper() {
		links = new HashSet<>();
	}

	public void getPageLinks(String URL, int depth) throws IOException {
		String fileName = SCRAPPED_URL_TXT_FILE;
		BufferedWriter writer = new BufferedWriter(new FileWriter(scrappedURLFile));
		if ((!links.contains(URL) && (depth < MAX_DEPTH))) {

			// System.out.println(">> Depth: " + depth + " [" + URL + "]");

			try {
				links.add(URL);

				Document document = Jsoup.connect(URL).get();
				Elements linksOnPage = document.select("a[href]");

				depth++;
				for (Element page : linksOnPage) {
					getPageLinks(page.attr("abs:href"), depth);
					String link = page.attr("abs:href") + "\n";
					writer.write(link);
				}
			} catch (IOException e) {
				System.err.println("For '" + URL + "': " + e.getMessage());
			}
		}
		writer.close();

	}

	public static void main(String[] args) throws IOException {

		new URLScrapper().getPageLinks("http://www.mkyong.com/", 0);
	}

}
