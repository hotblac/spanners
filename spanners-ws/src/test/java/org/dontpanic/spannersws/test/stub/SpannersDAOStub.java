package org.dontpanic.spannersws.test.stub;

import org.dontpanic.spanners.Spanner;
import org.dontpanic.spanners.SpannersDAO;

import java.util.Collections;
import java.util.List;

public class SpannersDAOStub implements SpannersDAO {

    @Override
    public Spanner get(int i) {
        Spanner spanner = new Spanner();
        spanner.setId(1);
        spanner.setName("Spanner 1");
        spanner.setSize(42);
        return spanner;

    }

    @Override
    public List<Spanner> getAll() {
        return Collections.emptyList();
    }

    @Override
    public int create(Spanner spanner) {
        return 0;
    }

    @Override
    public void delete(int id) {
    }
}
