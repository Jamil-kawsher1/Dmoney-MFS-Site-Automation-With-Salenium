package Utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static void addIntoJsonList (String fileLocation, String name, String email, String phone, String role) throws IOException, ParseException {
        String fileName = fileLocation;
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject jsonList = new JSONObject();
        jsonList.put("name",name);
        jsonList.put("email",email);
        jsonList.put("phone",phone);
        jsonList.put("role",role);
        jsonArray.add(jsonList);
        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
    }

    public static JSONObject readJSONFile(String filename,int index) throws IOException, ParseException {


        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(filename));
        JSONArray jsonArray = (JSONArray) obj;
        int arraySize=jsonArray.size();
        int arrayIn=arraySize-index;

        JSONObject    arryObject= (JSONObject) jsonArray.get(arrayIn);


        return arryObject;



    }
}
