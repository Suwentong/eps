const { defineConfig } = require('@vue/cli-service')
module.exports = {
  devServer: {
    port: 8081,
    proxy: 'http://localhost:8080'
  },
  transpileDependencies: true
}