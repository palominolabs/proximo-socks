package com.palominolabs.heroku

import org.slf4j.Logger
import org.slf4j.LoggerFactory

public class Proximo {
  private static final Logger logger = LoggerFactory.getLogger(Proximo.class);

  private static final String PROXIMO_ENV_VAR = 'PROXIMO_URL'
  private static final String PROXIMO_PORT = '1080'

  /**
   * Setup Proximo proxying
   *
   * @return True if Proximo was configured, false if the Proximo environment variable was not found
   */
  public static boolean setup() {
    String proximoUrl = System.getenv(PROXIMO_ENV_VAR)
    if (proximoUrl != null) {
      URL proximo = new URL(proximoUrl)
      String userInfo = proximo.getUserInfo()
      String user = userInfo.substring(0, userInfo.indexOf(':'))
      String password = userInfo.substring(userInfo.indexOf(':') + 1)

      System.setProperty('socksProxyHost', proximo.getHost())
      System.setProperty('socksProxyPort', PROXIMO_PORT)
      Authenticator.setDefault(new ProxyAuth(user, password))

      logger.info("$PROXIMO_ENV_VAR specified; using <$user:$password> $proximo.host:$PROXIMO_PORT as SOCKS proxy")

      return true
    }

    logger.info("$PROXIMO_ENV_VAR environment variable not set")
    return false
  }

  private static class ProxyAuth extends Authenticator {
    private PasswordAuthentication passwordAuthentication;

    private ProxyAuth(String user, String password) {
      passwordAuthentication = new PasswordAuthentication(user, password.toCharArray())
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
      return passwordAuthentication;
    }
  }
}
