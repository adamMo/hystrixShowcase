package pl.test.hystrix.hystrix;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Adam.Molewko
 */
@RestController
public class Controller {

  @Autowired
  private DaoComponent daoComponent;

  @GetMapping
  public String getResponse() {
    return daoComponent.getDatabaseResponse(UUID.randomUUID().toString());
  }

}
