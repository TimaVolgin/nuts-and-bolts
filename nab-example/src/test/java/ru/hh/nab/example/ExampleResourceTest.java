package ru.hh.nab.example;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.nab.starter.servlet.DefaultServletConfig;
import ru.hh.nab.starter.servlet.ServletConfig;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.nab.testbase.NabTestConfig;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = {NabTestConfig.class})
public class ExampleResourceTest extends NabTestBase {

  @Test
  public void hello() {
    final String name = "test";
    Response response = target("/hello")
        .queryParam("name", name)
        .request()
        .get();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    assertEquals(String.format("Hello, %s!", name), response.readEntity(String.class));
  }

  @Test
  public void helloWithoutParams() {
    Response response = createRequest("/hello").get();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    assertEquals("Hello, world!", response.readEntity(String.class));
  }

  @Override
  protected ServletConfig getServletConfig() {
    return new DefaultServletConfig() {
      @Override
      public void registerResources(ResourceConfig resourceConfig) {
        resourceConfig.register(ExampleResource.class);
      }
    };
  }
}