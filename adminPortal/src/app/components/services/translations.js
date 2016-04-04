(function() {
    'use strict';

    angular
        .module('adminPortal')
        .config(function($translateProvider) {

            $translateProvider.translations('en', {
                CANCEL: 'Cancel',
                CHANGE_PASSWORD_FORM_NEW_PASSWORD: 'New password',
                CHANGE_PASSWORD_FORM_NEW_PASSWORD_IS_REQUIRED: 'New password is required',
                CHANGE_PASSWORD_FORM_NEW_PASSWORD_MAX: 'New password max',
                CHANGE_PASSWORD_FORM_NEW_PASSWORD_MIN: 'New password min',
                CHANGE_PASSWORD_FORM_OLD_PASSWORD: 'Old password',
                CHANGE_PASSWORD_FORM_OLD_PASSWORD_IS_REQUIRED: 'Old password is required',
                CHANGE_PASSWORD_FORM_OLD_PASSWORD_MAX: 'Old password max',
                CHANGE_PASSWORD_FORM_OLD_PASSWORD_MIN: 'Old password min',
                CHANGE_PASSWORD_FORM_SUBMIT: 'Submit',
                ERROR_MESSAGE: 'There was an error executing operation.',
                ERROR_TITLE: 'Error',
                NO: 'No',
                OK: 'Ok',
                RESET_PASSWORD_FORM_EMAIL: 'Email',
                RESET_PASSWORD_FORM_EMAIL_IS_REQUIRED: 'Email is required',
                RESET_PASSWORD_FORM_EMAIL_MAX: 'Email max',
                RESET_PASSWORD_FORM_EMAIL_MIN: 'Email min',
                RESET_PASSWORD_FORM_SUBMIT: 'Submit',
                SELECT_ONE: 'Select one',
                SIGN_IN_FORM_EMAIL: 'Email',
                SIGN_IN_FORM_EMAIL_IS_REQUIRED: 'Email is required',
                SIGN_IN_FORM_EMAIL_MAX: 'Email max',
                SIGN_IN_FORM_EMAIL_MIN: 'Email min',
                SIGN_IN_FORM_PASSWORD: 'Password',
                SIGN_IN_FORM_PASSWORD_IS_REQUIRED: 'Password is required',
                SIGN_IN_FORM_PASSWORD_MAX: 'Password max',
                SIGN_IN_FORM_PASSWORD_MIN: 'Password min',
                SIGN_IN_FORM_SUBMIT: 'Submit',
                SIGN_IN_PAGE_FORGOT_PASSWORD: 'Forgot password',
                SIGN_OUT_FORM_SUBMIT: 'Submit',
                SIGN_UP_FORM_EMAIL: 'Email',
                SIGN_UP_FORM_EMAIL_IS_REQUIRED: 'Email is required',
                SIGN_UP_FORM_EMAIL_MAX: 'Email max',
                SIGN_UP_FORM_EMAIL_MIN: 'Email min',
                SIGN_UP_FORM_FIRST_NAME: 'First name',
                SIGN_UP_FORM_FIRST_NAME_IS_REQUIRED: 'First name is required',
                SIGN_UP_FORM_FIRST_NAME_MAX: 'First name max',
                SIGN_UP_FORM_FIRST_NAME_MIN: 'First name min',
                SIGN_UP_FORM_LAST_NAME: 'Last name',
                SIGN_UP_FORM_LAST_NAME_IS_REQUIRED: 'Last name is required',
                SIGN_UP_FORM_LAST_NAME_MAX: 'Last name max',
                SIGN_UP_FORM_LAST_NAME_MIN: 'Last name min',
                SIGN_UP_FORM_PASSWORD: 'Password',
                SIGN_UP_FORM_PASSWORD_IS_REQUIRED: 'Password is required',
                SIGN_UP_FORM_PASSWORD_MAX: 'Password max',
                SIGN_UP_FORM_PASSWORD_MIN: 'Password min',
                SIGN_UP_FORM_SUBMIT: 'Submit',
                TODOS_DATE: 'Date',
                TODOS_ID: 'Id',
                TODOS_TASK: 'Task',
                TODOS_USER_ID: 'User id',
                UNKNOWN_ERROR: 'Unknown error',
                UPLOAD_PHOTO: 'Upload photo',
                USERS_EMAIL: 'Email',
                USERS_EMAIL_VERIFICATION_CODE: 'Email verification code',
                USERS_EMAIL_VERIFICATION_CODE_TIMESTAMP: 'Email verification code timestamp',
                USERS_EMAIL_VERIFIED: 'Email verified',
                USERS_FIRST_NAME: 'First name',
                USERS_ID: 'Id',
                USERS_LAST_NAME: 'Last name',
                USERS_PASSWORD_HASH: 'Password hash',
                USERS_ROLE: 'Role',
                VERIFY_EMAIL_FORM_EMAIL_VERIFICATION_CODE: 'Email verification code',
                VERIFY_EMAIL_FORM_EMAIL_VERIFICATION_CODE_IS_REQUIRED: 'Email verification code is required',
                VERIFY_EMAIL_FORM_EMAIL_VERIFICATION_CODE_MAX: 'Email verification code max',
                VERIFY_EMAIL_FORM_EMAIL_VERIFICATION_CODE_MIN: 'Email verification code min',
                VERIFY_EMAIL_FORM_SUBMIT: 'Submit',
                YES: 'Yes'
            });

            $translateProvider.preferredLanguage('en');

            $translateProvider.useSanitizeValueStrategy('sanitize');
        });
})();