import AuthRequests from '../../api/auth';
import UserRequests from '../../api/users';

describe('test users list within users CRUD operations', () => {
  it('Test GET /user after create users', () => {
    AuthRequests.registation('someLogin', 'someLogin');

    AuthRequests.login('someLogin', 'someLogin');

    // UserRequests.usersList().should((response) => {
    //   cy.log(JSON.parse(response.body));
    // });
  });
});
