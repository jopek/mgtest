package mgtest.service;

import mgtest.domain.UrlTokenizer;
import mgtest.rest.dto.UrlTokensDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlTokenizerService
{
  private UrlTokenizer tokenizer;

  @Autowired
  public UrlTokenizerService(UrlTokenizer tokenizer) {
    this.tokenizer = tokenizer;
  }

  public UrlTokensDto tokenizeInputUrl(String input) {
    UrlTokensDto dto = new UrlTokensDto();

    tokenizer.setUrl(input);

    dto.protocol = tokenizer.getProtocol();
    dto.user = tokenizer.getUser();
    dto.pass = tokenizer.getPass();
    dto.domain = tokenizer.getDomain();
    dto.path = tokenizer.getPath();

    try {
      dto.port = Integer.parseInt(tokenizer.getPort());
    } catch (NumberFormatException e) {
      dto.port = -1;
    }

    if (dto.port > 2 << 16) {
      dto.port = -1;
    }

    return dto;
  }
}
