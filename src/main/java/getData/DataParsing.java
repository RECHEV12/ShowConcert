package getData;

import concert.dto.ConcertDTO;
import concert.service.ConcertService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataParsing {
    public static void main(String[] args) throws IOException, ParseException {
        ConcertService concertService = ConcertService.getInstance();
        String path = System.getProperty("user.dir");
        FileReader reader = new FileReader(path + "/src/files/data.txt");
        char[] bowl = new char[1];

        StringBuilder sb = new StringBuilder();

        while (true) {
            int cnt = reader.read(bowl);

            if (cnt == -1) {
                break;
            }

            String text = new String(bowl);
            sb.append(text);
        }

        JSONParser jsonParser = new JSONParser();
        JSONObject body = (JSONObject) jsonParser.parse(sb.toString());

        JSONObject itemsList = (JSONObject) body.get("items");
        JSONArray items = (JSONArray) itemsList.get("item");



        for (int i = 0 ; i <items.size(); i++){
            JSONObject item = (JSONObject)items.get(i);

            String period = (String)item.get("period");
            String tel = (String)item.get("contactPoint");
            long evPeriod = (long)item.get("eventPeriod");
            String title = (String)item.get("title");
            String url = (String)item.get("url");



            ConcertDTO concert = new ConcertDTO(period, tel, evPeriod, title, url, "",0,"","");
            concertService.insertConcert(concert);
        }


    }
}
