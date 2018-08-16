package co.koralat.com.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import co.koralat.com.vo.Contacto;

@WebService
public class CrudContactoImpl implements ICrudContacto 
{
	public void registrarContacto(Contacto contacto, String bd, String nombreDoc) 
	{
		final RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9200, "http")));
		IndexRequest indexRequest = new IndexRequest(bd, nombreDoc).source("identificador",
				contacto.getIdentificador(), "nombre", contacto.getNombre(), "apellido", contacto.getApellido(),
				"numero", contacto.getNumero());

		      IndexResponse indexResponse;
			try {
				indexResponse = client.index(indexRequest);
				if (indexResponse.getResult() == Result.CREATED) {
					System.out.println("Creado");
				} else if (indexResponse.getResult() == Result.UPDATED) {
					System.out.println("Estaba creado y se actualizó");
				}
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}		
		}

	public void eliminarContacto(String bd, String nombreDoc, String index)
	{
		final RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9200, "http")));
		DeleteRequest request = new DeleteRequest(bd, nombreDoc, index);
		
		DeleteResponse deleteResponse;
		try {
			deleteResponse = client.delete(request);
			ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
			if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
				System.out.println("Se borro");
			}
			if (shardInfo.getFailed() > 0) {
				for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
					System.out.println(failure.reason());
				}
			}
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) 
		{
			e1.printStackTrace();
		}
			
	}

	public void actualizarContacto(Contacto contacto, String bd, String nombreDoc, String index) 
	{
		final RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9200, "http")));
		try {

			UpdateRequest requestUpdate = new UpdateRequest(bd, nombreDoc, index).doc("identificador",
					contacto.getIdentificador(), "nombre", contacto.getNombre(), "apellido", contacto.getApellido(),
					"numero", contacto.getNumero());
			UpdateResponse updateResponse = client.update(requestUpdate);
					if (updateResponse.getResult() == Result.CREATED) {
						System.out.println("Creado");
					} else if (updateResponse.getResult() == Result.UPDATED) {
						System.out.println("Actualizado");
					} else if (updateResponse.getResult() == Result.DELETED) {
						System.out.println("Borrado");
					} else if (updateResponse.getResult() == Result.NOOP) {
						System.out.println("No");
					}

					try {
						client.close();
					} catch (IOException e) {

						e.printStackTrace();
				
				}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public List<Contacto> listarContacto() 
	{
		final RestHighLevelClient cliente = new RestHighLevelClient
		(
		  RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9200, "http"))
		 );
		List<Contacto> contactos = new ArrayList<Contacto>();
		SearchRequest searchRequest = new SearchRequest("posts"); 
		searchRequest.types("doc");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		searchSourceBuilder.query(QueryBuilders.matchAllQuery()); 
		searchRequest.source(searchSourceBuilder);
		
		SearchResponse searchResponse;
		try 
		{
			searchResponse = cliente.search(searchRequest);
			SearchHits hits = searchResponse.getHits();		
			SearchHit[] searchHits = hits.getHits();
			for (SearchHit hit : searchHits) 
			{
				Map<String,Object> sourceAsMap = hit.getSourceAsMap();
				Contacto contacto = new Contacto();
				contacto.setApellido(sourceAsMap.get("apellido").toString());
				contacto.setIdentificador(Integer.parseInt(sourceAsMap.get("identificador").toString()));
				contacto.setNombre(sourceAsMap.get("nombre").toString());
				contacto.setNumero(sourceAsMap.get("numero").toString());
				contactos.add(contacto);
			}
		} catch (IOException e1) {

			e1.printStackTrace();
		}	
		try {
				cliente.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		
		return contactos;
	}

	public boolean existeContacto(String bd, String nombreDoc, String index) {
		boolean exists = false;
		final RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9200, "http")));
		GetRequest getRequest = new GetRequest(bd, nombreDoc, index);
		getRequest.fetchSourceContext(new FetchSourceContext(false));
		getRequest.storedFields("_none_");
		try {
			exists = client.exists(getRequest);
			ActionListener<Boolean> listenerExists = new ActionListener<Boolean>() {
				public void onResponse(Boolean exists) {
					System.out.println(exists);
					try {
						client.close();
					} catch (IOException e) {

						e.printStackTrace();
					}
				}

				public void onFailure(Exception e) {
					System.out.println("Fallo el exists");
				}
			};
			client.existsAsync(getRequest, listenerExists);
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		return exists;
	}

	@Override
	public String obtenerIndexContacto(String identificacion) 
	{
		String index="-1";
		final RestHighLevelClient cliente = new RestHighLevelClient
		(
		   RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9200, "http"))
		);
		SearchRequest searchRequest = new SearchRequest("posts"); 
		searchRequest.types("doc");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		searchSourceBuilder.query(QueryBuilders.termQuery("identificador",identificacion));
		searchRequest.source(searchSourceBuilder);
		
		SearchResponse searchResponse;
		try 
		{
			searchResponse = cliente.search(searchRequest);
			SearchHits hits = searchResponse.getHits();		
			SearchHit[] searchHits = hits.getHits();
			for (SearchHit hit : searchHits) 
			{
				Map<String,Object> sourceAsMap = hit.getSourceAsMap();
				if(sourceAsMap.get("identificador").toString().equals(identificacion))
				index=hit.getId();

			}
		} catch (IOException e1) {

			e1.printStackTrace();
		}	
		try {
				cliente.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		return index;
	}

}
