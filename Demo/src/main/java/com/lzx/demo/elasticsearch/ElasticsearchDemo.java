package com.lzx.demo.elasticsearch;

import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Order;
import org.elasticsearch.search.sort.SortOrder;

public class ElasticsearchDemo {
	public static void main(String[] args) throws Exception {
//		selectByGroup();
//		insert();
//		delete();
		select();

	}
	public static void delete() throws Exception{
		Settings settings = ImmutableSettings.settingsBuilder()
				.put("client.transport.sniff", true)
				.put("cluster.name", "elasticsearch").build();
		Client client = new TransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(
						"172.16.2.57", 9300));
		DeleteRequestBuilder builder=client.prepareDelete().setIndex("comment_index").setType("comment_ugc");
		builder.setId("comment_12");
		DeleteResponse response=builder.execute().actionGet();
		System.out.println(response.isFound());
		System.out.println(response.getHeaders());
	}
	public static void select()throws Exception {
		Settings settings = ImmutableSettings.settingsBuilder()
				.put("client.transport.sniff", true)
				.put("cluster.name", "elasticsearch").build();
		Client client = new TransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(
						"172.16.2.57", 9300));
	    SearchRequestBuilder builder = client.prepareSearch("comment_index").setTypes("comment_ugc").setSearchType(SearchType.DEFAULT).setFrom(0).setSize(100);  
//	    BoolQueryBuilder qb = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("valid", true));  
//	    BoolQueryBuilder qb = QueryBuilders.boolQuery().must(new QueryStringQueryBuilder("上北").field("body"));  
//	    MatchQueryBuilder qb=QueryBuilders.matchPhraseQuery("body", "北上");
//	    BoolQueryBuilder qb = QueryBuilders.boolQuery().must(QueryBuilders.matchPhraseQuery("author", "569874")).must(QueryBuilders.matchPhraseQuery("author", "569874"));  
//	    builder.setQuery(qb);  
//	    builder.addSort("name",SortOrder.ASC);
//	    builder.addSort("sec",SortOrder.ASC);
	    AbstractAggregationBuilder aggbuilder=AggregationBuilders.terms("group");
	    SearchResponse response = builder.execute().actionGet(); 
	    System.out.println("  " + response);  
	    System.out.println(response.getHits().getTotalHits());  
	}
	public static void selectByGroup()throws Exception {
		Settings settings = ImmutableSettings.settingsBuilder()
				.put("client.transport.sniff", true)
				.put("cluster.name", "elasticsearch").build();
		Client client = new TransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(
						"172.16.2.57", 9300));
	    SearchRequestBuilder builder = client.prepareSearch("comment_index").setTypes("comment_ugc").setSearchType(SearchType.COUNT).setFrom(0).setSize(100);  
	    AbstractAggregationBuilder aggbuilder=AggregationBuilders.terms("group");
	    builder.addAggregation(AggregationBuilders.terms("group").field("group").subAggregation(AggregationBuilders.count("count").field("sec")).order(Order.aggregation("count", false)));
	    SearchResponse response = builder.execute().actionGet(); 
	    System.out.println("  " + response);  
	    System.out.println(response.getHits().getTotalHits());
	}
	public static void selectByID()throws Exception {
		Settings settings = ImmutableSettings.settingsBuilder()
				.put("client.transport.sniff", true)
				.put("cluster.name", "elasticsearch").build();
		Client client = new TransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(
						"172.16.2.57", 9300));
	    GetResponse responseGet = client.prepareGet("comment_index", "comment_ugc","comment_1").execute().actionGet();  
	    
	    System.out.println(responseGet.getSourceAsString());  
	}
	public static void insert()throws Exception {
		Settings settings = ImmutableSettings.settingsBuilder()
				.put("client.transport.sniff", true)
				.put("cluster.name", "elasticsearch").build();
		Client client = new TransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(
						"172.16.2.57", 9300));
		for(int i=0;i<10000;i++){
			IndexResponse response = client
					.prepareIndex("comment_index", "comment_ugc", "comment_"+i)
					.setSource(
							XContentFactory.jsonBuilder().startObject()
									.field("name", "name"+i)
									.field("sec", i)
									.field("group","group"+(i%10))
									.field("createDate", "2013080117552")
									.field("valid", true).endObject())
					.execute().actionGet();
		}
		System.out.println("运行结束");
		
	}
	
}
