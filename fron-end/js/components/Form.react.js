/**
 * Form.react.js
 *
 * The form with a username and a password input field, both of which are
 * controlled via the application state.
 *
 */

import React, {Component} from 'react';
import {changeForm} from '../actions/LoginActions';
import LoadingButton from './LoadingButton.react';
import ErrorMessage from './ErrorMessage.react';
// Object.assign is not yet fully supported in all browsers, so we fallback to
// a polyfill
const assign = Object.assign || require('object.assign');

class LoginForm extends Component {

    // Change the username in the app state
    _changeUsername = (evt) => {
        let newState = this._mergeWithCurrentState({
            username: evt.target.value
        });
        this._emitChange(newState);
    }

    // Change the password in the app state
    _changePassword = (evt) => {
        let newState = this._mergeWithCurrentState({
            password: evt.target.value
        });
        this._emitChange(newState);
    }
    // Merges the current state with a change
    _mergeWithCurrentState = change =>
        ({...(this.props.data), ...change});

    // Emits a change of the form state to the application state
    _emitChange = (newState) => {
        this.props.dispatch(changeForm(newState));
    }
    // onSubmit call the passed onSubmit function
    _onSubmit = (evt) => {
        evt.preventDefault();
        this.props.onSubmit(this.props.data.username, this.props.data.password);
    }

    render() {
        return (
            <form className="form" onSubmit={this._onSubmit}>
                <ErrorMessage />
                <div className="form__field-wrapper">
                    <input className="form__field-input" type="text" id="username" value={this.props.data.username}
                           placeholder="frank.underwood" onChange={this._changeUsername.bind(this)} autoCorrect="off"
                           autoCapitalize="off" spellCheck="false"/>
                    <label className="form__field-label" htmlFor="username">Username</label>
                </div>
                <div className="form__field-wrapper">
                    <input className="form__field-input" id="password" type="password" value={this.props.data.password}
                           placeholder="••••••••••" onChange={this._changePassword}/>
                    <label className="form__field-label" htmlFor="password">Password</label>
                </div>
                <div className="form__submit-btn-wrapper">
                    {this.props.currentlySending ? (
                        <LoadingButton />
                    ) : (
                        <button className="form__submit-btn" type="submit">{this.props.btnText}</button>
                    )}
                </div>
            </form>
        );
    }

}

LoginForm.propTypes = {
    onSubmit: React.PropTypes.func.isRequired,
    btnText: React.PropTypes.string.isRequired,
    data: React.PropTypes.object.isRequired
}

export default LoginForm;
