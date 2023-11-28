package getData;

import org.json.JSONObject;
import org.json.XML;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;



public class openAPI {
    public static void main(String[] args) throws Exception {
        StringBuilder urlBUilder = new StringBuilder("http://api.kcisa.kr/openapi/CNV_060/request");

        urlBUilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + "bfd7bf7e-8c76-4161-8160-556f599ba9a5");
        urlBUilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBUilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));

        URL url = new URL(urlBUilder.toString());

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("response code: " + conn.getResponseCode());

        BufferedReader rd;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();



    JSONObject jsonResp = XML.toJSONObject(sb.toString());

        JSONObject json =   (JSONObject) jsonResp.get("response");
        JSONObject body =  ((JSONObject)json.get("body"));


//        String path = System.getProperty("user.dir");
//        FileWriter writer = new FileWriter(path + "/src/files/data1.txt");
//        writer.write(body.toString());
//        writer.flush();



    }
}


