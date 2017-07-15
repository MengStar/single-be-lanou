/*
 * MessageConstants
 * These are the variables that contain the text which is displayed on certain errors
 *
 * Follow this format:
 * export const YOUR_CONSTANT = 'Your message text';
 */
/**
 * 多国语言配置
 */
if (true) {
    export const FIELD_MISSING = '表单没有填好.';
    export const WRONG_PASSWORD = '密码错误.';
    export const USER_NOT_FOUND = '用户不存在.';
    export const USERNAME_TAKEN = '用户已存在.';
    export const GENERAL_ERROR = '发生错误，请稍候.';
} else {
    export const FIELD_MISSING = 'Please fill out the entire form.';
    export const WRONG_PASSWORD = 'Wrong password.';
    export const USER_NOT_FOUND = 'This username does not exist.';
    export const USERNAME_TAKEN = 'Sorry, but this username is already taken';
    export const GENERAL_ERROR = 'Something went wrong, please try again';
}