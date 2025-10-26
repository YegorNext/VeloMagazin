$(document).ready(function() {
    $('#submit-order').click(function(event) {
		event.preventDefault();
		
        var name = $('#name').val();
        var lastname = $('#lastname').val();
        var phone = $('#phone').val();

        if (name === "" || lastname === "" || phone === "") {
            alert("Будь ласка, заповніть всі поля!");
            return;
        }
		
		$('#submit-order').prop('disabled', true).text('В обробці...');

        $.ajax({
            url: '/cart/apply', 
            method: 'GET',
            data: {
                name: name,
                lastname: lastname,
                phone: phone
            },
            success: function(response) {
                alert("Заявку оформлено успішно!");
                location.reload(); 
            },
            error: function(xhr, status, error) {
                alert("Сталася помилка при оформленні заявки. Спробуйте знову.");
            }
        });
    });
});