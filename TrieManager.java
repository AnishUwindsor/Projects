import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;

public class TrieManager implements Constants {
	TST<Integer> tst = new TST<Integer>();
	Hashtable<Integer, HashMap<String, Integer>> lst = new Hashtable<Integer, HashMap<String, Integer>>();

	public void triemanager(String word) {
		// TODO Auto-generated method stub
		System.out.println("Searching started");
		int id = tst.get(word);
		System.out.println("Value:" + lst.get(id));

	}

	public void generateHashTable() throws Exception {
		System.out.println("Generating HashTable");
		File webpage = new File(absoluteParsedHTMLContentDirectoryPath);
		File[] allFiles = webpage.listFiles();

		ArrayList<String> arr = new ArrayList<String>();

		for (int i = 0; i < allFiles.length; i++) {
			if (allFiles[i].isFile()) {
				File myfile = new File(absoluteParsedHTMLContentDirectoryPath + allFiles[i].getName());
				// System.out.println(allFiles[i].getName());
				org.jsoup.nodes.Document doc = Jsoup.parse(myfile, "UTF-8");

				String text = doc.text();
				// System.out.println(text);
				// System.out.println("Creating Trie..........");

				Pattern p = Pattern.compile("[a-zA-Z]+");

				Matcher m1 = p.matcher(text);
				while (m1.find()) {
					arr.add(m1.group());
				}
			}
		}
		Integer ind = 0;

		for (String s : arr) {
			tst.put(s, ind);
			ind++;
			// System.out.println("Key ----->"+ s + " has index -------->"+ tst.get(s));
		}

		InvertedIndex invertedIndex = new InvertedIndex();
		// read all HTML files from
		File directoryPath = new File(absoluteParsedHTMLContentDirectoryPath);
		// List of all files and directories
		File filesList[] = directoryPath.listFiles();


		for (File file : filesList) {
			invertedIndex.indexFile(file);
		}

		// System.out.print("Entering HashTable");
		for (String word : arr) {
			// System.out.println("List"+invertedIndex.searchSingleWord(word));
			int index = tst.get(word);
			HashMap<String, Integer> indFreq = invertedIndex.searchSingleWord(word);
			lst.put(index, indFreq);
			// System.out.println("Word: "+word+"LIST: "+indFreq);
		}
		System.out.println("HashTable generated Successfully");
	}
}
