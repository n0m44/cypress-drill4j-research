import AuthRequests from '../../api/auth';
import HTTPStatusCode from '../../api/statusesCodes';

describe('Test registration', () => {
  before(() => {
    Cypress.on('uncaught:exception', () => false);
  });
  it('Test on empty login', () => {
    AuthRequests.registation('', 'testpassword').should((response) => {
      expect(response.status).equal(HTTPStatusCode.unauthorized);
    });
  });
  it('Test on empty passowrd', () => {
    AuthRequests.registation('testlogin', '').should((response) => {
      expect(response.status).equal(HTTPStatusCode.unauthorized);
    });
  });

  it('Test on exists login', () => {
    AuthRequests.registation('existlogin', 'password');

    AuthRequests.registation('existlogin', 'password').should((response) => {
      expect(response.status).equal(HTTPStatusCode.conflict);
    });

    AuthRequests.registation('existlogin', 'otherpassword').should((response) => {
      expect(response.status).equal(HTTPStatusCode.conflict);
    });
  });

  it('Test on exists password', () => {
    AuthRequests.registation('existsPassword', 'existsPassword');

    AuthRequests.registation('existsPasswordSecond', 'existsPassword').should((response) => {
      expect(response.status).equal(HTTPStatusCode.success);
    });
  });
});
