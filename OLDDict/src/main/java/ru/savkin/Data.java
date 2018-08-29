/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.savkin;

import java.io.*;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 * f
 *
 * @author dmitriy
 */
public class Data {

    static String s2;
    HashMap<String, String> h = new HashMap();

    public HashMap readFile(String fileName) {
        int i = 0, i1 = 0, l = 0, l1 = 0;
        s2 = "";
        h.clear();

        Definition.definition.clear();
        boolean flag = false;

        if (fileName == null || fileName.isEmpty()) {

            new Error(Client.frame, Error.FILE_ERROR, JOptionPane.WARNING_MESSAGE);

            return null;
        } else {
            BufferedReader in = null;


            try {
                in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            } catch (FileNotFoundException ex) {
            }


            try {
                while (in.ready()) {
                    s2 += in.readLine();


                }

            } catch (IOException ex) {
            }
            String key = null;
            String value = null;
            ArrayList definition = new ArrayList();
            for (int j = 0; j < s2.length(); j++) {
                if (!flag) {
                    i = s2.indexOf("<dt>", i1);

                    i1 = s2.indexOf("</dt>", i1 + 4);

                    l = s2.indexOf("<dd>", l1);
                    l1 = s2.indexOf("</dd>", l1 + 4);
                }
                try {

                    if (!flag) {
                        key = s2.substring(i + 4, i1);
                        definition.clear();
                        value = s2.substring(l + 4, l1 + 5);

                    }
                    if (!flag) {
                        definition.add(value);
                    }
                    flag = false;
                    if (s2.substring(l1 + 5, l1 + 9).equals("<dd>")) {


                        l = s2.indexOf("<dd>", l1);
                        l1 = s2.indexOf("</dd>", l1 + 4);
                        flag = true;
                        value = s2.substring(l + 4, l1 + 5);
                        definition.add(value);

                    }


                    h.put(key, definition.toString());

                } catch (Exception ex) {
                }
                if (i == -1) {
                    break;
                }
            }
        }
        return h;
    }

    public void searchTerm(String term) {
        String g = null;

    

            System.out.println(term);
            HashMap h1 = readFile(Definition.file);
            if (h1 == null) {
            } else {
                for (Iterator<String> it = h.keySet().iterator(); it.hasNext();) {

                    g = it.next();

                    if (g.startsWith(term)) {

                        String definition = h.get(g).toString().substring(1, h.get(g).toString().length() - 1);
                        String tmp[] = definition.split("</dd>");
                        for (int i = 0; i < tmp.length; i++) {
                            Definition.definition.add(tmp[i].toString().trim());
                        }
                    }

                }

            
        }
        h.clear();
    }

    /**
     * <dt> ACID </dt>
     *
     * @param term
     * @param definition
     */
    public void editFile(String term, String definition) {

        int i = 0, i1 = 0, l = 0, l1 = 0;
        HashMap h1 = readFile(Definition.file);
        BufferedWriter out;
        String g, de = "";
        String termTags = "";
        for (Iterator<String> it = h.keySet().iterator(); it.hasNext();) {
            g = it.next();
            if (g.startsWith(term)) {
                String def = h1.get(g).toString().substring(1, h.get(g).toString().length() - 1);
                String tmp[] = def.split("</dd>");
                for (int y = 0; y < tmp.length; y++) {
                    Definition.definition.add(tmp[y]);
                }

                Definition.definition.set(Client.i - 1, definition);
                for (int j = 0; j < Definition.definition.size(); j++) {
                    de += "<dd>" + Definition.definition.get(j);
                }
            }
            termTags = "<dt>" + term + "</dt>" + de;
        }
        int index = s2.indexOf(term);
        int cc = s2.indexOf("<dt>", index);
        if (cc == -1) {
            cc = s2.indexOf("</dl>", index);
        }
        String s = s2.substring(index - 4, cc);
        String rep = s2.replaceFirst(s, termTags);
        try {
            out = new BufferedWriter(new FileWriter(Definition.file));
            out.write(rep);
            out.flush();
        } catch (IOException ex) {
            new Error(Client.frame, Error.WRITTING_ERROR, JOptionPane.WARNING_MESSAGE);
        }

    }

    public void delete(String term) {
        String definition = Client.definitionField.getText();
        if (term.equals("")) {
            new Error(Client.frame, Error.TERM_ERROR, JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (definition.equals("")) {
            return;
        }
        BufferedWriter out;
        HashMap h1 = readFile(Definition.file);

        String g, de = "";
        String termTags = "";
        for (Iterator<String> it = h1.keySet().iterator(); it.hasNext();) {
            g = it.next();
            if (g.startsWith(term)) {
                String def = h1.get(g).toString().substring(1, h1.get(g).toString().length() - 1);
                String tmp[] = def.split("</dd>");
                for (int y = 0; y < tmp.length; y++) {
                    Definition.definition.add(tmp[y]);
                }
                Definition.definition.remove(Client.definitionField.getText());

                for (int j = 0; j < Definition.definition.size(); j++) {
                    de += "<dd>" + Definition.definition.get(j)
                            + "</dd>";
                }
            }
            if (Definition.definition.size() == 0) {
                termTags = "";
            } else {
                termTags = "<dt>" + term + "</dt>" + de;
            }

        }
        int index = s2.indexOf(term);
        int cc = s2.indexOf("<dt>", index);
        if (cc == -1) {
            cc = s2.indexOf("</dl>", index);
        }
        String s = s2.substring(index - 4, cc);
        String rep = s2.replaceFirst(s, termTags);
        try {
            out = new BufferedWriter(new FileWriter(Definition.file));
            out.write(rep);
            out.flush();
        } catch (IOException ex) {
            new Error(Client.frame, Error.WRITTING_ERROR, JOptionPane.WARNING_MESSAGE);
        }

    }

    public void add(String term, String definition) {

        String s;

        if (term.equals("")) {
            new Error(Client.frame, Error.TERM_ERROR, JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (definition.equals("")) {
            new Error(Client.frame, Error.DEDFINITION_ERROR, JOptionPane.WARNING_MESSAGE);
            return;
        }
        int index = 0;
        boolean flag = false;
        BufferedWriter out;
        int i = 0;
        HashMap h1 = readFile(Definition.file);
        String g, de = "";
        String termTags = "";
        for (Iterator<String> it = h1.keySet().iterator(); it.hasNext();) {
            g = it.next();
            if (g.equals(term)) {
                flag = true;
                String def = h1.get(g).toString().substring(1, h1.get(g).toString().length() - 1);
                String tmp[] = def.split(",");
                for (int y = 0; y < tmp.length; y++) {
                    Definition.definition.add(tmp[y]);

                }

            }
        }

        if (!flag) {

            int cc = 0;
            termTags = "<dt>" + term + "</dt><dd>" + definition + "</dd>";
            System.out.println("bbfvbv  " + termTags);
            String sub = "", sub2 = "";
            for (Iterator<String> it = h1.keySet().iterator(); it.hasNext();) {

                g = it.next();
                System.out.println(g + "  " + term + " " + g.compareTo(term));

                if (g.compareTo(term) > 0) {
                    System.out.println(g + "  " + term);

                    index = s2.indexOf(g);
                    cc = s2.indexOf("<dt>", index);
                    if (cc == -1) {
                        cc = s2.indexOf("</dl>", index);
                    }
                    sub = s2.substring(0, cc);
                    sub2 = s2.substring(cc);

                    break;
                }

            }

            s = sub + termTags + sub2;
            System.out.println("s " + s);


        } else {
            for (int j = 0; j < Definition.definition.size(); j++) {
                if (Definition.definition.get(j).toString().compareTo(definition) > 0) {
                    i = j;
                }
            }
            if (i == 0) {
                Definition.definition.add(definition);
            } else {
                Definition.definition.add(i, definition);
            }
            for (int j = 0; j < Definition.definition.size(); j++) {
                de += "<dd>" + Definition.definition.get(j);

            }
            termTags = "<dt>" + term + "</dt>" + de + "</dd>";
            index = s2.indexOf(term);
            int cc = s2.indexOf("<dt>", index);
            if (cc == -1) {
                cc = s2.indexOf("</dl>", index);
            }
            s = s2.substring(index - 4, cc);
        }

        s = s2.replaceFirst(s, termTags);

        System.out.println(Definition.file);
        try {
            out = new BufferedWriter(new FileWriter(Definition.file));
            out.write(s);
            out.flush();
        } catch (IOException ex) {
            new Error(Client.frame, Error.WRITTING_ERROR, JOptionPane.WARNING_MESSAGE);
        }

    }
}
