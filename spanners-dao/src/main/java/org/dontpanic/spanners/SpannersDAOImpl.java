package org.dontpanic.spanners;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;


public class SpannersDAOImpl extends HibernateDaoSupport implements SpannersDAO {

    public Spanner get(int id) {
        return (Spanner)getHibernateTemplate().get(Spanner.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Spanner> getAll() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Spanner.class);
        criteria.addOrder(Order.asc(Spanner.PROP_ID));
        return getHibernateTemplate().findByCriteria(criteria);
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


