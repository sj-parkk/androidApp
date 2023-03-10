package com.example.etc;

import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class OpenAPI extends AsyncTask<Void, Void, String> {

    private String url;

    public OpenAPI(String url) {
        this.url = url;
    }

    @Override
    protected String doInBackground(Void... params) {

        // parsing할 url 지정(API 키 포함해서)

        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactoty.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = dBuilder.parse(url);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }

        // root tag
        doc.getDocumentElement().normalize();
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName()); // Root element: result

        // 파싱할 tag
        NodeList nList = doc.getElementsByTagName("item");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                if(getTagValue("category",eElement).equals("POP")){
                    Log.d("OPEN_API", "fcstDate  : " + getTagValue("fcstDate", eElement));
                    Log.d("OPEN_API", "fcstTime  : " + getTagValue("fcstTime", eElement));
                    Log.d("OPEN_API", "fcstValue  : " + getTagValue("fcstValue", eElement));
                    Log.d("OPEN_API", "ny  : " + getTagValue("ny", eElement));
                    Log.d("OPEN_API", "nx  : " + getTagValue("nx", eElement));
                    Log.d("OPEN_API", "ny  : " + getTagValue("ny", eElement));

                }



            }    // for end
        }    // if end
        return null;
    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
    }


    private String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if (nValue == null)
            return null;
        return nValue.getNodeValue();
    }
}