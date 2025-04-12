$(document).ready(function () {
    $('.add-item-btn').on('click', function (e) {
        e.preventDefault();

        const itemId = $(this).attr('id');

        $.ajax({
            url: '/cart/add',
            type: 'GET',
            data: { id: itemId },
            success: function () {
				location.reload();
            },
            error: function () {
                alert('Не вдалося додати товар до кошика.');
            }
        });
    });
});