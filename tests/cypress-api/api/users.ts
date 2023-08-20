import { usersListEndpoint } from './endpoints/endpoints';

export default class UserRequests {
  static usersList = () => {
    return cy.api({
      failOnStatusCode: false,
      method: usersListEndpoint.method,
      url: usersListEndpoint.path,
    });
  };
}
