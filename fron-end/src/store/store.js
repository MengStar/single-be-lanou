/**
 * 状态统一管理
 * Created by Administrator on 2017/7/15.
 */
import {createStore, combineReducers, applyMiddleware} from 'redux';
import thunk from 'redux-thunk';
import {createLogger} from 'redux-logger';
import loginReducer from '../reducer/loginReducer';
const middleware = [thunk];
if (process.env.NODE_ENV !== 'production') {
    middleware.push(createLogger())
}
const appStore = createStore(
    /**
     * 添加reducer到下面的数组
     */
    combineReducers({
        loginReducer
    }),
    applyMiddleware(...middleware)
)
export default appStore;