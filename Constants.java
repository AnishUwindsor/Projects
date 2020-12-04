
// constants
import java.io.File;

public interface Constants {
	public static final String DATASETS = "datasets" + "/";
	public static final String HTML_PAGE_DIR = DATASETS + "htmlWebpages";
	public static final String PARSED_HTML_PAGE_DIR = DATASETS + "parsedHtmlContentPages";
	public static final String SCRAPPED_URL_TXT_FILE = DATASETS + "ScrappedURLs.txt";
	public static final String absoluteHTMLDirectoryPath = new File(HTML_PAGE_DIR).getAbsolutePath() + "/";
	public static final String absoluteParsedHTMLContentDirectoryPath = new File(PARSED_HTML_PAGE_DIR).getAbsolutePath()
			+ "/";
	public static final String scrappedURLFile = new File(DATASETS).getAbsolutePath()
			+ "/scrappedURL.txt";
	public static final String htmlPageExtention = ".html";
	public static final String txtPageExtention = ".txt";
}
