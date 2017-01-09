package word2vec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.BreakIterator;
import java.util.Locale;
import java.util.stream.Stream;

public class SentencesToLines {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Path file = Paths.get("C:\\Users\\Ivo\\Desktop\\test\\raw_sentences.txt");
		
		try (Stream<String> lines = Files.lines(file, StandardCharsets.UTF_8)) {
			  lines.forEach(line -> process(line));
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}		
		
	}

	private static void process(String line) {
		File fixedF =new File("C:\\Users\\Ivo\\Desktop\\test\\raw_sentences_in_lines.txt");
		try {
			FileOutputStream fout =new FileOutputStream(fixedF,true);
			BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
//			String source = "This is a test. This is a T.L.A. test. Now with a Dr. in it.";
			String source = line;
			iterator.setText(source);
			int start = iterator.first();
			for (int end = iterator.next();
			    end != BreakIterator.DONE;
			    start = end, end = iterator.next()) {
//			  System.out.println(source.substring(start,end));
				fout.write((source.substring(start,end) + "\n").getBytes("UTF-8"));
			}
			fout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
