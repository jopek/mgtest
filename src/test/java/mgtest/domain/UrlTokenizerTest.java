package mgtest.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UrlTokenizerTest
{
  private UrlTokenizer urlTokenizer;

  @Before
  public void setUp() throws Exception {
    urlTokenizer = new UrlTokenizer();
  }

  @Test
  public void getProtocol_should_return_url_protocol() {
    String input = "http://www.meteogroup.com/en/gb/about-us/careers.html";

    urlTokenizer.setUrl(input);
    String result = urlTokenizer.getProtocol();

    assertEquals("http", result);
  }

  @Test
  public void url_split_into_tokens_given_scheme_domain_port_path() {
    String input = "http://www.meteogroup.com:8080/en/gb/about-us/careers.html";

    urlTokenizer.setUrl(input);

    assertEquals("http", urlTokenizer.getProtocol());
    assertEquals("www.meteogroup.com", urlTokenizer.getDomain());
    assertEquals("8080", urlTokenizer.getPort());
    assertEquals("/en/gb/about-us/careers.html", urlTokenizer.getPath());
  }

  @Test
  public void url_split_into_tokens_given_domain_path() {
    String input = "www.meteogroup.com/en/gb/about-us/careers.html";

    urlTokenizer.setUrl(input);

    assertEquals("", urlTokenizer.getProtocol());
    assertEquals("www.meteogroup.com", urlTokenizer.getDomain());
    assertEquals("", urlTokenizer.getPort());
    assertEquals("/en/gb/about-us/careers.html", urlTokenizer.getPath());
  }

  @Test
  public void url_split_into_tokens_should_return_all_empty_given_invalid_port() {
    String input = "www.meteogroup.com:crap/";

    urlTokenizer.setUrl(input);

    assertEquals("", urlTokenizer.getProtocol());
    assertEquals("", urlTokenizer.getDomain());
    assertEquals("", urlTokenizer.getUser());
    assertEquals("", urlTokenizer.getPass());
    assertEquals("", urlTokenizer.getPort());
    assertEquals("", urlTokenizer.getPath());
  }

  @Test
  public void url_split_into_tokens_given_domain_port_path() {
    String input = "www.meteogroup.com:8080/en/gb/about-us/careers.html";

    urlTokenizer.setUrl(input);

    assertEquals("", urlTokenizer.getProtocol());
    assertEquals("www.meteogroup.com", urlTokenizer.getDomain());
    assertEquals("8080", urlTokenizer.getPort());
    assertEquals("/en/gb/about-us/careers.html", urlTokenizer.getPath());
  }

  @Test
  public void url_split_into_tokens_given_odd_scheme_user_pass_domain_port_path() {
    String input = "httppp://user:secret@esoteric.com:5678/full_eclipse_of_the_moon?q=i://#dinnerTable=9";

    urlTokenizer.setUrl(input);

    assertEquals("httppp", urlTokenizer.getProtocol());
    assertEquals("user", urlTokenizer.getUser());
    assertEquals("secret", urlTokenizer.getPass());
    assertEquals("esoteric.com", urlTokenizer.getDomain());
    assertEquals("5678", urlTokenizer.getPort());
    assertEquals("/full_eclipse_of_the_moon?q=i://#dinnerTable=9", urlTokenizer.getPath());
  }

  @Test
  public void setUrl_can_be_called_multiple_times() {
    String input1 = "https://help.ubuntu.com/lts/serverguide/ftp-server.html";
    String input2 = "www.meteogroup.com/en/gb/about-us/careers.html";
    String input3 = "ftp://imaginary_user:password@ftp5.gwdg.de:21/pub/linux/slackware/";
    String input4 = "www.google.com/";

    urlTokenizer.setUrl(input1);

    assertEquals("https", urlTokenizer.getProtocol());
    assertEquals("", urlTokenizer.getUser());
    assertEquals("", urlTokenizer.getPass());
    assertEquals("help.ubuntu.com", urlTokenizer.getDomain());
    assertEquals("", urlTokenizer.getPort());
    assertEquals("/lts/serverguide/ftp-server.html", urlTokenizer.getPath());

    urlTokenizer.setUrl(input2);

    assertEquals("", urlTokenizer.getProtocol());
    assertEquals("", urlTokenizer.getUser());
    assertEquals("", urlTokenizer.getPass());
    assertEquals("www.meteogroup.com", urlTokenizer.getDomain());
    assertEquals("", urlTokenizer.getPort());
    assertEquals("/en/gb/about-us/careers.html", urlTokenizer.getPath());

    urlTokenizer.setUrl(input3);

    assertEquals("ftp", urlTokenizer.getProtocol());
    assertEquals("imaginary_user", urlTokenizer.getUser());
    assertEquals("password", urlTokenizer.getPass());
    assertEquals("ftp5.gwdg.de", urlTokenizer.getDomain());
    assertEquals("21", urlTokenizer.getPort());
    assertEquals("/pub/linux/slackware/", urlTokenizer.getPath());

    urlTokenizer.setUrl(input4);

    assertEquals("", urlTokenizer.getProtocol());
    assertEquals("", urlTokenizer.getUser());
    assertEquals("", urlTokenizer.getPass());
    assertEquals("www.google.com", urlTokenizer.getDomain());
    assertEquals("", urlTokenizer.getPort());
    assertEquals("/", urlTokenizer.getPath());
  }

  @Test
  public void urls_given_in_kata_split_correctly() {
    urlTokenizer.setUrl("http://some.thing");

    assertEquals("http", urlTokenizer.getProtocol());
    assertEquals("", urlTokenizer.getUser());
    assertEquals("", urlTokenizer.getPass());
    assertEquals("some.thing", urlTokenizer.getDomain());
    assertEquals("", urlTokenizer.getPort());
    assertEquals("", urlTokenizer.getPath());

    urlTokenizer.setUrl("ftp://a.large.site");

    assertEquals("ftp", urlTokenizer.getProtocol());
    assertEquals("", urlTokenizer.getUser());
    assertEquals("", urlTokenizer.getPass());
    assertEquals("a.large.site", urlTokenizer.getDomain());
    assertEquals("", urlTokenizer.getPort());
    assertEquals("", urlTokenizer.getPath());

    urlTokenizer.setUrl("http://a.site.with/a-path");

    assertEquals("http", urlTokenizer.getProtocol());
    assertEquals("", urlTokenizer.getUser());
    assertEquals("", urlTokenizer.getPass());
    assertEquals("a.site.with", urlTokenizer.getDomain());
    assertEquals("", urlTokenizer.getPort());
    assertEquals("/a-path", urlTokenizer.getPath());

  }

}