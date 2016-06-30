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

public class SegWord {

	private static JiebaSegmenter segmenter = null;

	public static void init() {
		if (segmenter == null) {
			WordDictionary.getInstance().init(new File("seg/"));
			segmenter = new JiebaSegmenter();

		}
	}

	public static String[] segment(String sentence) {
		init();
		List<SegToken> words = segmenter.process(sentence, SegMode.SEARCH);
		int iSize = words.size();
		String[] word = new String[iSize];
		for (int i = 0; i < iSize; i++) {
			SegToken seg = words.get(i);
			word[i] = sentence.substring(seg.startOffset, seg.endOffset);
		}

		return word;
	}

	private static String clearStr(String word) {
		String key = word;
		if (null != word && !"".equals(word.trim())) {
			key = word.trim().toLowerCase();
		}
		return key;
	}

}
