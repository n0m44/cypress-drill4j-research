import HTTPStatusCode from '../statusesCodes';
import UserDefinition from '../types/user';

export const registrationEndpoint: Partial<Cypress.RequestOptions> = {
  method: 'POST',
  url: '/auth/registration',
};

export const usersListEndpoint: Partial<Cypress.RequestOptions> = {
  method: 'GET',
  url: '/user',
} as const;
