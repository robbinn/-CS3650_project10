package com.siwen;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Analyzer {
    static File _filename;
    static String _tempOutFile;
    static String _outFile;
    static HashMap<String, String> _map;

    static void analyze (File filename) {
        _filename = filename;
        _tempOutFile = _filename.toString().split("\\.")[0] + "Temp.xml";
        cleanFile(_filename);
        _outFile = _filename.toString().split("\\.")[0] + "T.xml";
        _map = new HashMap<>();
        _map.put("<","&lt;");
        _map.put(">","&gt;");
        _map.put("\\","&quot;");
        _map.put("&","&amp;");
        firstAnalyzer();
    }

    static void cleanFile(File filename) {
        try {
            Scanner myReader = new Scanner(filename);
            //String outFile = filename.toString().split("\\.")[0] + "Temp.xml";
            FileWriter fWriter = new FileWriter(_tempOutFile);
            // write file with comment removed
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                data = data.trim();
                if (data.startsWith("//") || data.isEmpty())
                    continue;
                if (data.startsWith("/**") || data.startsWith("/*")) {
                    if (!data.contains("*/")) {
                        while (myReader.hasNextLine()) {
                            String next = myReader.nextLine();
                            if (next.contains("*/"))
                                break;
                        }
                    }
                    continue;
                }
                if (data.contains("//")) {
                    data = data.trim().split("//")[0];
                }
                fWriter.write(data + "\n");
            }
            myReader.close();
            fWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void firstAnalyzer () {
        File outFile = new File(_tempOutFile);
        try {
            Scanner myReader = new Scanner(outFile);
            FileWriter fWriter = new FileWriter(_outFile);
            fWriter.write("<tokens>\n");
            while (myReader.hasNextLine()) {
                String userIn = myReader.nextLine();
                char[] charStr = userIn.toCharArray();
                Queue<StringToken> queue = new LinkedList<>();
                StringTokenizer stk = new StringTokenizer(charStr);

                do {
                    StringToken t = stk.extractToken();
                    queue.add(t);
                    if (!t.stringType().equals("SPACE")) {
                        if (t.stringType().equals("stringConstant")) {
                            String temp = t.getToken().split("\"")[1];
                            fWriter.write("<" + t.stringType() + "> " + temp + " </" + t.stringType() + ">\n");
                        }
                        else if (_map.containsKey(t.getToken())) {
                            String temp = _map.get(t.getToken());
                            fWriter.write("<" + t.stringType() + "> " + temp + " </" + t.stringType() + ">\n");
                        }
                        else
                            fWriter.write("<" + t.stringType() + "> " + t + " </" + t.stringType() + ">\n");
                    }

                } while (stk.more());
            }
            fWriter.write("</tokens>\n");
            myReader.close();
            fWriter.close();

            //Deleting the temp file
            File myObj = new File(_tempOutFile);
            if (!myObj.delete()) {
                System.out.println("Failed to delete the file.");
            }
        } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
