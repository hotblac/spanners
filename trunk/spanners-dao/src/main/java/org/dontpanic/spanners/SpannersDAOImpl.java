package org.dontpanic.spanners;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class SpannersDAOImpl extends HibernateDaoSupport implements SpannersDAO {

    public Spanner get(int id) {
        return (Spanner)getHibernateTemplate().get(Spanner.class, id);
    }

    public int create(Spanner spanner) {
    	
    	if (spanner == null) {
    		throw new IllegalArgumentException
    			("Cannot save null spanner to database");
    	}
    	
    	if (spanner.getSize() < 1) {
    		throw new IllegalArgumentException
    			("Cannot save spanner with non-positive size: " + spanner.getSize());
    	}
    	
    	return (Integer)getHibernateTemplate().save(spanner);
    }

}


