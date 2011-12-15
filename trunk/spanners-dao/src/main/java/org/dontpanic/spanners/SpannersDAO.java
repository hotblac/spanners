package org.dontpanic.spanners;

import java.util.List;

/**
 * DAO for spanners
 */
public interface SpannersDAO {

    public Spanner get(int id);

    public int create(Spanner spanner);

}
