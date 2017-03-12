package service;

import model.Distribution;
import org.apache.jena.rdf.model.Resource;

public interface DistributionService {

    public Resource create(Distribution distribution);

    public Distribution update(Distribution distribution);

    public void delete(Distribution distribution);

    public Distribution find(Distribution distribution);

}
