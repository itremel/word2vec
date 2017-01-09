package word2vec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream.GetField;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlToSentences {

	public static void main(String[] args)	throws Exception {
		String datadir = "C:\\Users\\Ivo\\Desktop\\test";
//		String datadir = "C:\\Users\\Ivo\\Downloads\\Temporalia_Sample3.tar\\Temporalia_Sample3";
		File[] files = new File(datadir).listFiles();
		for (File f: files) {
			getDocument(f);
		}
	}
	
	protected static void getDocument(File f) throws Exception {
		
		try{
			DocumentBuilderFactory dbFactory= DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document d = dBuilder.parse(f);
			d.getDocumentElement().normalize();
			
			NodeList nList = d.getElementsByTagName("doc");
			for(int i = 0; i< nList.getLength(); i++){
				Node nNode = nList.item(i);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					Element e = (Element) nNode;
					if(e.getElementsByTagName("text").item(0) != null){
						File fixedF =new File("C:\\Users\\Ivo\\Desktop\\test\\raw_sentences.txt");
						FileOutputStream fout = new FileOutputStream(fixedF,true);
						fout.write(e.getElementsByTagName("text").item(0).getTextContent().getBytes("UTF-8"));
						fout.close();
						
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
