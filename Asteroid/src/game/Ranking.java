package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ranking {
	private File xml;
	private String[][] list;
	
	public Ranking(){
		list = new String[0][0];
		xml = new File("ranking.xml");
		readXML();
	}
	public boolean checkScore(int score){
		for(int i=0; i<list.length; i++)
			if(score>str2Int(list[i][0]))
				return true;
		return false;
	}
	public void addNewScore(String name, String score){
		addToList(new String[]{name,score});
		saveXML();
	}
	private int str2Int(String str){
		int value = 0;
		try{ value = Integer.parseInt(str);
		} catch(Exception e){};
		return value;
	}
	private void readXML(){
		try {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xml);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("player");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {	 
			Node nNode = nList.item(temp);
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				
				String name = eElement.getElementsByTagName("name").item(0).getTextContent();
				String score = eElement.getElementsByTagName("score").item(0).getTextContent();
				String[] row = {name,score};
				addToList(row);
			}
		}
		
		} catch (Exception e) {}
	}
	public void saveXML(){
		cleanFile();
		for(int i=0; i<list.length; i++)
			xmlWrite(list[i][0],list[i][1]);
	}
	private void cleanFile(){
		 PrintWriter save;
		try {
			save = new PrintWriter(xml);
			save.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
			save.println("<ranking>");
			save.println("</ranking>");
			save.close();
		} catch (FileNotFoundException e) {}
	}
	private void xmlWrite(String name, String score) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = docBuilder.parse(xml);

            Node data = doc.getFirstChild();

            org.w3c.dom.Element root = doc.createElement("ranking");
            org.w3c.dom.Element player = doc.createElement("player");
            org.w3c.dom.Element _name = doc.createElement("name");
            _name.setTextContent(name);
            org.w3c.dom.Element _score = doc.createElement("score");
            _score.setTextContent(score);

            player.appendChild(_name);
            player.appendChild(_score);
            root.appendChild(player);
            
            data.appendChild(player);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xml);
            transformer.transform(source, result);

        } catch (Exception ex) {}
    }
	private void addToList(String[] row){
		row[1] = str2Int(row[1])+"";
		
		String[][] temp = new String[list.length+1][2];
		
		for(int i=0; i<list.length; i++)
			temp[i] = list[i];
		temp[list.length] = row;
		
		list = new String[temp.length][2];
		list = temp;
		sort();
		
		if(temp.length>10){
			list = new String[10][2];
			for(int i=0; i<list.length; i++)
				list[i] = temp[i];
		}
	}
	private void sort(){
		int k = list.length-1;
		String[] temp = new String[2];
		temp = list[k];
		for(int i=0; i<list.length; i++){
			if(str2Int(temp[1])>str2Int(list[i][1])){
				String[] temp2 = list[i];
				list[i] = temp;
				temp = temp2;
				list[k] = temp;
			}
		}
	}
	public String[][] getList(){
		return list;
	}

}
