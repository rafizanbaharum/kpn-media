/**
 Custom module for you to write your own javascript functions
 **/
var Custom = function () {
    // private functions & variables

    var selectAllCheckBox = function (group) {
        $(group).change(function () {
            var set = $(this).attr("data-set");
            var checked = $(this).is(":checked");
            $(set).each(function () {
                if (checked) {
                    $(this).attr("checked", true);
                } else {
                    $(this).attr("checked", false);
                }
            });
            $.uniform.update(set);
        });
    };

    // handle start and stop scan
    var scanButton = function () {
        $('.btn-scan').bind('click', function(e) {
            e.preventDefault();
            if ($(this).is('.green')) {
                $('.btn-scan').html('Stop Scan');
                $('.btn-scan').removeClass('green');
                $('.btn-scan').addClass('red');
                $.getJSON('/scanner/start', function(data) {
                    console.log(data);
                });
            } else if ($(this).is('.red')) {
                $('.btn-scan').html('Start Scan');
                $('.btn-scan').removeClass('red');
                $('.btn-scan').addClass('green');
                $.getJSON('/scanner/stop', function(data) {
                    console.log(data);
                });
            } else {
                console.log("Unknown state");
            }
        });
    };

    var datePicker = function () {
        if (jQuery().datepicker) {
            $('.date-picker').datepicker({
                rtl: App.isRTL(),
                autoclose: true
            });
            $('body').removeClass("modal-open"); // fix bug when inline picker is used in modal
        }
    };

    var timePickers = function () {
        if (jQuery().timepicker) {
            $('.timepicker-default').timepicker({
                autoclose: true
            });
            $('.timepicker-24').timepicker({
                autoclose: true,
                minuteStep: 1,
                showSeconds: true,
                showMeridian: false
            });
        }
    };


    // public functions
    return {

        //main function
        init: function () {
            //initialize here something.            
        },

        //some helper function
        handleDatePicker: function () {
            datePicker();
        },
        handleTimePicker: function () {
            timePickers();
        },
        handleScanButton: function () {
            scanButton();
        },
        handleSelectAllCheckBox: function (group) {
            selectAllCheckBox(group);
        }
    };
}();
