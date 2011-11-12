package org.dontpanic.spannersws.test.stub;

import org.dontpanic.spanners.Spanner;
import org.dontpanic.spanners.SpannersDAO;

public class SpannersDAOStub implements SpannersDAO {

    @Override
    public Spanner get(int i) {
        return null;
    }

    @Override
    public int create(Spanner spanner) {
        return 0;
    }
}
