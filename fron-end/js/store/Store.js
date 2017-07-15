/**
 * Created by Meng on 2017/6/27.
 */
import {createStore, combineReducers, applyMiddleware} from 'redux';
import thunk from 'redux-thunk';
import {createLogger} from 'redux-logger';
import {homeReducer} from '../reducers/HomeReducer';

// Creates the Redux reducer with the redux-thunk middleware, which allows us
// to do asynchronous things in the actions
const middleware = [thunk];
if (process.env.NODE_ENV !== 'production') {
    middleware.push(createLogger())
}
const appStore = createStore(
    homeReducer,
    applyMiddleware(...middleware)
)
export default appStore;