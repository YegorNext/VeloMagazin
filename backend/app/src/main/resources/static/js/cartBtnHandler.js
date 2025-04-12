$(document).ready(function () {
	const $cartIcon = $('#cart-icon');
	
	$('.products__btn-container button').on('click', function () {
	     const $button = $(this);
	     const $span = $button.find('span');

	     const originalText = $span.text();

	     $button.addClass('btn-added');
	     $span.text('Додано!');

		 
         if (!$cartIcon.hasClass('cart-icon')) {
             $cartIcon.addClass('cart-icon');  
         }
		 
	     setTimeout(function () {
	         $button.removeClass('btn-added');
	         $span.text(originalText);
	     }, 2000);
	 });
});