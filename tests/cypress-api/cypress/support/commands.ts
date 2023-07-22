/* eslint-disable @typescript-eslint/no-namespace */
/// <reference types="cypress" />
// ***********************************************

Cypress.Commands.add('removeAllUsers', () => {
  return '';
});

declare global {
  namespace Cypress {
    interface Chainable {
      removeAllUsers(): void;
    }
  }
}

export {};
