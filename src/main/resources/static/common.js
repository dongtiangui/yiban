


function _confirm_common(settings, callback) {
    swal({
        text: settings.text,
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: settings.confirmButtonText,
        cancelButtonText: "取消",
        allowOutsideClick: false,
        allowEscapeKey: false,
        animation: false
    }).then(callback);
}

function _confirm_success(meg) {
    swal({
        type: 'success',
        title: meg
    });
}
function _confirm_error(meg) {
    swal({
        type: 'error',
        title: meg
    })
}