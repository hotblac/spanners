package org.dontpanic.spanners.users.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by stevie on 13/07/16.
 */
@RepositoryRestResource
public interface UserRepository extends JpaRepository <User, String>{
}
