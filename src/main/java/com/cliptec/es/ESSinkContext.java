package com.cliptec.es;

import com.cliptec.constants.DefaultConstants;
import com.cliptec.constants.LayoutConstants;
import org.apache.hadoop.mapred.JobConf;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.node.Node;
import static org.elasticsearch.node.NodeBuilder.*;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import cascading.tuple.TupleEntry;
import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/18/13
 * Time: 12:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class ESSinkContext {
    public String clusterName = null;
    public String esAddress = null;
    public String indexName = null;
    public String tableName = null;

    public Node node;
    public Client client;

    protected ActionRequestBuilder request;
    public XContentBuilder jsonBuilder;

    public void setUp(JobConf conf){

//        this.clusterName = conf.get()
        this.clusterName = conf.get(DefaultConstants.CLUSTER_NAME);
        System.out.println("Cluster Name::: " + this.clusterName);
        this.esAddress = conf.get(DefaultConstants.ES_ADDR);
        System.out.println("ES Address::::" + this.esAddress);
        this.indexName = conf.get(DefaultConstants.INDEX_NAME);
        System.out.println("Index Name::: " + this.indexName);
        this.tableName = conf.get(DefaultConstants.TABLE_NAME);
        System.out.println("Table Name:::: " + this.tableName);

        ImmutableSettings.Builder builder = ImmutableSettings.settingsBuilder();
        builder.put("cluster.name", this.clusterName);
        builder.put("discovery.zen.multicast.enabled", false);
        builder.putArray("discovery.zen.ping.unicast.hosts", this.esAddress);//host 1, host2 external EC2 DNS
        builder.put("node.local", "true");
        builder.put("transport.tcp.connect_timeout", "1200s");

        this.node = nodeBuilder().local(true).clusterName(this.clusterName).node();
        this.client = this.node.client();

        if(request == null)
            request = client.prepareBulk();
    }

    public void startRecord(){
        try {
            this.jsonBuilder = jsonBuilder();
            this.jsonBuilder.startObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startObject(){
        try {
            this.jsonBuilder.startObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processColumn(String columnName, Object data){
        try {
            this.jsonBuilder.field(columnName, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void collect(TupleEntry entry){
        this.startRecord();
        String dw_member_id = entry.getString(LayoutConstants.DW_MEMBER_ID);
        this.processColumn(LayoutConstants.DW_MEMBER_ID, dw_member_id);
        int age = entry.getInteger(LayoutConstants.MEMBER_AGE);
        this.processColumn(LayoutConstants.MEMBER_AGE, age);
        this.endRecord();
    }


    public void endObject(){
        try {
            this.jsonBuilder.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endRecord(){
        try {
            this.jsonBuilder.endObject();
            ((BulkRequestBuilder) request).add(Requests.indexRequest(this.indexName).type(this.tableName).id("s").create(false).source(this.jsonBuilder));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean cleanup() {

        BulkResponse response = (BulkResponse) this.request.execute().actionGet();
        if(response.hasFailures()){
            return false;
        }
        return true;
    }
}
