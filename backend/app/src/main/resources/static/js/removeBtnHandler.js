$(document).ready(function () {
    $('.delete-all-btn').on('click', function (e) {
        e.preventDefault();

        const itemId = $(this).attr('id');

        $.ajax({
            url: '/cart/deleteAll',
            type: 'DELETE',
            data: { id: itemId },
            success: function () {
				location.reload();
            },
            error: function () {
                alert('Помилка при видаленні товару з корзини.');
            }
        });
    });
});