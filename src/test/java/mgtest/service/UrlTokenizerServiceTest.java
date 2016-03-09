package mgtest.service;

import mgtest.domain.UrlTokenizer;
import mgtest.rest.dto.UrlTokensDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class UrlTokenizerServiceTest
{
  @Configuration
  static class ContextConfiguration
  {
    @Bean
    public UrlTokenizer tokenizer() {
      return new UrlTokenizer();
    }

    @Bean
    public UrlTokenizerService tokenizerService() {
      return new UrlTokenizerService(tokenizer());
    }
  }

  @Autowired
  private UrlTokenizerService service;

  @Test
  public void tokenizeInputUrl_returns_dto() throws Exception {
    String input = "http://you:s3cr37!@magic-domain.com:80/";
    UrlTokensDto urlTokensDto = service.tokenizeInputUrl(input);

    assertEquals("magic-domain.com", urlTokensDto.domain);
    assertEquals(80, urlTokensDto.port);
    assertEquals("http", urlTokensDto.protocol);
    assertEquals("s3cr37!", urlTokensDto.pass);
  }

  @Test
  public void tokenizeInputUrl_with_invalid_port_returns_dto_with_negative_port_number() throws Exception {
    String input = "http://magic-domain.com:808080/";
    UrlTokensDto urlTokensDto = service.tokenizeInputUrl(input);

    assertEquals("magic-domain.com", urlTokensDto.domain);
    assertEquals(-1, urlTokensDto.port);
  }

  @Test
  public void tokenizeInputUrl_with_invalid_port_characters_returns_empty_dto() throws Exception {
    String input = "http://magic-domain.com:<port>/";
    UrlTokensDto urlTokensDto = service.tokenizeInputUrl(input);

    assertEquals("", urlTokensDto.protocol);
    assertEquals("", urlTokensDto.user);
    assertEquals("", urlTokensDto.pass);
    assertEquals("", urlTokensDto.domain);
    assertEquals(-1, urlTokensDto.port);
    assertEquals("", urlTokensDto.path);
  }

  @Test
  public void tokenizeInputUrl_with_only_domain_returns_dto_with_only_domain() throws Exception {
    String input = "www.lala.com";
    UrlTokensDto urlTokensDto = service.tokenizeInputUrl(input);

    assertEquals("", urlTokensDto.protocol);
    assertEquals("", urlTokensDto.user);
    assertEquals("", urlTokensDto.pass);
    assertEquals("www.lala.com", urlTokensDto.domain);
    assertEquals(-1, urlTokensDto.port);
    assertEquals("", urlTokensDto.path);
  }

}