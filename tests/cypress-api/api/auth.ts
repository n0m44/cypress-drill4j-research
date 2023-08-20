import { ApiResponseBody } from 'cypress-plugin-api';
import { registrationEndpoint } from './endpoints/endpoints';
import UserDefinition from './types/user';

export default class AuthRequests {
  static registation = (
    user: UserDefinition
  ): Cypress.Chainable<ApiResponseBody & { userDefinition: UserDefinition }> => {
    const body = user;
    return cy.api({ ...registrationEndpoint, ...body }).then((response) => {
      const userDefinition: UserDefinition = response.body;
      return { ...response, ...{ userDefinition } };
    });
  };
}
