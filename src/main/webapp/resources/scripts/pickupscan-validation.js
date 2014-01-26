// for more info visit the official plugin documentation:
// http://docs.jquery.com/Plugins/Validation

var PickupScanValidation = function () {

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
                scanCode: {
                    required: true
                },
                locationId: {
                    required: true
                },
                routeId: {
                    required: true
                },
                dropboxId: {
                    required: true
                },
                pickupRequest: {
                    required: true
                },
                destinationCountry: {
                    required: true
                },
                destinationCity: {
                    required: true
                },
                destinationPostcode: {
                    required: true
                },
                originPostcode: {
                    required: true
                },
                handlingCode: {
                    required: true
                },
                routingCode: {
                    required: true
                },
                packagingType: {
                    required: true
                },
                serviceType: {
                    required: true
                },
                stopType: {
                    required: false
                },
                pickupDate: {
                    required: true
                },
                pickupTime: {
                    required: true
                },
                totalPieces: {
                    required: false,
                    number:true
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