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

import {CHANGE_FORM, SET_IS_LOGIN, SENDING_REQUEST, SET_ERROR_MESSAGE} from '../util/constant/Constants';

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
        case CHANGE_FORM:
            return {...state, formState: action.newState};
            break;
        case SET_IS_LOGIN:
            return {
                ...state, loggedIn: action.newState
            };
            break;
        case SENDING_REQUEST:
            return {
                ...state, currentlySending: action.sending
            };
            break;
        case SET_ERROR_MESSAGE:
            return {
                ...state, errorMessage: action.message
            };
        default:
            return state;
    }
}
