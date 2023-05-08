$(document).ready(function() {
	$('.detele-note').click(function(event) {
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

	$('.edit-note').click(function(event) {
		event.preventDefault();
		
		var noteId = $(this).attr("id").split("-")[2];
		var noteTitle = $(this).closest("tr").find("td, th").filter(":eq(1)").text();
    	var noteDescription = $(this).closest("tr").find("td, th").filter(":eq(2)").text();

		$('#note-id').val(noteId ? noteId : '');
		$('#note-title').val(noteTitle ? noteTitle : '');
		$('#note-description').val(noteDescription ? noteDescription : '');
		$('#noteModal').modal('show');
	});
	
});
