package pl.test.hystrix.hystrix;

import java.util.List;
import java.util.stream.Collectors;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

/**
 * @author Adam.Molewko
 */
@Service
public class DaoComponent {

  public static final String DEFAULT_RESPONSE = "default response";
  private int requestCounter;
  private int idCounter;

  @HystrixCollapser(batchMethod = "getDatabaseResponseBatch", scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
      collapserProperties = {
          @HystrixProperty(name = "maxRequestsInBatch", value = "100"),
          @HystrixProperty(name = "timerDelayInMilliseconds", value = "2000")
      }
  )
  public String getDatabaseResponse(String id) {
    //this method will never be called
    return null;
  }

  @HystrixCommand(fallbackMethod = "getDefaultResponseFallback")
  public List<String> getDatabaseResponseBatch(List<String> ids) {
    requestCounter++;
    idCounter += ids.size();

    System.out.println("Requests batched this time: " + ids.size());

    System.out.println("Total requests batched: " + idCounter);
    System.out.println("Total requests executed: " + requestCounter);

    return ids.stream().map("Response for object id: "::concat).collect(Collectors.toList());
  }

  private String getDefaultResponseFallback() {
    return DEFAULT_RESPONSE;
  }

}
