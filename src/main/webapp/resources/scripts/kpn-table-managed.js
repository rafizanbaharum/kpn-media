var KpnTableManaged = function () {

    return {

        //main function to initiate the module
        init: function () {

            if (!jQuery().dataTable) {
                return;
            }

            // begin first table
            $('#kpn_table_1').dataTable({
                "aoColumns": [
                    { "bSortable": false },
                    null,
                    { "bSortable": false }
                ],
                "aLengthMenu": [
                    [5, 10, 15, 20, -1],
                    [5, 10, 15, 20, "All"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": -1,
                "sPaginationType": "bootstrap",
                "oLanguage": {
                    "sLengthMenu": "_MENU_ records",
                    "oPaginate": {
                        "sPrevious": "Prev",
                        "sNext": "Next"
                    },
                    "sEmptyTable": "No data available in table. Use the red upload button"
                },
                "aoColumnDefs": [
                    {
                        'bSortable': false,
                        'aTargets': [0]
                    }
                ]
            });

            jQuery('#kpn_table_1 .group-checkable').change(function () {
                var set = jQuery(this).attr("data-set");
                var checked = jQuery(this).is(":checked");
                jQuery(set).each(function () {
                    if (checked) {
                        $(this).attr("checked", true);
                    } else {
                        $(this).attr("checked", false);
                    }
                    $(this).parents('tr').toggleClass("active");
                });
                jQuery.uniform.update(set);

            });

            jQuery('#kpn_table_1 tbody tr .checkboxes').change(function () {
                $(this).parents('tr').toggleClass("active");
            });

            jQuery('#kpn_table_1_wrapper .dataTables_filter input').addClass("form-control input-medium"); // modify table search input
            jQuery('#kpn_table_1_wrapper .dataTables_length select').addClass("form-control input-xsmall"); // modify table per page dropdown
            //jQuery('#kpn_table_1_wrapper .dataTables_length select').select2(); // initialize select2 dropdown


            // begin second table
            $('#kpn_table_2').dataTable({
                "aoColumns": [
                    { "bSortable": false },
                    null,
                    null,
                    null,
                    { "bSortable": false }
                ],
                "aLengthMenu": [
                    [5, 10, 15, 20, -1],
                    [5, 10, 15, 20, "All"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": -1,
                "sPaginationType": "bootstrap",
                "oLanguage": {
                    "sLengthMenu": "_MENU_ records",
                    "oPaginate": {
                        "sPrevious": "Prev",
                        "sNext": "Next"
                    },
                    "sEmptyTable": "No data available in table. Use the red upload button"

                },
                "aoColumnDefs": [
                    {
                        'bSortable': false,
                        'aTargets': [0]
                    }
                ]
            });

            jQuery('#kpn_table_2 .group-checkable').change(function () {
                var set = jQuery(this).attr("data-set");
                var checked = jQuery(this).is(":checked");
                jQuery(set).each(function () {
                    if (checked) {
                        $(this).attr("checked", true);
                    } else {
                        $(this).attr("checked", false);
                    }
                    $(this).parents('tr').toggleClass("active");
                });
                jQuery.uniform.update(set);

            });

            jQuery('#kpn_table_2 tbody tr .checkboxes').change(function () {
                $(this).parents('tr').toggleClass("active");
            });

            jQuery('#kpn_table_2_wrapper .dataTables_filter input').addClass("form-control input-medium"); // modify table search input
            jQuery('#kpn_table_2_wrapper .dataTables_length select').addClass("form-control input-xsmall"); // modify table per page dropdown
            //jQuery('#kpn_table_2_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
        }

    };

}();