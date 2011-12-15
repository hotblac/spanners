package org.dontpanic.spannersws;

import org.dontpanic.spanners.Spanner;
import org.dontpanic.spanners.SpannersDAO;
import org.jdom.Namespace;
import org.springframework.ws.server.endpoint.AbstractJDomPayloadEndpoint;
import org.jdom.Element;
import org.jdom.xpath.XPath;

public class Endpoint extends AbstractJDomPayloadEndpoint {

    private static final Namespace NS = Namespace.getNamespace("ws", "http://dontpanic.org/ws/spannersws");

    private SpannersDAO spannersDAO;


    public void setSpannersDAO(SpannersDAO spannersDAO) {
        this.spannersDAO = spannersDAO;
    }


    public Element invokeInternal(Element message) throws Exception {

        // Obtain the spanner id from the message
        XPath xpath = XPath.newInstance("//ws:id");
        xpath.addNamespace(NS);
        String id = xpath.valueOf(message);
        int spannerId = Integer.parseInt(id);

        Spanner spanner = spannersDAO.get(spannerId);

        int retries = 0;
        while (spanner == null && retries < 1000) {
            // If at first you don't succeed, get a bigger spanner...
            spanner = spannersDAO.get(spannerId);
            retries++;
        }

        // Assemble response
        Element response = new Element("GetSpannerResponse", NS);
        response.addContent(new Element("id", NS).setText(Integer.toString(spanner.getId())));
        response.addContent(new Element("name", NS).setText(spanner.getName()));
        response.addContent(new Element("size", NS).setText(Integer.toString(spanner.getSize())));
        return response;
    }

}
