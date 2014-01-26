// for more info visit the official plugin documentation:
// http://docs.jquery.com/Plugins/Validation

var ResponsibilityValidation = function () {

    var handleAddValidation = function() {
        var form = $('#responsibilityAddForm');
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
                description: {
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
        var form = $('#form_sample_2');
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
                description: {
                    minlength: 5,
                    required: true
                }
            },

            messages: {
                name: {
                    required: "Please select a valid responsibilityname"
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

        });
    }

    return {
        init: function () {
            handleAddValidation();
            handleUpdateValidation();
        }
    };
}();