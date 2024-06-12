const querystring = require('querystring');
const axios = require("axios")
const google_access_token_endpoint = 'https://oauth2.googleapis.com/token';
require('dotenv').config();

const get_profile_data = async access_token => {
    return await axios ({
      method: 'post',
      url: `https://www.googleapis.com/oauth2/v3/userinfo?alt=json&access_token=${access_token}`,
    });
  };

const get_access_token = async (auth_code, redirect_uri) => {
    const access_token_params = {
      client_id: process.env.CLIENT_ID,
      client_secret: process.env.CLIENT_SECRET,
      code: auth_code,
      grant_type: 'authorization_code',
      redirect_uri: `${process.env.BASE_URI}:${process.env.PORT}${process.env.REDIRECT_URI}`
    };
    return await axios.post(`${google_access_token_endpoint}?${querystring.stringify(access_token_params)}`);
  };

const googleAuthConfig = {
  tokenEndpoint: 'https://accounts.google.com/o/oauth2/v2/auth',
  scopes: ['profile', 'email', 'openid'],
  redirectUri: `http://localhost:4000${process.env.REDIRECT_URI}`,
  clientId: process.env.CLIENT_ID,
};

const buildAuthUrl = () => {
  const queryParams = {
    client_id: googleAuthConfig.clientId,
    redirect_uri: googleAuthConfig.redirectUri,
    response_type: 'code',
    scope: googleAuthConfig.scopes.join(' '),
  };

  return `${googleAuthConfig.tokenEndpoint}?${querystring.stringify(queryParams)}`;
};

const request_get_auth_code_url = buildAuthUrl();

module.exports = { request_get_auth_code_url, get_access_token, get_profile_data };
