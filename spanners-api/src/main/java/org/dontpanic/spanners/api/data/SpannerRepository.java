package org.dontpanic.spanners.api.data;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for the Spanner objects
 * Created by stevie on 04/06/16.
 */
public interface SpannerRepository extends JpaRepository<Spanner, Long> {
}
