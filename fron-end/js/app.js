/**
 *
 * app.js
 *
 * This is the entry file for the application, mostly just setup and boilerplate
 * code
 *
 */

// Load the ServiceWorker, the Cache polyfill, the manifest.json file and the .htaccess file
import 'file?name=[name].[ext]!../serviceworker.js';
import 'file?name=[name].[ext]!../serviceworker-cache-polyfill.js';
import 'file?name=[name].[ext]!../manifest.json';
import 'file?name=[name].[ext]!../.htaccess';
import 'file?name=[name].[ext]!../favicon.ico';
import 'file?name=[name].[ext]!../favicon.png';

//Check for ServiceWorker support before trying to install it
//service worker 相关
if ('serviceWorker' in navigator) {
    // Install ServiceWorker
  navigator.serviceWorker.register('/serviceworker.js').then(() => {
  }).catch((err) => {
    // Installation failed
    console.log('ServiceWorker registration failed, error:', err);
  });
} else {
  // No ServiceWorker Support
  console.log('ServiceWorker is not supported in this browser');
}

// Import all the third party stuff
// 引入react相关组件
import React from 'react';

import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { Router, Route, IndexRoute, browserHistory } from 'react-router';
import FontFaceObserver from 'fontfaceobserver';

// When Open Sans is loaded, add the js-open-sans-loaded class to the body
// which swaps out the fonts
// 下载字体
const openSansObserver = new FontFaceObserver('Open Sans');

openSansObserver.check().then(() => {
  document.body.classList.add('js-open-sans-loaded');
}, (err) => {
  document.body.classList.remove('js-open-sans-loaded');
});

// Import the components used as pages
import HomePage from './pages/HomePage.react';
import LoginPage from './pages/LoginPage.react';
import RegisterPage from './pages/RegisterPage.react';
import Dashboard from './pages/Dashboard.react';
import NotFound from './pages/NotFound.react';
import App from './pages/nav/App.react';
import appStore from './store/Store';

// Import the CSS file, which webpack transfers to the build folder
import '../css/main.css';

function checkAuth(nextState, replaceState) {
  let { loggedIn } = appStore.getState();

  // check if the path isn't dashboard,只有登录了才能进，否则调到主页
  // that way we can apply specific logic
  // to display/render the path we want to
  if (nextState.location.pathname !== '/dashboard') {
    if (loggedIn) {
      if (nextState.location.state && nextState.location.pathname) {
        replaceState(null, nextState.location.pathname);
      } else {
        replaceState(null, '/');
      }
    }
  } else {
    // If the user is already logged in, forward them to the homepage
    if (!loggedIn) {
      if (nextState.location.state && nextState.location.pathname) {
        replaceState(null, nextState.location.pathname);
      } else {
        replaceState(null, '/');
      }
    }
  }
}

// Mostly boilerplate, except for the Routes. These are the pages you can go to,
// which are all wrapped in the App component, which contains the navigation etc
ReactDOM.render(
  <Provider store={appStore}>
    <Router history={browserHistory}>
      <Route path="/" component={App}>
        <IndexRoute component={HomePage} />
        <Route onEnter={checkAuth}>
          <Route path="/login" component={LoginPage} />
          <Route path="/register" component={RegisterPage} />
          <Route path="/dashboard" component={Dashboard} />
        </Route>
        <Route path="*" component={NotFound} />
      </Route>
    </Router>
  </Provider>,
  document.getElementById('app')
);
