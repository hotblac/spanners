package org.dontpanic.spanners.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository for the Spanner objects
 * Created by stevie on 04/06/16.
 */
@RepositoryRestResource
public interface SpannerRepository extends JpaRepository<Spanner, Long> {
}
