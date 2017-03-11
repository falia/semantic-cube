package repository;

import model.DataSet;
import org.springframework.stereotype.Repository;

public interface DataSetRepository {

    public DataSet create(DataSet dataSet);

    public DataSet update(DataSet dataSet);

    public void delete(DataSet dataSet);

    public DataSet find(DataSet dataSet);
}
