package hello;

import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

@Controller
public class MapController {

    @RequestMapping("/map")
    public String map(Map<String, Object> model) {
        System.out.println("hello from MapController.map()");
        return "map";
    }

    @RequestMapping("/json/addresses")
    public @ResponseBody String addresses() {
        System.out.println("hello from MapController.addresses()");

        return getJSONMock("https://download.data.public.lu/resources/adresses-georeferencees-bd-adresses/20170306-053100/addresses.geojson");
    }

    public String getJSONMock(String url) {
        return "{\"name\":\"addresspoints\",\"type\":\"FeatureCollection\"\n" +
                ",\"features\":[\n" +
                "{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPoint\",\"coordinates\":[[5.84441104566029,49.8625812370655]]},\"properties\":{\"rue\":\"Rue du Lac\",\"numero\":\"61\",\"localite\":\"Arsdorf\",\"code_postal\":\"8808\",\"id_caclr_rue\":\"4441\",\"id_caclr_bat\":\"141578\"}}\n" +
                ",{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPoint\",\"coordinates\":[[5.84457183991774,49.8628841939451]]},\"properties\":{\"rue\":\"Rue du Lac\",\"numero\":\"50\",\"localite\":\"Arsdorf\",\"code_postal\":\"8808\",\"id_caclr_rue\":\"4441\",\"id_caclr_bat\":\"141603\"}}\n" +
                ",{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPoint\",\"coordinates\":[[5.84477698089321,49.862738497893]]},\"properties\":{\"rue\":\"Rue du Lac\",\"numero\":\"48\",\"localite\":\"Arsdorf\",\"code_postal\":\"8808\",\"id_caclr_rue\":\"4441\",\"id_caclr_bat\":\"141602\"}}\n" +
                ",{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPoint\",\"coordinates\":[[5.84432375129883,49.8629541246666]]},\"properties\":{\"rue\":\"Rue du Lac\",\"numero\":\"52\",\"localite\":\"Arsdorf\",\"code_postal\":\"8808\",\"id_caclr_rue\":\"4441\",\"id_caclr_bat\":\"174927\"}}\n" +
                ",{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPoint\",\"coordinates\":[[5.84579977577635,49.8628311520774]]},\"properties\":{\"rue\":\"Rue du Lac\",\"numero\":\"46\",\"localite\":\"Arsdorf\",\"code_postal\":\"8808\",\"id_caclr_rue\":\"4441\",\"id_caclr_bat\":\"141601\"}}\n" +
                ",{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPoint\",\"coordinates\":[[5.84603372406313,49.8616074199135]]},\"properties\":{\"rue\":\"Rue du Lac\",\"numero\":\"44\",\"localite\":\"Arsdorf\",\"code_postal\":\"8808\",\"id_caclr_rue\":\"4441\",\"id_caclr_bat\":\"141600\"}}\n" +
                ",{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPoint\",\"coordinates\":[[5.84614064083733,49.8615767918824]]},\"properties\":{\"rue\":\"Rue du Lac\",\"numero\":\"44A\",\"localite\":\"Arsdorf\",\"code_postal\":\"8808\",\"id_caclr_rue\":\"4441\",\"id_caclr_bat\":\"216128\"}}\n" +
                ",{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPoint\",\"coordinates\":[[5.8451814117835,49.8617419046941]]},\"properties\":{\"rue\":\"Rue du Lac\",\"numero\":\"42\",\"localite\":\"Arsdorf\",\"code_postal\":\"8808\",\"id_caclr_rue\":\"4441\",\"id_caclr_bat\":\"141599\"}}\n" +
                ",{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPoint\",\"coordinates\":[[5.8452695285337,49.8617276232575]]},\"properties\":{\"rue\":\"Rue du Lac\",\"numero\":\"42A\",\"localite\":\"Arsdorf\",\"code_postal\":\"8808\",\"id_caclr_rue\":\"4441\",\"id_caclr_bat\":\"191948\"}}\n" +
                ",{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPoint\",\"coordinates\":[[5.84462324622499,49.8611898969948]]},\"properties\":{\"rue\":\"An der Hiehlt\",\"numero\":\"10\",\"localite\":\"Arsdorf\",\"code_postal\":\"8809\",\"id_caclr_rue\":\"4435\",\"id_caclr_bat\":\"216129\"}}\n" +
                ",{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPoint\",\"coordinates\":[[5.8432546370796,49.8603660851073]]},\"properties\":{\"rue\":\"Rue du Lac\",\"numero\":\"30\",\"localite\":\"Arsdorf\",\"code_postal\":\"8808\",\"id_caclr_rue\":\"4441\",\"id_caclr_bat\":\"141593\"}}\n" +
                ",{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPoint\",\"coordinates\":[[5.843191561571,49.8608555056751]]},\"properties\":{\"rue\":\"An der Hiehlt\",\"numero\":\"2\",\"localite\":\"Arsdorf\",\"code_postal\":\"8809\",\"id_caclr_rue\":\"4435\",\"id_caclr_bat\":\"141511\"}}\n" +
                ",{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPoint\",\"coordinates\":[[5.84329865021924,49.8609562192933]]},\"properties\":{\"rue\":\"An der Hiehlt\",\"numero\":\"4\",\"localite\":\"Arsdorf\",\"code_postal\":\"8809\",\"id_caclr_rue\":\"4435\",\"id_caclr_bat\":\"141512\"}}\n" +
                ",{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPoint\",\"coordinates\":[[5.84342721510112,49.8609690583219]]},\"properties\":{\"rue\":\"An der Hiehlt\",\"numero\":\"6\",\"localite\":\"Arsdorf\",\"code_postal\":\"8809\",\"id_caclr_rue\":\"4435\",\"id_caclr_bat\":\"141513\"}}\n" +
                ",{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPoint\",\"coordinates\":[[5.843557197609,49.8609835491485]]},\"properties\":{\"rue\":\"An der Hiehlt\",\"numero\":\"8\",\"localite\":\"Arsdorf\",\"code_postal\":\"8809\",\"id_caclr_rue\":\"4435\",\"id_caclr_bat\":\"206398\"}}\n" +
                ",{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPoint\",\"coordinates\":[[5.84239890738254,49.8606667951291]]},\"properties\":{\"rue\":\"Rue du Lac\",\"numero\":\"32\",\"localite\":\"Arsdorf\",\"code_postal\":\"8808\",\"id_caclr_rue\":\"4441\",\"id_caclr_bat\":\"141594\"}}\n" +
                "]}";
    }

    public String getJSON(String url) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(url);
            getRequest.addHeader("accept", "application/json");

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }

            String res =  EntityUtils.toString(response.getEntity());
            System.out.println("finished json call from MapController.getJSON()");
            return res;
        } catch (ClientProtocolException e) {
            throw new RuntimeException("An error occurred with URL: " + url);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred with URL:" + url);
        }
    }
}