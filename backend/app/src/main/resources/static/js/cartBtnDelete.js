$(document).ready(function () {
    $('.delete-item-btn').on('click', function (e) {
        e.preventDefault();

        const itemId = $(this).attr('id');

        $.ajax({
            url: '/cart/delete',
            type: 'DELETE',
            data: { id: itemId },
            success: function () {
				location.reload();
            },
            error: function () {
                alert('Не вдалося видалити товар з кошика.');
            }
        });
    });
});