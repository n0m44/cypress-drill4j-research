const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function(app) {
    app.use('/auth/registration', createProxyMiddleware({ target: 'http://localhost:8080' }));
}