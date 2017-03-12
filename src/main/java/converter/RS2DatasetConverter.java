package converter;

import model.DataSet;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.cookie.Cookie;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Resource;

import java.util.*;


/**
 * Created by aliafa on 3/12/2017.
 */
public class RS2DatasetConverter {

    public Collection<DataSet> toDataSet(ResultSet resultSet) {
        Map<String, DataSet> map = new HashMap<>();

        while (resultSet.hasNext()) {
            QuerySolution qs = resultSet.next();

            String uri = qs.getResource("dataset").getURI();

            DataSet dataSet;
            if (map.containsKey(uri)) {
                dataSet = map.get(uri);
            } else {
                dataSet = new DataSet();
                map.put(uri, dataSet);
            }

            if (qs.getLiteral("description") != null && StringUtils.isNotEmpty(qs.getLiteral("description").getString())) {
                dataSet.setDescription(qs.getLiteral("description").getString());
            }

            if (qs.getLiteral("title") != null && StringUtils.isNotEmpty(qs.getLiteral("title").getString())) {
                dataSet.setTitle(qs.getLiteral("title").getString());
            }
        }

        return map.values();
    }

}
