/*
 * Actions change things in your application
 * Since this boilerplate uses a uni-directional data flow, specifically redux,
 * we have these actions which are the only way your application interacts with
 * your appliction state. This guarantees that your state is up to date and nobody
 * messes it up weirdly somewhere.
 *
 * To add a new Action:
 * 1) Import your constant
 * 2) Add a function like this:
 *    export function yourAction(var) {
 *        return { type: YOUR_ACTION_CONSTANT, var: var }
 *    }
 * 3) (optional) Add an async function like this:
 *    export function asyncYourAction(var) {
 *        return function(dispatch) {
 *             // Do async stuff here
 *             return dispatch(yourAction(var));
 *        }
 *    }
 *
 *    If you add an async function, remove the export from the function
 *    created in the second step
 *    中间件的函数声明为(dispatch,getState)=>{};
 *    dispatch为分发器，getState获取当前的store
 *    最终需要返回一个标准的action{type:... , ...}
 */
import {SET_IS_LOGIN, CHANGE_FORM, SENDING_REQUEST, SET_ERROR_MESSAGE} from '../util/constant/Constants';
import * as errorMessages  from '../util/constant/MessageConstants';


export function login(username, password) {
    return (dispatch) => {
        // 开始权限认证
        dispatch(sendingRequest(true));
        //密码或用户为空
        if (anyElementsEmpty({username, password})) {
            dispatch(setErrorMessage(errorMessages.FIELD_MISSING));
            dispatch(sendingRequest(false));
            return;//不改变store;
        }
    }
}
export function logout() {
    return (dispatch) => {
        dispatch(sendingRequest(true));
    }
}
export function register(username, password) {
    return (dispatch) => {
        // Show the loading indicator, hide the last error
        dispatch(sendingRequest(true));
        // If no username or password was specified, throw a field-missing error
        if (anyElementsEmpty({username, password})) {
            dispatch(setErrorMessage(errorMessages.FIELD_MISSING));
            dispatch(sendingRequest(false));
            return;
        }
    }
}
export function setIsLogin(newState) {
    return {type: SET_IS_LOGIN, newState};
}
export function changeForm(newState) {
    return {type: CHANGE_FORM, newState};
}
export function sendingRequest(sending) {
    return {type: SENDING_REQUEST, sending};
}


/**
 * 设置错误
 * @param message
 */
function setErrorMessage(message) {
    return (dispatch) => {
        dispatch({type: SET_ERROR_MESSAGE, message});
        //3庙后取消
        setTimeout(() => {
            dispatch({type: SET_ERROR_MESSAGE, message: ''});
        }, 3000);
    }
}
/**
 * 判断是否有空的元素
 */
function anyElementsEmpty(elements) {
    for (let element in elements) {
        if (!elements[element]) {
            return true;
        }
    }
    return false;
}

