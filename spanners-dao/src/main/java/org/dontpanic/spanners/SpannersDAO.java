package org.dontpanic.spanners;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * DAO for spanners
 */
@PreAuthorize("hasRole('ROLE_EXCLUDE_ALL')")
public interface SpannersDAO {

    public Spanner get(int id);

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    public List<Spanner> getAll();

    public int create(Spanner spanner);

    public void delete(Spanner spanner);
}
