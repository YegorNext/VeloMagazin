$(document).ready(function () {
  $('.cart__order-btn').on('click', function () {
    const modal = new bootstrap.Modal(document.getElementById('orderModal'));
    modal.show();
  });
});
