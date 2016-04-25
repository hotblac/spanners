package org.dontpanic.spanners.events.elasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.FactoryBean;

import java.net.InetAddress;

/**
 * Spring FactoryBean for creating an Elasticsearch client
 * Created by stevie on 24/04/16.
 */
public class ElasticSearchClientFactoryBean implements FactoryBean<Client> {

    private String nodeUrl;

    public void setNodeUrl(String nodeUrl) {
        this.nodeUrl = nodeUrl;
    }

    @Override
    public Client getObject() throws Exception {
        return TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(nodeUrl), 9300));

    }

    @Override
    public Class<?> getObjectType() {
        return Client.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
