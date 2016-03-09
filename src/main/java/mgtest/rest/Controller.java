package mgtest.rest;

import mgtest.rest.dto.UrlRequestDto;
import mgtest.rest.dto.UrlTokensDto;
import mgtest.service.UrlTokenizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller
{
  @Autowired
  private UrlTokenizerService service;

  @RequestMapping(method = RequestMethod.POST)
  public UrlTokensDto getTokens(@RequestBody UrlRequestDto requestDto) {
    return service.tokenizeInputUrl(requestDto.url);
  }
}
