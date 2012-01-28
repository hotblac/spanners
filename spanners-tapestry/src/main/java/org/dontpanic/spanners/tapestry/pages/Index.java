package org.dontpanic.spanners.tapestry.pages;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.dontpanic.spanners.SpannersDAO;
import org.springframework.context.ApplicationContext;

/**
 * Start page of application spanners-tapestry.
 */
public class Index {

    @Inject
    private ApplicationContext springContext;

    /**
     * We'd rather inject the spannersDAO bean using the @Inject annotation
     * but Tapestry would get confused between two beans that implement this
     * interface in our Spring context: the undecorated implementation and the
     * transaction proxy. Solution is to inject the whole ApplicationContext
     * and pick the required bean ourselves.
     * @return spannersDAO transaction proxy bean
     */
    private SpannersDAO getSpannersDAO() {
        return (SpannersDAO)springContext.getBean("spannersDAO");
    }

    public String getSpanner1() {
        return getSpannersDAO().get(1).getName();
    }


}
