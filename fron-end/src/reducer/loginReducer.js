/*
 * The reducer takes care of our data
 * Using actions, we can change our application state
 * To add a new action, add it to the switch statement in the homeReducer function
 *
 * Example:
 * case YOUR_ACTION_CONSTANT:
 *   return assign({}, state, {
 *       stateVariable: action.var
 *   });
 */


import * as actionType from "../action/loginAction";

//初始化相关状态
const initialState = {
    formState: {
        username: '',
        password: ''
    },
    currentlySending: false,
    loggedIn: false,
    errorMessage: ''
};
// 根据action改变状态
export default function loginReducer(state = initialState, action) {
    switch (action.type) {
        case actionType.LOGIN_CHANGE_FORM:
            return {...state, formState: action.value};
            break;
        case actionType.LOGIN_SET_LOGIN_STATE:
            return {
                ...state, loggedIn: action.value
            };
            break;
        case actionType.LOGIN_SENDING_REQUEST:
            return {
                ...state, currentlySending: action.value
            };
            break;
        case actionType.LOGIN_SET_ERROR_MESSAGE:
            return {
                ...state, errorMessage: action.value
            };
        default:
            return state;
    }
}
