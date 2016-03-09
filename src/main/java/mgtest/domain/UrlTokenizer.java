package mgtest.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UrlTokenizer
{
  public static final String SCHEME = "scheme";
  public static final String USER = "user";
  public static final String PASS = "pass";
  public static final String DOMAIN = "domain";
  public static final String PORT = "port";
  public static final String PATH = "path";

  private Map<String, String> urlTokens;

  public UrlTokenizer() {
    urlTokens = new HashMap<>();
    clearTokens();
  }

  private void clearTokens() {
    urlTokens.put(SCHEME, "");
    urlTokens.put(PASS, "");
    urlTokens.put(USER, "");
    urlTokens.put(DOMAIN, "");
    urlTokens.put(PORT, "");
    urlTokens.put(PATH, "");
  }

  public void setUrl(String input) {
    if (input == null || input.isEmpty()) {
      return;
    }
    clearTokens();
    tokenizeInputUrlRegex(input);
  }

  private void tokenizeInputUrlRegex(String input) {
    String urlRegex =  "^(([\\w_-]+)://)?(([\\w_-]+):([\\w_#\\.!-]+)@)?([\\w\\.-]+)(:(\\d+))?(\\/[\\w\\/?=#:._-]*)?$";

    Pattern pattern = Pattern.compile(urlRegex);
    Matcher matcher = pattern.matcher(input);

    if (matcher.find()) {
      List<String> groups = new ArrayList<>();

      for (int i = 0; i <= matcher.groupCount(); i++) {
        String group = matcher.group(i);
        if (group == null){
          groups.add("");
        } else {
          groups.add(group);
        }
      }

      urlTokens.put(SCHEME, groups.get(2));
      urlTokens.put(USER,   groups.get(4));
      urlTokens.put(PASS,   groups.get(5));
      urlTokens.put(DOMAIN, groups.get(6));
      urlTokens.put(PORT,   groups.get(8));
      urlTokens.put(PATH,   groups.get(9));
    }
  }

  public String getProtocol() {
    return urlTokens.get(SCHEME);
  }

  public String getUser() {
    return urlTokens.get(USER);
  }

  public String getPass() {
    return urlTokens.get(PASS);
  }

  public String getDomain() {
    return urlTokens.get(DOMAIN);
  }

  public String getPort() {
    return urlTokens.get(PORT);
  }

  public String getPath() {
    return urlTokens.get(PATH);
  }

  public Map<String, String> getUrlTokens() {
    return urlTokens;
  }
}
