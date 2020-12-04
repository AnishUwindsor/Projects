import java.util.Scanner;

public class SearchEngine implements Constants {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TrieManager trie = new TrieManager();
		WebCrawler webcrawler = new WebCrawler();
		HTMLParser htmlParser = new HTMLParser();
		Scanner scan = new Scanner(System.in);
		char op;
		String input;
		try {
			//webcrawler.web_Crawler();
			htmlParser.html_Parser();
			trie.generateHashTable();

			do {
				System.out.println("Enter the string to be searched:");
				input = scan.next();
				trie.triemanager(input);
				System.out.println("Do you want to continue?(Y/N):");
				op = scan.next().charAt(0);
			} while (op == 'Y' || op == 'y');

		} catch (Exception ie) {
			System.out.println("Exception raised" + ie.getMessage());
		}
		System.out.println("Search Completed");

	}
}
