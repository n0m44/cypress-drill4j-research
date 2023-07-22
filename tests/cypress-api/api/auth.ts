import { loginEndpoint, registrationEndpoint } from './endpoints';

export default class AuthRequests {
  static registation = (login: string, password: string) => {
    return cy.api({
      failOnStatusCode: false,
      method: registrationEndpoint.method,
      url: registrationEndpoint.path,
      body: registrationEndpoint.body(login, password),
    });
  };

  static login = (login: string, password: string) => {
    return cy.api({
      failOnStatusCode: false,
      method: loginEndpoint.method,
      url: loginEndpoint.path,
      body: loginEndpoint.body(login, password),
    });
  };
}
