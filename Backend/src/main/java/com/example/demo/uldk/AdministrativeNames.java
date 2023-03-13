package com.example.demo.uldk;

import com.example.demo.model.UldkItem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public abstract class AdministrativeNames {
    public static ArrayList<UldkItem> getNames(String type, String teryt) {
        if (teryt.isEmpty())
            teryt = "";

        String param = "";
        String result = "";
        ArrayList<UldkItem> uldkItems = new ArrayList<>();

        switch (type) {
            case "Wojewodztwo" -> {
                param = "GetVoivodeshipById";
                result = "voivodeship";
            }
            case "Powiat" -> {
                param = "GetCountyById";
                result = "county";
            }
            case "Gmina" -> {
                param = "GetCommuneById";
                result = "commune";
            }
            case "Region" -> {
                param = "GetRegionById";
                result = "region";
            }
            case "Dzialka" -> {
                param = "GetParcelById";
                result = "geom_wkt";
            }
        }

        try {
            String link = "https://uldk.gugik.gov.pl/?request=" + param + "&result=teryt," + result + "&id=" + teryt;
            URL url = new URL(link);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() > 5) {
                    String[] tmp = line.split("\\|");
                    UldkItem uldkItem = new UldkItem();
                    uldkItem.setName(tmp[1]);
                    uldkItem.setTeryt(tmp[0]);
                    uldkItems.add(uldkItem);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            return null;
        }

        return uldkItems;
    }
}
