$(document).ready(function() {
	$('.delete-credential').click(function(event) {
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

	$('.edit-credential').click(function(event) {
		event.preventDefault();
		
		var credentialId = $(this).attr("id").split("-")[2];
		var credentialUrl = $(this).closest("tr").find("td, th").filter(":eq(1)").text();
    	var credentialUsername = $(this).closest("tr").find("td, th").filter(":eq(2)").text();
    	var credentialPassword = $(this).closest("tr").find("td, th").filter(":eq(3)").text();
		var credentialPasswordDecrypt = $(this).closest("tr").find("td, th").filter(":eq(4)").text();

		$('#credential-id').val(credentialId ? credentialId : '');
		$('#credential-url').val(credentialUrl ? credentialUrl : '');
		$('#credential-username').val(credentialUsername ? credentialUsername : '');
		$('#credential-password').val(credentialPasswordDecrypt ? credentialPasswordDecrypt : credentialPassword);
		$('#credentialModal').modal('show');
	});
	
});
