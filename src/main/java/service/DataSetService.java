package service;

import model.DataSet;
import org.springframework.stereotype.Service;

public interface DataSetService {

    public DataSet create(DataSet dataSet);

    public DataSet update(DataSet dataSet);

    public void delete(DataSet dataSet);

    public DataSet find(DataSet dataSet);

}
