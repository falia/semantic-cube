package service;

import model.Distribution;

public interface DistributionService {

    public Distribution create(Distribution distribution);

    public Distribution update(Distribution distribution);

    public void delete(Distribution distribution);

    public Distribution find(Distribution distribution);

}
