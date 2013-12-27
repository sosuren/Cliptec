package com.cliptec.es;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

import org.junit.Test;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/6/13
 * Time: 5:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class ESTest {

    @Test
    public void testIndexing(){
//        MemberProfileLoader loader = new MemberProfileLoader();
//        loader.process();
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "es_slama")
                .build();
//        Node node = nodeBuilder().local(true).clusterName("es_slama").node();
//        Client client = node.client();
        Client client = new TransportClient(settings);
        ((TransportClient) client).addTransportAddress(new InetSocketTransportAddress("localhost", 9300));


        try {
            XContentBuilder builder = jsonBuilder()
                    .startObject()
                    .field("name", "surendra")
                    .field("address", "kapan")
                    .endObject();
            IndexResponse indexResponse = client
                    .prepareIndex("deerwalk", "makalu", "367")
                    .setSource(
                            builder
                    )
                    .execute()
                    .actionGet();
            System.out.println(indexResponse.getId());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
