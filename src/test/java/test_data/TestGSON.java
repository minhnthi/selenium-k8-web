package test_data;

import com.google.gson.Gson;
import test_data.computer.ComputerData;

import java.util.Arrays;

public class TestGSON {

    public static void main(String[] args) {
     //   exploregsonFeature();
        testDataBuilder();
    }

    private static void testDataBuilder() {
       // String fileLocation = "/src/test/java/test_data/computer/CheapComputerDataList.json";
        String fileLocation = "/src/test/java/test_data/computer/CheapComputerData.json";
      // ComputerData[] computerData =  DataObjectBuilder.buildDataObjectFrom(fileLocation,ComputerData[].class);
       ComputerData computerData =  DataObjectBuilder.buildDataObjectFrom(fileLocation,ComputerData.class);
        System.out.println(computerData);

    }

    private static void exploregsonFeature() {

        String JSONString= "{\n" +
                "    \"processorType\": \"Fast\",\n" +
                "    \"ram\": \"8 GB\",\n" +
                "    \"hdd\": \"320 GB\",\n" +
                "    \"software\": \"Image Viewer\"\n" +
                "  }";
        Gson gson =new Gson();
        ComputerData computerData =  gson.fromJson(JSONString, ComputerData.class);
        System.out.println(computerData);

        System.out.println(gson.toJson(computerData));
    }


}

