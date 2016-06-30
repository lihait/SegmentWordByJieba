package sen.word;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.WordDictionary;

public class segment {

	private static JiebaSegmenter segmenter = null;

	public static void init(String path) {
		if (segmenter == null) {
			System.out.println(path);
			WordDictionary.getInstance().init(new File(path));
			segmenter = new JiebaSegmenter();

		}
	}

	public static String[] segment(String sentence, String path) {
		init(path);
		List<SegToken> words = segmenter.process(sentence, SegMode.SEARCH);
		int iSize = words.size();
		String[] word = new String[iSize];
		for (int i = 0; i < iSize; i++) {
			SegToken seg = words.get(i);
			word[i] = sentence.substring(seg.startOffset, seg.endOffset);
		}

		return word;
	}

	public static void readTxtFile(String filePath1, String filepath2,
			String userpath) {
		try {
			String encoding = "UTF-16LE";
			JiebaSegmenter segmenter = new JiebaSegmenter();
			File file = new File(filePath1);
			File file2 = new File(filepath2);
			ArrayList<String> list = new ArrayList<String>();
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);

				OutputStreamWriter write = new OutputStreamWriter(
						new FileOutputStream(file2), encoding);

				BufferedReader bufferedReader = new BufferedReader(read);

				BufferedWriter writer = new BufferedWriter(write);

				String lineTxt = null;
				int count = 1;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					String[] word = (segment(lineTxt, userpath));
					String result = "";
					for (String w : word) {
						result += w + "  ";
					}
					System.out.println(result);
					writer.write(result);
					writer.newLine();
				}
				read.close();
				writer.close();

			} else {
				System.out.println("error");
			}
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		String filepath1 = args[0];
		String filepath2 = args[1];
		String userpath = args[2];
		readTxtFile(filepath1, filepath2, userpath);
	}

	private static String clearStr(String word) {
		String key = word;
		if (null != word && !"".equals(word.trim())) {
			key = word.trim().toLowerCase();
		}
		return key;
	}

}
