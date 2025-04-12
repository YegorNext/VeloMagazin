$(document).ready(function () {
    $('a.btn.btn-primary').on('click', function () {
        const productId = $(this).attr('id');
        $.get('/cart/add', { id: productId })
            .done(function () {
               ///
            })
            .fail(function () {
                console.error("Error while adding item to cart");
            });
    });
});