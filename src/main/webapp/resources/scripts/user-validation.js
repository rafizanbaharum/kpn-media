// for more info visit the official plugin documentation:
// http://docs.jquery.com/Plugins/Validation

var UserValidation = function () {

    var handleAddValidation = function() {
        var form = $('#userAddForm');
        var error = $('.alert-danger', form);
        var success = $('.alert-success', form);

        form.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                name: {
                    minlength: 5,
                    required: true
                },
                password: {
                    minlength: 5,
                    required: true
                },
                firstName: {
                    minlength: 5,
                    required: true
                },
                lastName: {
                    minlength: 5,
                    required: true
                },
                phone: {
                    minlength: 5,
                    required: true
                },
                email: {
                    minlength: 5,
                    required: true
                }
            },

            errorPlacement: function (error, element) {
            },

            invalidHandler: function (event, validator) {
                success.hide();
                error.show();
                App.scrollTo(error, -200);
            },

            highlight: function (element) {
                $(element)
                    .closest('.form-group').addClass('has-error');
            },

            unhighlight: function (element) {
                $(element)
                    .closest('.form-group').removeClass('has-error');
            },

            success: function (label) {
                label
                    .closest('.form-group').removeClass('has-error');
            },

            submitHandler: function (form) {
                success.show();
                error.hide();
            }
        });
    }

    var handleUpdateValidation = function() {
        var form = $('#userUpdateForm');
        var error = $('.alert-error', form);
        var success = $('.alert-success', form);

        form.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                name: {
                    minlength: 5,
                    required: true
                },
                password: {
                    minlength: 5,
                    required: true
                },
                firstName: {
                    minlength: 5,
                    required: true
                },
                lastName: {
                    minlength: 5,
                    required: true
                },
                phone: {
                    minlength: 5,
                    required: true
                },
                email: {
                    minlength: 5,
                    required: true
                }
            },

            messages: {
                name: {
                    required: "Please select a valid username"
                }
            },

            errorPlacement: function (error, element) {
            },

            invalidHandler: function (event, validator) {
                success.hide();
                error.show();
                App.scrollTo(error, -200);
            },

            highlight: function (element) {
                $(element)
                    .closest('.form-group').addClass('has-error');
            },

            unhighlight: function (element) {
                $(element)
                    .closest('.form-group').removeClass('has-error');
            },

            success: function (label) {
                label
                    .closest('.form-group').removeClass('has-error');
            },

            submitHandler: function (form) {
                success.show();
                error.hide();
            }

        });
    }

    return {
        init: function () {
            handleAddValidation();
            handleUpdateValidation();
        }
    };
}();