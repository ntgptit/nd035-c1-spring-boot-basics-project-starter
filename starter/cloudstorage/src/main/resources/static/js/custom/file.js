$(document).ready(function() {
	$('.btn-delete-file').click(function(event) {
		event.preventDefault();
		var deleteUrl = $(this).attr('href');
		$.ajax({
			url: deleteUrl,
			type: 'POST'
			,
			success: function(result) {
				// Refresh the table
				location.reload();
			},
			error: function(xhr, status, error) {
				console.log('Error:', error);
			}
		});
	});

});