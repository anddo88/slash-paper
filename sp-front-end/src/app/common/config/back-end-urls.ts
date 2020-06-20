export class BackEndUrls {

  static readonly API_PROTOCOL = 'http://';
  static readonly API_HOST = 'localhost';
  static readonly API_PORT = '8080';

  // End-points of API services
  static readonly API_PREFIX = '/api';
  static readonly API_APP_CONFIG = '/app-config';
  static readonly API_LOGIN = '/login';
  static readonly API_OAUTH_AUTHORIZE = '/oauth/authorize';
  static readonly API_REDIRECT = '/redirect';

  /**
   * Full host URL & Port
   *
   */
  static get API_URL(): string {
    return BackEndUrls.API_PROTOCOL + BackEndUrls.API_HOST  + ':' + BackEndUrls.API_PORT;
  }

  /** Returns full end point url
   *
   * @param path api path to be concatenated
   */
  static API_ENDPOINT(path: string) {
    return BackEndUrls.API_URL + BackEndUrls.API_PREFIX + path;
  }

}
