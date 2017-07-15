import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import appStore from './store/store';
import {Provider} from 'react-redux';

import './index.css';
import App from './component/App/App';
import registerServiceWorker from './registerServiceWorker';


ReactDOM
    .render(
        <Provider store="appStore">
            <Router >
                <div>
                    <Route exact path="/" component={App}/>
                    <Route exact path="/1" component={App}/>
                </div>
            </Router>
        </Provider>
        , document.getElementById('root'));


//registerServiceWorker();
