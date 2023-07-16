const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function(app) {
    app.use('/auth/*', createProxyMiddleware({ 
        target: 'http://localhost:8080',
        cookieDomainRewrite: '',
    }));
    app.use('/user/*', createProxyMiddleware({ 
        target: 'http://localhost:8080',
    }));
}