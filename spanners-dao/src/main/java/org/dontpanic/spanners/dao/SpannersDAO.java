package org.dontpanic.spanners.dao;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * DAO for spanners
 */
// Secure all methods with most restrictive role and secure methods with specific roles.
@PreAuthorize("hasRole('ROLE_EXCLUDE_ALL')")
public interface SpannersDAO {

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    public Spanner get(int id);

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    public List<Spanner> getAll();

    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public int create(Spanner spanner);

    @PreAuthorize("hasRole('ROLE_EDITOR') or hasPermission(#spanner, 'owner')")
    public void update(Spanner spanner);

    @PreAuthorize("hasRole('ROLE_EDITOR') or hasPermission(#spanner, 'owner')")
    public void delete(Spanner spanner);
}
