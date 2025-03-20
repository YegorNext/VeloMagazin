$(document).ready(function() {
	
    $('.categories__search button').click(function() {
        var query = $(this).siblings('input').val();
        window.location.href = '/product/search?query=' + encodeURIComponent(query);
    });
	
	$('.categories__search input').focus(function(event) {
		$(this).keydown(function(event) {
		    if (event.which == 13) {
		        var query = $(this).val();
		        window.location.href = '/product/search?query=' + encodeURIComponent(query);
		    }
		});
	});
});