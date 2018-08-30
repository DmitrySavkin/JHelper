package ru.savkin;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.text.BadLocationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentReader {


    private static DocumentReader documentReader;
    private final String TAG = "definition";
    private final String fileName = "dictinary.xml";
    public final File file = new File(ClassLoader.getSystemResource(fileName).getFile());

    private DocumentReader() {}
    /**
     * Gets document structure of xml booking file
     * @return the xml file with booking data
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws BadLocationException
     *
     */
    public final Document getContent() throws SAXException,
            ParserConfigurationException,
            IOException, BadLocationException
             {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);

        return document;
    }


    /**
     * Gets booked datas
     * @return the map with available booking time
     */
    public List<Definition> getBookedDatas() {
        List<Definition> times = new ArrayList<>();
        try {
            NodeList l = getContent().getElementsByTagName(TAG);
            for (int i = 0; i < l.getLength(); i++) {
                Node n = l.item(i);
                String term = n.getAttributes().getNamedItem("name").getTextContent().trim();
                String def = n.getTextContent().trim();
                Definition definition = new Definition(term,def);
                times.add(definition);
                System.out.println(definition.getDefinition() + " <> " + definition.getDescription());
            }
        }
        catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        return  times;
    }




    public void addData(String name, String def) {

        try {
            Document doc = getContent();
            Element root = doc.getDocumentElement();
            Element defTag = doc.createElement("definition");
            defTag.setAttribute("name", name);
            defTag.setTextContent(def);
            root.appendChild(defTag);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            File file = new File(ClassLoader.getSystemResource(fileName).getFile());
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadLocationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void removeDefinition(String name) {
        try {
            Document doc = getContent();
            NodeList l = doc.getElementsByTagName(TAG);

            for (int i = 0; i < l.getLength(); i++) {
                if (l.item(i).getAttributes().getNamedItem("name").getTextContent().equalsIgnoreCase(name)) {
                    Node node = l.item(i);
                    node.getParentNode().removeChild(l.item(i));
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource domSource = new DOMSource(doc);
                    StreamResult streamResult = new StreamResult(new File(file.getCanonicalPath()));
                    transformer.transform(domSource, streamResult);
                    break;
                }

            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (BadLocationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static final DocumentReader getDocumentReader() {
        if (documentReader == null) {
            documentReader = new DocumentReader();
        }
        return documentReader;
    }


    public void addData(Definition definition) {

        addData(definition.getDefinition(), definition.getDescription());
    }


    public void editData(String def, String desc) {
        try {
            Document doc = getContent();
            NodeList l = doc.getElementsByTagName(TAG);
            for (int i = 0; i < l.getLength(); i++) {
                if (l.item(i).getAttributes().getNamedItem("name").getTextContent().equalsIgnoreCase(def)) {
                    Node node = l.item(i);
                    node.setTextContent(desc);
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource domSource = new DOMSource(doc);
                    StreamResult streamResult = new StreamResult(new File(file.getCanonicalPath()));
                    transformer.transform(domSource, streamResult);
                    break;
                }

            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (BadLocationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editData(Definition definition) {
            editData(definition.getDefinition(), definition.getDescription());
    }

}
