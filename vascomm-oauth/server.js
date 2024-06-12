const express = require('express');
const utils = require('./utils');

const app = express();
const port = process.env.PORT;

app.get('/auth', async (req, res) => {
  try {
    res.redirect(utils.request_get_auth_code_url);
  } catch (error) {
    res.sendStatus(500);
    console.error('Error redirecting to auth:', error);
  }
});


app.get(process.env.REDIRECT_URI || '/callback', async (req, res) => {
  // Extract authorization code from query parameters
  const authorization_code = req.query.code;
  try {
    const response = await utils.get_access_token(authorization_code);
    const { access_token } = response.data;
    const user = await utils.get_profile_data(access_token);
    const user_data = user.data;
    res.send(user_data);
  } catch (error) {
    console.error('Error getting access token:', error);
    res.sendStatus(500);
  }
});

// Start the server
app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
